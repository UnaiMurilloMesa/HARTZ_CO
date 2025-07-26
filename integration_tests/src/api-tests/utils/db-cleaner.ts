import { pgPool, mongoClient } from './db-clients';

export async function cleanPostgres() {
  const client = await pgPool.connect();
  try {
    await client.query('BEGIN');

    await client.query('TRUNCATE TABLE customer');

    await client.query('COMMIT');
  } catch (err) {
    await client.query('ROLLBACK');
    throw err;
  } finally {
    client.release();
  }
}

export async function cleanMongo() {
  const db = mongoClient.db(process.env.MONGO_DB || 'hartz_mongo_db');

  await db.collection('workouts').deleteMany({});
}
