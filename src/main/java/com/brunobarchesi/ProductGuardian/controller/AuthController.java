package com.brunobarchesi.ProductGuardian.controller;

import com.brunobarchesi.ProductGuardian.dto.userdtos.UserRegisterDTO;
import com.brunobarchesi.ProductGuardian.dto.userdtos.UserResponseDTO;
import com.brunobarchesi.ProductGuardian.dto.userdtos.logindtos.LoginRequestDTO;
import com.brunobarchesi.ProductGuardian.dto.userdtos.logindtos.TokenResponseDTO;
import com.brunobarchesi.ProductGuardian.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody @Valid UserRegisterDTO userDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(userDto));
    }


    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> login(@RequestBody @Valid LoginRequestDTO loginDto) {
        return ResponseEntity.ok(authService.login(loginDto));
    }
}
