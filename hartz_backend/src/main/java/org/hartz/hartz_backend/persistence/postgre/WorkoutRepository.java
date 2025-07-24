package org.hartz.hartz_backend.persistence.postgre;

import org.hartz.hartz_backend.model.Workout;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkoutRepository extends MongoRepository<Workout, Long> {

    Optional<Workout> findByName(String name);

    Optional<Workout> findByUserID(String user_id);

    boolean existsByName(String name); // Si son únicos

    boolean existsByUserID(String user_id);

}
