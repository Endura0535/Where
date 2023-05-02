package com.ssafy.enjoytrip.model.dto;

public class SidoDto {

	private int areaCode;
	private String areaName;

	public int getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(int areaCode) {
		this.areaCode = areaCode;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public SidoDto(int areaCode, String areaName) {
		super();
		this.areaCode = areaCode;
		this.areaName = areaName;
	}

	private SidoDto() {
		super();
	}

}
