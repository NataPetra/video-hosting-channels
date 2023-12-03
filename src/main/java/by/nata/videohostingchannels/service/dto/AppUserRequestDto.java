package by.nata.videohostingchannels.service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

public record AppUserRequestDto(
        @NotBlank(message = "Login must not be null or empty")
        @Length(max = 30, message = "Login should be no longer than {max} characters")
        String login,
        @NotBlank(message = "Name must not be null or empty")
        @Length(max = 40, message = "Name should be no longer than {max} characters")
        String name,
        @NotBlank(message = "Email must not be null or empty")
        @Email
        @Length(max = 50, message = "Email should be no longer than {max} characters")
        String email) implements Serializable {
}
