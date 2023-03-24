package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.ClientRequestDto;
import com.pragma.powerup.application.exception.exception.DataAlreadyExistsException;
import com.pragma.powerup.application.exception.exception.NoDataFoundException;
import com.pragma.powerup.application.handler.IClientHandler;
import com.pragma.powerup.application.mapper.request.IClientRequestMapper;
import com.pragma.powerup.domain.api.IClientServicePort;
import com.pragma.powerup.domain.exception.EmailAlreadyExistsException;
import com.pragma.powerup.domain.exception.RoleNotFoundException;
import com.pragma.powerup.domain.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class ClientHandler implements IClientHandler {
    private final IClientServicePort clientServicePort;
    private final IClientRequestMapper clientRequestMapper;

    /**
     * Creates a client
     *
     * @param clientRequestDto - client information
     * @throws EmailAlreadyExistsException - client's email is already exists on the database
     * @throws RoleNotFoundException - client role not found on the database
     * */
    @Override
    public void createClientAccount(ClientRequestDto clientRequestDto) {
        UserModel userModel = this.clientRequestMapper.toModel(clientRequestDto);
        try {
            this.clientServicePort.createClientAccount(userModel);
        } catch (EmailAlreadyExistsException ex) {
            throw new DataAlreadyExistsException(ex.getMessage());
        } catch (RoleNotFoundException ex) {
            throw new NoDataFoundException(ex.getMessage());
        }
    }
}
