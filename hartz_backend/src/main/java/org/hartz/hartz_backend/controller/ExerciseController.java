package org.hartz.hartz_backend.controller;

import org.hartz.hartz_backend.model.exercise.Exercise;
import org.hartz.hartz_backend.model.exercise.dto.out.ExerciseDTO;
import org.hartz.hartz_backend.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/exercise")
public class ExerciseController {

    private ExerciseService exerciseService;

    @Autowired
    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping("/{exerciseName}")
    public ResponseEntity<ExerciseDTO> getExerciseDetails(@PathVariable String exerciseName) {
        Optional<Exercise> exerciseOptional = exerciseService.findByExerciseName(exerciseName);
        if (exerciseOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(exerciseService.toDTO(exerciseOptional.get()));
    }
}
