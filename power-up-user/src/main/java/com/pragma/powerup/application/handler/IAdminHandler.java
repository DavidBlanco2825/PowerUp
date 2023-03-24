package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.OwnerRequestDto;

public interface IAdminHandler {
    void createRestaurantOwner(OwnerRequestDto ownerRequestDto);
}
