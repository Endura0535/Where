package com.ssafy.enjoytrip.model.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class TripPlanDto {
	
	int pid;
	String uid;
	String planName;
	int dayNo;
	int orderNo;
	int attractionId;
	Date startDay;
	
	public TripPlanDto(String uid, String planName, int dayNo, int orderNo, int attractionId, Date startDay) {
		super();
		this.uid = uid;
		this.planName = planName;
		this.dayNo = dayNo;
		this.orderNo = orderNo;
		this.attractionId = attractionId;
		this.startDay = startDay;
	}
	
	public TripPlanDto(String planName, String uid) {
		this.uid = uid;
		this.planName = planName;
	}

	public TripPlanDto() {
		// TODO Auto-generated constructor stub
	}
	
}
