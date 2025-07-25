package org.hartz.hartz_backend.persistence.mongo;

import org.hartz.hartz_backend.model.workout.Workout;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutRepository extends MongoRepository<Workout, Long> {

    List<Workout> findByUsername(String username);

}
