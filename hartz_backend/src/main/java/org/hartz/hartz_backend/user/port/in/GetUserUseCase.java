package org.hartz.hartz_backend.user.port.in;

import org.hartz.hartz_backend.user.model.User;

import java.util.Optional;

public interface GetUserUseCase {
    Optional<User> findById(String userId);
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
}
