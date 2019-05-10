package com.mvc.app.data.constraint.validator;

import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.stereotype.Component;

@Component
public class ValidatorInstance {
	private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	public Validator getValidator() {
		return validator;
	}

	public void setValidator(Validator validator) {
		this.validator = validator;
	}
	
}
