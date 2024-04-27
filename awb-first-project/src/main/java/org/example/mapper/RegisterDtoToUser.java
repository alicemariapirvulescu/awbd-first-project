package org.example.mapper;

import org.example.controller.payload.request.RegisterDto;
import org.example.domain.entities.User;
import org.mapstruct.Mapper;

@Mapper
public interface RegisterDtoToUser {

    User registerDtoToUser(RegisterDto registerDto);
}
