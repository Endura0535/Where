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

import com.ssafy.enjoytrip.model.dao.UserDao;
import com.ssafy.enjoytrip.model.dto.User;
import com.ssafy.enjoytrip.model.service.UserService;

@SpringBootTest
class UserTest {
	private final Logger logger = LoggerFactory.getLogger(UserTest.class);

	@Test
	void contextLoads() {
	}

	@Autowired
	private UserDao dao;

	@Autowired
	private UserService service;

	@Autowired
	private DataSource datasource;

	@Autowired
	private SqlSession session;

	@Test
	public void login() throws Exception {
		User user = service.login("ssafy2", "ssafy2");
		assertNotNull(user);
		logger.debug("userDto : {}", user);
	}


}
