package com.mvc.app.data.constraint.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.mvc.app.board.service.BoardService;
import com.mvc.app.data.BBSVO;
import com.mvc.app.data.constraint.DeleteBBSVOConstraint;

public class BBSDeleteValidator implements ConstraintValidator<DeleteBBSVOConstraint, BBSVO> {
	
	@Autowired
	BoardService boardService;
	
	@Override
	public void initialize(DeleteBBSVOConstraint constraintAnnotation) {
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
		
		if(vo.getId()==null||vo.getId()<=0) {
			isValid = isValid&&false;
			context.buildConstraintViolationWithTemplate("반드시 아이디의 값이 존재하고 1 이상이어야 됩니다.")
			.addPropertyNode("BBSVO.아이디")
			.addConstraintViolation();
			return isValid;
		}
		System.out.println("id : "+vo.getId());
		System.out.println("boardService : "+boardService);
		
		
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

}
