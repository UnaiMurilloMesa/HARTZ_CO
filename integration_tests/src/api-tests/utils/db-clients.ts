import { Pool } from 'pg';
import { MongoClient } from 'mongodb';

export const pgPool = new Pool({
  host: process.env.PG_HOST || 'localhost',
  port: parseInt(process.env.PG_PORT || '5432', 10),
  user: process.env.PG_USER || 'hartz_user',
  password: process.env.PG_PASSWORD || 'hartz_password',
  database: process.env.PG_DB || 'hartz_db',
});

export const mongoClient = new MongoClient(process.env.MONGO_URI || 'mongodb://localhost:27017');
