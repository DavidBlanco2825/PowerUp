package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.OwnerRequestDto;
import com.pragma.powerup.application.exception.exception.DataAlreadyExistsException;
import com.pragma.powerup.application.exception.exception.NoDataFoundException;
import com.pragma.powerup.application.handler.IAdminHandler;
import com.pragma.powerup.application.mapper.request.IRestaurantOwnerRequestMapper;
import com.pragma.powerup.domain.api.IAdminServicePort;
import com.pragma.powerup.domain.exception.EmailAlreadyExistsException;
import com.pragma.powerup.domain.exception.RoleNotFoundException;
import com.pragma.powerup.domain.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminHandler implements IAdminHandler {
    private final IAdminServicePort adminServicePort;
    private final IRestaurantOwnerRequestMapper restaurantOwnerRequestMapper;

    /**
     * Creates the restaurant owner
     *
     * @param ownerRequestDto - restaurant owner information
     * @throws EmailAlreadyExistsException - Owner's email already exists on the database
     * @throws RoleNotFoundException - Role not found on the database
     * */
    @Override
    public void createRestaurantOwner(OwnerRequestDto ownerRequestDto) {
        UserModel userModel = this.restaurantOwnerRequestMapper.toUser(ownerRequestDto);
        try {
            this.adminServicePort.createRestaurantOwner(userModel);
        } catch (EmailAlreadyExistsException ex) {
            throw new DataAlreadyExistsException(ex.getMessage());
        } catch (RoleNotFoundException ex) {
            throw new NoDataFoundException(ex.getMessage());
        }
    }
}
