package net.icestone.springsecurity.mapper;


import org.mapstruct.Mapper;

import net.icestone.springsecurity.dto.user.UserResponse;
import net.icestone.springsecurity.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {

  UserResponse toResponse(UserEntity userEntity);
}
