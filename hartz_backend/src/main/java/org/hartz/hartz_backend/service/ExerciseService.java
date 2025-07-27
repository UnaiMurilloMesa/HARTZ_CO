package org.hartz.hartz_backend.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.hartz.hartz_backend.model.exercise.Exercise;
import org.hartz.hartz_backend.model.exercise.Exercise.ExerciseType;
import org.hartz.hartz_backend.model.exercise.ExerciseSet;
import org.hartz.hartz_backend.model.exercise.GymSet;
import org.hartz.hartz_backend.model.exercise.dto.out.ExerciseDTO;
import org.hartz.hartz_backend.model.exercise.dto.out.ExerciseSetDTO;
import org.hartz.hartz_backend.model.exercise.dto.out.GymSetDTO;
import org.hartz.hartz_backend.persistence.postgre.ExerciseRepositoryAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExerciseService {

    private ExerciseRepositoryAdapter exerciseRepository;

    private final Map<ExerciseType, List<String>> exercisesAttributes;


    @Autowired
    public ExerciseService(ExerciseRepositoryAdapter exerciseRepository) {
        this.exerciseRepository = exerciseRepository;

        exercisesAttributes = new HashMap<>();
        exercisesAttributes.put(
                ExerciseType.BODY_REPS, List.of("reps")
        );
        exercisesAttributes.put(
                ExerciseType.WEIGHT_REPS, List.of("reps", "weight")
        );
        exercisesAttributes.put(
                ExerciseType.CARDIO, List.of("timeInSeconds")
        );
        exercisesAttributes.put(
                ExerciseType.TIME, List.of("timeInSeconds")
        );
    }

    public Optional<Exercise> findByExerciseName(String exerciseName) {
        return exerciseRepository.findByExerciseName(exerciseName);
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

    public ExerciseSetDTO toDTO(ExerciseSet exerciseSet) {
        Optional<Exercise> exerciseOptional = exerciseRepository.findByExerciseName(exerciseSet.getExerciseName());
        return new ExerciseSetDTO(
                toDTO(exerciseOptional.get()),
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

    public List<String> getAttributes(ExerciseType exerciseType) {
        return exercisesAttributes.get(exerciseType);
    }
}
