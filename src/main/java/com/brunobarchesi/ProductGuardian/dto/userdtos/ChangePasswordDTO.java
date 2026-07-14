package com.brunobarchesi.ProductGuardian.dto.userdtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ChangePasswordDTO(@NotBlank String currentPassword,
                                @NotBlank @Size(min = 6, max = 100) String newPassword) {
}
