package com.mvc.app.data.constraint.validaton.aop;

import java.lang.reflect.Method;

import javax.validation.Validation;
import javax.validation.Validator;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import com.mvc.app.data.BBSVO;
import com.mvc.app.data.constraint.validator.ValidationGroup;

@Aspect
@Component
public class ControllerParameterViolationAop {

	Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	SpringValidatorAdapter adapter = new SpringValidatorAdapter(validator);

	@Around("execution(* com.mvc.app.board.controller.*.*(..))")
	public void testValidationAdvice(ProceedingJoinPoint joinPoint) {
		testValidation(joinPoint);
	}



	public void testValidation(ProceedingJoinPoint joinPoint) {
		// 검증할 객체
		BBSVO vo = null;
		// 검증한 결과를 반환할 값들
		Errors errors = null;
		BindingResult result = null;
		Class<?>[] groups = null;
		// 파라미터 가져오기
		Object[] parameters = joinPoint.getArgs();
		for (Object obj : parameters) {
			if (obj == null) {
				continue;
			} else if (obj instanceof Errors) {
				errors = (Errors) obj;
			} else if (obj instanceof BBSVO) {
				vo = (BBSVO) obj;
			} else if (obj instanceof BindingResult) {
				result = (BindingResult) obj;
			}
		}

		// 그룹 찾기
		try {
			groups = getValidationGroups(joinPoint);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// VO와 Errors null검사
		try {
			if (vo == null) {
				System.out.println("vo is null");
				joinPoint.proceed();
				return;
			} else if (errors == null) {
				System.out.println("errors is null");
			} else if (groups != null) {
				adapter.validate(vo, errors, groups);
			} else {
				adapter.validate(vo, errors);
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		
		//Erorrs와 BindingReulst가 존재할 경우 BindingReulst에 Errors의 값을 넣어준다.
		if (result != null && errors != null) {
			result.addAllErrors(errors);
		}

			// 메소드 실행
			try {
				joinPoint.proceed();
			} catch (Throwable e3) {
				e3.printStackTrace();
			}

	}

	private Class<?>[] getValidationGroups(ProceedingJoinPoint joinPoint) throws Exception {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();

		Method method = null;
		ValidationGroup validationGroup = null;
		String methodName = signature.getMethod().getName(); // 메소드 이름
		Class<?>[] methodParameterTypes = signature.getMethod().getParameterTypes(); // 메소드 파라미터타입들
		method = joinPoint.getTarget().getClass().getMethod(
				methodName,
				methodParameterTypes
				);

		if (method != null)
			validationGroup = method.getAnnotation(ValidationGroup.class);
		if (validationGroup != null) {
			return validationGroup.value();
		}
		return null;
	}
// BBS를 생성하는 컨트롤러의 파라미터로 오는 VO를 검증한다.
//	@Around("execution(* insertBBS(..))")
//	public void createValidation(ProceedingJoinPoint joinPoint) throws Throwable {
//		BBSValidation(joinPoint, CreateConstraintGroup.class);
//	}
//
//	@Around("execution(* updateBBS(..))")
//	public void updateValidation(ProceedingJoinPoint joinPoint) throws Throwable {
//		BBSValidation(joinPoint, UpdateConstraintGroup.class);
//	}
//
//	@Around("execution(* deleteBBS(..))")
//	public void deleteValidation(ProceedingJoinPoint joinPoint) throws Throwable {
//		BBSValidation(joinPoint, DeleteConstraintGroup.class);
//	}
//
//	public void BBSValidation(ProceedingJoinPoint joinPoint, Class<?> group) throws Throwable {
//
//		// 컨트롤러를 실행할때 쓰일 파라미터
//		Object[] objs = joinPoint.getArgs();
//
//		// 받아올 파라미터들
//		// 제약 위반 여부
//		Boolean violationBool = true;
//		// Model이 파라미터에 있을 경우 가져올 모델
//		Model model = null;
//		// 검증할 VO객체
//		BBSVO vo = null;
//
//		Set<ConstraintViolation<BBSVO>> violations;
//
//		// 컨트롤러의 파라미터들을 가져온다.
//		for (Object obj : joinPoint.getArgs()) {
//			System.out.println(obj);
//			if (obj instanceof Model) {
//				model = (Model) obj;
//			} else if (obj instanceof BBSVO) {
//				vo = (BBSVO) obj;
//			} else if (obj instanceof Set) {
//				violations = (Set<ConstraintViolation<BBSVO>>) obj;
//			} else if (obj == null) {
//				violationBool = true;
//			}
//		}
//
//		System.out.println("init boolean : " + violationBool);
//
//		// 결과를 넣을 Model이나 boolean이 둘 다 없거나 검증할 vo가 없으면 검증이 불가능하다.
//		if (vo == null) {
//			System.out.println("Bad request!");
//			joinPoint.proceed();
//			return;
//		}
//		// vo를 검증
//		violations = validator.validate(vo, group);
//
//		// 모델이 있을 경우 모델에 제약 위반 메시지를 넣는다.
//		// [propertyPath]Error를 속성 명으로 넣고
//		// 에러 메시지를 속성으로 넣는다.
//		// if (model != null) {
//		for (ConstraintViolation<BBSVO> violation : violations) {
//			// model.addAttribute(violation.getPropertyPath().toString() + "Error",
//			// violation.getMessage());
//			System.out.println("propertyPath : " + violation.getPropertyPath().toString());
//			System.out.println("message : " + violation.getMessage());
//		}
//		// }
//
//		// System.out.println("set : "+violations);
//
//		if (violations.isEmpty()) {
//			violationBool = false;
//		} else {
//			violationBool = true;
//		}
//
//		for (int i = 0; i < objs.length; i++) {
//			if (objs[i] instanceof Model) {
//				objs[i] = model;
//			} else if (objs[i] instanceof BBSVO) {
//				objs[i] = vo;
//			} else if (objs[i] == null) {
//				objs[i] = violationBool;
//			}
//		}
//
//		// System.out.println("last boolean : "+violationBool);
//
//		joinPoint.proceed(objs);
//	}

}
