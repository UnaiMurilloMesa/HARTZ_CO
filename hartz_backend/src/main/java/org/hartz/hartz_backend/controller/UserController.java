package org.hartz.hartz_backend.controller;

import jakarta.validation.Valid;
import org.hartz.hartz_backend.exception.userExceptions.*;
import org.hartz.hartz_backend.model.user.User;
import org.hartz.hartz_backend.model.user.dto.in.UpdateUserBiographyInfoDTO;
import org.hartz.hartz_backend.model.user.dto.in.UpdateUserHeightInfoDTO;
import org.hartz.hartz_backend.model.user.dto.in.UpdateUserMascotInfoDTO;
import org.hartz.hartz_backend.model.user.dto.in.UpdateUserWeightInfoDTO;
import org.hartz.hartz_backend.model.user.dto.out.PersonalPrivateInfoDTO;
import org.hartz.hartz_backend.model.user.dto.out.UserInfoDTO;
import org.hartz.hartz_backend.persistence.postgre.UserRepositoryAdapter;
import org.hartz.hartz_backend.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserRepositoryAdapter userRepository;
    private final CustomUserDetailsService userService;

    @Autowired
    public UserController(UserRepositoryAdapter userRepository, CustomUserDetailsService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserInfoDTO> getUserByUsername(@PathVariable String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(UserInfoDTO.toDTO(userOptional.get()));
    }

    @GetMapping("/profile")
    public ResponseEntity<PersonalPrivateInfoDTO> getUserPersonalInfo(@AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(userService.getPersonalPrivateInfoByUsername(username));
    }

    @PutMapping("/height")
    public ResponseEntity<Object> updateUserHeight(@AuthenticationPrincipal UserDetails userDetails,
                                                   @Valid @RequestBody UpdateUserHeightInfoDTO updateUserHeightInfoDTO) {
        String username = userDetails.getUsername();
        try {
            userService.updateUserHeight(username, updateUserHeightInfoDTO);
            return ResponseEntity.ok().build();
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body("User not found");
        } catch (InvalidHeightException e) {
            return ResponseEntity.badRequest().body("Invalid height data");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/weight")
    public ResponseEntity<Object> updateUserWeight(@AuthenticationPrincipal UserDetails userDetails,
                                                   @Valid @RequestBody UpdateUserWeightInfoDTO updateUserWeightInfoDTO) {
        String username = userDetails.getUsername();
        try {
            userService.updateUserWeight(username, updateUserWeightInfoDTO);
            return ResponseEntity.ok().build();
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body("User not found");
        } catch (InvalidWeightException e) {
            return ResponseEntity.badRequest().body("Invalid weight data");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/mascot")
    public ResponseEntity<Object> updateUserMascot(@AuthenticationPrincipal UserDetails userDetails,
                                                   @Valid @RequestBody UpdateUserMascotInfoDTO updateUserMascotInfoDTO) {
        String username = userDetails.getUsername();
        try {
            userService.updateUserMascot(username, updateUserMascotInfoDTO);
            return ResponseEntity.ok().build();
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body("User not found");
        } catch (InvalidMascotInputException e) {
            return ResponseEntity.badRequest().body("Invalid mascot input");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/biography")
    public ResponseEntity<Object> updateUserBiography(@AuthenticationPrincipal UserDetails userDetails,
                                                      @Valid @RequestBody UpdateUserBiographyInfoDTO updateUserBiographyInfoDTO) {
        String username = userDetails.getUsername();
        try {
            userService.updateUserBiography(username, updateUserBiographyInfoDTO);
            return ResponseEntity.ok().build();
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body("User not found");
        } catch (InvalidBiographyInputException e) {
            return ResponseEntity.badRequest().body("Invalid biography data");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
