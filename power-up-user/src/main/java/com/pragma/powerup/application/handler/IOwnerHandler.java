package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.EmployeeRequestDto;
import com.pragma.powerup.application.dto.response.EmployeeResponseDto;

public interface IOwnerHandler {
    EmployeeResponseDto createEmployee(EmployeeRequestDto employeeRequestDto);
}
