package org.hartz.hartz_backend.persistence.postgres;

import org.hartz.hartz_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);
}
