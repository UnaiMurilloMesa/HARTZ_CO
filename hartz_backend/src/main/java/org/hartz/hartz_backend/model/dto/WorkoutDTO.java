package org.hartz.hartz_backend.model.dto;

import org.hartz.hartz_backend.model.User;

import java.time.LocalDateTime;
import java.util.List;

public class WorkoutDTO {

    private String workoutName;
    private String description;
    private User user;
    private List<ExerciseDTO> exercises;
    private LocalDateTime date;

    public WorkoutDTO(String workoutName, String description, User user, List<ExerciseDTO> exercises, LocalDateTime date) {
        this.workoutName = workoutName;
        this.description = description;
        this.user = user;
        this.exercises = exercises;
        this.date = date;
    }

    public static WorkoutDTO toDTO(org.hartz.hartz_backend.model.Workout workout) {
        List<ExerciseDTO> exerciseDTOs = workout.getExercises().stream()
                .map(ExerciseDTO::toDTO)
                .toList();
        return new WorkoutDTO(workout.getName(), workout.getDescription(), workout.getUser(), exerciseDTOs, workout.getDate());
    }

}
