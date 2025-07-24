package org.hartz.hartz_backend.persistence.postgres;

import org.hartz.hartz_backend.model.Workout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class WorkoutRepositoryAdapter {
    WorkoutRepository workoutRepository;

    @Autowired
    public WorkoutRepositoryAdapter(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    public Optional<Workout> findById(Long id) {
        return workoutRepository.findById(id);
    }

    public Optional<Workout> findByName(String name) {
        return workoutRepository.findByName(name);
    }

    public Optional<Workout> findByUser(String userId) {
        return workoutRepository.findByUserID(userId);
    }

    public boolean existsByName(String name) {
        return workoutRepository.existsByName(name);
    }

    public boolean existsByUser(String userId) {
        return workoutRepository.existsByUserID(userId);
    }



}
