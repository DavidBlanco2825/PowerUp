package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.EmployeeRequestDto;
import com.pragma.powerup.application.dto.response.EmployeeResponseDto;
import com.pragma.powerup.application.exception.exception.DataAlreadyExistsException;
import com.pragma.powerup.application.exception.exception.NoDataFoundException;
import com.pragma.powerup.application.handler.IOwnerHandler;
import com.pragma.powerup.application.mapper.request.IEmployeeRequestMapper;
import com.pragma.powerup.application.mapper.response.ICreateEmployeeResponseMapper;
import com.pragma.powerup.domain.api.IOwnerServicePort;
import com.pragma.powerup.domain.exception.EmailAlreadyExistsException;
import com.pragma.powerup.domain.exception.RoleNotFoundException;
import com.pragma.powerup.domain.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class OwnerHandler implements IOwnerHandler {
    private final IOwnerServicePort ownerServicePort;
    private final ICreateEmployeeResponseMapper employeeResponseMapper;
    private final IEmployeeRequestMapper employeeRequestMapper;

    /**
     * Creates a restaurant employee
     *
     * @param employeeRequestDto - employee information
     * @throws EmailAlreadyExistsException - email already exists on the database
     * @throws RoleNotFoundException employee role wasn't found
     * */
    @Override
    public EmployeeResponseDto createEmployee(EmployeeRequestDto employeeRequestDto) {
        UserModel userModel = this.employeeRequestMapper.toUserModel(employeeRequestDto);
        UserModel createdUserModel;
        try {
             createdUserModel = this.ownerServicePort.createEmployee(userModel);
        } catch (EmailAlreadyExistsException ex) {
            throw new DataAlreadyExistsException(ex.getMessage());
        } catch (RoleNotFoundException ex) {
            throw new NoDataFoundException(ex.getMessage());
        }

        return this.employeeResponseMapper.toDto(createdUserModel);
    }
}
