CREATE TABLE IF NOT EXISTS plans (
                                     type VARCHAR(20) PRIMARY KEY,
    max_workouts INT,
    max_groups INT
    );

INSERT INTO plans (type, max_workouts, max_groups)
VALUES
    ('basic', 4, 2),
    ('premium', 10, 5)
    ON CONFLICT (type) DO NOTHING;
