package com.ssafy.enjoytrip.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssafy.enjoytrip.model.dto.Board;
import com.ssafy.enjoytrip.model.dto.PageBean;
import com.ssafy.enjoytrip.model.dto.User;
import com.ssafy.enjoytrip.model.service.BoardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/article")
@Api("게시판 컨트롤러  API V1")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private BoardService boardService;

	@ApiOperation(value = "게시판 글목록", notes = "모든 게시글의 정보를 반환한다.")
	@GetMapping("/list")
	public ResponseEntity<List<Board>> listArticle(PageBean bean) throws Exception {
		log.debug("articleslist....................pageBean:{}", bean);
		List<Board> articles = boardService.listArticle(bean);
		log.debug("articleslist....................{}", articles);
		log.debug("sortKey....................{}", bean.getSortKey());
		return new ResponseEntity<List<Board>>(articles, HttpStatus.OK);
	}

//	@GetMapping("/write")
//	public String write() {
//		log.debug("board................... write 화면으로 이동");
//		return "board/InfoBoardWrite";
//	}
	@ApiOperation(value = "게시판 글작성", notes = "새로운 게시글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/write")
	public ResponseEntity<String> writeArticle(Board board, HttpSession session, RedirectAttributes redirectAttributes) throws Exception {
		User user = (User) session.getAttribute("userInfo");
		board.setId(user.getId());
		log.debug("Board............................write article:" + board);
		
		boardService.writeArticle(board);
//		redirectAttributes.addAttribute("pgno", "1");
//		redirectAttributes.addAttribute("key", "");
//		redirectAttributes.addAttribute("word", "");
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

	// protected void doGet(HttpServletRequest request, HttpServletResponse
	// response)
	// throws ServletException, IOException {
	// String action = request.getParameter("action");
	// System.out.println(action);
	// pgno = ParameterCheck.notNumberToOne(request.getParameter("pgno")); // 숫자가
	// 아니면 1로 만듬.
	// sortKey = ParameterCheck.nullToBlank(request.getParameter("sortKey"));
	// key = ParameterCheck.nullToBlank(request.getParameter("key")); // null이면 빈
	// 문자열
	// word = ParameterCheck.nullToBlank(request.getParameter("word"));
	// queryStrig = "?pgno=" + pgno + "&key=" + key + "&word=" +
	// URLEncoder.encode(word, "utf-8");
	//
	// String path = "";
	// if ("list".equals(action)) {
	// path = list(request, response);
	// forward(request, response, path);
	// } else if ("view".equals(action)) {
	// path = view(request, response);
	// forward(request, response, path);
	// } else if ("mvwrite".equals(action)) {
	// path = "/board/InfoBoardWrite.jsp";
	// forward(request, response, path);
	// } else if ("write".equals(action)) {
	// path = write(request, response);
	// redirect(request, response, path);
	// } else if ("mvmodify".equals(action)) {
	// path = mvModify(request, response);
	// forward(request, response, path);
	// } else if ("modify".equals(action)) {
	// path = modify(request, response);
	// redirect(request, response, path);
	// } else if ("delete".equals(action)) {
	// path = delete(request, response);
	// redirect(request, response, path);
	// } else {
	// redirect(request, response, path);
	// }
	// }
	//
	// protected void doPost(HttpServletRequest request, HttpServletResponse
	// response)
	// throws ServletException, IOException {
	// request.setCharacterEncoding("utf-8");
	// doGet(request, response);
	// }
	//
	// private void forward(HttpServletRequest request, HttpServletResponse
	// response, String path)
	// throws ServletException, IOException {
	// RequestDispatcher dispatcher = request.getRequestDispatcher(path);
	// dispatcher.forward(request, response);
	// }
	//
	// private void redirect(HttpServletRequest request, HttpServletResponse
	// response, String path) throws IOException {
	// response.sendRedirect(request.getContextPath() + path);
	// }
	//
	// private String list(HttpServletRequest request, HttpServletResponse response)
	// {
	// HttpSession session = request.getSession();
	// User user = (User) session.getAttribute("userInfo");
	// System.out.println(user);
	// if (user != null) {
	// try {
	// Map<String, String> map = new HashMap<String, String>();
	// map.put("pgno", pgno + "");
	// map.put("key", key);
	// map.put("word", word);
	//
	// List<Board> list = boardService.listArticle(map);
	//
	// switch (sortKey) {
	// case "article_no":
	// Collections.sort(list, new Comparator<Board>() {
	// @Override
	// public int compare(Board o1, Board o2) {
	// return o1.getArticleNo() - o2.getArticleNo();
	// }
	// });
	// break;
	// case "view_count":
	// Collections.sort(list, new Comparator<Board>() {
	// @Override
	// public int compare(Board o1, Board o2) {
	// return o2.getHit() - o1.getHit();
	// }
	// });
	// break;
	// case "subject":
	// Collections.sort(list, new Comparator<Board>() {
	// @Override
	// public int compare(Board o1, Board o2) {
	// return o1.getSubject().compareTo(o2.getSubject());
	// }
	// });
	// break;
	// }
	//
	// request.setAttribute("articles", list);
	//
	// System.out.println(list);
	//
	// PageNavigation pageNavigation = boardService.makePageNavigation(map);
	// request.setAttribute("navigation", pageNavigation);
	//
	// return "/board/InfoBoard.jsp" + queryStrig;
	// } catch (Exception e) {
	// e.printStackTrace();
	// request.setAttribute("msg", "글목록 출력 중 문제 발생!!!");
	// return "/error/error.jsp";
	// }
	// } else {
	// alertAndClose(response, "로그인 후 접속할 수 있는 페이지입니다.");
	// return "/index.jsp";
	//// request.setAttribute("msg", "로그인 후 접속할 수 있는 페이지입니다.");
	//// request.setAttribute("referer", "/article?action=list");
	//// return "/index.jsp";
	// }
	// }
	//
	// private String view(HttpServletRequest request, HttpServletResponse response)
	// {
	// HttpSession session = request.getSession();
	// User User = (User) session.getAttribute("userInfo");
	// if (User != null) {
	// int articleNo =
	// ParameterCheck.notNumberToOne(request.getParameter("articleno"));
	// System.out.println("hi" + articleNo);
	// try {
	// Board boardDto = boardService.getArticle(articleNo);
	// boardService.updateHit(articleNo);
	// request.setAttribute("article", boardDto);
	//
	// return "/board/InfoBoardDetail.jsp";
	// } catch (Exception e) {
	// e.printStackTrace();
	// request.setAttribute("msg", "글내용 출력 중 문제 발생!!!");
	// return "/error/error.jsp";
	// }
	// } else
	// return "/user/login.jsp";
	// }
	//
	// private String write(HttpServletRequest request, HttpServletResponse
	// response) {
	// HttpSession session = request.getSession();
	// User User = (User) session.getAttribute("userInfo");
	// if (User != null) {
	// Board boardDto = new Board();
	// boardDto.setUserId(User.getId());
	// boardDto.setSubject(request.getParameter("subject"));
	// boardDto.setContent(request.getParameter("content"));
	// try {
	// boardService.writeArticle(boardDto);
	// return "/article?action=list";
	// } catch (Exception e) {
	// e.printStackTrace();
	// request.setAttribute("msg", "글작성 중 문제 발생!!!");
	// return "/error/error.jsp";
	// }
	// } else
	// return "/board/list.jsp";
	// }
	//
	// private String mvModify(HttpServletRequest request, HttpServletResponse
	// response) {
	// // TODO : 수정하고자하는 글의 글번호를 얻는다.
	// // TODO : 글번호에 해당하는 글정보를 얻고 글정보를 가지고 modify.jsp로 이동.
	// HttpSession session = request.getSession();
	// User User = (User) session.getAttribute("userInfo");
	// if (User != null) {
	// int articleNo =
	// ParameterCheck.notNumberToOne(request.getParameter("articleno"));
	// try {
	// Board board = boardService.getArticle(articleNo);
	// request.setAttribute("article", board);
	// return "/board/InfoBoardModify.jsp";
	// } catch (Exception e) {
	// e.printStackTrace();
	// request.setAttribute("msg", "글작성 중 문제 발생!!!");
	// return "/error/error.jsp";
	// }
	// } else
	// return "/board/list.jsp";
	// }
	//
	// private String modify(HttpServletRequest request, HttpServletResponse
	// response) {
	// HttpSession session = request.getSession();
	// User user = (User) session.getAttribute("userInfo");
	// if (user != null) {
	// Board board = new Board();
	// board.setArticleNo(Integer.parseInt(request.getParameter("articleno")));
	// board.setSubject(request.getParameter("subject"));
	// board.setContent(request.getParameter("content"));
	//
	// try {
	// boardService.modifyArticle(board);
	// return "/article?action=list&pgno=1&key=&word=";
	// } catch (Exception e) {
	// e.printStackTrace();
	// request.setAttribute("msg", "글수정 중 문제 발생!!!");
	// return "/error/error.jsp";
	// }
	//
	// } else
	// return "/board/list.jsp";
	// }
	//
	// private String delete(HttpServletRequest request, HttpServletResponse
	// response) {
	// // TODO : 삭제할 글 번호를 얻는다.
	// // TODO : 글번호를 파라미터로 service의 deleteArticle()을 호출.
	// // TODO : 글삭제 완료 후 list.jsp로 이동.(이후의 프로세스를 생각해 보세요.)
	// try {
	// HttpSession session = request.getSession();
	// User user = (User) session.getAttribute("userInfo");
	// if (user != null) {
	// int articleNo =
	// ParameterCheck.notNumberToOne(request.getParameter("articleno"));
	// System.out.println("momomomo" + request.getParameter("articleno"));
	// boardService.deleteArticle(articleNo);
	// System.out.println("hey" + articleNo);
	// return "/article?action=list";
	// } else
	// return "index.jsp";
	// } catch (Exception e) {
	// e.printStackTrace();
	// request.setAttribute("msg", "글내용얻는중문제발생!!!");
	// return "/error/error.jsp";
	// }
	//
	// }
	//
	public static void alertAndClose(HttpServletResponse response, String msg) {
		try {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter w = response.getWriter();
			w.write("<script>alert('" + msg + "');history.go(-1);</script>");
			w.flush();
			w.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
