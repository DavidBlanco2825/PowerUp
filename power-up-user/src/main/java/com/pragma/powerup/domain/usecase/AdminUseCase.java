package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.Roles;
import com.pragma.powerup.domain.model.RoleModel;
import com.pragma.powerup.domain.model.UserModel;
import com.pragma.powerup.domain.spi.IAuthPasswordEncoderPort;
import com.pragma.powerup.domain.spi.IRolePersistentPort;
import com.pragma.powerup.domain.spi.IUserPersistentPort;
import com.pragma.powerup.domain.exception.EmailAlreadyExistsException;
import com.pragma.powerup.domain.exception.RoleNotFoundException;
import com.pragma.powerup.domain.api.IAdminServicePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AdminUseCase implements IAdminServicePort {
    private final IAuthPasswordEncoderPort authServicePort;
    private final IUserPersistentPort userPersistentPort;
    private final IRolePersistentPort rolePersistentPort;

    /**
     * Creates a restaurant owner
     *
     * @param  userModel - restaurant owner information
     * @throws EmailAlreadyExistsException - Owner's email already exists on the database
     * @throws RoleNotFoundException - Role not found on the database
     * */
    @Override
    public void createRestaurantOwner(UserModel userModel) {
        RoleModel roleModel = this.rolePersistentPort
                .getRoleByName(Roles.OWNER).orElse(null);
        if (roleModel == null) {
            throw new RoleNotFoundException("Role not found");
        }
        if (this.userPersistentPort.getUserByEmail(userModel.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("User already exists");
        }
        userModel.setRole(roleModel);
        userModel.setPassword(this.authServicePort.encodePassword(userModel.getPassword()));
        this.userPersistentPort.saveUser(userModel);
    }
}
