package org.hartz.hartz_backend.controller;

import org.hartz.hartz_backend.model.Exercise;
import org.hartz.hartz_backend.model.dto.ExerciseDTO;
import org.hartz.hartz_backend.persistence.postgre.ExerciseRepositoryAdapter;
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

    private ExerciseRepositoryAdapter exerciseRepository;
    private ExerciseService exerciseService;

    @Autowired
    public ExerciseController(ExerciseRepositoryAdapter exerciseRepository, ExerciseService exerciseService) {
        this.exerciseRepository = exerciseRepository;
        this.exerciseService = exerciseService;
    }

    @GetMapping("/{exerciseName}")
    public ResponseEntity<ExerciseDTO> getExerciseDetails(@PathVariable String exerciseName) {
        Optional<Exercise> exerciseOptional = exerciseRepository.findByExerciseName(exerciseName);
        if (exerciseOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(exerciseService.toDTO(exerciseOptional.get()));
    }
}
