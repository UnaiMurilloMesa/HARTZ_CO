package org.hartz.hartz_backend.model.exercise.dto.out;

import lombok.Data;
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

    public ExerciseDTO(String exerciseName, String difficultyLevel, List<String> muscleGroups, String equipment,
                       boolean unilateral, String gripType, String bodyRegion) {
        this.exerciseName = exerciseName;
        this.difficultyLevel = difficultyLevel;
        this.muscleGroups = muscleGroups;
        this.equipment = equipment;
        this.unilateral = unilateral;
        this.gripType = gripType;
        this.bodyRegion = bodyRegion;
    }
}
