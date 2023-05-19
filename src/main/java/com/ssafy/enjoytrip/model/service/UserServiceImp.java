package com.ssafy.enjoytrip.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.model.dao.UserDao;
import com.ssafy.enjoytrip.model.dto.BookException;
import com.ssafy.enjoytrip.model.dto.User;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImp implements UserService {
	
	private final UserDao dao;

	@Override
	public User login(User user) throws Exception{
//		System.out.println(user.getId() + "    " + user.getId());
		String id = user.getId();
		
		if (id == null || user.getPass() == null)
			return null;
		User loginUser = dao.login(user.getId(), "$2a$10$Ew.oH3WBajykJ//cYGnZE.kVZ9HWqgyPcbM0woUs6Sg5ttMuxuSV6");
		
		if (loginUser == null) {
			System.out.println("loginUser null");
			throw new BookException("등록되지 않은 아이디입니다.");
		}
			
//		if (!BCrypt.checkpw(user.getPass(), loginUser.getPass()))
//			throw new BookException("비밀 번호 오류 발생!!!!");

		return user;
	
//		try {
//			log.debug("로그인.................................");
//			
//			if (user == null) {
//				System.out.println("user null");
//				throw new BookException("등록되지 않은 아이디입니다.");
//			}
//				
//
//			if (!BCrypt.checkpw(pw, user.getPass()))
//				throw new BookException("비밀 번호 오류 발생!!!!");
//
//			return user;
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw new BookException("로그인 처리 중 오류 발생");
//		}
	}
	
	@Override
	public Object getRefreshToken(String userid) throws Exception {
		return dao.getRefreshToken(userid);
	}
	
	@Override
	public void saveRefreshToken(String userid, String refreshToken) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", userid);
		map.put("token", refreshToken);
		dao.saveRefreshToken(map);
	}
	
	@Override
	public void deleRefreshToken(String userid) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", userid);
		map.put("token", null);
		dao.deleteRefreshToken(map);
	}

	@Override
	public User userInfo(String userid) throws Exception {
		return dao.search(userid);
	}

	@Override
	public void regist(User user) {
		try {
			User find = dao.search(user.getId());
			if (find != null) {
				throw new BookException("이미 등록된 아이디 입니다.");
			}
			user.setPass(BCrypt.hashpw(user.getPass(), BCrypt.gensalt()));
			System.out.println(user.getPass());
			dao.regist(user);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BookException("회원 정보 처리 중 오류 발생!!!");
		}
	}

	@Override
	public void update(User user) {
		try {
			user.setPass(BCrypt.hashpw(user.getPass(), BCrypt.gensalt()));
			log.debug(user.toString());
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

//@Override
//public MemberDto login(MemberDto memberDto) throws Exception {
//	if (memberDto.getUserid() == null || memberDto.getUserpwd() == null)
//		return null;
//	return sqlSession.getMapper(MemberDao.class).login(memberDto);
//}
//
//@Override
//public MemberDto userInfo(String userid) throws Exception {
//	return sqlSession.getMapper(MemberDao.class).userInfo(userid);
//}
//
//@Override
//public void saveRefreshToken(String userid, String refreshToken) throws Exception {
//	Map<String, String> map = new HashMap<String, String>();
//	map.put("userid", userid);
//	map.put("token", refreshToken);
//	sqlSession.getMapper(MemberDao.class).saveRefreshToken(map);
//}
//
//@Override
//public Object getRefreshToken(String userid) throws Exception {
//	return sqlSession.getMapper(MemberDao.class).getRefreshToken(userid);
//}
//
//@Override
//public void deleRefreshToken(String userid) throws Exception {
//	Map<String, String> map = new HashMap<String, String>();
//	map.put("userid", userid);
//	map.put("token", null);
//	sqlSession.getMapper(MemberDao.class).deleteRefreshToken(map);
//}

