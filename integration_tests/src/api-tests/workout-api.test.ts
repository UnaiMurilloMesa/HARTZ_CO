import { cleanMongo } from "./utils/db-cleaner";
import { mongoClient } from "./utils/db-clients";
import { api, registerUser } from './utils/api-utils';
import type { RegisterParams } from './interfaces';

let authToken: string;

const testUser: RegisterParams = {
  username: 'workoutUser',
  email: 'workout@test.com',
  password: 'TestPass123',
  mascot: 'polarBear',
};

const WorkoutLimits = {
  MAX_NAME_LENGTH: 70,
  MAX_DESCRIPTION_LENGTH: 200,
  MAX_EXERCISES: 30,
  MAX_SETS_PER_EXERCISE: 20,
};

beforeAll(async () => {
  await mongoClient.connect();
  const res = await registerUser(testUser);
  authToken = res.body.token;
});

afterAll(async () => {
  await mongoClient.close();
});

beforeEach(async () => {
  await cleanMongo();
});

describe('WorkoutController Integration Tests', () => {
  const validWorkout = {
    name: 'Morning Routine',
    description: 'Warm-up and stretch',
    isRoutine: true,
    exerciseSets: [
      {
        exerciseName: 'Face pull',
        notes: 'Try full range',
        sets: [
          {
            reps: 10,
            weight: 50,
            restSeconds: 60,
          },
        ],
      },
      {
        exerciseName: 'Pull-Ups',
        notes: 'Try full range',
        sets: [
          {
            reps: 10,
          },
        ],
      },
      {
        exerciseName: 'Air bike',
        sets: [
          {
            restSeconds: 60,
            timeInSeconds: 180
          },
        ],
      }
    ],
  };

  it('should create workout successfully', async () => {
    const res = await api()
      .post('/api/workout')
      .set('Authorization', `Bearer ${authToken}`)
      .send(validWorkout);

    const date = new Date();

    expect(res.status).toBe(200);
    expect(res.body.workoutName).toBe(validWorkout.name);
    expect(res.body.username).toBe(testUser.username);

    const dateSeconds = new Date(res.body.dateSeconds * 1000);
    expect(dateSeconds.toString()).not.toBe('Invalid Date');
    const diffSeconds = Math.abs((dateSeconds.getTime() - date.getTime()) / 1000);
    expect(diffSeconds).toBeLessThanOrEqual(60); // Allow 1 minute difference
  });

  it('should fail if isRoutine is true and name is missing', async () => {
    const { name, ...noName } = validWorkout;
    const res = await api()
      .post('/api/workout')
      .set('Authorization', `Bearer ${authToken}`)
      .send({ ...noName, isRoutine: true });

    expect(res.status).toBe(400);
    expect(res.text).toContain("Workout name can't be null");
  });

  it('should fail if name exceeds max length', async () => {
    const longName = 'x'.repeat(WorkoutLimits.MAX_NAME_LENGTH + 1);
    const res = await api()
      .post('/api/workout')
      .set('Authorization', `Bearer ${authToken}`)
      .send({ ...validWorkout, name: longName });

    expect(res.status).toBe(400);
    expect(res.text).toContain(`Workout name can't be over`);
  });

  it('should fail if description exceeds max length', async () => {
    const longDesc = 'x'.repeat(WorkoutLimits.MAX_DESCRIPTION_LENGTH + 1);
    const res = await api()
      .post('/api/workout')
      .set('Authorization', `Bearer ${authToken}`)
      .send({ ...validWorkout, description: longDesc });

    expect(res.status).toBe(400);
    expect(res.text).toContain('Workout description can\'t be over');
  });

  it('should fail if exerciseSets is empty', async () => {
    const res = await api()
      .post('/api/workout')
      .set('Authorization', `Bearer ${authToken}`)
      .send({ ...validWorkout, exerciseSets: [] });

    expect(res.status).toBe(400);
    expect(res.text).toContain('A workout must have at least one exercise');
  });

  it('should fail if exerciseSets exceeds limit', async () => {
    const exercises = Array.from({ length: WorkoutLimits.MAX_EXERCISES + 1 }, (_, i) => ({
      exerciseName: `Exercise ${i}`,
      notes: '',
      sets: [{ reps: 5, timeInSeconds: 0, weight: 0, restSeconds: 30 }],
    }));

    const res = await api()
      .post('/api/workout')
      .set('Authorization', `Bearer ${authToken}`)
      .send({ ...validWorkout, exerciseSets: exercises });

    expect(res.status).toBe(400);
    expect(res.text).toContain('You can\'t create more than');
  });

  it('should fail if any exercise has no sets', async () => {
    const res = await api()
      .post('/api/workout')
      .set('Authorization', `Bearer ${authToken}`)
      .send({
        ...validWorkout,
        exerciseSets: [
          {
            exerciseName: 'Empty Exercise',
            notes: '',
            sets: [],
          },
        ],
      });

    expect(res.status).toBe(400);
    expect(res.text).toContain('An exercise must have at least one set');
  });

  it('should fail if any exercise has too many sets', async () => {
    const tooManySets = Array.from({ length: WorkoutLimits.MAX_SETS_PER_EXERCISE + 1 }, () => ({
      reps: 8,
      timeInSeconds: 0,
      weight: 20,
      restSeconds: 60,
    }));

    const res = await api()
      .post('/api/workout')
      .set('Authorization', `Bearer ${authToken}`)
      .send({
        ...validWorkout,
        exerciseSets: [
          {
            exerciseName: 'Heavy Bench',
            notes: '',
            sets: tooManySets,
          },
        ],
      });

    expect(res.status).toBe(400);
    expect(res.text).toContain("An exercise can't hace more than");
  });

  it('should fail if isRoutine is missing (violates @NotNull)', async () => {
    const { isRoutine, ...rest } = validWorkout;
    const res = await api()
      .post('/api/workout')
      .set('Authorization', `Bearer ${authToken}`)
      .send(rest);

    expect(res.status).toBe(400);
  });

  it('should fail if exerciseSets is missing (violates @NotNull)', async () => {
    const { exerciseSets, ...rest } = validWorkout;
    const res = await api()
      .post('/api/workout')
      .set('Authorization', `Bearer ${authToken}`)
      .send(rest);

    expect(res.status).toBe(400);
  });

  it('should fail if cardio exercise set is missing timeInSeconds', async () => {
    const invalidWorkout = {
      ...validWorkout,
      exerciseSets: validWorkout.exerciseSets.map(es => {
        if (es.exerciseName === 'Air bike') {
          return {
            ...es,
            sets: es.sets.map(set => {
              if ('timeInSeconds' in set) {
                const { timeInSeconds, ...rest } = set;
                return { ...rest, reps: 10 };
              }
              return set;
            }),
          };
        }
        return es;
      }),
    };

    const res = await api()
      .post('/api/workout')
      .set('Authorization', `Bearer ${authToken}`)
      .send(invalidWorkout);

    expect(res.status).toBe(400);
    expect(res.text).toContain('timeInSeconds is required for cardio exercises');
  });

  it('should fail if strength exercise set is missing reps', async () => {
    const invalidWorkout = {
      ...validWorkout,
      exerciseSets: validWorkout.exerciseSets.map(es => {
        if (es.exerciseName === 'Face pull') {
          return {
            ...es,
            sets: es.sets.map(set => {
              if ('reps' in set) {
                const { reps, ...rest } = set;
                return { ...rest };
              }
            }),
          };
        }
        return es;
      }),
    };

    const res = await api()
      .post('/api/workout')
      .set('Authorization', `Bearer ${authToken}`)
      .send(invalidWorkout);

    expect(res.status).toBe(400);
    expect(res.text).toContain('reps is required for strength exercises');
  });

  it('should fail if strength exercise set is missing weight', async () => {
    const invalidWorkout = {
      ...validWorkout,
      exerciseSets: validWorkout.exerciseSets.map(es => {
        if (es.exerciseName === 'Face pull') {
          return {
            ...es,
            sets: es.sets.map(set => {
              if ('weight' in set) {
                const { weight, ...rest } = set; // eliminar weight
                return { ...rest }; // weight falta, debería fallar
              }
            }),
          };
        }
        return es;
      }),
    };

    const res = await api()
      .post('/api/workout')
      .set('Authorization', `Bearer ${authToken}`)
      .send(invalidWorkout);

    expect(res.status).toBe(400);
    expect(res.text).toContain('weight is required for strength exercises');
  });

  it('should fail if bodyweight exercise set is missing reps', async () => {
    const invalidWorkout = {
      ...validWorkout,
      exerciseSets: validWorkout.exerciseSets.map(es => {
        if (es.exerciseName === 'Pull-Ups') {
          return {
            ...es,
            sets: es.sets.map(set => {
              if ('reps' in set) {
                const { reps, ...rest } = set;
                return { ...rest };
              }
            }),
          };
        }
        return es;
      }),
    };

    const res = await api()
      .post('/api/workout')
      .set('Authorization', `Bearer ${authToken}`)
      .send(invalidWorkout);

    expect(res.status).toBe(400);
    expect(res.text).toContain('reps is required for bodyweight exercises');
  });
});