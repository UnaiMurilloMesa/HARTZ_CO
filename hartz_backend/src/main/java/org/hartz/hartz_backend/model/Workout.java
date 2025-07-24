package org.hartz.hartz_backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

/**
 * IMPORTANT! Queremos guardar, por cada SetExercise, tanto el objeto Exercise como su nombre.
 * En ese momento habrá que crear converters para serializar y deserializar desde mongo.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "workouts")
public class Workout {

    @Id
    private Long id;
    private User user;
    private String name;
    private String description;
    private LocalDateTime date;
    private boolean isRoutine;
    private List<Exercise> exercises;

}
