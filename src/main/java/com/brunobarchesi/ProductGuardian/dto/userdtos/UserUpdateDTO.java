package com.brunobarchesi.ProductGuardian.dto.userdtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UserUpdateDTO(
        @Size(max = 100)
        String name,
        @Email
        @Size(max = 255)
        String email
) {}