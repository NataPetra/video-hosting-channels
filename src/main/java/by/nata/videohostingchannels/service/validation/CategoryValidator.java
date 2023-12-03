package by.nata.videohostingchannels.service.validation;

import by.nata.videohostingchannels.service.CategoryService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CategoryValidator implements ConstraintValidator<CategoryValidation, Long> {

    private final CategoryService categoryService;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return categoryService.isCategoryExist(value);
    }
}
