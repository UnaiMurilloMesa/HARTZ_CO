package org.hartz.hartz_backend.controller;

import lombok.RequiredArgsConstructor;
import org.hartz.hartz_backend.model.Workout;
import org.hartz.hartz_backend.model.dto.WorkoutDTO;
import org.hartz.hartz_backend.persistence.mongo.WorkoutRepositoryAdapter;
import org.hartz.hartz_backend.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/workout")
public class WorkoutController {

    private final WorkoutRepositoryAdapter workoutRepository;
    private final WorkoutService workoutService;

    @Autowired
    public WorkoutController(WorkoutRepositoryAdapter workoutRepository, WorkoutService workoutService) {
        this.workoutRepository = workoutRepository;
        this.workoutService = workoutService;
    }

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
                        .map(workoutService::toDTO)
                        .toList()
        );
    }
}
