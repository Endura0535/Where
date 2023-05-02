package com.ssafy.enjoytrip.model.service;

import com.ssafy.enjoytrip.model.dto.User;

public interface UserService {
	User login(String id, String pass) ;
	User search(String id) ;
	void regist(User user) ;
	void update(User user) ;
	void remove(String id) ;
	User findPw(String name, String email);
}
