package by.nata.videohostingchannels.service.validation;

import by.nata.videohostingchannels.service.AppUserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AppUserValidator implements ConstraintValidator<AppUserValidation, Long> {

    private final AppUserService appUserService;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return appUserService.isAppUserExist(value);
    }
}
