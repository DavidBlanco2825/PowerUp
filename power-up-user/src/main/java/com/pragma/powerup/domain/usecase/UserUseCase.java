package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.Roles;
import com.pragma.powerup.domain.api.IUserServicePort;
import com.pragma.powerup.domain.exception.RoleNotFoundException;
import com.pragma.powerup.domain.exception.UserNotFoundException;
import com.pragma.powerup.domain.model.RoleModel;
import com.pragma.powerup.domain.model.UserModel;
import com.pragma.powerup.domain.spi.IRolePersistentPort;
import com.pragma.powerup.domain.spi.IUserPersistentPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserUseCase implements IUserServicePort {
    private final IUserPersistentPort userPersistentPort;
    private final IRolePersistentPort rolePersistentPort;

    /**
     * Gets a restaurant owner by its email
     *
     * @param email - restaurant owner's email
     * @return Restaurant owner information
     * @throws RoleNotFoundException owner role wasn't found
     * @throws UserNotFoundException restaurant owner not found by the provided id
     * */
    @Override
    public UserModel getRestaurantOwnerByEmail(String email) {
        RoleModel roleModel = this.rolePersistentPort
                .getRoleByName(Roles.OWNER).orElse(null);
        if (roleModel == null) {
            throw new RoleNotFoundException("Owner role not found");
        }
        UserModel userModel = this.userPersistentPort.getUserByEmailAndRole(email, roleModel).orElse(null);
        if (userModel == null) {
            throw new UserNotFoundException("Restaurant owner not found");
        }
        return userModel;
    }

    /**
     * Gets a user by email
     *
     * @param email - user's email
     * @return user model corresponding to the user, if not found, returns null
     * @throws UserNotFoundException user not found by email
     * */
    @Override
    public UserModel getUserByEmail(String email) {
        UserModel userModel = this.userPersistentPort.getUserByEmail(email).orElse(null);
        if (userModel == null) {
            throw new UserNotFoundException("User not found");
        }
        return userModel;
    }
}
