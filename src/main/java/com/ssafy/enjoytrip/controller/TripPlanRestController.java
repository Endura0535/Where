package com.ssafy.enjoytrip.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.enjoytrip.model.dto.AttractionDto;
import com.ssafy.enjoytrip.model.dto.Board;
import com.ssafy.enjoytrip.model.dto.TripPlanDto;
import com.ssafy.enjoytrip.model.service.TripPlanService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin(origins = { "*" }, maxAge = 6000) // 다른 서버에서 AJax로 요청이 와도 서비스 되도록
@RequestMapping("/plan")
@Api(value = "TripPlan REST Controller", description = "여행계획 정보 ")

public class TripPlanRestController {

	@Autowired
	TripPlanService tripplanService;
	
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@ApiOperation(value = "여행 계획 등록", notes = "여행 계획을 등록한다.")
	@PostMapping("")
	private ResponseEntity<String> planRegister(@RequestBody List<TripPlanDto> submitPlan) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		log.debug("tripPlan......[]",submitPlan);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@ApiOperation(value = "여행 계획 가져오기", notes = "검색 조건에 맞는 여행지 계획을 검색한 목록 정보")
	@GetMapping("/list")
	private ResponseEntity<List<TripPlanDto>> hotRegi(TripPlanDto tripplanDto) throws Exception {
		List<TripPlanDto> plans = tripplanService.getTripplan(tripplanDto);
		log.debug("tripplanDto.list.............................tripplanDto:{}", plans);
		
		return new ResponseEntity<List<TripPlanDto>>(plans, HttpStatus.OK);
	}
	
	@ApiOperation(value = "여행 계획 추가", notes = "여행 계획에 여행지를 추가한다")
	@PostMapping("/insert")
	public ResponseEntity<String> insertAttraction(@RequestBody @ApiParam(value = "여행지 정보", required = true) TripPlanDto tripplanDto) throws Exception {
		log.info("insertAttraction..........................:{}", tripplanDto);
		tripplanService.addAttraction(tripplanDto);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@ApiOperation(value = "여행지 삭제", notes = "여행지를 삭제한다.")
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteAttraction(@RequestBody @ApiParam(value = "여행지 정보", required = true) TripPlanDto tripplanDto) throws Exception {
		log.info("deleteAttraction..........................:{}", tripplanDto);
		tripplanService.deleteAttraction(tripplanDto);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@ApiOperation(value = "여행 계획 수정", notes = "여행 계획을 수정한다.")
	@PostMapping("/update")
	public ResponseEntity<String> updateAttraction(@RequestParam String uid,@RequestParam String plan_name,@RequestBody List<TripPlanDto> tripPlan) throws Exception {
		log.info("insertAttraction..........................:{}", tripPlan);
		
		tripplanService.updateTripPlan(uid, plan_name, tripPlan);

		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

}
