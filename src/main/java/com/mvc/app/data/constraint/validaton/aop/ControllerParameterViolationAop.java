package com.mvc.app.data.constraint.validaton.aop;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.mvc.app.data.BBSVO;
import com.mvc.app.data.constraint.CreateConstraintGroup;
import com.mvc.app.data.constraint.DeleteConstraintGroup;
import com.mvc.app.data.constraint.UpdateConstraintGroup;
import com.mvc.app.data.constraint.validator.ValidatorInstance;

@Aspect
@Component
public class ControllerParameterViolationAop {

	@Autowired
	ValidatorInstance validatorInstance;
	//BBS를 생성하는 컨트롤러의 파라미터로 오는 VO를 검증한다.
	@Around("execution(* com..controller.*Controller.insertBBS(..)))")
	public void createValidation(ProceedingJoinPoint joinPoint) throws Throwable {
		BBSValidation(joinPoint,CreateConstraintGroup.class);
	}
	
	@Around("execution(* com..controller.*Controller.updateBBS(..))")
	public void updateValidation(ProceedingJoinPoint joinPoint) throws Throwable {
		BBSValidation(joinPoint,UpdateConstraintGroup.class);
	}
	
	@Around("execution(* com..controller.*Controller.deleteBBS(..))")
	public void deleteValidation(ProceedingJoinPoint joinPoint) throws Throwable {
		BBSValidation(joinPoint,DeleteConstraintGroup.class);
	}
	
	public void BBSValidation(ProceedingJoinPoint joinPoint, Class<?> group) throws Throwable {
		// 검증기를 가져온다.
		Validator validator = validatorInstance.getValidator();
		// 컨트롤러를 실행할때 쓰일 파라미터
		Object[] objs = joinPoint.getArgs();

		// 받아올 파라미터들
		// 제약 위반 여부
		Boolean violationBool = true;
		// Model이 파라미터에 있을 경우 가져올 모델
		Model model = null;
		// 검증할 VO객체
		BBSVO vo = null;

		// 컨트롤러의 파라미터들을 가져온다.
		for (Object obj : joinPoint.getArgs()) {
			System.out.println(obj);
			if (obj instanceof Model) {
				model = (Model) obj;
			} else if (obj instanceof BBSVO) {
				vo = (BBSVO) obj;
			} else if (obj == null) {
				violationBool = true;
			}
		}
		
		System.out.println("init boolean : "+violationBool);

		// 결과를 넣을 Model이나 boolean이 둘 다 없거나 검증할 vo가 없으면 검증이 불가능하다.
		if (vo == null) {
			System.out.println("Bad request!");
			joinPoint.proceed();
			return;
		}
		// vo를 검증
		Set<ConstraintViolation<BBSVO>> violations = validator.validate(vo, group);

		// 모델이 있을 경우 모델에 제약 위반 메시지를 넣는다.
		// [propertyPath]Error를 속성 명으로 넣고
		// 에러 메시지를 속성으로 넣는다.
		//if (model != null) {
			for (ConstraintViolation<BBSVO> violation : violations) {
				//model.addAttribute(violation.getPropertyPath().toString() + "Error", violation.getMessage());
				System.out.println("propertyPath : " + violation.getPropertyPath().toString());
				System.out.println("message : " + violation.getMessage());
			}
		//}
		
			
			//System.out.println("set : "+violations);

		if (violations.isEmpty()) {
			violationBool = false;
		} else {
			violationBool = true;
		}
		
		for(int i=0; i<objs.length; i++) {
			if (objs[i] instanceof Model) {
				objs[i] = model;
			} else if (objs[i] instanceof BBSVO) {
				objs[i] = vo;
			} else if (objs[i] == null) {
				objs[i] = violationBool;
			}
		}
		
		//System.out.println("last boolean : "+violationBool);
		
		joinPoint.proceed(objs);
	}
	
}
