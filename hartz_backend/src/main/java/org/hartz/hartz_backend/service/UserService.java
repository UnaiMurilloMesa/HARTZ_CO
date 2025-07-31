package org.hartz.hartz_backend.service;

import org.hartz.hartz_backend.exception.userExceptions.InvalidBiographyInputException;
import org.hartz.hartz_backend.exception.userExceptions.InvalidHeightException;
import org.hartz.hartz_backend.exception.userExceptions.InvalidMascotInputException;
import org.hartz.hartz_backend.exception.userExceptions.InvalidWeightException;
import org.hartz.hartz_backend.model.user.User;
import org.hartz.hartz_backend.model.user.dto.in.UpdateUserBiographyInfoDTO;
import org.hartz.hartz_backend.model.user.dto.in.UpdateUserHeightInfoDTO;
import org.hartz.hartz_backend.model.user.dto.in.UpdateUserMascotInfoDTO;
import org.hartz.hartz_backend.model.user.dto.in.UpdateUserWeightInfoDTO;
import org.hartz.hartz_backend.model.user.dto.out.PersonalPrivateInfoDTO;
import org.hartz.hartz_backend.model.workout.Workout;
import org.hartz.hartz_backend.persistence.postgre.UserRepositoryAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    private UserRepositoryAdapter userRepository;
    private WorkoutService workoutService;

    @Autowired
    public UserService(UserRepositoryAdapter userRepository, WorkoutService workoutService) {
        this.userRepository = userRepository;
        this.workoutService = workoutService;
    }

    public PersonalPrivateInfoDTO getPersonalPrivateInfoByUsername(String username) {
        User user = userRepository.findByUsername(username)
                  .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return PersonalPrivateInfoDTO.toDto(user);
    }

    public void updateUserHeight(String username, UpdateUserHeightInfoDTO updateUserHeightInfoDTO) {
        User user = userRepository.findByUsername(username)
                  .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (updateUserHeightInfoDTO.getHeight() == null || updateUserHeightInfoDTO.getHeight() <= 0) {
            throw new InvalidHeightException();
        }

        user.setHeight(updateUserHeightInfoDTO.getHeight());
        userRepository.save(user);
    }

    public void updateUserWeight(String username, UpdateUserWeightInfoDTO updateUserWeightInfoDTO) {
        User user = userRepository.findByUsername(username)
                  .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (updateUserWeightInfoDTO.getWeight() == null || updateUserWeightInfoDTO.getWeight() <= 0) {
            throw new InvalidWeightException();
        }

        user.setWeight(updateUserWeightInfoDTO.getWeight());
        userRepository.save(user);
    }

    public void updateUserMascot(String username, UpdateUserMascotInfoDTO updateMascotInfoDTO) {
        User user = userRepository.findByUsername(username)
                  .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (updateMascotInfoDTO.getMascot() == null) {
            throw new InvalidMascotInputException();
        }

        user.setMascot(updateMascotInfoDTO.getMascot());
        userRepository.save(user);
    }

    public void updateUserBiography(String username, UpdateUserBiographyInfoDTO updateUserBiographyInfoDTO) {
        User user = userRepository.findByUsername(username)
                  .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (updateUserBiographyInfoDTO.getBiography() == null) {
            throw new InvalidBiographyInputException();
        }

        user.setBiography(updateUserBiographyInfoDTO.getBiography());
        userRepository.save(user);
    }

    public List<Double> getUserHeatMap(String username) {
        List<Workout> workouts = workoutService.getWorkoutsByUserAndRoutine(username, false);
        Instant startOfYear = ZonedDateTime.now(ZoneOffset.UTC)
                  .withDayOfYear(1)
                  .toLocalDate()
                  .atStartOfDay(ZoneOffset.UTC)
                  .toInstant();
        List<Workout> filteredWorkouts = workouts.stream().filter(w -> w.getStartDate().isAfter(startOfYear)).toList();
        Map<Integer, Long> minutesPerWeek = workoutService.getMinutesOfTrainingPerWeek(filteredWorkouts);
        return workoutService.normalizeHeatMap(minutesPerWeek);
    }
}

