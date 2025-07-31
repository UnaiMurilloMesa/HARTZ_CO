package org.hartz.hartz_backend;

import org.hartz.hartz_backend.model.workout.Workout;
import org.hartz.hartz_backend.service.WorkoutService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Year;
import java.time.ZoneOffset;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WorkoutServiceTest {
    private WorkoutService workoutService;

    @BeforeEach
    public void setup() {
        workoutService = new WorkoutService(null, null);
    }

    @Test
    public void testGetMinutesOfTrainingPerWeek() {
        int year = Year.now().getValue();

        // Semana 12 - 30 min
        Instant start1 = LocalDate.of(year, 3, 18).atStartOfDay().toInstant(ZoneOffset.UTC);
        Instant end1 = start1.plus(Duration.ofMinutes(30));

        // Semana 12 - 45 min
        Instant start2 = start1.plus(Duration.ofHours(1));
        Instant end2 = start2.plus(Duration.ofMinutes(45));

        // Semana 20 - 60 min
        Instant start3 = LocalDate.of(year, 5, 17).atStartOfDay().toInstant(ZoneOffset.UTC);
        Instant end3 = start3.plus(Duration.ofMinutes(60));

        Workout w1 = new Workout();
        w1.setStartDate(start1);
        w1.setEndDate(end1);

        Workout w2 = new Workout();
        w2.setStartDate(start2);
        w2.setEndDate(end2);

        Workout w3 = new Workout();
        w3.setStartDate(start3);
        w3.setEndDate(end3);

        List<Workout> workouts = List.of(w1, w2, w3);

        Map<Integer, Long> result = workoutService.getMinutesOfTrainingPerWeek(workouts);

        int week12 = LocalDate.of(year, 3, 18).get(WeekFields.ISO.weekOfYear());
        int week20 = LocalDate.of(year, 5, 17).get(WeekFields.ISO.weekOfYear());

        assertEquals(2, result.size());
        assertEquals(75L, result.get(week12));
        assertEquals(60L, result.get(week20));
    }

    @Test
    public void testNormalizeHeatMap() {
        Map<Integer, Long> mockDurations = Map.of(
                  10, 30L,
                  15, 90L,
                  20, 60L
        );

        List<Double> normalized = workoutService.normalizeHeatMap(mockDurations);

        int totalWeeks = LocalDate.of(Year.now().getValue(), 12, 28)
                  .get(WeekFields.ISO.weekOfWeekBasedYear());

        assertEquals(totalWeeks, normalized.size());
        assertEquals(1.0/3, normalized.get(9), 0.001);
        assertEquals(1.0, normalized.get(14), 0.001);
        assertEquals(2.0/3, normalized.get(19), 0.001);

        for (int i = 0; i < normalized.size(); i++) {
            if (i != 9 && i != 14 && i != 19) {
                assertEquals(0.0, normalized.get(i), 0.001);
            }
        }
    }
}
