package org.hartz.hartz_backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GymSetDTO {
    private int reps;
    private int timeInSeconds;
    private double weight;
    private int restSeconds;
}
