package com.ssafy.enjoytrip.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.enjoytrip.model.dto.SidoDto;
import com.ssafy.enjoytrip.model.dto.AreaCodeDto;
import com.ssafy.enjoytrip.model.dto.AttractionDto;
import com.ssafy.enjoytrip.model.dto.DetailDto;
import com.ssafy.enjoytrip.model.dto.GugunDto;
import com.ssafy.enjoytrip.model.dto.HotPlaceDto;

public interface TripDao {

	List<AttractionDto> getAttractionList(AreaCodeDto areaCodeDto) throws SQLException;

	List<SidoDto> getSidoList() throws SQLException;

	List<GugunDto> getGugunList(int sidoCode) throws SQLException;
	
	DetailDto getDetailInfo(int contentId) throws SQLException;
	
	List<HotPlaceDto> getAllHotPlace() throws SQLException;
	
	void insertHotPlace(HotPlaceDto hotplaceDto) throws SQLException;
}
