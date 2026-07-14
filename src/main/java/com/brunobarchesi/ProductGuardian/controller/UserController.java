package com.brunobarchesi.ProductGuardian.controller;

import com.brunobarchesi.ProductGuardian.dto.userdtos.ChangePasswordDTO;
import com.brunobarchesi.ProductGuardian.dto.userdtos.UserResponseDTO;
import com.brunobarchesi.ProductGuardian.dto.userdtos.UserUpdateDTO;
import com.brunobarchesi.ProductGuardian.entity.UserEntity;
import com.brunobarchesi.ProductGuardian.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> me(@AuthenticationPrincipal UserEntity currentUser) {
        return ResponseEntity.ok(userService.getMe(currentUser));
    }

    @PutMapping("/me")
    public ResponseEntity<UserResponseDTO> updateMe(
            @AuthenticationPrincipal UserEntity currentUser,
            @Valid @RequestBody UserUpdateDTO dto
    ) {
        return ResponseEntity.ok(userService.updateMe(currentUser, dto));
    }

    @PatchMapping("/me/change-password")
    public ResponseEntity<Void> changePassword(
            @AuthenticationPrincipal UserEntity currentUser,
            @Valid @RequestBody ChangePasswordDTO dto
    ) {
        userService.changePassword(currentUser, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/me")
    public ResponseEntity<Void> deleteMe(@AuthenticationPrincipal UserEntity currentUser) {
        userService.deleteMe(currentUser);
        return ResponseEntity.noContent().build();
    }
}
