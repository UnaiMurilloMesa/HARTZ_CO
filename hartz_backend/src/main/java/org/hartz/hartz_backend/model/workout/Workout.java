package org.hartz.hartz_backend.model.workout;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hartz.hartz_backend.model.exercise.ExerciseSet;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "workouts")
public class Workout {

    public static final int MAX_NAME_LENGTH = 70;
    public static final int MAX_DESCRIPTION_LENGTH = 200;
    public static final int MAX_EXERCISES = 30;
    public static final int MAX_SETS_PER_EXERCISE = 20;

    @Id
    private String id;
    private String username;
    private String name;
    private String description;
    private Instant startDate; // For workouts (routine = false)
    private Instant endDate; // For workouts (routine = false)
    private Instant createdDate; // For routines (routine = true)
    private boolean isRoutine;
    private List<ExerciseSet> exerciseSets;

    // For workouts
    public Workout(String username, String name, String description, Instant startDate, Instant endDate,
                   List<ExerciseSet> exerciseSets) {
        this.username = username;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isRoutine = false;
        this.exerciseSets = exerciseSets;
    }

    // For routines
    public Workout(String username, String name, String description, Instant createdDate,
                   List<ExerciseSet> exerciseSets) {
        this.username = username;
        this.name = name;
        this.description = description;
        this.createdDate = createdDate;
        this.isRoutine = true;
        this.exerciseSets = exerciseSets;
    }
}
