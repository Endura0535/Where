package com.ssafy.enjoytrip.model.dto;

import lombok.Data;

public class DetailDto {
	private int contentId;
	private String title;
	private String address1;
	private String address2;
	private String imgPath;
	private String zipCode;
	private String overview;
	private String telNumber;
	private double latitude;
	private double longitude;

	public DetailDto(int contentId, String title, String address1, String address2, String imgPath, String zipCode, String overview, String telNumber, double latitude, double longitude) {
		this.contentId = contentId;
		this.title = title;
		this.address1 = address1;
		this.address2 = address2;
		this.imgPath = imgPath;
		this.zipCode = zipCode;
		this.overview = overview;
		this.telNumber = telNumber;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public DetailDto(int contentId, String title, String address1, String address2, String imgPath, String zipCode,
					 String overview, String telNumber) {
		super();
		this.contentId = contentId;
		this.title = title;
		this.address1 = address1;
		this.address2 = address2;
		this.imgPath = imgPath;
		this.zipCode = zipCode;
		this.overview = overview;
		this.telNumber = telNumber;
	}

	public int getContentId() {
		return contentId;
	}

	public void setContentId(int contentId) {
		this.contentId = contentId;
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
