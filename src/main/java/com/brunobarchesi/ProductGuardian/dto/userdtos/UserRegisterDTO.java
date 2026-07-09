package com.brunobarchesi.ProductGuardian.dto.userdtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRegisterDTO(
        @NotBlank(message = "Campo name é obrigatório")
        @Size(max = 100)
        String name,

        @NotBlank(message = "Campo email é obrigatório")
        @Email
        @Size(max = 255)
        String email,

        @NotBlank(message = "é obrigatorio informar uma senha")
        @Size(min = 6, max = 100)
        String password
) {
}