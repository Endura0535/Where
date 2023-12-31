package com.ssafy.enjoytrip.model.service;

import java.util.List;

import com.ssafy.enjoytrip.model.dto.TripPlanDto;
import com.ssafy.enjoytrip.model.dto.TripPlanHeaderDto;

public interface TripPlanService {

	void addAttraction(TripPlanDto tripplanDto) throws Exception;

	List<TripPlanDto> getTripplan(TripPlanDto tripplanDto) throws Exception;

	void deleteAttraction(TripPlanDto tripplanDto) throws Exception;
	
	public void updateTripPlan(String uid, String plan_name, List<TripPlanDto> tripPlans) throws Exception;

	List<TripPlanHeaderDto> getTripPlanHeader(String uid) throws Exception;
}
