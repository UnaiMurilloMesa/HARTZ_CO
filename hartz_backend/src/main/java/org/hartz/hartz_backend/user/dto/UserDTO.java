package org.hartz.hartz_backend.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hartz.hartz_backend.user.entity.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String username;
    private String email;
    private String mascot;

    public static UserDTO toDTO(User user) {
        return new UserDTO(user.getUsername(), user.getEmail(), user.getMascot());
    }
}
