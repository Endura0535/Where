package com.ssafy.enjoytrip.model.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.enjoytrip.model.dao.BoardDao;
import com.ssafy.enjoytrip.model.dto.Board;
import com.ssafy.enjoytrip.model.dto.BookException;
import com.ssafy.enjoytrip.model.dto.PageBean;
import com.ssafy.enjoytrip.util.PageUtility;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {

	private final BoardDao boardDao;

	@Transactional
	@Override
	public void writeArticle(Board boardDto) throws Exception {
		boardDao.writeArticle(boardDto);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Board> listArticle(PageBean bean) throws Exception {
		try {
			int total = boardDao.totalCount(bean);
			PageUtility page = new PageUtility(bean.getInterval(), total, bean.getPageNo(), "");
			bean.setPageLink(page.getPageBar()); //paging 처리한 html 링크를 추출하기 
			return boardDao.listArticle(bean);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BookException("책 목록 정보를 조회 하는 중 오류 발생!!!");
		}
	}

	@Transactional
	@Override
	public Board getArticle(int articleNo) throws Exception {
		return boardDao.getArticle(articleNo);
	}

	@Transactional
	@Override
	public void updateHit(int articleNo) throws Exception {
		boardDao.updateHit(articleNo);
	}

	@Transactional
	@Override
	public void modifyArticle(Board boardDto) throws Exception {
		boardDao.modifyArticle(boardDto);

	}

	@Transactional
	@Override
	public void deleteArticle(int articleNo) throws Exception {
		boardDao.deleteArticle(articleNo);

	}

}
