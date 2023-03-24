package com.pragma.powerup.application.mapper.response;

import com.pragma.powerup.application.dto.response.EmployeeResponseDto;
import com.pragma.powerup.domain.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ICreateEmployeeResponseMapper {
    @Mapping(source = "userModel.id", target = "id")
    @Mapping(source = "userModel.firstName", target = "firstName")
    @Mapping(source = "userModel.lastName", target = "lastName")
    @Mapping(source = "userModel.phone", target = "phone")
    @Mapping(source = "userModel.email", target = "email")
    EmployeeResponseDto toDto(UserModel userModel);
}
