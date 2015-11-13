package org.springframework.format.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.mask.MaskFormatterValidation;

@Documented
@Constraint(validatedBy = { MaskFormatterValidation.class })
@Target(value = { ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface MaskFormat {
	
	String value();

	String message() default "{org.springframework.format.annotation.MaskFormat.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
