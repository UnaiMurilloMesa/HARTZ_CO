package org.hartz.hartz_backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Workout {

    private Long ID;

    private User user;

    private String name;

    private String description;

    private LocalDateTime date;

    private List<Exercise> exercises;

}
