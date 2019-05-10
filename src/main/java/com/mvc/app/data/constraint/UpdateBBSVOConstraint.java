package com.mvc.app.data.constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.mvc.app.data.constraint.validator.BBSUpdateValidator;

@Target({ElementType.TYPE,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {BBSUpdateValidator.class})
@Documented
public @interface UpdateBBSVOConstraint {
	String message() default "{com.mvc.app.data.constraint.UpdateBBSVOConstraint.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
