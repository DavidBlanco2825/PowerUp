package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.OwnerRequestDto;
import com.pragma.powerup.application.dto.response.OwnerResponseDto;
import com.pragma.powerup.application.handler.IAdminHandler;
import com.pragma.powerup.application.handler.IUserHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users/v1/admin")
@RequiredArgsConstructor
public class AdminRestController {
    private final IAdminHandler adminHandler;
    private final IUserHandler userHandler;

    @Operation(summary = "Creates restaurant owner")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Restaurant Owner created", content = @Content),
            @ApiResponse(responseCode = "404", description = "Restaurant Owner not found", content = @Content),
            @ApiResponse(responseCode = "409", description = "Restaurant Owner already exists", content = @Content)
    })
    @PostMapping("/create-owner")
    public ResponseEntity<Void> createOwner(@RequestBody @Valid OwnerRequestDto ownerRequestDto) {
        this.adminHandler.createRestaurantOwner(ownerRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Search a restaurant owner by its email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Restaurant owner found", content = @Content),
            @ApiResponse(responseCode = "404", description = "Restaurant owner not found", content = @Content)
    })
    @GetMapping("/restaurant-owner")
    public ResponseEntity<OwnerResponseDto> getOwnerUser(@RequestParam("email") String email) {
        return new ResponseEntity<>(this.userHandler.getRestaurantOwner(email), HttpStatus.OK);
    }

//    @GetMapping("/try")
//    public ResponseEntity<Void> trying() {
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}
