package com.ssafy.enjoytrip;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ssafy.enjoytrip.model.dao.BoardDao;
import com.ssafy.enjoytrip.model.dao.UserDao;
import com.ssafy.enjoytrip.model.dto.Board;
import com.ssafy.enjoytrip.model.dto.User;
import com.ssafy.enjoytrip.model.service.UserService;

@SpringBootTest
class BoardTest {
	private final Logger logger = LoggerFactory.getLogger(BoardTest.class);

	@Test
	void contextLoads() {
	}

	@Autowired
	private BoardDao dao;

	@Autowired
	private UserService service;

	@Autowired
	private DataSource datasource;

	@Autowired
	private SqlSession session;

	@Test
	public void listTest() throws Exception {
		List<Board> boards = dao.listArticle(null);
		assertNotNull(boards);
		logger.debug("userDto : {}", boards);
	}


}
