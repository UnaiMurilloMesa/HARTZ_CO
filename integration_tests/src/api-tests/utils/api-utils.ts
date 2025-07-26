import request from 'supertest';
import type { RegisterParams } from '../interfaces';

export const getBaseUrl = () => {
  return process.env.API_BASE_URL || 'http://localhost:8080';
};

export const api = () => request(getBaseUrl());

export async function registerUser(
  params: RegisterParams = {}
): Promise<request.Response> {
  const defaultPayload: Required<RegisterParams> = {
    username: 'defaultUser',
    email: 'default@example.com',
    password: 'defaultPass123',
    mascot: 'polarBear',
  };

  const combined = { ...defaultPayload, ...params };

  const payload: Record<string, unknown> = {};
  for (const [key, value] of Object.entries(combined)) {
    if (value !== null && value !== undefined) {
      payload[key] = value;
    }
  }

  const response = await api()
    .post('/api/auth/register')
    .send(payload);

  return response;
};
