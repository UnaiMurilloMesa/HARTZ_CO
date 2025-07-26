package org.hartz.hartz_backend.model.exercise.dto.in;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hartz.hartz_backend.model.exercise.ExerciseSet;

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
