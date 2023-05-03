package com.ssafy.enjoytrip.model.dto;

public class SidoDto {

	private int sidoCode;
	private String sidoName;

	public int getAreaCode() {
		return sidoCode;
	}

	public void setAreaCode(int areaCode) {
		this.sidoCode = areaCode;
	}

	public String getAreaName() {
		return sidoName;
	}

	public void setAreaName(String areaName) {
		this.sidoName = areaName;
	}

	public SidoDto(int areaCode, String areaName) {
		super();
		this.sidoCode = areaCode;
		this.sidoName = areaName;
	}

	@Override
	public String toString() {
		return "SidoDto [sidoCode=" + sidoCode + ", sidoName=" + sidoName + "]";
	}

}
