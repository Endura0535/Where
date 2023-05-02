package com.ssafy.enjoytrip.model.dto;

public class AreaCodeDto {
	private int sidoCode;
	private int gugunCode;
	private int contentTypeId;
	
	public int getSidoCode() {
		return sidoCode;
	}
	
	public void setSidoCode(int sidoCode) {
		this.sidoCode = sidoCode;
	}
	
	public int getGugunCode() {
		return gugunCode;
	}
	
	public void setGugunCode(int gugunCode) {
		this.gugunCode = gugunCode;
	}
	
	public int getContentTypeId() {
		return contentTypeId;
	}
	
	public void setContentTypeId(int contentTypeId) {
		this.contentTypeId = contentTypeId;
	}
	
	public AreaCodeDto(int sidoCode, int gugunCode, int contentTypeId) {
		super();
		this.sidoCode = sidoCode;
		this.gugunCode = gugunCode;
		this.contentTypeId = contentTypeId;
	}
	
}
