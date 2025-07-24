package org.hartz.hartz_backend.controller;

import lombok.RequiredArgsConstructor;
import org.hartz.hartz_backend.model.Workout;
import org.hartz.hartz_backend.model.dto.WorkoutDTO;
import org.hartz.hartz_backend.persistence.postgre.WorkoutRepositoryAdapter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/workout")
@RequiredArgsConstructor
public class WorkoutController {

    private final WorkoutRepositoryAdapter workoutRepository;

    @GetMapping("/{username}")
    public ResponseEntity<List<WorkoutDTO>> getWorkoutsByUsername(
            @PathVariable String username,
            @RequestParam boolean isRoutine) {
        List<Workout> workoutList = workoutRepository
                .findByUsername(username)
                .stream()
                .filter(w -> w.isRoutine() == isRoutine)
                .toList();
        if (workoutList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(
                workoutList
                        .stream()
                        .map(WorkoutDTO::toDTO)
                        .toList()
        );
    }
}
