package org.hartz.hartz_backend.persistence.postgres;

import org.hartz.hartz_backend.model.Exercise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ExerciseRepositoryAdapter {

    private ExerciseRepository exerciseRepository;

    @Autowired
    public ExerciseRepositoryAdapter(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    public Optional<Exercise> findByExerciseName(String name) {
        return exerciseRepository.findById(name);
    }

    public Exercise save(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }
}
