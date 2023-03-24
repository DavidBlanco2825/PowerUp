package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.RoleModel;
import com.pragma.powerup.domain.spi.IRolePersistentPort;
import com.pragma.powerup.infrastructure.out.jpa.entity.RoleEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IRoleEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IRoleRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class RolePersistenceAdapter implements IRolePersistentPort {
    private final IRoleRepository roleRepository;
    private final IRoleEntityMapper roleEntityMapper;

    @Override
    public void saveRole(RoleModel roleModel) {
        this.roleRepository.save(this.roleEntityMapper.toEntity(roleModel));
    }

    @Override
    public Optional<RoleModel> getRoleByName(String roleName) {
        RoleEntity roleEntity = this.roleRepository.findByName(roleName).orElse(null);
        return Optional.ofNullable(this.roleEntityMapper.toRolModel(roleEntity));
    }

    @Override
    public Optional<RoleModel> getRoleById(Long roleId) {
        RoleEntity roleEntity = this.roleRepository.findById(roleId).orElse(null);
        return Optional.ofNullable(this.roleEntityMapper.toRolModel(roleEntity));
    }
}
