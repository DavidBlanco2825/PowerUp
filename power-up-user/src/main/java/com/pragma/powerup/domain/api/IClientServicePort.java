package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.UserModel;

public interface IClientServicePort {
    void createClientAccount(UserModel userModel);
}
