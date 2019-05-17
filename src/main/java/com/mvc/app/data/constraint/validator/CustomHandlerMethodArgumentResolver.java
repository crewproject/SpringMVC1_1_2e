package com.mvc.app.data.constraint.validator;

import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.mvc.app.data.BBSVO;

public class CustomHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver{
	
	public Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
	BBSVO vo;
	
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		boolean setBool;
		boolean voBool;
		
		setBool = Set.class.isAssignableFrom(parameter.getParameterType());
		voBool = BBSVO.class.isAssignableFrom(parameter.getParameterType());
		return setBool || voBool;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		boolean setBool;
		boolean voBool;
		
		setBool = Set.class.isAssignableFrom(parameter.getParameterType());
		voBool = BBSVO.class.isAssignableFrom(parameter.getParameterType());
		System.out.println(parameter.clone());
		if(setBool)
			return new HashSet<ConstraintViolation<BBSVO>>();
		else if (voBool) System.out.println(parameter);
		
		return null;
	}

}
