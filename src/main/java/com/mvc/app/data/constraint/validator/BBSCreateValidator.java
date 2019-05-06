package com.mvc.app.data.constraint.validator;

import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;

import com.mvc.app.data.BBSVO;
import com.mvc.app.data.constraint.CreateBBSVOConstraint;

public class BBSCreateValidator implements ConstraintValidator<CreateBBSVOConstraint, BBSVO> {
	//Hibernate Validator에서 구현한 이메일 검증기
	private static final EmailValidator emailValidator = new EmailValidator();
	//영어를 한국어로 바꿔주는 맵
	private static final Map<String, String> stringConverter = new HashMap<String, String>();
	//각 문자열 항목을 담는 맵
	private static Map<String, String> stringMap = new HashMap<String, String>();
	
	static {//stringConverter 초기화
		stringConverter.put("name", "이름");
		stringConverter.put("email", "이메일");
		stringConverter.put("pass", "비밀번호");
		stringConverter.put("title", "제목");
		stringConverter.put("comment", "내용");
	}
	
	@Override
	public void initialize(CreateBBSVOConstraint constraintAnnotation) {
	}

	@Override
	public boolean isValid(BBSVO vo, ConstraintValidatorContext context) {
		boolean isValid = true;
		String notValidString = "";
		
		//null검사
		if (vo == null) {
			isValid = isValid&&false;
			notValidString+="BBSVO가 Null이이면 안됩니다.\n";
			return isValid;
		} 
		
		stringMap.put("name", vo.getName());
		stringMap.put("email", vo.getEmail());
		stringMap.put("pass", vo.getPass());
		stringMap.put("title", vo.getTitle());
		stringMap.put("comment", vo.getComment());
		
		//모든 문자열의 공백 검사
		for(String key : stringMap.keySet()) {
			if(!stringIsNotNullOrEmpty(stringMap.get(key))) {
				isValid = isValid&&false;
				notValidString+="반드시 "+stringConverter.get(key)
				+"의 값이 존재하고 공백 문자를 제외한 길이가 0보다 커야 합니다.\n";
			}
		}
		//email 형식 검사
		String email = stringMap.get("email");
		if (stringIsNotNullOrEmpty(email)&&!emailValidator.isValid(email, context)) {
			isValid = isValid&&false;
			notValidString+="이메일이 형식에 맞지 않습니다.\n";
		} 
		
		notValidString = notValidString.replaceAll("\n$", "");
		
		//메시지 기입
		if(!isValid) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(notValidString+"")
			.addConstraintViolation();
		}
		return isValid;
	}
	
	//blank나 null하지  않으면 true반환
	public boolean stringIsNotNullOrEmpty(String string) {
		if ( string == null ) return false;
		return string.toString().trim().length() > 0;
	}

}
