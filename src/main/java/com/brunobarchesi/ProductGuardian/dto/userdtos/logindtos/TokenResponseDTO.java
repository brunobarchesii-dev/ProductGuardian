package com.brunobarchesi.ProductGuardian.dto.userdtos.logindtos;

public record TokenResponseDTO(String token, String type, long expiration) {
}
