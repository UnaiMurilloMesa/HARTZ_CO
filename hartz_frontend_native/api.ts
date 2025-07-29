import axios, { type AxiosResponse } from 'axios';
import type { LoginRequest, RegisterRequest } from './interfaces';

const API_BASE_URL = 'http://localhost:8080/api';

export const registerUser = async (
    registerRequest: RegisterRequest
): Promise<AxiosResponse<{ token: string}>> => {
    return axios.post(`${API_BASE_URL}/auth/register`, registerRequest);
}

export const logInUser = async (
    credentials: LoginRequest
): Promise<AxiosResponse<{ token: string }>> => {
    return axios.post(`${API_BASE_URL}/auth/login`, credentials);
}