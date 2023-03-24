package com.pragma.powerup.application.mapper.request;

import com.pragma.powerup.application.dto.request.ClientRequestDto;
import com.pragma.powerup.domain.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IClientRequestMapper {
    @Mapping(target = "id", source = "clientRequestDto.id")
    @Mapping(target = "firstName", source = "clientRequestDto.firstName")
    @Mapping(target = "lastName", source = "clientRequestDto.lastName")
    @Mapping(target = "phone", source = "clientRequestDto.phone")
    @Mapping(target = "email", source = "clientRequestDto.email")
    @Mapping(target = "password", source = "clientRequestDto.password")
    UserModel toModel(ClientRequestDto clientRequestDto);
}
