package com.mvc.app.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mvc.app.board.service.BoardService;
import com.mvc.app.data.BBSVO;
import com.mvc.app.data.SearchType;
import com.mvc.app.data.constraint.CreateConstraintGroup;
import com.mvc.app.data.constraint.DeleteConstraintGroup;
import com.mvc.app.data.constraint.UpdateConstraintGroup;
import com.mvc.app.data.constraint.validator.ValidationGroup;

@RestController
@RequestMapping("/rest")
public class HTTPAPIController {

	@Autowired
	BoardService boardService;

	@PostMapping("/newbbs")
	@ValidationGroup(CreateConstraintGroup.class)
	public ResponseEntity<Void> insertBBS(
			@ModelAttribute BBSVO vo,
			HttpServletRequest request,
			Errors errors
			){
		if(errors.hasErrors()) {
			System.out.println("Violation!!!!");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		vo.setIp(request.getRemoteAddr());
		boardService.createBBS(vo);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/list")
	public List<Map<String, ?>> getList() {
		List<Map<String, ?>> bbsList = boardService.getListBBS();
		return bbsList;
	}

	@GetMapping("/list/{SearchType}/{text}")
	public <T> ResponseEntity<Map<String, Object>> getSearchList(@PathVariable("SearchType") String inputSearchType,
			@PathVariable("text") String text) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		boolean equals = false;

		resultMap.put("searchType", inputSearchType);
		resultMap.put("text", text);

		for (SearchType types : SearchType.values()) {
			if (inputSearchType.equals(types)) {
				equals = true;
			}
		}

		if (!equals)
			return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);

		List<Map<String, ?>> bbsList = boardService.getSearchListBBS(SearchType.valueOf(inputSearchType), text);

		resultMap.put("list", bbsList);
		return new ResponseEntity<>(resultMap, HttpStatus.OK);
	}

	@GetMapping("/bbs/{id}")
	public ResponseEntity<BBSVO> getOneBBS(@PathVariable int id) {
		BBSVO vo = boardService.getOneBBS(String.valueOf(id));
		if (vo == null)
			return new ResponseEntity<BBSVO>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<BBSVO>(vo, HttpStatus.OK);
	}

	@PatchMapping("/bbs")
	@ValidationGroup(UpdateConstraintGroup.class)
	public ResponseEntity<Void> updateBBS(
			BBSVO vo,
			Errors errors
			) {
		if(!errors.hasErrors()) boardService.modifyBBS(vo);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@DeleteMapping("/bbs")
	@ValidationGroup(DeleteConstraintGroup.class)
	public ResponseEntity<Void> deleteBBS(
			BBSVO vo,
			Errors errors,
			BindingResult result
			) {
		if(!errors.hasErrors())boardService.removeBBS(String.valueOf(vo.getId()));
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
