package org.hartz.hartz_backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hartz.hartz_backend.model.GymSet;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputPostGymSetDTO {
    private int reps;
    private int timeInSeconds;
    private double weight;
    private int restSeconds;

    public GymSet toGymSet() {
        return new GymSet(
                reps,
                timeInSeconds,
                weight,
                restSeconds
        );
    }
}
