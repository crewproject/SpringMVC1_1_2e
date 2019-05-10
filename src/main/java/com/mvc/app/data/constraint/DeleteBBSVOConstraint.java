package com.mvc.app.data.constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.mvc.app.data.constraint.validator.BBSDeleteValidator;

@Target({ElementType.TYPE,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {BBSDeleteValidator.class})
@Documented
public @interface DeleteBBSVOConstraint {
	String message() default "{com.mvc.app.data.constraint.DeleteBBSVOConstraint.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
