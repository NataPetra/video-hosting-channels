package by.nata.videohostingchannels.service.validation;

import by.nata.videohostingchannels.service.ChanelService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ChanelValidator implements ConstraintValidator<ChanelValidation, Long> {

    private final ChanelService chanelService;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return chanelService.isChanelExist(value);
    }
}
