package com.ssafy.enjoytrip.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.enjoytrip.model.dto.AreaCodeDto;
import com.ssafy.enjoytrip.model.dto.AttractionDto;
import com.ssafy.enjoytrip.model.dto.DetailDto;
import com.ssafy.enjoytrip.model.dto.GugunDto;
import com.ssafy.enjoytrip.model.dto.HotPlaceDto;
import com.ssafy.enjoytrip.model.service.TripService;

@Controller
@RequestMapping("/trip")
public class TripController {
private static final long serialVersionUID = 1L;
	
	@Autowired
	TripService tripService;

	private static final Logger logger = LoggerFactory.getLogger(TripController.class);
	
	@GetMapping("/attr")
	public String TripList() {
		return "trip/recommendByLocation";
	}
	
	@GetMapping("/attr/{sidoCode}")
	public ResponseEntity<?> getGugun(@PathVariable int sidoCode) {
		logger.debug("sidoCode.............................sidoCode:{}", sidoCode);
		List<GugunDto> gugunList = tripService.getGugunList(sidoCode);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("gugunList", result);

		if (gugunList != null && !gugunList.isEmpty()) {
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/detail/{contentId}")
	public ResponseEntity<?> getDetail(@PathVariable int contentId) {
		logger.debug("contentId.............................contentId:{}", contentId);
		DetailDto detailDto = tripService.getDetailInfo(contentId);
		if (detailDto != null) {
			return new ResponseEntity<DetailDto>(detailDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<DetailDto>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/hotplace")
	private ResponseEntity<?> getAllHotPlace() {
		List<HotPlaceDto> hotplaces = tripService.getAllHotPlace();
		logger.debug("hotplaces.searchAll.............................hotplaces:{}", hotplaces);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("hotplaces", hotplaces);

		if (hotplaces != null && !hotplaces.isEmpty()) {
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
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
	
	@PostMapping("/hotplace")
	private ResponseEntity<?> hotRegi(HotPlaceDto hotplaceDto) {
		tripService.hotRegi(hotplaceDto);
		logger.debug("hotplaceDto.insert.............................hotplaceDto:{}", hotplaceDto);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
