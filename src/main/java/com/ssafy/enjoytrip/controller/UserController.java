package com.ssafy.enjoytrip.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.model.dto.User;
import com.ssafy.enjoytrip.model.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user")
@Api("사용자 컨트롤러  API V1")
public class UserController {

	private Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@ExceptionHandler(Exception.class)
	public String handler(Exception ex, Model model) {
		model.addAttribute("msg", ex.getMessage());
		return "redirect:/index";
	}
	
	@ApiOperation(value = "로그인", notes = "로그인 결과 메세지를 반환한다.")
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(String id, String pw, HttpSession session) {
		logger.debug("User............................login id:" + id);
		logger.debug("User............................login pw:" + pw);
		
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			User user = userService.login(id, pw);
			if (user != null) {
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "로그아웃", notes = "로그아웃 결과 메세지를 반환한다.")
	@GetMapping("/logout")
	public ResponseEntity<Map<String, Object>> logout(HttpSession session) {
		logger.info("User............................logout");
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			session.invalidate();
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			logger.error("로그아웃 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
//	@GetMapping("/mypage")
//	public String mypage(HttpSession session) {
//		logger.info("User............................mypage");
//		return "user/MyPage";
//	}
	
//	@GetMapping("/signup")
//	public String signup(HttpSession session) {
//		logger.info("User............................signup");
//		return "user/SignUp";
//	}
	
	@ApiOperation(value = "로그아웃", notes = "로그아웃 결과 메세지를 반환한다.")
	@PostMapping("/regist")
	public ResponseEntity<Map<String, Object>> regist(User regiUser, HttpSession session) {
		logger.debug("User............................regist user:" + regiUser);
		
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			userService.regist(regiUser);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
//	@GetMapping("/modifyForm")
//	public String modifyForm(HttpSession session) {
//		logger.info("User............................modifyForm으로 이동");
//		return "user/UserInfoModify";
//	}
	
	@ApiOperation(value = "유저 정보 수정", notes = "수정 결과 메세지를 반환한다.")
	@PostMapping("/modify")
	public ResponseEntity<Map<String, Object>> modify(User modiUser, HttpSession session) {
		logger.info("User............................modify		 modiuser:" + modiUser);
		User user = (User)session.getAttribute("userInfo");
		logger.debug(user.toString());
		
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		
		String modiAtt = modiUser.getPass();
		if(modiAtt != null && modiAtt != "")
			user.setPass(modiAtt);
		
		modiAtt = modiUser.getEmail();
		if(modiAtt != null && modiAtt != "")
			user.setEmail(modiAtt);
		
		modiAtt = modiUser.getAddr1();
		if(modiAtt != null && modiAtt != "")
			user.setAddr1(modiAtt);
		
		modiAtt = modiUser.getAddr2();
		if(modiAtt != null && modiAtt != "")
			user.setAddr2(modiAtt);
		
		logger.info("User............................modify		 modiuser:" + user);
		
		userService.update(user);
		resultMap.put("message", SUCCESS);
		status = HttpStatus.ACCEPTED;
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "유저 정보 수정", notes = "수정 결과 메세지를 반환한다.")
	@GetMapping("/deleteAccount")
	public ResponseEntity<Map<String, Object>> deleteAccount(HttpSession session) {
		User user = (User)session.getAttribute("userInfo");
		logger.info("User............................delete" + user.getId());
		
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;

		userService.remove(user.getId());
		session.invalidate();
		resultMap.put("message", SUCCESS);
		status = HttpStatus.ACCEPTED;
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
}
