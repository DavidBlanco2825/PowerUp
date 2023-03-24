package com.pragma.powerup.application.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class OwnerRequestDto {
    @NotNull(message = "Please verify your input, your identification should not be empty.")
    private Long id;

    @NotBlank(message = "Please verify your input, your first name should not be empty.")
    @Size(min = 1, max = 16, message = "First Name field should be between 1 and 16 characters.")
    private String firstName;

    @NotBlank(message = "Please verify your input, your last name should not be empty.")
    @Size(min = 1, max = 16, message = "Last Name field should be between 1 and 16 characters.")
    private String lastName;

    @Pattern(regexp = "^\\+?\\d{1,13}$", message = "Please verify your input, the phone format is invalid.")
    @Size(min = 7, max = 13, message = "Phone number field should be between 7 and 13 characters.")
    @NotBlank(message = "Phone number field should not be empty.")
    private String phone;

    @NotBlank(message = "Email field should not be empty.")
    @Email(message = "Please verify your input, the email format is invalid.", regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
    private String email;

    @NotBlank(message = "Password should not be empty.")
    @Size(min = 4, max = 16, message = "Password field should be between 4 and 16 characters.")
    private String password;
}
