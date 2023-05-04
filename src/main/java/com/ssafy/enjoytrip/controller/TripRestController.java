//package com.ssafy.enjoytrip.controller;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.ssafy.enjoytrip.model.dto.AreaCodeDto;
//import com.ssafy.enjoytrip.model.dto.AttractionDto;
//import com.ssafy.enjoytrip.model.dto.DetailDto;
//import com.ssafy.enjoytrip.model.dto.GugunDto;
//import com.ssafy.enjoytrip.model.dto.HotPlaceDto;
//import com.ssafy.enjoytrip.model.service.TripService;
//
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//
///**
// * Servlet implementation class tripController
// */
//
//@RestController // REST 서비스를 전용으로 하는 Controller, 모든 메서드는 @ResponseBody를 붙인 효과
//@CrossOrigin(origins = { "*" }, maxAge = 6000) // 다른 서버에서 AJax로 요청이 와도 서비스 되도록
//@RequestMapping("/trip")
//@Api(value = "Trip REST Controller", description = "여행지 정보 ")
//public class TripRestController {
//
//	private static final long serialVersionUID = 1L;
//	
//	@Autowired
//	TripService tripService;
//
//	private static final Logger logger = LoggerFactory.getLogger(TripRestController.class);
//	private static final String SUCCESS = "success";
//	
//	@ApiOperation(value = "여행지 목록 정보", notes = "검색 조건에 맞는 여행지 정보를 검색한 목록 정보")
//	@GetMapping("/attr")
//	public ResponseEntity<?> searchAll(AreaCodeDto areaCode) {
//		logger.debug("areaCode.............................{}", areaCode);
//		List<AttractionDto> attractions = tripService.getAttractionList(areaCode);
//		logger.debug("attractions.searchAll.............................attractions:{}", attractions);
//
//		Map<String, Object> result = new HashMap<String, Object>();
//		result.put("attractions", attractions);
//
//		if (attractions != null && !attractions.isEmpty()) {
//			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
//		} else {
//			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
//		}
//	}
//	
//	@ApiOperation(value = "여행지 목록 정보", notes = "검색 조건에 맞는 여행지 정보를 검색한 목록 정보")
//	@GetMapping("/attr/{sidoCode}")
//	public ResponseEntity<?> getGugun(@PathVariable int sidoCode) {
//		logger.debug("sidoCode.............................sidoCode:{}", sidoCode);
//		List<GugunDto> gugunList = tripService.getGugunList(sidoCode);
//		Map<String, Object> result = new HashMap<String, Object>();
//		result.put("gugunList", result);
//
//		if (gugunList != null && !gugunList.isEmpty()) {
//			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
//		} else {
//			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
//		}
//	}
//	
//	@ApiOperation(value = "여행지 목록 정보", notes = "검색 조건에 맞는 여행지 정보를 검색한 목록 정보")
//	@GetMapping("/detail/{contentId}")
//	public ResponseEntity<?> getDetail(@PathVariable int contentId) {
//		logger.debug("contentId.............................contentId:{}", contentId);
//		DetailDto detailDto = tripService.getDetailInfo(contentId);
//		if (detailDto != null) {
//			return new ResponseEntity<DetailDto>(detailDto, HttpStatus.OK);
//		}else {
//			return new ResponseEntity<DetailDto>(HttpStatus.NO_CONTENT);
//		}
//	}
//	
//	@ApiOperation(value = "여행지 목록 정보", notes = "검색 조건에 맞는 여행지 정보를 검색한 목록 정보")
//	@GetMapping("/hotplace")
//	private ResponseEntity<?> getAllHotPlace() {
//		List<HotPlaceDto> hotplaces = tripService.getAllHotPlace();
//		logger.debug("hotplaces.searchAll.............................hotplaces:{}", hotplaces);
//
//		Map<String, Object> result = new HashMap<String, Object>();
//		result.put("hotplaces", hotplaces);
//
//		if (hotplaces != null && !hotplaces.isEmpty()) {
//			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
//		} else {
//			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
//		}
//	}
//	
//	@ApiOperation(value = "여행지 목록 정보", notes = "검색 조건에 맞는 여행지 정보를 검색한 목록 정보")
//	@GetMapping("/hotplace/{contentId}")
//	private ResponseEntity<?> getHotPlace(@PathVariable int contentId) {
//		DetailDto hotplace = tripService.getHotPlace(contentId);
//		logger.debug("hotplace.search.............................hotplaces:{}", hotplace);
//		
//		if (hotplace != null ) {
//			return new ResponseEntity<DetailDto>(hotplace, HttpStatus.OK);
//		} else {
//			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
//		}
//	}
//	
//	@ApiOperation(value = "여행지 목록 정보", notes = "검색 조건에 맞는 여행지 정보를 검색한 목록 정보")
//	@PostMapping("/hotplace")
//	private ResponseEntity<?> hotRegi(HotPlaceDto hotplaceDto) {
//		tripService.hotRegi(hotplaceDto);
//		logger.debug("hotplaceDto.insert.............................hotplaceDto:{}", hotplaceDto);
//		
//		return new ResponseEntity<Void>(HttpStatus.OK);
//	}
//
//}
