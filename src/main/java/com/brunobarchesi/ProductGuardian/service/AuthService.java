package com.brunobarchesi.ProductGuardian.service;

import com.brunobarchesi.ProductGuardian.config.security.TokenProvider;
import com.brunobarchesi.ProductGuardian.dto.enums.RoleEnum;
import com.brunobarchesi.ProductGuardian.dto.userdtos.UserRegisterDTO;
import com.brunobarchesi.ProductGuardian.dto.userdtos.UserResponseDTO;
import com.brunobarchesi.ProductGuardian.dto.userdtos.logindtos.LoginRequestDTO;
import com.brunobarchesi.ProductGuardian.dto.userdtos.logindtos.TokenResponseDTO;
import com.brunobarchesi.ProductGuardian.entity.UserEntity;
import com.brunobarchesi.ProductGuardian.exception.ConflictException;
import com.brunobarchesi.ProductGuardian.exception.InvalidCredentialsException;
import com.brunobarchesi.ProductGuardian.mapper.UserMapper;
import com.brunobarchesi.ProductGuardian.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public UserResponseDTO register(UserRegisterDTO userRegisterDTO){
        if (userRepository.findByEmail(userRegisterDTO.email()).isPresent()){
            throw new ConflictException("Ja existe um usuario cadastrado com esse email");
        }
        UserEntity userEntity = userMapper.toUserEntity(userRegisterDTO);
        userEntity.setRole(RoleEnum.USER);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userRepository.save(userEntity);

        return userMapper.toUserResponseDTO(userEntity);
    }


    public TokenResponseDTO login(LoginRequestDTO loginDto){
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.email(), loginDto.password())
            );

            String token = tokenProvider.generateToken(authentication);
            return new TokenResponseDTO(token, "Bearer", 15 * 60 * 1000L);
        }catch (BadCredentialsException e){
            throw new InvalidCredentialsException("Credenciais inválidas");
        }
    }
}
