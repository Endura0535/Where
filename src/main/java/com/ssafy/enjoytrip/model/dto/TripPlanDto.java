package com.ssafy.enjoytrip.model.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class TripPlanDto {
	
	String uid;
	String planName;
	int dayNo;
	int orderNo;
	int attractionId;
	Date startDay;
	
}
