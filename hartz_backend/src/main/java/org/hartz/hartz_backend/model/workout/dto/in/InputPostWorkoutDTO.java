package org.hartz.hartz_backend.model.workout.dto.in;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hartz.hartz_backend.model.exercise.dto.in.InputPostExerciseSetDTO;

import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
public class InputPostWorkoutDTO {
    private String name;
    private String description;
    @NotNull
    private Boolean isRoutine;
    @NotNull
    private List<InputPostExerciseSetDTO> exerciseSets;
    private Instant startDate;
    private Instant endDate;
}
