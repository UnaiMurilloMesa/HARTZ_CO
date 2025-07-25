package org.hartz.hartz_backend.service;

import org.hartz.hartz_backend.model.ExerciseSet;
import org.hartz.hartz_backend.model.GymSet;
import org.hartz.hartz_backend.model.Workout;
import org.hartz.hartz_backend.model.dto.ExerciseSetDTO;
import org.hartz.hartz_backend.model.dto.GymSetDTO;
import org.hartz.hartz_backend.model.dto.WorkoutDTO;
import org.hartz.hartz_backend.persistence.postgre.ExerciseRepositoryAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.List;

@Service
public class WorkoutService {
    private final ExerciseRepositoryAdapter exerciseRepository;
    private final ExerciseService exerciseService;

    @Autowired
    public WorkoutService(ExerciseRepositoryAdapter exerciseRepository, ExerciseService exerciseService) {
        this.exerciseRepository = exerciseRepository;
        this.exerciseService = exerciseService;
    }

    public WorkoutDTO toDTO(Workout workout) {

        List<ExerciseSet> exerciseSets = workout.getExerciseSets();
        List<ExerciseSetDTO> exerciseSetDTOs = exerciseSets.stream()
                .map(this::toDTO)
                .toList();

        return new WorkoutDTO(
                workout.getName(),
                workout.getDescription(),
                workout.getUsername(),
                exerciseSetDTOs,
                workout.getDate().atZone(ZoneId.of("UTC")).toInstant().toEpochMilli() / 1000
        );
    }

    private ExerciseSetDTO toDTO(ExerciseSet exerciseSet) {
        return new ExerciseSetDTO(
                exerciseService.toDTO(exerciseRepository.findByExerciseName(exerciseSet.getExerciseName()).get()),
                exerciseSet.getSets().stream().map(this::toDTO).toList(),
                exerciseSet.getNotes()
        );
    }

    private GymSetDTO toDTO(GymSet gymSet) {
        return new GymSetDTO(
                gymSet.getReps(),
                gymSet.getTimeInSeconds(),
                gymSet.getWeight(),
                gymSet.getRestSeconds()
        );
    }
}
