package org.hartz.hartz_backend.persistence.postgre;

import org.hartz.hartz_backend.model.Workout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
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

    public List<Workout> findByUserName(String userName) {
        return workoutRepository.findByUsername(userName);
    }
}
