package org.hartz.hartz_backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hartz.hartz_backend.model.Exercise;
import org.hartz.hartz_backend.model.Exercise.MuscleGroup;

import java.util.List;

@Data
public class ExerciseDTO {

    private String exerciseName;
    private String difficultyLevel;
    private List<String> muscleGroups;
    private String equipment;
    private boolean unilateral;
    private String gripType;
    private String bodyRegion;

    private ExerciseDTO(String exerciseName, String difficultyLevel, List<String> muscleGroups, String equipment,
                       boolean unilateral, String gripType, String bodyRegion) {
        this.exerciseName = exerciseName;
        this.difficultyLevel = difficultyLevel;
        this.muscleGroups = muscleGroups;
        this.equipment = equipment;
        this.unilateral = unilateral;
        this.gripType = gripType;
        this.bodyRegion = bodyRegion;
    }

    public static ExerciseDTO toDTO(Exercise exercise) {
        List<String> muscleGroups = exercise.getMuscleGroup().stream()
                .map(MuscleGroup::name)
                .toList();
        return new ExerciseDTO(exercise.getExerciseName(), exercise.getDifficultyLevel().name(), muscleGroups,
                exercise.getEquipment().name(), exercise.isUnilateral(), exercise.getGripType().name(),
                exercise.getBodyRegion().name());
    }
}
