package com.ssafy.enjoytrip.model.dto;

public class HotPlaceDto {
	private int contentId;
	private int uid;
	private int likes;
	
	public int getContentId() {
		return contentId;
	}
	
	public void setContentId(int contentId) {
		this.contentId = contentId;
	}
	
	public int getUid() {
		return uid;
	}
	
	public void setUid(int uid) {
		this.uid = uid;
	}
	
	public int getLikes() {
		return likes;
	}
	
	public void setLikes(int likes) {
		this.likes = likes;
	}
	
	public HotPlaceDto(int contentId, int uid, int likes) {
		super();
		this.contentId = contentId;
		this.uid = uid;
		this.likes = likes;
	}
}
