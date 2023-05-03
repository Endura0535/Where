package com.ssafy.enjoytrip.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.enjoytrip.model.dto.Board;
import com.ssafy.enjoytrip.model.dto.PageBean;
import com.ssafy.enjoytrip.util.PageNavigation;

public interface BoardService {

	void writeArticle(Board boardDto) throws Exception;

	List<Board> listArticle(PageBean bean) throws Exception;

	Board getArticle(int articleNo) throws Exception;

	void updateHit(int articleNo) throws Exception;

	void modifyArticle(Board boardDto) throws Exception;

	void deleteArticle(int articleNo) throws Exception;

}
