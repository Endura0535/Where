package com.ssafy.enjoytrip.model.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.enjoytrip.model.dto.Board;

public interface BoardDao {

	void writeArticle(Board boardDto) throws SQLException;
	List<Board> listArticle(Map<String, Object> param) throws SQLException;
	int getTotalArticleCount(Map<String, Object> param) throws SQLException;
	Board getArticle(int articleNo) throws SQLException;
	void updateHit(int articleNo) throws SQLException;
	
	void modifyArticle(Board boardDto) throws SQLException;
	void deleteArticle(int articleNO) throws SQLException;
	
}
