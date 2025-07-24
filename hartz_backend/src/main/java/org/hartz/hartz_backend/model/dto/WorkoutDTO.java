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

    private WorkoutDTO(String workoutName, String description, String username, List<ExerciseDTO> exercises, Long dateSeconds) {
        this.workoutName = workoutName;
        this.description = description;
        this.username = username;
        this.exercises = exercises;
        this.dateSeconds = dateSeconds;
    }

    public static WorkoutDTO toDTO(Workout workout) {
        List<ExerciseDTO> exerciseDTOs = workout.getExercises().stream()
                .map(ExerciseDTO::toDTO)
                .toList();
        return new WorkoutDTO(
                workout.getName(),
                workout.getDescription(),
                workout.getUser().getUsername(),
                exerciseDTOs,
                workout.getDate().atZone(ZoneId.of("UTC")).toInstant().toEpochMilli()/1000
        );
    }

}
