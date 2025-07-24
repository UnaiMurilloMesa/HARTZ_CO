package org.hartz.hartz_backend.model.dto;

import lombok.Data;
import org.hartz.hartz_backend.model.Workout;

import java.time.ZoneId;
import java.util.List;

@Data
public class WorkoutDTO {

    private String workoutName;
    private String description;
    private String username;
    private List<ExerciseDTO> exercises;
    private Long dateSeconds;

    public WorkoutDTO(String workoutName, String description, String username, List<ExerciseDTO> exercises, Long dateSeconds) {
        this.workoutName = workoutName;
        this.description = description;
        this.username = username;
        this.exercises = exercises;
        this.dateSeconds = dateSeconds;
    }
}
