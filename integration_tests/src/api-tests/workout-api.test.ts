import { cleanMongo } from "./utils/db-cleaner";
import { mongoClient } from "./utils/db-clients";

beforeAll(async () => {
  await mongoClient.connect();
});

afterAll(async () => {
  await mongoClient.close();
});

beforeEach(async () => {
    await cleanMongo();
});