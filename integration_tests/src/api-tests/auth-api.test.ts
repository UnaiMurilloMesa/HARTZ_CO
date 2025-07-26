import { pgPool } from './utils/db-clients';
import { api, registerUser } from './utils/api-utils';
import { cleanPostgres } from './utils/db-cleaner';

describe('AuthController Integration Tests', () => {
  beforeEach(async () => {
    await cleanPostgres();
  });

  afterAll(async () => {
	await pgPool.end();
  });

  describe('POST /api/auth/register', () => {
    it('should register a user successfully', async () => {
      const res = await api()
        .post('/api/auth/register')
        .send({
          username: 'testuser',
          email: 'test@example.com',
          password: '12345678',
          mascot: 'bear',
        });

      expect(res.status).toBe(200);
      expect(res.body).toHaveProperty('token');
    });

    it('should fail if username is taken', async () => {
      await registerUser({ username: 'takenuser' });

      const res = await registerUser({ username: 'takenuser', email: 'new@email.com' });
      expect(res).toHaveProperty('status', 400);
      expect(res.text).toContain('Username taken');
    });

    it('should fail if email is taken', async () => {
      await registerUser({ email: 'taken@example.com' });

      const res = await registerUser({ username: 'newuser', email: 'taken@example.com' });
      expect(res).toHaveProperty('status', 400);
      expect(res.text).toContain('Email taken');
    });

    it('should fail if password is too short', async () => {
      const res = await registerUser({ password: '123' });

      expect(res.status).toBe(400);
      expect(res.text).toContain('Password shorter than 6 characters');
    });

    it('should fail if email format is incorrect', async () => {
      const res = await registerUser({ email: 'invalid-email' });

      expect(res.status).toBe(400);
      expect(res.text).toContain('Email format exception');
    });
  });

  describe('POST /api/auth/login', () => {
    it('should login successfully after registration', async () => {
      const user = {
        username: 'loginuser',
        email: 'login@example.com',
        password: 'mypassword',
        mascot: 'lion',
      };
      await registerUser(user);

      const res = await api()
        .post('/api/auth/login')
        .send({
          email: user.email,
          password: user.password,
        });

      expect(res.status).toBe(200);
      expect(res.body).toHaveProperty('token');
    });

    it('should fail if email not found', async () => {
      const res = await api()
        .post('/api/auth/login')
        .send({
          email: 'notfound@example.com',
          password: 'irrelevant',
        });

      expect(res.status).toBe(400);
      expect(res.text).toContain('Email not registered');
    });

    it('should fail if password is incorrect', async () => {
      const user = {
        username: 'wrongpass',
        email: 'wrongpass@example.com',
        password: 'correctpass',
      };
      await registerUser(user);

      const res = await api()
        .post('/api/auth/login')
        .send({
          email: user.email,
          password: 'wrongpassword',
        });

      expect(res.status).toBe(400);
      expect(res.text).toContain('Password does not match with email');
    });
  });
});
