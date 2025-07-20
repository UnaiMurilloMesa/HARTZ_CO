package org.hartz.hartz_backend.user.port.out;

import org.hartz.hartz_backend.user.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepositoryOutPort {
    Optional<User> save(User user);
    Optional<User> findByEmail(String email);
    Optional<User> findById(String userId);
    Optional<User> findByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
