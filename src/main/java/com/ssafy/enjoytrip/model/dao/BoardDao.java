package com.ssafy.enjoytrip.model.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.enjoytrip.model.dto.Board;
import com.ssafy.enjoytrip.model.dto.PageBean;

public interface BoardDao {

	void writeArticle(Board boardDto) throws SQLException;
	List<Board> listArticle(PageBean bean) throws SQLException;
	int totalCount(PageBean bean) throws SQLException;
	Board getArticle(int articleNo) throws SQLException;
	void updateHit(int articleNo) throws SQLException;
	
	void modifyArticle(Board boardDto) throws SQLException;
	void deleteArticle(int articleNO) throws SQLException;
	
}
