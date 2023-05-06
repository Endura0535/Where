package com.ssafy.enjoytrip.model.dto;

public class HotPlaceDto {
	private int contentId;
	private String uid;
	private int likes;
	
	public int getContentId() {
		return contentId;
	}
	
	public void setContentId(int contentId) {
		this.contentId = contentId;
	}
	
	public String getUid() {
		return uid;
	}
	
	public void setUid(String uid) {
		this.uid = uid;
	}
	
	public int getLikes() {
		return likes;
	}
	
	public void setLikes(int likes) {
		this.likes = likes;
	}
	
	public HotPlaceDto(int contentId, int likes) {
		super();
		this.contentId = contentId;
		this.likes = likes;
	}
	
	public HotPlaceDto(int contentId, String uid) {
		super();
		this.contentId = contentId;
		this.uid = uid;
	}

	@Override
	public String toString() {
		return "HotPlaceDto [contentId=" + contentId + ", uid=" + uid + ", likes=" + likes + "]";
	}
	
	
}
