package org.hartz.hartz_backend.controller;

import jakarta.validation.Valid;
import org.hartz.hartz_backend.model.exercise.Exercise;
import org.hartz.hartz_backend.model.exercise.ExerciseSet;
import org.hartz.hartz_backend.model.exercise.dto.in.InputPostExerciseSetDTO;
import org.hartz.hartz_backend.model.exercise.dto.in.InputPostGymSetDTO;
import org.hartz.hartz_backend.model.exercise.dto.out.GymSetDTO;
import org.hartz.hartz_backend.model.workout.Workout;
import org.hartz.hartz_backend.model.workout.dto.in.InputPostWorkoutDTO;
import org.hartz.hartz_backend.model.workout.dto.out.WorkoutDTO;
import org.hartz.hartz_backend.service.ExerciseService;
import org.hartz.hartz_backend.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/workout")
public class WorkoutController {

    private final WorkoutService workoutService;
    private final ExerciseService exerciseService;

    @Autowired
    public WorkoutController(WorkoutService workoutService, ExerciseService exerciseService) {
        this.workoutService = workoutService;
        this.exerciseService = exerciseService;
    }

    @GetMapping("/{username}")
    public ResponseEntity<List<WorkoutDTO>> getWorkoutsByUsername(
            @PathVariable String username,
            @RequestParam boolean isRoutine) {
        List<Workout> workoutList = workoutService.getWorkoutsByUserAndRoutine(username, isRoutine);
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

    @PostMapping
    public ResponseEntity<Object> createWorkout(@Valid @RequestBody InputPostWorkoutDTO workout, @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();

        // Si es una rutina debe tener un nombre
        if (workout.getIsRoutine() && (workout.getName() == null || workout.getName().trim().isEmpty())) {
            return ResponseEntity
                    .badRequest()
                    .body("Workout name can't be null");
        }

        if (workout.getName() != null && workout.getName().length() > Workout.MAX_NAME_LENGTH) {
            return ResponseEntity
                    .badRequest()
                    .body("Workout name can't be over " + Workout.MAX_NAME_LENGTH + " characters");
        }

        if (workout.getDescription() != null && workout.getDescription().length() > Workout.MAX_DESCRIPTION_LENGTH) {
            return ResponseEntity
                    .badRequest()
                    .body("Workout description can't be over " + Workout.MAX_DESCRIPTION_LENGTH + " characters");
        }

        // Exercises
        if (workout.getExerciseSets() != null && workout.getExerciseSets().size() > Workout.MAX_EXERCISES) {
            return ResponseEntity
                    .badRequest()
                    .body("You can't create more than " + Workout.MAX_EXERCISES + " exercises");
        }

        if (workout.getExerciseSets() != null && workout.getExerciseSets().isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body("A workout must have at least one exercise");
        }

        // Sets
        if (workout.getExerciseSets() != null && workout.getExerciseSets().stream()
                .anyMatch(es -> es.getSets().size() > Workout.MAX_SETS_PER_EXERCISE)) {
            return ResponseEntity
                    .badRequest()
                    .body("An exercise can't have more than " + Workout.MAX_SETS_PER_EXERCISE + " sets");
        }

        if (workout.getExerciseSets() != null && workout.getExerciseSets().stream()
                .anyMatch(es -> es.getSets().isEmpty())) {
            return ResponseEntity
                    .badRequest()
                    .body("An exercise must have at least one set");
        }

        if (workout.getExerciseSets() != null) {
            for (InputPostExerciseSetDTO exerciseSet : workout.getExerciseSets()) {
                String exerciseName = exerciseSet.getExerciseName();
                Optional<Exercise> exerciseOptional = exerciseService.findByExerciseName(exerciseName);

                if (exerciseOptional.isEmpty()) {
                    return ResponseEntity
                            .badRequest()
                            .body(exerciseName + " not found");
                }

                for (InputPostGymSetDTO gymSet : exerciseSet.getSets()) {
                    List<String> attributes = exerciseService.getAttributes(exerciseOptional.get().getExerciseType());

                    if (attributes.contains("reps")) {
                        if (gymSet.getReps() == null) {
                            return ResponseEntity
                                    .badRequest()
                                    .body("Mandatory attribute: Reps for exercise: " + exerciseName);
                        }
                    }

                    if (attributes.contains("weight")) {
                        if (gymSet.getWeight() == null) {
                            return ResponseEntity
                                    .badRequest()
                                    .body("Mandatory attribute: Weight for exercise: " + exerciseName);
                        }
                    }

                    if (attributes.contains("timeInSeconds")) {
                        if (gymSet.getTimeInSeconds() == null) {
                            return ResponseEntity
                                    .badRequest()
                                    .body("Mandatory attribute: TimeInSeconds for exercise: " + exerciseName);
                        }
                    }

                }
            }
        }


        Workout creatingWorkout = new Workout(
                username,
                workout.getName(),
                workout.getDescription(),
                Instant.now(),
                workout.getIsRoutine(),
                workout.getExerciseSets().stream().map(InputPostExerciseSetDTO::toExerciseSet).toList()
        );

        Workout savedWorkout = workoutService.save(creatingWorkout);

        return ResponseEntity.ok(
                workoutService.toDTO(savedWorkout)
        );
    }
}
