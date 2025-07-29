import { pgPool } from './utils/db-clients';
import { registerUser, api } from './utils/api-utils';
import { RegisterParams } from './interfaces';

describe('UserController Integration Tests', () => {

  afterAll(async () => {
    await pgPool.end();
  });

  let authToken: string;
  let registerDate: Date;

  const testUser: RegisterParams = {
    username: 'integrationTestUser',
    email: 'integration@test.com',
    password: 'TestPass123',
    mascot: 'polarBear',
  };

  const otherUser: RegisterParams = {
    username: 'otherTestUser',
    email: 'other-integration@test.com',
    password: 'TestPass123',
    mascot: 'polarBear',
  };

  beforeAll(async () => {
    authToken = (await registerUser(testUser)).body.token;
    await registerUser(otherUser);
    registerDate = new Date();
  });

  describe('GET /api/user/username/:username', () => {
    it('should return 403 if there is not token', async () => {
      const res = await api()
        .get('/api/user/username/whatever');
      expect(res.status).toBe(403);
    });

    it('should return 404 if user does not exist', async () => {
      const res = await api()
        .get('/api/user/username/nonexistentuser')
        .set('Authorization', `Bearer ${authToken}`);
      expect(res.status).toBe(404);
    });

    it('should return user info for me', async () => {
      const res = await api()
        .get(`/api/user/username/${testUser.username}`)
        .set('Authorization', `Bearer ${authToken}`);
      expect(res.status).toBe(200);
      expect(res.body.username).toBe(testUser.username);
      expect(res.body.mascot).toBe(testUser.mascot);
      expect(res.body).not.toHaveProperty('email');
      expect(res.body).not.toHaveProperty('password');
    });

    it('should return user info for other user', async () => {
      const res = await api()
        .get(`/api/user/username/${otherUser.username}`)
        .set('Authorization', `Bearer ${authToken}`);
      expect(res.status).toBe(200);
      expect(res.body.username).toBe(otherUser.username);
      expect(res.body.mascot).toBe(otherUser.mascot);
      expect(res.body).not.toHaveProperty('email');
      expect(res.body).not.toHaveProperty('password');
    });
  });

  describe('GET /api/user/profile', () => {
    it('should return personal info of authenticated user', async () => {

      await api()
        .put('/api/user/weight')
        .set('Authorization', `Bearer ${authToken}`)
        .send({ weight: 70 });

      await api()
        .put('/api/user/height')
        .set('Authorization', `Bearer ${authToken}`)
        .send({ height: 180 });

      const res = await api()
        .get('/api/user/profile')
        .set('Authorization', `Bearer ${authToken}`);

      expect(res.status).toBe(200);
      expect(res.body).toHaveProperty('email', testUser.email);
      expect(res.body).toHaveProperty('username', testUser.username);
      expect(res.body).toHaveProperty('mascot', testUser.mascot);
      expect(res.body).toHaveProperty('height');
      expect(res.body.height).toBe(180);
      expect(res.body).toHaveProperty('weight');
      expect(res.body.weight).toBe(70);
      expect(res.body).toHaveProperty('createdAt');
      const createdAtDate = new Date(res.body.createdAt);
      expect(createdAtDate.toString()).not.toBe('Invalid Date');
      const diffSeconds = Math.abs((createdAtDate.getTime() - registerDate.getTime()) / 1000);
      expect(diffSeconds).toBeLessThanOrEqual(60); // Allow 1 minute difference
    });

    it('should return 403 if not authenticated', async () => {
      const res = await api().get('/api/user/profile');
      expect(res.status).toBe(403);
    });
  });

  describe('PUT /api/user/height', () => {
    it('should be authenticated to update height', async () => {
      const res = await api()
        .put('/api/user/height')
        .send({ height: 180 });

      expect(res.status).toBe(403);
    });

    it('should update height successfully', async () => {
      const res = await api()
        .put('/api/user/height')
        .set('Authorization', `Bearer ${authToken}`)
        .send({ height: 180 });

      expect(res.status).toBe(200);
    });

    it('should return 400 for invalid height', async () => {
      const res = await api()
        .put('/api/user/height')
        .set('Authorization', `Bearer ${authToken}`)
        .send({ height: -10 });

      expect(res.status).toBe(400);
      expect(res.text).toContain('Invalid height data');
    });
  });

  describe('PUT /api/user/weight', () => {
    it('should be authenticated to update weight', async () => {
      const res = await api()
        .put('/api/user/weight')
        .send({ weight: 70 });

      expect(res.status).toBe(403);
    });

    it('should update weight successfully', async () => {
      const res = await api()
        .put('/api/user/weight')
        .set('Authorization', `Bearer ${authToken}`)
        .send({ weight: 70 });

      expect(res.status).toBe(200);
    });

    it('should return 400 for invalid weight', async () => {
      const res = await api()
        .put('/api/user/weight')
        .set('Authorization', `Bearer ${authToken}`)
        .send({ weight: -5 });

      expect(res.status).toBe(400);
      expect(res.text).toContain('Invalid weight data');
    });
  });

  describe('PUT /api/user/mascot', () => {
    it('should be authenticated to update mascot', async () => {
      const res = await api()
        .put('/api/user/mascot')
        .send({ mascot: 'polarBear' });

      expect(res.status).toBe(403);
    });

    it('should update mascot successfully', async () => {
      const res = await api()
        .put('/api/user/mascot')
        .set('Authorization', `Bearer ${authToken}`)
        .send({ mascot: 'polarBear' });

      expect(res.status).toBe(200);
    });

    it('should return 400 for null mascot input', async () => {
      const res = await api()
        .put('/api/user/mascot')
        .set('Authorization', `Bearer ${authToken}`)
        .send({ mascot: null });

      expect(res.status).toBe(400);
    });
  });

  describe('PUT /api/user/biography', () => {
    it('should be authenticated to update bio', async () => {
      const res = await api()
        .put('/api/user/biography')
        .send({ biography: "asdfasdfasdf" });

      expect(res.status).toBe(403);
    });

    it('should update bio successfully', async () => {
      const res = await api()
        .put('/api/user/biography')
        .set('Authorization', `Bearer ${authToken}`)
        .send({ biography: "asdfasdfasdf" });

      expect(res.status).toBe(200);
    });

    it('should return 400 for invalid bio', async () => {
      const res = await api()
        .put('/api/user/biography')
        .set('Authorization', `Bearer ${authToken}`)
        .send({ biography: null });

      expect(res.status).toBe(400);
    });

    it('should return 400 for bio not found', async () => {
      const res = await api()
        .put('/api/user/biography')
        .set('Authorization', `Bearer ${authToken}`)

      expect(res.status).toBe(400);
    });
  });
});