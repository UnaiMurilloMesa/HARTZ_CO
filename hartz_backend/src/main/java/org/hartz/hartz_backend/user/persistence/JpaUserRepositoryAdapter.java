package org.hartz.hartz_backend.user.persistence;

import lombok.RequiredArgsConstructor;
import org.hartz.hartz_backend.user.UserMapper;
import org.hartz.hartz_backend.user.model.User;
import org.hartz.hartz_backend.user.entity.UserEntity;
import org.hartz.hartz_backend.user.port.out.UserRepositoryOutPort;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaUserRepositoryAdapter implements UserRepositoryOutPort {
    private final UserRepositoryInPort userRepositoryInPort;

    @Override
    public Optional<User> save(User user) {
        UserEntity mapped = UserMapper.toUserEntity(user);
        UserEntity savedEntity = userRepositoryInPort.save(mapped);
        return Optional.of(UserMapper.toDomainUser(savedEntity));
    }

    @Override
    public Optional<User> findById(String userId) {
        return userRepositoryInPort.findById(Long.valueOf(userId))
                .map(UserMapper::toDomainUser);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepositoryInPort.findByEmail(email)
                .map(UserMapper::toDomainUser);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepositoryInPort.findByUsername(username)
                .map(UserMapper::toDomainUser);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepositoryInPort.existsByEmail(email);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepositoryInPort.existsByUsername(username);
    }
}
