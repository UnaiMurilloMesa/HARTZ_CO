package org.hartz.hartz_backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseSetDTO {

    private ExerciseDTO exerciseDTO;
    private int reps;
    private int timeInSeconds;
    private double weight;
    private int restSeconds;
    private String notes;
}
