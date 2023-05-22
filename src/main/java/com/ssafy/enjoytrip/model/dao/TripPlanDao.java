package com.ssafy.enjoytrip.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.enjoytrip.model.dto.TripPlanDto;

public interface TripPlanDao {
	
	List<TripPlanDto> getTripPlanList(TripPlanDto tripplanDto) throws SQLException;
	
	void insertTripPlan(TripPlanDto tripplanDto) throws SQLException;
	void deleteTripPlan(TripPlanDto tripplanDto) throws SQLException;
	void updateTripPlan(TripPlanDto tripplanDto) throws SQLException;
	
}
