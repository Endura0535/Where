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
import com.ssafy.enjoytrip.model.dao.UserDao;
import com.ssafy.enjoytrip.model.dto.AreaCodeDto;
import com.ssafy.enjoytrip.model.dto.AttractionDto;
import com.ssafy.enjoytrip.model.dto.DetailDto;
import com.ssafy.enjoytrip.model.dto.GugunDto;
import com.ssafy.enjoytrip.model.dto.HotPlaceDto;
import com.ssafy.enjoytrip.model.dto.SidoDto;
import com.ssafy.enjoytrip.model.dto.User;
import com.ssafy.enjoytrip.model.service.UserService;

@SpringBootTest
class EnjoytripApplicationTests {
	private final Logger logger = LoggerFactory.getLogger(EnjoytripApplicationTests.class);
	
	
	@Test
	void contextLoads() {
	}
	
//	@Autowired
//	private UserDao dao;
//	
//	@Autowired
//	private UserService service;
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
	public void SqlSessionTest() throws Exception{
		logger.debug("datasource :{}",session);
		assertNotNull(session);
		
	}
	@Test
	public void TripDaoTest() throws Exception{
		logger.debug("datasource :{}",dao);
		assertNotNull(dao);
		
	}
	
	@Test
	public void getAttractionListTest() throws Exception{
		List<AttractionDto> list = dao.getAttractionList(new AreaCodeDto(1,1,12));
		logger.debug("oksubin : {} " ,list);
		assertNotNull(list);
	}
	
	@Test
	public void getGugunListTest() throws Exception{
		List<GugunDto> list = dao.getGugunList(1);
		logger.debug("oksubin : {} " ,list);
		assertNotNull(list);
	}
	
	@Test
	public void getSidoListTest() throws Exception{
		List<SidoDto> list = dao.getSidoList();
		logger.debug("oksubin : {} " ,list);
		assertNotNull(list);
	}
	
	@Test
	public void insertHotPlaceTest() throws Exception{
		dao.insertHotPlace(new HotPlaceDto(125405, "admin2", 0));
	}
	
	@Test
	public void getAllHotPlaceTest() throws Exception{
		List<HotPlaceDto> list = dao.getAllHotPlace();
		logger.debug("oksubin : {} " ,list);
		assertNotNull(list);
	}
	
	@Test
	public void getDetailInfoTest() throws Exception{
		DetailDto dto = dao.getDetailInfo(125405);
		logger.debug("oksubin : {} " ,dto);
		assertNotNull(dto);
	}

}
