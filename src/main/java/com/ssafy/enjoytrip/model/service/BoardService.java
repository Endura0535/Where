package com.ssafy.enjoytrip.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.enjoytrip.model.dto.Board;
import com.ssafy.enjoytrip.util.PageNavigation;

public interface BoardService {

	void writeArticle(Board boardDto) throws Exception;
	List<Board> listArticle(Map<String, String> map) throws Exception;
	PageNavigation makePageNavigation(Map<String, String> map) throws Exception;
	Board getArticle(int articleNo) throws Exception;
	void updateHit(int articleNo) throws Exception;
	
	void modifyArticle(Board boardDto) throws Exception;
	void deleteArticle(int articleNo) throws Exception;
	
}
