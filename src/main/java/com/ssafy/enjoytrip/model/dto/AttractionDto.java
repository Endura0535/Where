package com.ssafy.enjoytrip.model.dto;

public class AttractionDto {
	private String contentId;
	private String title;
	private String imgPath;
	private String address1;
	private String address2;
	private double latitude;
	private double longitude;
	private int count;

	public AttractionDto(String contentId, String title, String imgPath, String address1, String address2,
			double latitude, double longitude) {
		super();
		this.contentId = contentId;
		this.title = title;
		this.imgPath = imgPath;
		this.address1 = address1;
		this.address2 = address2;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	

	public AttractionDto(String contentId, String title, String imgPath, String address1, String address2,
			double latitude, double longitude, int count) {
		super();
		this.contentId = contentId;
		this.title = title;
		this.imgPath = imgPath;
		this.address1 = address1;
		this.address2 = address2;
		this.latitude = latitude;
		this.longitude = longitude;
		this.count = count;
	}

	public AttractionDto() {
		super();
	}

	public String getContentId() {
		return contentId;
	}

	public void setContentId(String contentId) {
		this.contentId = contentId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
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
	

	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AttractionDto [contentId=").append(contentId).append(", title=").append(title)
				.append(", imgPath=").append(imgPath).append(", address1=").append(address1).append(", address2=")
				.append(address2).append(", latitude=").append(latitude).append(", longitude=").append(longitude)
				.append("]");
		return builder.toString();
	}

}
