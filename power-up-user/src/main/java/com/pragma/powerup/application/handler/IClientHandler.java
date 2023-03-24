package com.pragma.powerup.application.handler;


import com.pragma.powerup.application.dto.request.ClientRequestDto;

public interface IClientHandler {
    void createClientAccount(ClientRequestDto clientRequestDto);
}
