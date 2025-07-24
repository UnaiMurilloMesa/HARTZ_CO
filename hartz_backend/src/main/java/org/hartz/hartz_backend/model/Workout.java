package org.hartz.hartz_backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Workout {

    private Long id;

    private String username;

    private String name;

    private String description;

    private Long date;

    private boolean isRoutine;

    private List<Exercise> exercises;

}
