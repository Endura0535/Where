package com.ssafy.enjoytrip;

import static org.junit.Assert.assertNotNull;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EnjoytripApplicationTests {
	private final Logger logger = LoggerFactory.getLogger(EnjoytripApplicationTests.class);
	
	
	@Test
	void contextLoads() {
	}
	
//	@Autowired
//	private UserDao dao;
	
	
	@Autowired
	private DataSource datasource; 
	
	@Autowired
	private SqlSession session; 
	
	
	@Test
	public void dataSourceTest() throws Exception{
		logger.debug("datasource :{}",datasource);
		assertNotNull(datasource);
		
	}
	@Test
	public void daoTest() throws Exception{
		logger.debug("session :{}",session);
		assertNotNull(session);

	}
//	@Test
//	public void daoTest() throws Exception{
//		logger.debug("dao :{}",dao);
//		assertNotNull(dao);
//
//	}
//	
//	@Test
//	public void searchAllTest() throws Exception{
//		User user = dao.search("hi");
//		assertNotNull(user);
//		logger.debug("books size : {}", user);
//	}

}
