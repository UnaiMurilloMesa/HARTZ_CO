package org.hartz.hartz_backend.persistence.postgres;

import org.hartz.hartz_backend.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<Exercise, String> {

}
