package com.ssafy.enjoytrip.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.model.dto.AreaCodeDto;
import com.ssafy.enjoytrip.model.dto.AttractionDto;
import com.ssafy.enjoytrip.model.dto.DetailDto;
import com.ssafy.enjoytrip.model.dto.GugunDto;
import com.ssafy.enjoytrip.model.dto.HotPlaceDto;
import com.ssafy.enjoytrip.model.dto.SidoDto;
import com.ssafy.enjoytrip.model.service.TripService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Servlet implementation class tripController
 */

@RestController // REST 서비스를 전용으로 하는 Controller, 모든 메서드는 @ResponseBody를 붙인 효과
@CrossOrigin(origins = { "*" }, maxAge = 6000) // 다른 서버에서 AJax로 요청이 와도 서비스 되도록
@RequestMapping("/trip")
@Api(value = "Trip REST Controller", description = "여행지 정보 ")
public class TripRestController {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	TripService tripService;

	private static final Logger logger = LoggerFactory.getLogger(TripRestController.class);
	private static final String SUCCESS = "success";
	
	@ApiOperation(value = "여행지 목록 정보", notes = "검색 조건에 맞는 여행지 정보를 검색한 목록 정보")
	@GetMapping("")
	public ResponseEntity<?> trip() {
		List<SidoDto> sidoList = tripService.getSidoList();
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("sidoList", sidoList);
		
		logger.debug("result.............result:{}",result);

		if (sidoList != null && !sidoList.isEmpty()) {
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	@ApiOperation(value = "여행지 목록 정보", notes = "검색 조건에 맞는 여행지 정보를 검색한 목록 정보")
	@GetMapping("/attrs")
	public ResponseEntity<?> tripList(@RequestParam int sidoCode,@RequestParam int gugunCode,@RequestParam int contentTypeId) {
		AreaCodeDto areaCode = new AreaCodeDto(sidoCode, gugunCode, contentTypeId);
		logger.debug("sidoCode..........................areaCode:{}",sidoCode);
		logger.debug("gugunCode..........................areaCode:{}",gugunCode);
		logger.debug("contentTypeId..........................areaCode:{}",contentTypeId);
		List<AttractionDto> attrList = tripService.getAttractionList(areaCode);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("attrList", attrList);
		
		logger.debug("result.............result:{}",result);

		if (attrList != null && !attrList.isEmpty()) {
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		
	}
	
	@ApiOperation(value = "시도 정보", notes = "구군 정보")
	@GetMapping("/attr/{sidoCode}")
	public ResponseEntity<?> getGugun(@PathVariable int sidoCode) {
		logger.debug("sidoCode.............................sidoCode:{}", sidoCode);
		List<GugunDto> gugunList = tripService.getGugunList(sidoCode);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("gugunList", gugunList);

		if (gugunList != null && !gugunList.isEmpty()) {
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	@ApiOperation(value = "여행지 목록 정보", notes = "검색 조건에 맞는 여행지 정보를 검색한 목록 정보")
	@GetMapping("/detail")
	public ResponseEntity<?> getDetail(@RequestParam int contentId) {
		logger.debug("contentId.............................contentId:{}", contentId);
		DetailDto detailDto = tripService.getDetailInfo(contentId);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("detailDto", detailDto);

		if (detailDto != null) {
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

		}
	}
	
	@ApiOperation(value = "여행지 목록 정보", notes = "검색 조건에 맞는 여행지 정보를 검색한 목록 정보")
	@GetMapping("/hotplace")
	private ResponseEntity<?> getAllHotPlace() {
		List<AttractionDto> hotplaces = tripService.getAllHotPlace();
		logger.debug("hotplaces.searchAll.............................hotplaces:{}", hotplaces);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("hotplaces", hotplaces);

		if (hotplaces != null && !hotplaces.isEmpty()) {
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	@ApiOperation(value = "핫플레이스 id", notes = "핫플레이스 디테일 정보")
	@GetMapping("/hotplace/{contentId}")
	private ResponseEntity<?> getHotPlace(@PathVariable int contentId) {
		DetailDto hotplace = tripService.getHotPlace(contentId);
		logger.debug("hotplace.search.............................hotplaces:{}", hotplace);
		
		if (hotplace != null ) {
			return new ResponseEntity<DetailDto>(hotplace, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	@ApiOperation(value = "핫플레이스", notes = "핫플레이스 등록(좋아요)")
	@PostMapping("/hotplace")
	private ResponseEntity<?> hotRegi(HotPlaceDto hotplaceDto) {
		tripService.hotRegi(hotplaceDto);
		logger.debug("hotplaceDto.insert.............................hotplaceDto:{}", hotplaceDto);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
