package com.ssafy.enjoytrip.model.dto;

import lombok.Data;

@Data
public class Board {
	private int articleNo;
	private String id;
	private String subject;
	private String content;
	private int hit;
	private String registerTime;
}
