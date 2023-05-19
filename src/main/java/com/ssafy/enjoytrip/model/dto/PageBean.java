package com.ssafy.enjoytrip.model.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiParam;
import lombok.Data;


/** UI 화면 페이지에 대한 정보를 표시하는 클래스 */
@Data
public class PageBean implements Serializable {
	/** 검색 조건 */
	@ApiParam(value = "검색 조건")
	private String searchKey;
	/** 검색 단어 */
	@ApiParam(value = "검색어")
	private String searchWord;
	
	/** 페이징 처리에 대한 link정보 */
	private String pageLink;

	private String sortKey;

	/** 현재 페이지 번호 */
	@ApiParam(value = "현재 페이지", required = true)
	private int pageNo;
	/** 한 페이지에 보여줄 content 개수 */
	private int interval = 5;
	/** 페이지 시작 번호 */
	private int start = 0;

	public int getStart() {
		if (pageNo > 1) {
			return start = (pageNo - 1) * interval;
		} else {
			return 0;
		}
	}
	
}
