package com.mvc.app.board.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mvc.app.data.BBSVO;
import com.mvc.app.data.constraint.CreateConstraintGroup;
import com.mvc.app.data.constraint.validator.ValidationGroup;

@Controller
@RequestMapping("/testCon")
public class TestController {
//	@InitBinder
//	protected void initBinder(WebDataBinder binder) {
//		binder.setValidator(new SpringValidatorAdapterForHV());
//	}
	
	@RequestMapping("/argResolverTest")
	@ValidationGroup(CreateConstraintGroup.class)
	public ResponseEntity<Void> testResolver(
			@Valid
			BBSVO vo, 
			//BindingResult result
			Errors errors){
//		System.out.println(result.hasErrors());
//		for (FieldError fieldError : result.getFieldErrors()) {
//			System.out.println(fieldError.getDefaultMessage());
//		}
//		System.out.println(result.getAllErrors());
		System.out.println("hello");
		System.out.println("has error? : "+errors.hasErrors());
		for ( ObjectError error : errors.getAllErrors()) {
			System.out.println(error.getDefaultMessage());
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
