package com.mvc.app.data.constraint.validaton.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerParameterViolationAop {
	
	@Around("execution(String com..controller.*Controller.insertBBS(..))")
	public void createBBSValidation(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("hello");
		Object[] objs = joinPoint.getArgs();
		for(Object obj : objs) {
			System.out.println(obj);
		}
		joinPoint.proceed();
		
	}
}
