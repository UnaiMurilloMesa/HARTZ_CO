package org.hartz.hartz_backend.model.user.dto.out;

import lombok.Data;
import org.hartz.hartz_backend.model.user.User;

@Data
public class UserInfoDTO {
    private String username;
    private String mascot;

    private UserInfoDTO(String username, String mascot) {
        this.username = username;
        this.mascot = mascot;
    }

    public static UserInfoDTO toDTO(User user) {
        return new UserInfoDTO(user.getUsername(), user.getMascot());
    }
}
