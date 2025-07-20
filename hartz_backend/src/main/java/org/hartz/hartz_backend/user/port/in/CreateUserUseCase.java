package org.hartz.hartz_backend.user.port.in;

import org.hartz.hartz_backend.user.dto.UserDTO;
import org.hartz.hartz_backend.user.model.User;

import java.util.Optional;

public interface CreateUserUseCase {
    Optional<User> save(UserDTO user);
}
