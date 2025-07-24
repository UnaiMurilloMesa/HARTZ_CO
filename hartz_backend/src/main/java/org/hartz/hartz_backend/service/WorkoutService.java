package org.hartz.hartz_backend.service;

import org.hartz.hartz_backend.model.Exercise;
import org.hartz.hartz_backend.model.Workout;
import org.hartz.hartz_backend.model.dto.ExerciseDTO;
import org.hartz.hartz_backend.model.dto.WorkoutDTO;
import org.hartz.hartz_backend.persistence.mongo.WorkoutRepositoryAdapter;
import org.hartz.hartz_backend.persistence.postgre.ExerciseRepositoryAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.List;

@Service
public class WorkoutService {

    private final WorkoutRepositoryAdapter workoutRepository;
    private final ExerciseRepositoryAdapter exerciseRepository;
    private final ExerciseService exerciseService;

    @Autowired
    public WorkoutService(WorkoutRepositoryAdapter workoutRepository, ExerciseRepositoryAdapter exerciseRepository, ExerciseService exerciseService) {
        this.workoutRepository = workoutRepository;
        this.exerciseRepository = exerciseRepository;
        this.exerciseService = exerciseService;
    }

    public WorkoutDTO toDTO(Workout workout) {

        List<String> exerciseNames = workout.getExerciseNames();
        List<Exercise> exercises = exerciseRepository.findAllByNames(exerciseNames);
        List<ExerciseDTO> exerciseDTOs = exercises.stream()
                .map(exerciseService::toDTO)
                .toList();

        return new WorkoutDTO(
                workout.getName(),
                workout.getDescription(),
                workout.getUsername(),
                exerciseDTOs,
                workout.getDate().atZone(ZoneId.of("UTC")).toInstant().toEpochMilli() / 1000
        );
    }
}
