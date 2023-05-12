//package com.ssafy.enjoytrip.controller;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import com.ssafy.enjoytrip.model.dto.AreaCodeDto;
//import com.ssafy.enjoytrip.model.dto.AttractionDto;
//import com.ssafy.enjoytrip.model.dto.DetailDto;
//import com.ssafy.enjoytrip.model.dto.GugunDto;
//import com.ssafy.enjoytrip.model.dto.HotPlaceDto;
//import com.ssafy.enjoytrip.model.dto.SidoDto;
//import com.ssafy.enjoytrip.model.service.TripService;
//
//@Controller
//@RequestMapping("/trip")
//public class TripController {
//private static final long serialVersionUID = 1L;
//	
//	@Autowired
//	TripService tripService;
//
//	private static final Logger logger = LoggerFactory.getLogger(TripController.class);
//	
//	@GetMapping("/attr")
//	public String trip(Model model) {
//		List<SidoDto> sido = tripService.getSidoList();
//		model.addAttribute("sido", sido);
//		return "trip/recommendByLocation";
//	}
//	
//	@GetMapping("/attrs")
//	public ResponseEntity<?> tripList(@RequestParam int sidoCode,@RequestParam int gugunCode,@RequestParam int contentTypeId, Model model) {
//		AreaCodeDto areaCode = new AreaCodeDto(sidoCode, gugunCode, contentTypeId);
//		logger.debug("areaCode..........................areaCode:{}",areaCode);
//		List<AttractionDto> attrList = tripService.getAttractionList(areaCode);
//		model.addAttribute("attrList",attrList);
//		
//		Map<String, Object> result = new HashMap<String, Object>();
//		result.put("attrList", attrList);
//		
//		logger.debug("result.............result:{}",result);
//
//		if (attrList != null && !attrList.isEmpty()) {
//			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
//		} else {
//			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
//		}
//		
//	}
//	
//	@GetMapping("/attr/{sidoCode}")
//	public ResponseEntity<?> getGugun(@PathVariable int sidoCode) {
//		logger.debug("sidoCode.............................sidoCode:{}", sidoCode);
//		List<GugunDto> gugunList = tripService.getGugunList(sidoCode);
//		Map<String, Object> result = new HashMap<String, Object>();
//		result.put("gugunList", gugunList);
//
//		if (gugunList != null && !gugunList.isEmpty()) {
//			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
//		} else {
//			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
//		}
//	}
//	
//	@GetMapping("/detail")
//	public ResponseEntity<?> getDetail(@RequestParam int contentId) {
//		logger.debug("contentId.............................contentId:{}", contentId);
//		DetailDto detailDto = tripService.getDetailInfo(contentId);
//		Map<String, Object> result = new HashMap<String, Object>();
//		result.put("detailDto", detailDto);
//
//		if (detailDto != null) {
//			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
//		}else {
//			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
//
//		}
//	}
//	
//	@GetMapping("/hotplace")
//	private String getAllHotPlace(Model model) {
//		List<AttractionDto> hotplaces = tripService.getAllHotPlace();
//		logger.debug("hotplaces.searchAll.............................hotplaces:{}", hotplaces);
//
//		model.addAttribute("hotplaces", hotplaces);
//
//		return "trip/HotPlace";
//	}
//	
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
//	@PostMapping("/hotplace")
//	private ResponseEntity<?> hotRegi(@RequestBody HotPlaceDto hotplaceDto) {
//		tripService.hotRegi(hotplaceDto);
//		logger.debug("hotplaceDto.insert.............................hotplaceDto:{}", hotplaceDto);
//		
//		return new ResponseEntity<Void>(HttpStatus.OK);
//	}
//}
