package com.mvc.app.data.constraint.validator;

import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.mvc.app.board.service.BoardService;
import com.mvc.app.data.BBSVO;
import com.mvc.app.data.constraint.UpdateBBSVOConstraint;

public class BBSUpdateValidator implements ConstraintValidator<UpdateBBSVOConstraint, BBSVO> {
	//영어를 한국어로 바꿔주는 맵
	private static final Map<String, String> stringConverter = new HashMap<String, String>();
	//각 문자열 항목을 담는 맵
	private static Map<String, String> stringMap = new HashMap<String, String>();
	
	@Autowired
	BoardService boardService;
	
	static {//stringConverter 초기화
		stringConverter.put("pass", "비밀번호");
		stringConverter.put("title", "제목");
		stringConverter.put("comment", "내용");
	}
	
	@Override
	public void initialize(UpdateBBSVOConstraint constraintAnnotation) {
	}

	@Override
	public boolean isValid(BBSVO vo, ConstraintValidatorContext context) {
		boolean isValid = true;
		
		context.disableDefaultConstraintViolation();
		
		//null검사
		if (vo == null) {
			isValid = isValid&&false;
			context.buildConstraintViolationWithTemplate("BBSVO가 Null이이면 안됩니다.")
			.addPropertyNode("BBSVO")
			.addConstraintViolation();
			return isValid;
		} 
		
		stringMap.put("pass", vo.getPass());
		stringMap.put("title", vo.getTitle());
		stringMap.put("comment", vo.getComment());
		
		//모든 문자열의 공백 검사
		for(String key : stringMap.keySet()) {
			if(!stringIsNotNullOrEmpty(stringMap.get(key))) {
				isValid = isValid&&false;
				context.buildConstraintViolationWithTemplate(
						"반드시 "+stringConverter.get(key)
					   +"의 값이 존재하고 공백 문자를 제외한 길이가 0보다 커야 합니다.")
				.addPropertyNode("BBSVO."+stringConverter.get(key))
				.addConstraintViolation();
			}
		}
		
		if(vo.getId()==null||vo.getId()<=0) {
			isValid = isValid&&false;
			context.buildConstraintViolationWithTemplate("반드시 id의 값이 존재하고 1 이상이어야 됩니다.")
			.addPropertyNode("BBSVO.아이디")
			.addConstraintViolation();
			return isValid;
		}
		
//		BBSVO dbvo = boardService.getOneBBS(String.valueOf(vo.getId()));
//		
//		if(dbvo == null) {
//			isValid = isValid&&false;
//			context.buildConstraintViolationWithTemplate("존재하지 않는 아이디입니다.")
//			.addPropertyNode("BBSVO.아이디")
//			.addConstraintViolation();
//			return isValid;
//		}
//		
//		if(dbvo.getPass().equals(vo.getPass())) {
//			isValid = isValid&&false;
//			context.buildConstraintViolationWithTemplate("패스워드가 일치하지 않습니다.")
//			.addPropertyNode("BBSVO.패스워드")
//			.addConstraintViolation();
//		}
		return isValid;
	}
	
	//blank나 null하지  않으면 true반환
	public boolean stringIsNotNullOrEmpty(String string) {
		if ( string == null ) return false;
		return string.toString().trim().length() > 0;
	}

}
