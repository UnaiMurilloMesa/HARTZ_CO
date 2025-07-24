package org.hartz.hartz_backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "workouts")
public class Workout {

    @Id
    private Long id;
    private String username;
    private String name;
    private String description;
    private LocalDateTime date;
    private boolean isRoutine;
    private List<String> exerciseNames;

}
