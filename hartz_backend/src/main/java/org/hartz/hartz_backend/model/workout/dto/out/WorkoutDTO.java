package org.hartz.hartz_backend.model.workout.dto.out;

import lombok.Data;
import org.hartz.hartz_backend.model.exercise.dto.out.ExerciseSetDTO;

import java.util.List;

@Data
public class WorkoutDTO {

    private String workoutName;
    private String description;
    private String username;
    private List<ExerciseSetDTO> exerciseSets;
    private Long dateSeconds;

    public WorkoutDTO(String workoutName, String description, String username, List<ExerciseSetDTO> exerciseSets, Long dateSeconds) {
        this.workoutName = workoutName;
        this.description = description;
        this.username = username;
        this.exerciseSets = exerciseSets;
        this.dateSeconds = dateSeconds;
    }
}
