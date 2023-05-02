package com.ssafy.enjoytrip.model.service;

import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.model.dao.UserDao;
import com.ssafy.enjoytrip.model.dto.BookException;
import com.ssafy.enjoytrip.model.dto.User;

@Service
public class UserServiceImp implements UserService {
	private UserDao dao;
	public UserServiceImp(UserDao dao) {
		super();
		this.dao = dao;
		System.out.println("UserServiceImp........................dao:"+dao);
	}

	public User login(String id, String pw) {
		try {
			User user = dao.search(id);

			if (user == null)
				throw new BookException("등록되지 않은 아이디입니다.");

			if (!BCrypt.checkpw(pw, user.getpass()))
				throw new BookException("비밀 번호 오류 발생!!!!");

			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BookException("로그인 처리 중 오류 발생");
		}
	}

	@Override
	public User search(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void regist(User user) {
		try {
			User find = dao.search(user.getId());
			if (find != null) {
				throw new BookException("이미 등록된 아이디 입니다.");
			}
			user.setpass(BCrypt.hashpw(user.getpass(), BCrypt.gensalt()));
			System.out.println(user.getpass());
			dao.regist(user);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BookException("회원 정보 처리 중 오류 발생!!!");
		}
	}

	@Override
	public void update(User user) {
		try {
			user.setpass(BCrypt.hashpw(user.getpass(), BCrypt.gensalt()));
			dao.update(user);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BookException("회원 정보 수정 중 오류 발생!!");
		}
	}

	@Override
	public void remove(String id) {
		try {
			dao.remove(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BookException("회원 탈퇴 중 오류 발생!!");
		}
	}
	
	@Override
	public User findPw(String name, String email) {
		try {
			return dao.findPw(name, email);
		}catch (Exception e) {
			e.printStackTrace();
			throw new BookException("비밀 번호 찾기 중 오류 발생!!");
		}
	}

}