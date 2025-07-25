package org.hartz.hartz_backend.persistence.mongo;

import org.hartz.hartz_backend.model.Workout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class WorkoutRepositoryAdapter {
    private WorkoutRepository workoutRepository;

    @Autowired
    public WorkoutRepositoryAdapter(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    public Optional<Workout> findById(Long id) {
        return workoutRepository.findById(id);
    }

    public List<Workout> findByUsername(String username) {
        return workoutRepository.findByUsername(username);
    }

    public Workout save(Workout workout) {
        return workoutRepository.save(workout);
    }
}
