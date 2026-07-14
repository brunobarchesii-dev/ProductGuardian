package com.brunobarchesi.ProductGuardian.dto.userdtos.logindtos;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(
        @NotBlank(message = "Email é obrigatorio")
        String email,
        @NotBlank(message = "Senha é obrigatorio")
        String password) {
}
