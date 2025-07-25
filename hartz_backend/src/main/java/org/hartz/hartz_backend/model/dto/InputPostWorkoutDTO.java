package org.hartz.hartz_backend.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

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
