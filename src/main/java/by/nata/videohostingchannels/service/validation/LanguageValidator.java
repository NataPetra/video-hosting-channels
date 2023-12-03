package by.nata.videohostingchannels.service.validation;

import by.nata.videohostingchannels.service.LanguageService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LanguageValidator implements ConstraintValidator<LanguageValidation, Long> {

    private final LanguageService languageService;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return languageService.isLanguageExist(value);
    }
}
