//package com.ssafy.enjoytrip;
//
//import static org.junit.Assert.assertNotNull;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.sql.DataSource;
//
//import org.apache.ibatis.session.SqlSession;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import com.ssafy.enjoytrip.model.dao.BoardDao;
//import com.ssafy.enjoytrip.model.dto.Board;
//import com.ssafy.enjoytrip.model.dto.PageBean;
//import com.ssafy.enjoytrip.model.service.BoardService;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@SpringBootTest
//class BoardTest {
//
//	@Test
//	void contextLoads() {
//	}
//
//	@Autowired
//	private BoardDao dao;
//
//	@Autowired
//	private BoardService service;
//
//	@Autowired
//	private DataSource datasource;
//
//	@Autowired
//	private SqlSession session;
//	
//	@Test
//	public void insertTest() throws Exception {
//		Board board = new Board();
//		board.setId("ssafy2");
//		board.setSubject("sub");
//		board.setContent("con");
//		assertNotNull(board);
//
//		service.writeArticle(board);
//		log.debug("boardDto : {}", board);
//	}
//
//	@Test
//	public void listTest() throws Exception {
//		
//		PageBean bean = new PageBean();
//		
//		List<Board> boards = dao.listArticle(bean);
//		log.debug("boardsDto : {}", boards);
//		assertNotNull(boards);
//	}
//	
//	@Test
//	public void getTotalCntTest() throws Exception {
//
//		PageBean bean = new PageBean();
//		
//		int cnt = dao.totalCount(bean);
//		
//		log.debug("totalcnt : {}", cnt);
//		assertNotNull(cnt);
//	}
//
//
//}
