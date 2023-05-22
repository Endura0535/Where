package com.ssafy.enjoytrip.model.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.enjoytrip.model.dao.TripPlanDao;
import com.ssafy.enjoytrip.model.dto.TripPlanDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class TripPlanServiceImpl implements TripPlanService {

	private final TripPlanDao tripplanDao;
	
	@Transactional
	@Override
	public void addAttraction(TripPlanDto tripplanDto) throws Exception {
		tripplanDao.insertTripPlan(tripplanDto);
	}
	
	@Transactional
	@Override
	public List<TripPlanDto> getTripplan(TripPlanDto tripplanDto) throws Exception {
		return tripplanDao.getTripPlanList(tripplanDto);
	}

	@Transactional
	@Override
	public void deleteAttraction(TripPlanDto tripplanDto) throws Exception {
		tripplanDao.deleteTripPlan(tripplanDto);
	}

	@Transactional
	@Override
	public void updateTripPlan(String uid, String plan_name, List<TripPlanDto> tripPlans) throws Exception {
		
		TripPlanDto deletePlan = new TripPlanDto();
		
		deletePlan.setUid(uid);
		deletePlan.setPlanName(plan_name);
		
		tripplanDao.deleteTripPlan(deletePlan);
		
		for(TripPlanDto plan : tripPlans) {
			addAttraction(plan);
		}
		
	}

}
