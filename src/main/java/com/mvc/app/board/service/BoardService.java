package com.mvc.app.board.service;

import java.util.List;
import java.util.Map;

import com.mvc.app.data.*;

public interface BoardService {
	public List<Map<String, Object>> test();
	
	//dao를 통해 bbs를 받아온다.
	public BBSVO getOneBBS(String id);

	//dao를 통해 bbs목록을를 받아온다.
	public List<BBSVO> getListBBS();
	
	//dao를 통해 name 또는 title 또는 comment로 검색한다.
	public List<BBSVO> getSearchListBBS(SearchType searchType, String text);
	
	//dao를 통해 bbs를 추가한다.
	public void createBBS(BBSVO vo);
	
	//dao를 통해 bbs를 제거한다.
	public void removeBBS(String id);
	
	//bbs를 통해 bbs를 수정한다.
	public void modifyBBS(BBSVO vo);
}
