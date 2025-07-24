package org.hartz.hartz_backend.controller;

import lombok.RequiredArgsConstructor;
import org.hartz.hartz_backend.model.Workout;
import org.hartz.hartz_backend.model.dto.WorkoutDTO;
import org.hartz.hartz_backend.persistence.postgre.WorkoutRepositoryAdapter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class WorkoutController {

    private final WorkoutRepositoryAdapter workoutRepository;

    @GetMapping("/{userName}/workout")
    public ResponseEntity<Object> getWorkoutsByUserID(@RequestBody String username) {
        Optional<Workout> workoutOptional = workoutRepository.findByUser(username);
        if (workoutOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(WorkoutDTO.toDTO(workoutOptional.get()));
    }
}
