//package com.ssafy.enjoytrip.controller;
//
//import javax.servlet.http.HttpSession;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import com.ssafy.enjoytrip.model.dto.User;
//import com.ssafy.enjoytrip.model.service.UserService;
//
//@Controller
//@RequestMapping("/user")
//public class UserController {
//
//	private Logger logger = LoggerFactory.getLogger(UserController.class);
//
//	@Autowired
//	private UserService userService;
//
//	@ExceptionHandler(Exception.class)
//	public String handler(Exception ex, Model model) {
//		model.addAttribute("msg", ex.getMessage());
//		return "redirect:/index";
//	}
//
//	@PostMapping("/login")
//	public String login(String id, String pw, HttpSession session) {
//		logger.debug("User............................login id:" + id);
//		logger.debug("User............................login pw:" + pw);
//
//		User user = userService.login(id, pw);
//		session.setAttribute("userInfo", user);
//		return "redirect:/index";
//	}
//
//	@GetMapping("/logout")
//	public String logout(HttpSession session) {
//		session.invalidate();
//		logger.info("User............................logout");
//		return "redirect:/index";
//	}
//	
//	@GetMapping("/mypage")
//	public String mypage(HttpSession session) {
//		logger.info("User............................mypage");
//		return "user/MyPage";
//	}
//	
//	@GetMapping("/signup")
//	public String signup(HttpSession session) {
//		logger.info("User............................signup");
//		return "user/SignUp";
//	}
//	
//	@PostMapping("/regist")
//	public String regist(User regiUser, HttpSession session) {
//		logger.debug("User............................regist user:" + regiUser);
//
//		userService.regist(regiUser);
//		return "redirect:/index";
//	}
//
//	@GetMapping("/modifyForm")
//	public String modifyForm(HttpSession session) {
//		logger.info("User............................modifyForm으로 이동");
//		return "user/UserInfoModify";
//	}
//	
//	@PostMapping("/modify")
//	public String modify(User modiUser, HttpSession session) {
//		logger.info("User............................modify		 modiuser:" + modiUser);
//		User user = (User)session.getAttribute("userInfo");
//		logger.debug(user.toString());
//		
//		String modiAtt = modiUser.getPass();
//		if(modiAtt != null && modiAtt != "")
//			user.setPass(modiAtt);
//		
//		modiAtt = modiUser.getEmail();
//		if(modiAtt != null && modiAtt != "")
//			user.setEmail(modiAtt);
//		
//		modiAtt = modiUser.getAddr1();
//		if(modiAtt != null && modiAtt != "")
//			user.setAddr1(modiAtt);
//		
//		modiAtt = modiUser.getAddr2();
//		if(modiAtt != null && modiAtt != "")
//			user.setAddr2(modiAtt);
//		
//		logger.info("User............................modify		 modiuser:" + user);
//		
//		userService.update(user);
//		return "user/MyPage";
//	}
//	
//	@GetMapping("/deleteAccount")
//	public String deleteAccount(HttpSession session) {
//		User user = (User)session.getAttribute("userInfo");
//		logger.info("User............................delete" + user.getId());
//
//		userService.remove(user.getId());
//		session.invalidate();
//		return "redirect:/index";
//	}
//}
