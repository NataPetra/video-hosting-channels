package by.nata.videohostingchannels.service.dto;

import by.nata.videohostingchannels.service.validation.AppUserValidation;
import by.nata.videohostingchannels.service.validation.CategoryValidation;
import by.nata.videohostingchannels.service.validation.LanguageValidation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public record ChanelRequestDto(
        @NotBlank(message = "Name must not be null or empty")
        String name,
        @NotBlank(message = "Description must not be null or empty")
        String description,
        @AppUserValidation
        @NotNull(message = "AuthorId must not be null")
        Long authorId,
        @LanguageValidation
        @NotNull(message = "LanguageId must not be null")
        Long languageId,
        @NotBlank(message = "Image must not be null or empty")
        String image,
        @CategoryValidation
        @NotNull(message = "CategoryId must not be null")
        Long categoryId) implements Serializable {
}
