package org.hartz.hartz_backend.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.hartz.hartz_backend.model.Exercise;
import org.hartz.hartz_backend.model.dto.ExerciseDTO;
import org.hartz.hartz_backend.persistence.postgre.ExerciseRepositoryAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return new ExerciseDTO(exercise.getExerciseName(), exercise.getDifficultyLevel().name(), muscleGroups,
                exercise.getEquipment().name(), exercise.isUnilateral(), exercise.getGripType().name(),
                exercise.getBodyRegion().name());
    }
}
