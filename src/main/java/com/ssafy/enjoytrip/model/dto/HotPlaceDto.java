package com.ssafy.enjoytrip.model.dto;

import lombok.Data;

@Data
public class HotPlaceDto {
	private int contentId;
	private String uid;
	private int likes;
	
	public HotPlaceDto(int contentId, String uid){
		this.contentId = contentId;
		this.uid = uid;
	}
}
