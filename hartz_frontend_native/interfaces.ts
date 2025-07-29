export interface RegisterRequest {
    email: string;
    password: string;
    username: string;
    mascot?: string;
}

export interface LoginRequest {
    email: string;
    password: string;
}
