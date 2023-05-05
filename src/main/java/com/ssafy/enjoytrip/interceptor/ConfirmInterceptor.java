//package com.ssafy.enjoytrip.interceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import com.ssafy.enjoytrip.model.dto.BookException;
//import com.ssafy.enjoytrip.model.dto.User;
//
////@SuppressWarnings("deprecation")
////public class ConfirmInterceptor extends HandlerInterceptorAdapter {
////spring 5.3 부터는 HandlerInterceptor implements
//public class ConfirmInterceptor implements HandlerInterceptor { 
//
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//			throws Exception {
//		HttpSession session = request.getSession();
//		User  user = (User)session.getAttribute("userInfo");
//		if(user == null  || !user.getId().equals("admin")) {
//			throw new BookException("관리자 전용 페이지 입니다. 접근 권한이 없습니다.");
//		}
//		return true;
//	}
//	
//}