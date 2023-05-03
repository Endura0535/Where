package com.ssafy.enjoytrip.model.dto;

import lombok.Data;

@Data
public class DetailDto {
	private String title;
	private String address1;
	private String address2;
	private String imgPath;
	private String zipCode;
	private String overview;
	private String telNumber;
}
