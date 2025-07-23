package org.hartz.hartz_backend.service;

import org.hartz.hartz_backend.model.User;
import org.hartz.hartz_backend.persistence.postgre.UserRepositoryAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepositoryAdapter userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepositoryAdapter userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                List.of() // TODO: Aplicar roles en función del plan del usuario.
        );
    }
}
