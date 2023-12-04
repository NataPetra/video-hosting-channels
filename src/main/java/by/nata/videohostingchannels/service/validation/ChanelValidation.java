package by.nata.videohostingchannels.service.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;

@Target({FIELD, PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ChanelValidator.class)
public @interface ChanelValidation {

    String message() default "Invalid id: chanel not found";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
