package org.hartz.hartz_backend.model.workout.dto.in;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hartz.hartz_backend.model.exercise.dto.in.InputPostExerciseSetDTO;

import java.util.List;

@Data
@AllArgsConstructor
public class InputPostWorkoutDTO {
    private String name;
    private String description;
    @NotNull
    private boolean isRoutine;
    @NotNull
    private List<InputPostExerciseSetDTO> exerciseSets;
}
