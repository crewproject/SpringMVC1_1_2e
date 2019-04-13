package com.spring.app.model;

import java.util.List;


import com.spring.app.vo.BoardVO;

public interface BoardDAO {
	public List<BoardVO> selectAllBoard();

}
