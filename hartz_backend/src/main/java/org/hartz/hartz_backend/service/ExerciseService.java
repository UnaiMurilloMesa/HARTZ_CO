package org.hartz.hartz_backend.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.hartz.hartz_backend.model.Exercise;
import org.hartz.hartz_backend.model.dto.ExerciseDTO;
import org.hartz.hartz_backend.persistence.postgre.ExerciseRepositoryAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExerciseService {

    private ExerciseRepositoryAdapter exerciseRepository;

    @Autowired
    public ExerciseService(ExerciseRepositoryAdapter exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    public ExerciseDTO toDTO(Exercise exercise) {
        List<String> muscleGroups = exercise.getMuscleGroup().stream()
                .map(Exercise.MuscleGroup::name)
                .toList();
        return new ExerciseDTO(
                exercise.getExerciseName(),
                exercise.getDifficultyLevel().name(),
                muscleGroups,
                Optional.ofNullable(exercise.getEquipment())
                        .map(Enum::name)
                        .orElse(null),
                exercise.isUnilateral(),
                Optional.ofNullable(exercise.getGripType())
                        .map(Enum::name)
                        .orElse(null),
                exercise.getBodyRegion().name());
    }
}
