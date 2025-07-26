const { createDefaultPreset } = require("ts-jest");

/** @type {import('jest').Config} */
module.exports = {
  testEnvironment: "node",
  transform: {
    '^.+\\.ts$': 'ts-jest',
  },
  testMatch: [
    "**/src/api-tests/**/*.test.ts"
  ],
};
