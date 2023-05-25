package com.ssafy.enjoytrip.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
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

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.enjoytrip.model.dto.AttractionDto;
import com.ssafy.enjoytrip.model.dto.Board;
import com.ssafy.enjoytrip.model.dto.PlanRequest;
import com.ssafy.enjoytrip.model.dto.TripPlanDto;
import com.ssafy.enjoytrip.model.dto.TripPlanHeaderDto;
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
	
//	@ApiOperation(value = "여행 계획 등록", notes = "여행 계획을 등록한다.")
//	@PostMapping("")
//	private ResponseEntity<String> planRegister(@RequestBody PlanRequest planData) throws Exception {
//		
//		
//		log.debug("planData...[]",planData.getPlanData());
//		
//
//		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
//	}
	
	@ApiOperation(value = "여행 계획 등록", notes = "여행 계획을 등록한다.")
	@PostMapping("")
	private ResponseEntity<String> planRegister(@RequestBody String planData) throws Exception {
		// ObjectMapper 생성
        ObjectMapper objectMapper = new ObjectMapper();

        // JSON 문자열을 JsonNode로 파싱
        JsonNode jsonNode = objectMapper.readTree(planData);

        // JsonNode에서 submitPlan 속성값 가져오기
        JsonNode submitPlanNode = jsonNode.get("submitPlan");

        // submitPlanNode를 순회하며 JsonNode로 파싱된 각각의 plan 객체 처리
        for (JsonNode planNode : submitPlanNode) {
            // planNode에서 필요한 데이터 추출
            String uid = planNode.get("uid").asText();
            String planName = planNode.get("planName").asText();
            int dayNo = planNode.get("dayNo").asInt();
            int orderNo = planNode.get("orderNo").asInt();
            int attractionId = planNode.get("attractionId").asInt();
            String startDayTxt = planNode.get("startDay").asText();
            // 추출한 데이터로 원하는 작업 수행
            // 예: 데이터베이스에 저장하거나 다른 비즈니스 로직 수행 등
            SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
            Date startDay = Date.valueOf(startDayTxt);
            TripPlanDto tripplanDto = new TripPlanDto(uid,planName,dayNo,orderNo,attractionId,startDay);
            tripplanService.addAttraction(tripplanDto);
            System.out.println(tripplanDto);
        }
        
        
        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@ApiOperation(value = "여행 계획 가져오기", notes = "검색 조건에 맞는 여행지 계획을 검색한 목록 정보")
	@GetMapping("/list")
	private ResponseEntity<List<TripPlanDto>> listPlan(@RequestParam String planName, @RequestParam String uid) throws Exception {
		List<TripPlanDto> plans = tripplanService.getTripplan(new TripPlanDto(planName, uid));
		log.debug("tripplanDto.list.............................tripplanDto:{}", plans);
		
		return new ResponseEntity<List<TripPlanDto>>(plans, HttpStatus.OK);
	}
	
	@ApiOperation(value = "여행 계획 가져오기", notes = "검색 조건에 맞는 여행지 계획을 검색한 목록 정보")
	@GetMapping("/header/{uid}")
	private ResponseEntity<List<TripPlanHeaderDto>> listPlanHeader(@PathVariable String uid) throws Exception {
		log.debug(uid);
		List<TripPlanHeaderDto> plans = tripplanService.getTripPlanHeader(uid);
		log.debug("TripPlanHeaderDto.list.............................TripPlanHeaderDto:{}", plans);
		
		return new ResponseEntity<List<TripPlanHeaderDto>>(plans, HttpStatus.OK);
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
