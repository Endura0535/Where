package com.ssafy.enjoytrip.model.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class TripPlanHeaderDto {
	private String planName;
	private Date startDay;
}
