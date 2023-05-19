package com.ssafy.enjoytrip.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssafy.enjoytrip.model.dto.Board;
import com.ssafy.enjoytrip.model.dto.PageBean;
import com.ssafy.enjoytrip.model.dto.User;
import com.ssafy.enjoytrip.model.service.BoardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController // REST 서비스를 전용으로 하는 Controller, 모든 메서드는 @ResponseBody를 붙인 효과
@CrossOrigin(origins = { "*" }, maxAge = 6000) // 다른 서버에서 AJax로 요청이 와도 서비스 되도록
@RequestMapping("/board")
@Api(value = "Board REST Controller", description = "여행지 정보 ")
public class BoardRestController {
	@Autowired
	BoardService boardService;

	private static final Logger logger = LoggerFactory.getLogger(BoardRestController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	
	@ApiOperation(value = "여행지 목록 정보", notes = "검색 조건에 맞는 여행지 정보를 검색한 목록 정보")
	@GetMapping("/list")
	public ResponseEntity<List<Board>> listArticle(PageBean bean) throws Exception {
		logger.info("listArticle - 호출");
//		PageBean bean1 = new PageBean(null, null, 0);
		logger.info("bean................................:" + bean.toString());
		List<Board> boards = boardService.listArticle(bean);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("boards", boards);
		result.put("pageLink", bean.getPageLink());
		
		return new ResponseEntity<List<Board>>(boards, HttpStatus.OK);
	}
	
	@ApiOperation(value = "여행지 목록 정보", notes = "검색 조건에 맞는 여행지 정보를 검색한 목록 정보")
	@GetMapping("/detail/{articleNo}")
	public ResponseEntity<Board> detailArticle(@PathVariable int articleNo) throws Exception {
		logger.info("detailArticle - 호출" + articleNo);
		boardService.updateHit(articleNo);
//		PageBean bean1 = new PageBean(null, null, 0);
		
		return new ResponseEntity<Board>(boardService.getArticle(articleNo), HttpStatus.OK);
	}
	
	@ApiOperation(value = "게시판 글작성", notes = "새로운 게시글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/write")
	public ResponseEntity<String> writeArticle(@RequestBody @ApiParam(value = "게시글 정보.", required = true) Board board) throws Exception {
		logger.info("writeArticle - 호출");
		boardService.writeArticle(board);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@ApiOperation(value = "게시판 글 삭제", notes = "게시글을 삭제한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("{articleNo}")
	public ResponseEntity<String> deleteArticle(@PathVariable int articleNo) throws Exception {
		logger.info("deleteArticle - 호출");
		boardService.deleteArticle(articleNo);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@ApiOperation(value = "게시판 글 수정", notes = "게시글을 수정한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("{articleNo}")
	public ResponseEntity<String> modifyArticle(@RequestBody @ApiParam(value = "게시글 정보.", required = true) Board board) throws Exception {
		logger.info("modifyArticle - 호출");
		boardService.modifyArticle(board);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	
}
