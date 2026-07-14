package com.brunobarchesi.ProductGuardian.service;

import com.brunobarchesi.ProductGuardian.dto.userdtos.ChangePasswordDTO;
import com.brunobarchesi.ProductGuardian.dto.userdtos.UserResponseDTO;
import com.brunobarchesi.ProductGuardian.dto.userdtos.UserUpdateDTO;
import com.brunobarchesi.ProductGuardian.entity.UserEntity;
import com.brunobarchesi.ProductGuardian.exception.ConflictException;
import com.brunobarchesi.ProductGuardian.exception.InvalidCredentialsException;
import com.brunobarchesi.ProductGuardian.mapper.UserMapper;
import com.brunobarchesi.ProductGuardian.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    //Obter dados do usuario logado
    public UserResponseDTO getMe(UserEntity currentUser) {
        return userMapper.toUserResponseDTO(currentUser);
    }


    //atualizar dados do usuario logado
    public UserResponseDTO updateMe(UserEntity currentUser, UserUpdateDTO dto) {
        if (dto.name() != null) {
            currentUser.setName(dto.name());
        }
        // Se o email for alterado, verifica se ja existe um usuario com esse email novo
        if (dto.email() != null) {
            userRepository.findByEmail(dto.email())
                    .filter(user -> !user.getId().equals(currentUser.getId()))
                    .ifPresent(user -> {
                        throw new ConflictException("Já existe usuário com esse email"); });

            currentUser.setEmail(dto.email());
        }

        UserEntity saved = userRepository.save(currentUser);
        return userMapper.toUserResponseDTO(saved);
    }


    //mudar senha do usuario logado
    public void changePassword(UserEntity currentUser, ChangePasswordDTO dto) {
        //Se a senha atual digita for diferente da senha do usuario salva no banco de dados, lanca erro:
        if (!passwordEncoder.matches(dto.currentPassword(), currentUser.getPassword())) {
            throw new InvalidCredentialsException("Senha atual inválida");
        }
        //Se a senha atual for igual, atualiza a senha do usuario
        currentUser.setPassword(passwordEncoder.encode(dto.newPassword()));
        userRepository.save(currentUser);
    }

    //deletar usuario logado
    public void deleteMe(UserEntity currentUser) {
        userRepository.delete(currentUser);
    }
}

