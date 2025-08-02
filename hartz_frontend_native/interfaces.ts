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

type Mascot = 'anteater' | 'black' | 'grizzly' | 'panda' | 'polar' | 'tanuki';
export interface PrivateProfile {
    username: string,
    email: string,
    mascot: Mascot,
    biography: string,
    height: number,
    weight: number,
    birthDate: Date
}
