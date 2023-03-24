package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.response.OwnerResponseDto;
import com.pragma.powerup.application.dto.response.UserResponseDto;

public interface IUserHandler {
    OwnerResponseDto getRestaurantOwner(String email);
    UserResponseDto getUserByEmail(String email);
}
