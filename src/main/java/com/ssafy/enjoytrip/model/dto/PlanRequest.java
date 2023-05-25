package com.ssafy.enjoytrip.model.dto;

import java.util.List;

import lombok.Data;

@Data
public class PlanRequest {
	private List<TripPlanDto> planData;
	
}
