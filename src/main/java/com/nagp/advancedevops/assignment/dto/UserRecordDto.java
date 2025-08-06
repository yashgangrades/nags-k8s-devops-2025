package com.nagp.advancedevops.assignment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name = "UserRecord",
        description = "Schema to hold user information for the NAGP 2025 assignment"
)
public class UserRecordDto {

    @Schema(
            description = "Full name of the user",
            example = "Alice Johnson"
    )
    @NotEmpty(message = "Name cannot be null or empty")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    @Schema(
            description = "Professional description or job title",
            example = "Senior Software Engineer - Java + Kubernetes"
    )
    @NotEmpty(message = "Description cannot be null or empty")
    @Size(min = 10, max = 100, message = "Description must be between 10 and 100 characters")
    private String description;

    @Schema(
            description = "Email address of the user",
            example = "alice.johnson@example.com"
    )
    @NotEmpty(message = "Email cannot be null or empty")
    @Email(message = "Must be a valid email address")
    private String email;

    @Schema(
            description = "Mobile number of the user",
            example = "9876543210"
    )
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be exactly 10 digits")
    private String mobileNumber;
}


