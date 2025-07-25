package org.hartz.hartz_backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hartz.hartz_backend.model.ExerciseSet;

import java.util.List;

@Data
@AllArgsConstructor
public class InputPostExerciseSetDTO {
    private String exerciseName;
    private List<InputPostGymSetDTO> sets;
    private String notes;

    public ExerciseSet toExerciseSet(){
        return new ExerciseSet(
                exerciseName,
                sets.stream().map(InputPostGymSetDTO::toGymSet).toList(),
                notes
        );
    }

}
