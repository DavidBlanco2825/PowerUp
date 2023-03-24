package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.Roles;
import com.pragma.powerup.domain.api.IOwnerServicePort;
import com.pragma.powerup.domain.exception.EmailAlreadyExistsException;
import com.pragma.powerup.domain.exception.RoleNotFoundException;
import com.pragma.powerup.domain.model.RoleModel;
import com.pragma.powerup.domain.model.UserModel;
import com.pragma.powerup.domain.spi.IAuthPasswordEncoderPort;
import com.pragma.powerup.domain.spi.IRolePersistentPort;
import com.pragma.powerup.domain.spi.IUserPersistentPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OwnerUseCase implements IOwnerServicePort {
    private final IUserPersistentPort userPersistentPort;
    private final IRolePersistentPort rolePersistentPort;
    private final IAuthPasswordEncoderPort authPasswordEncoderPort;

    /**
     * Creates an employee owner
     *
     * @param  userModel - employee information
     * @throws EmailAlreadyExistsException - email already exists on the database
     * @throws RoleNotFoundException employee role wasn't found
     * */
    @Override
    public UserModel createEmployee(UserModel userModel) {
        RoleModel roleModel = this.rolePersistentPort
                .getRoleByName(Roles.EMPLOYEE).orElse(null);
        if (roleModel == null) {
            throw new RoleNotFoundException("Role not found");
        }
        if (this.userPersistentPort.getUserByEmail(userModel.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("User already exists");
        }
        userModel.setRole(roleModel);
        userModel.setPassword(this.authPasswordEncoderPort.encodePassword(userModel.getPassword()));
        return this.userPersistentPort.saveUser(userModel);
    }
}
