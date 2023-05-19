package com.ssafy.enjoytrip.model.service;

import com.ssafy.enjoytrip.model.dto.User;

public interface UserService {
	public User login(User user) throws Exception;
	public User userInfo(String userid) throws Exception;
	public void regist(User user) throws Exception;
	public void update(User user);
	public void remove(String id);
	public User findPw(String name, String email);
	public void saveRefreshToken(String userid, String refreshToken) throws Exception;
	public Object getRefreshToken(String userid) throws Exception;
	public void deleRefreshToken(String userid) throws Exception;
}


//User login(String id, String pass) ;
//User search(String id) ;
//void regist(User user) ;
//void update(User user) ;
//void remove(String id) ;
//User findPw(String name, String email);
