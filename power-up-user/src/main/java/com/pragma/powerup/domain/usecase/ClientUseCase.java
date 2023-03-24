package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.Roles;
import com.pragma.powerup.domain.api.IClientServicePort;
import com.pragma.powerup.domain.exception.EmailAlreadyExistsException;
import com.pragma.powerup.domain.exception.RoleNotFoundException;
import com.pragma.powerup.domain.model.RoleModel;
import com.pragma.powerup.domain.model.UserModel;
import com.pragma.powerup.domain.spi.IAuthPasswordEncoderPort;
import com.pragma.powerup.domain.spi.IRolePersistentPort;
import com.pragma.powerup.domain.spi.IUserPersistentPort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class ClientUseCase implements IClientServicePort {
    private final IUserPersistentPort userPersistentPort;
    private final IRolePersistentPort rolePersistentPort;
    private final IAuthPasswordEncoderPort authPasswordEncoderPort;

    /**
     * Creates a client account
     *
     * @param  userModel - data of the client
     * @throws EmailAlreadyExistsException - client's email is already exists on the database
     * @throws RoleNotFoundException - client role not found on the database
     * */
    @Override
    public void createClientAccount(UserModel userModel) {
        Optional<RoleModel> roleModel = this.rolePersistentPort
                .getRoleByName(Roles.CLIENT);
        if (roleModel.isEmpty()) {
            throw new RoleNotFoundException("Role client not found");
        }
        if (this.userPersistentPort.getUserByEmail(userModel.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email already in use");
        }
        userModel.setRole(roleModel.get());
        userModel.setPassword(this.authPasswordEncoderPort.encodePassword(userModel.getPassword()));
        this.userPersistentPort.saveUser(userModel);
    }
}
