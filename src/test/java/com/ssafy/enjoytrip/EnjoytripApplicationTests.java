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

import com.ssafy.enjoytrip.model.dao.TripDao;
import com.ssafy.enjoytrip.model.dto.AreaCodeDto;
import com.ssafy.enjoytrip.model.dto.AttractionDto;

@SpringBootTest
class EnjoytripApplicationTests {
	private final Logger logger = LoggerFactory.getLogger(EnjoytripApplicationTests.class);
	
	
	@Test
	void contextLoads() {
	}
	
//	@Autowired
//	private UserDao dao;
	
	@Autowired
	private TripDao dao;
	
	
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
	public void getAttractionListTest() throws Exception{
		List<AttractionDto> list = dao.getAttractionList(new AreaCodeDto(1,1,12));
		logger.debug("oksubin : {} " ,list);
		assertNotNull(list);
	}

}
