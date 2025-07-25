package org.hartz.hartz_backend.model.exercise.dto.out;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseSetDTO {

    private ExerciseDTO exerciseDTO;
    private List<GymSetDTO> gymSetDTO;
    private String notes;
}
