package com.spring.app.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.app.model.BoardDAO;
import com.spring.app.vo.BoardVO;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	SqlSessionTemplate sqlSession;
	BoardDAO boardDAO;
	
	public void setBoardDAO() {
		this.boardDAO = sqlSession.getMapper(BoardDAO.class);
	}



	@Override
	public List<BoardVO> getAllBoard() {
		// TODO Auto-generated method stub
		setBoardDAO();
		return boardDAO.selectAllBoard();
	}

}
