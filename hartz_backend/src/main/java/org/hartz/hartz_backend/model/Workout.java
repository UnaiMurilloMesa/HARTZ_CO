package org.hartz.hartz_backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
    private LocalDateTime date;
    private boolean isRoutine;
    private List<ExerciseSet> exerciseSets;

    public Workout(String username, String name, String description, LocalDateTime date, boolean isRoutine, List<ExerciseSet> exerciseSets) {
        this.username = username;
        this.name = name;
        this.description = description;
        this.date = date;
        this.isRoutine = isRoutine;
        this.exerciseSets = exerciseSets;
    }
}
