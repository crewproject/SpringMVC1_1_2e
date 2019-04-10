package com.mvc.app.board.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import com.mvc.app.board.dao.BoardDAO;
import com.mvc.app.data.BBSVO;
import com.mvc.app.data.SearchType;

@Service
public class BoardServiceImpl implements BoardService {

	@Inject
	SqlSessionTemplate sqlSession;
	BoardDAO boardDAO;
	
	public void setBoardDAO() {
		this.boardDAO = sqlSession.getMapper(BoardDAO.class);
		System.out.println("setBoardDAO : " + boardDAO);
	}

	@Override
	public List<Map<String, Object>> test() {
		setBoardDAO();
		return boardDAO.test();
	}

	@Override
	public BBSVO getOneBBS(String id) {
		setBoardDAO();
		boardDAO.updateSee(id);
		return boardDAO.selectOneBBS(id);

	}

	@Override
	public List<BBSVO> getListBBS() {
		setBoardDAO();
		return boardDAO.selectListBBS();
	}

	@Override
	public void createBBS(BBSVO vo) {
		setBoardDAO();
		boardDAO.insertBBS(vo);
		;
	}

	@Override
	public void removeBBS(String id) {
		setBoardDAO();
		boardDAO.deleteBBS(id);
	}

	@Override
	public void modifyBBS(BBSVO vo) {
		setBoardDAO();
		boardDAO.updateBBS(vo);
	}

	@Override
	public List<BBSVO> getSearchListBBS(SearchType searchType, String text) {
		setBoardDAO();
		return boardDAO.selectSearchListBBS(searchType, text);
	}
	
	

}
