package com.mvc.app.board.controller;

import javax.validation.Valid;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mvc.app.data.BBSVO;
import com.mvc.app.data.constraint.UpdateConstraintGroup;

@Controller
@RequestMapping("/testCon")
public class TestController {
//	@InitBinder
//	protected void initBinder(WebDataBinder binder) {
//		binder.setValidator(new SpringValidatorAdapterForHV());
//	}
	
	@RequestMapping("/argResolverTest")
	public ResponseEntity<Void> testResolver(
			@Valid
			BBSVO vo, 
			BindingResult result){
		System.out.println(result.hasErrors());
		for (FieldError fieldError : result.getFieldErrors()) {
			System.out.println(fieldError.getDefaultMessage());
		}
		System.out.println(result.getAllErrors());
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
