package com.example.restapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AddStudentRequestDto {

        @NotBlank(message = "Name is required")
        @Size(min = 3, max = 30, message = "Name length should be 3 to 30 character")
        private String name;

        @Email(message = "Invalid Email address")
        @NotBlank(message = "Email is required")
        private String email;
}

