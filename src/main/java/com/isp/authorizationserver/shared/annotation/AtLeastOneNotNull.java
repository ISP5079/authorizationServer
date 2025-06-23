package com.isp.authorizationserver.shared.annotation;

import com.isp.authorizationserver.shared.annotation.validated.AtLeastOneNotNullVal;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AtLeastOneNotNullVal.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AtLeastOneNotNull {
    String message() default "At least one field must be not null";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String field1();

    String field2();
}
