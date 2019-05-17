package com.mvc.app.data.constraint.validator;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;

import org.springframework.validation.Errors;

import com.mvc.app.data.BBSVO;

public class SpringValidatorAdapterForHV implements  org.springframework.validation.Validator {
	
	javax.validation.Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
	
	@Override
	public boolean supports(Class<?> clazz) {
		return BBSVO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(target.getClass() == BBSVO.class.getClass()) {
			BBSVO vo = (BBSVO) target;
			Set<ConstraintViolation<BBSVO>> violations = validator.validate(vo);
			for (ConstraintViolation<BBSVO> constraintViolation : violations) {
				errors.reject(constraintViolation.getPropertyPath()+" : "+constraintViolation.getMessage());
			}
			
		}
		
	}

}
