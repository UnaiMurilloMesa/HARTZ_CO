// >>> User
type Mascot = 'anteater' | 'black' | 'grizzly' | 'panda' | 'polar' | 'tanuki';

export interface RegisterRequest {
    email: string;
    password: string;
    username: string;
    mascot?: Mascot;
}

export interface LoginRequest {
    email: string;
    password: string;
}

export interface UpdateBiographyRequest {
    biography: string
}

export interface UpdateHeightRequest {
    height: number
}

export interface UpdateWeightRequest {
    weight: number
}

export interface UpdateMascotRequest {
    mascot: Mascot
}

export interface User {
    username: string,
    mascot: Mascot
}

export interface PrivateProfile {
    username: string,
    email: string,
    mascot: Mascot,
    biography: string,
    height: number,
    weight: number,
    birthDate: Date
}

export interface AuthResponse {
    token: string
}

// >>> Workout
export interface PostGymSetRequest {
    reps?: number,
    timeInSeconds?: number,
    weight?: number,
    restInSeconds?: number
}

export interface PostExerciseSetRequest {
    exerciseName: string,
    sets: PostGymSetRequest[],
    notes?: string
}

export interface PostWorkoutRequest {
    name?: string,
    description?: string,
    isRoutine: boolean,
    exerciseSets: PostExerciseSetRequest[],
    startDate?: Date, // for workouts (isRoutine = false)
    endDate?: Date, // for workouts (isRoutine = false)
}

export interface Workout {
    workoutName?: string,
    description?: string,
    username?: string,
    exerciseSets: ExerciseSet[],
    createdDate?: Date, // For routines
    startDate?: Date, // For workouts
    endDate?: Date, // For workouts
}

// >>> Exercise
export interface Exercise {
    exerciseName: string,
    difficultyLevel: string,
    muscleGroups: string[],
    equipment?: string,
    unilateral: boolean,
    gripType?: string,
    bodyRegion: string
}

export interface GymSet {
    reps?: number,
    timeInSeconds?: number,
    weight?: number,
    restSeconds?: number
}

export interface ExerciseSet {
    exercise: Exercise,
    gymSet: GymSet,
    notes?: string
}