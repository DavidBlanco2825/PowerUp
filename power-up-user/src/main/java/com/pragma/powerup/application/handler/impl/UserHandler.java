package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.response.OwnerResponseDto;
import com.pragma.powerup.application.dto.response.UserResponseDto;
import com.pragma.powerup.application.exception.exception.NoDataFoundException;
import com.pragma.powerup.application.handler.IUserHandler;
import com.pragma.powerup.application.mapper.response.IRestaurantOwnerResponseMapper;
import com.pragma.powerup.application.mapper.response.IUserResponseMapper;
import com.pragma.powerup.domain.api.IUserServicePort;
import com.pragma.powerup.domain.exception.RoleNotFoundException;
import com.pragma.powerup.domain.exception.UserNotFoundException;
import com.pragma.powerup.domain.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserHandler implements IUserHandler {
    private final IRestaurantOwnerResponseMapper restaurantOwnerResponseMapper;
    private final IUserServicePort userServicePort;
    private final IUserResponseMapper userResponseMapper;

    /**
     * Get a restaurant owner by its email
     *
     * @param email - restaurant owner email
     * @return restaurant owner information
     * @throws NoDataFoundException - restaurant owner's information not found
     * */
    @Override
    public OwnerResponseDto getRestaurantOwner(String email) {
        UserModel userModel;
        try {
            userModel = userServicePort.getRestaurantOwnerByEmail(email);
        } catch (RoleNotFoundException | UserNotFoundException ex) {
            throw new NoDataFoundException(ex.getMessage());
        }
        return this.restaurantOwnerResponseMapper.toResponseDto(userModel);
    }

    /**
     * Get a user by its email
     *
     * @param email - users email
     * @return user's information
     * @throws NoDataFoundException - user's information not found
     * */
    @Override
    public UserResponseDto getUserByEmail(String email) {
        UserModel userModel;
        try {
            userModel = userServicePort.getUserByEmail(email);
        } catch (UserNotFoundException ex) {
            throw new NoDataFoundException(ex.getMessage());
        }
        return this.userResponseMapper.toDto(userModel);
    }
}
