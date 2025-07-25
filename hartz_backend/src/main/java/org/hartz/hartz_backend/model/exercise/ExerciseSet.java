package org.hartz.hartz_backend.model.exercise;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseSet {
    private String exerciseName;
    private List<GymSet> sets;
    private String notes;
}



