import { pgPool } from './utils/db-clients';

afterAll(async () => {
  await pgPool.end();
});