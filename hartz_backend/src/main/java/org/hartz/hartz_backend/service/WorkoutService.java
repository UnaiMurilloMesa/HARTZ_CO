package org.hartz.hartz_backend.service;

import org.hartz.hartz_backend.model.exercise.ExerciseSet;
import org.hartz.hartz_backend.model.exercise.dto.out.ExerciseSetDTO;
import org.hartz.hartz_backend.model.workout.Workout;
import org.hartz.hartz_backend.model.workout.dto.out.WorkoutDTO;
import org.hartz.hartz_backend.persistence.mongo.WorkoutRepositoryAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Year;
import java.time.ZoneOffset;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class WorkoutService {
    private final WorkoutRepositoryAdapter workoutRepository;
    private final ExerciseService exerciseService;

    @Autowired
    public WorkoutService(WorkoutRepositoryAdapter workoutRepository, ExerciseService exerciseService) {
        this.workoutRepository = workoutRepository;
        this.exerciseService = exerciseService;
    }

    public List<Workout> getWorkoutsByUserAndRoutine(String username, boolean isRoutine) {
        return workoutRepository
                .findByUsername(username)
                .stream()
                .filter(w -> w.isRoutine() == isRoutine)
                .toList();
    }

    public Workout save(Workout workout) {
        return workoutRepository.save(workout);
    }

    public WorkoutDTO toDTO(Workout workout) {

        List<ExerciseSet> exerciseSets = workout.getExerciseSets();
        List<ExerciseSetDTO> exerciseSetDTOs = exerciseSets.stream()
                .map(exerciseService::toDTO)
                .toList();

        return new WorkoutDTO(
                workout.getName(),
                workout.getDescription(),
                workout.getUsername(),
                exerciseSetDTOs,
                workout.getCreatedDate(),
                workout.getStartDate(),
                workout.getEndDate()
        );
    }

    public Map<Integer, Long> getMinutesOfTrainingPerWeek(List<Workout> workouts) {
        return workouts.stream()
                  .collect(Collectors.groupingBy(
                            w -> {
                                LocalDate localDate = w.getStartDate().atZone(ZoneOffset.UTC).toLocalDate();
                                return localDate.get(WeekFields.ISO.weekOfYear());
                            },
                            Collectors.summingLong(w -> {
                                Instant start = w.getStartDate();
                                Instant end = w.getEndDate();
                                if (start == null || end == null) return 0L;
                                return Duration.between(start, end).toMinutes();
                            })
                  ));
    }

    public List<Double> normalizeHeatMap(Map<Integer, Long> durationPerWeek) {
        int totalWeeks = LocalDate.of(Year.now().getValue(), 12, 28) // Está garantizado de que el 28 de diciembre siempre está en la última semana del año
                  .get(WeekFields.ISO.weekOfWeekBasedYear());
        long max = durationPerWeek.values().stream().mapToLong(Long::longValue).max().orElse(0);
        long min;
        if (durationPerWeek.size() < totalWeeks) {
            min = 0;
        } else {
            min = durationPerWeek.values().stream().mapToLong(Long::longValue).min().orElse(0);
        }

        List<Double> normalizedDurations = new ArrayList<>();
        for (int week = 1; week <= totalWeeks; week++) {
            long minutes = durationPerWeek.getOrDefault(week, 0L);
            double normalized;

            if (max == min) {
                normalized = minutes > 0 ? 1.0 : 0.0;
            } else {
                normalized = (double) (minutes - min) / (max - min);
            }
            normalizedDurations.add(normalized);
        }

        return normalizedDurations;
    }
}
