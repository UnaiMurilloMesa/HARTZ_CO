package org.hartz.hartz_backend.service;

import org.hartz.hartz_backend.model.exercise.ExerciseSet;
import org.hartz.hartz_backend.model.exercise.dto.out.ExerciseSetDTO;
import org.hartz.hartz_backend.model.workout.Workout;
import org.hartz.hartz_backend.model.workout.dto.out.WorkoutDTO;
import org.hartz.hartz_backend.persistence.mongo.WorkoutRepositoryAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutService {
    private final WorkoutRepositoryAdapter workoutRepository;
    private final ExerciseService exerciseService;

    @Autowired
    public WorkoutService(WorkoutRepositoryAdapter workoutRepository, ExerciseService exerciseService) {
        this.workoutRepository = workoutRepository;
        this.exerciseService = exerciseService;
    }

    public List<Workout> getWorkoutsByUserAndRoutine(String username, boolean isRoutine) {
        return workoutRepository
                .findByUsername(username)
                .stream()
                .filter(w -> w.isRoutine() == isRoutine)
                .toList();
    }

    public Workout save(Workout workout) {
        return workoutRepository.save(workout);
    }

    public WorkoutDTO toDTO(Workout workout) {

        List<ExerciseSet> exerciseSets = workout.getExerciseSets();
        List<ExerciseSetDTO> exerciseSetDTOs = exerciseSets.stream()
                .map(exerciseService::toDTO)
                .toList();

        return new WorkoutDTO(
                workout.getName(),
                workout.getDescription(),
                workout.getUsername(),
                exerciseSetDTOs,
                workout.getCreatedDate(),
                workout.getStartDate(),
                workout.getEndDate()
        );
    }
}
