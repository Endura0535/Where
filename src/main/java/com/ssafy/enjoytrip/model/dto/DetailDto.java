package com.ssafy.enjoytrip.model.dto;

import lombok.Data;

public class DetailDto {
	private String title;
	private String address1;
	private String address2;
	private String imgPath;
	private String zipCode;
	private String overview;
	private String telNumber;
	
	public DetailDto(String title, String address1, String address2, String imgPath, String zipCode, String overview,
			String telNumber) {
		super();
		this.title = title;
		this.address1 = address1;
		this.address2 = address2;
		this.imgPath = imgPath;
		this.zipCode = zipCode;
		this.overview = overview;
		this.telNumber = telNumber;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public String getTelNumber() {
		return telNumber;
	}

	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}
	
	
}
