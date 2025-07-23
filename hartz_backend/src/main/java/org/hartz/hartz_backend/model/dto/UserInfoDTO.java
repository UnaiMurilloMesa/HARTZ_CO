package org.hartz.hartz_backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hartz.hartz_backend.model.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDTO {
    private String username;
    private String mascot;

    public static UserInfoDTO toDTO(User user) {
        return new UserInfoDTO(user.getUsername(), user.getMascot());
    }
}
