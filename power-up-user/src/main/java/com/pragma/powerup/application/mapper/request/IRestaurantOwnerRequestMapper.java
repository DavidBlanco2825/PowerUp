package com.pragma.powerup.application.mapper.request;

import com.pragma.powerup.application.dto.request.OwnerRequestDto;
import com.pragma.powerup.domain.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestaurantOwnerRequestMapper {
    UserModel toUser(OwnerRequestDto ownerRequestDto);
}
