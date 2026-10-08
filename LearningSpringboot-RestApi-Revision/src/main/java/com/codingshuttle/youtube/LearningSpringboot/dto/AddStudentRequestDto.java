package com.codingshuttle.youtube.LearningSpringboot.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AddStudentRequestDto {
    
        @NotBlank(message = "name is required.")
        @Size(min = 3, max = 30, message = "The name field should consist 3 to 30 character.")
        private String name;
        
        @Email(message = "Please provide a valid email address.")
        @NotBlank(message = "Email field is required.")
        @Pattern(
                regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
                message = "Email must contain a valid domain extension."
        )
        private String email;
}

