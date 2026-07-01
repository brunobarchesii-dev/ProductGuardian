package com.brunobarchesi.ProductGuardian.dto;
import com.brunobarchesi.ProductGuardian.dto.enums.RoleEnum;
import java.time.LocalDateTime;
import java.util.UUID;

public record UserResponseDTO(UUID id, String name, String email, RoleEnum role, LocalDateTime updatedAt,
                              LocalDateTime createdAt) {
}
