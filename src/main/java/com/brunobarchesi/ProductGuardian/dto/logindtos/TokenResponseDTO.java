package com.brunobarchesi.ProductGuardian.dto.logindtos;

public record TokenResponseDTO(String token, String type, long expiration) {
}
