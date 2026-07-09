package com.brunobarchesi.ProductGuardian.mapper;

import com.brunobarchesi.ProductGuardian.dto.userdtos.UserRegisterDTO;
import com.brunobarchesi.ProductGuardian.dto.userdtos.UserResponseDTO;
import com.brunobarchesi.ProductGuardian.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity toUserEntity (UserRegisterDTO userRegisterDTO);

    UserResponseDTO toUserResponseDTO(UserEntity userEntity);
}
