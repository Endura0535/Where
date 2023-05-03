package com.ssafy.enjoytrip.model.service;

import java.util.List;

import com.ssafy.enjoytrip.model.dto.AreaCodeDto;
import com.ssafy.enjoytrip.model.dto.AttractionDto;
import com.ssafy.enjoytrip.model.dto.DetailDto;
import com.ssafy.enjoytrip.model.dto.GugunDto;
import com.ssafy.enjoytrip.model.dto.HotPlaceDto;
import com.ssafy.enjoytrip.model.dto.SidoDto;


public interface TripService {

	List<AttractionDto> getAttractionList(AreaCodeDto areaCode);

	List<SidoDto> getSidoList();

	List<GugunDto> getGugunList(int sidoCode);

	DetailDto getDetailInfo(int contentId);
	
	public List<HotPlaceDto> getAllHotPlace();
	
	public DetailDto getHotPlace(int contentId);
	
	public void hotRegi(HotPlaceDto hotPlace);
}
