package org.hartz.hartz_backend.model.user.dto.in;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserBiographyInfoDTO {
    @NotNull
    private String biography;
}
