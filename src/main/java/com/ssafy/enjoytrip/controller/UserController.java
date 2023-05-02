package com.ssafy.enjoytrip.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.enjoytrip.model.dto.User;
import com.ssafy.enjoytrip.model.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	private Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@ExceptionHandler(Exception.class)
	public String handler(Exception ex, Model model) {
		model.addAttribute("msg", ex.getMessage());
		return "redirect:/index";
	}

	@PostMapping("/login")
	public String login(String id, String pw, HttpSession session) {
		logger.debug("User............................login id:" + id);
		logger.debug("User............................login pw:" + pw);

		User user = userService.login(id, pw);
		session.setAttribute("userInfo", user);
		return "redirect:/index";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		logger.info("User............................logout");
		return "redirect:/index";
	}
	
	@GetMapping("/mypage")
	public String mypage(HttpSession session) {
		logger.info("User............................mypage");
		return "user/MyPage";
	}

}
