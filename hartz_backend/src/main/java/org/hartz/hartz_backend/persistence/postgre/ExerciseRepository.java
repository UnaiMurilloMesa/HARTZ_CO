package org.hartz.hartz_backend.persistence.postgre;

import org.hartz.hartz_backend.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExerciseRepository extends JpaRepository<Exercise, String> {
    List<Exercise> findByExerciseNameIn(List<String> names);
}
