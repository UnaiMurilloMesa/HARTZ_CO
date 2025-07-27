package org.hartz.hartz_backend.model.exercise.dto.in;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hartz.hartz_backend.model.exercise.GymSet;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputPostGymSetDTO {
    private Integer reps;
    private Integer timeInSeconds;
    private Double weight;
    private Integer restSeconds;

    public GymSet toGymSet() {
        return new GymSet(
                reps == null ? -1 : reps,
                timeInSeconds == null ? -1 : timeInSeconds,
                weight == null ? -1 : weight,
                restSeconds == null ? -1 : restSeconds
        );
    }
}
