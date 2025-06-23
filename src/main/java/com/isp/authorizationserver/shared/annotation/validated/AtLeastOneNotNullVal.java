package com.isp.authorizationserver.shared.annotation.validated;

import com.isp.authorizationserver.shared.annotation.AtLeastOneNotNull;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;

public class AtLeastOneNotNullVal implements ConstraintValidator<AtLeastOneNotNull, Object> {

    private String field1;
    private String field2;

    @Override
    public void initialize(AtLeastOneNotNull constraintAnnotation) {
        this.field1 = constraintAnnotation.field1();
        this.field2 = constraintAnnotation.field2();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        try {
            Object v1 = null;
            Object v2 = null;

            for (PropertyDescriptor pd : Introspector.getBeanInfo(value.getClass()).getPropertyDescriptors()) {
                if (pd.getName().equals(field1)) {
                    v1 = pd.getReadMethod().invoke(value);
                } else if (pd.getName().equals(field2)) {
                    v2 = pd.getReadMethod().invoke(value);
                }
            }

            //Ejemplo obsoleto a partir de java 17
            /*Field f1 = value.getClass().getDeclaredField(field1);
            Field f2 = value.getClass().getDeclaredField(field2);

            f1.setAccessible(true);
            f2.setAccessible(true);

            Object v1 = f1.get(value);
            Object v2 = f2.get(value);*/
            if (v1 != null || v2 != null)
                return true;

            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(String.format("At least one of the fields '%s' or '%s' must be not null", field1, field2)).addConstraintViolation();
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}
