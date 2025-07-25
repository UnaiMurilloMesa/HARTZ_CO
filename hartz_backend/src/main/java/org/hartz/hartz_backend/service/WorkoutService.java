package org.hartz.hartz_backend.service;

import org.hartz.hartz_backend.model.Exercise;
import org.hartz.hartz_backend.model.ExerciseSet;
import org.hartz.hartz_backend.model.GymSet;
import org.hartz.hartz_backend.model.Workout;
import org.hartz.hartz_backend.model.dto.ExerciseSetDTO;
import org.hartz.hartz_backend.model.dto.GymSetDTO;
import org.hartz.hartz_backend.model.dto.WorkoutDTO;
import org.hartz.hartz_backend.persistence.mongo.WorkoutRepositoryAdapter;
import org.hartz.hartz_backend.persistence.postgre.ExerciseRepositoryAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

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
                workout.getDate().atZone(ZoneId.of("UTC")).toInstant().toEpochMilli() / 1000
        );
    }
}
