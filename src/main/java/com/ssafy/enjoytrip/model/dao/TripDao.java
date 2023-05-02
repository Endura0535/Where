package com.ssafy.enjoytrip.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.enjoytrip.model.dto.SidoDto;
import com.ssafy.enjoytrip.model.dto.AttractionDto;
import com.ssafy.enjoytrip.model.dto.DetailDto;
import com.ssafy.enjoytrip.model.dto.GugunDto;
import com.ssafy.enjoytrip.model.dto.HotPlaceDto;

public interface TripDao {

	List<AttractionDto> getAttractionList(int sidoCode, int gugunCode, int contentTypeId) throws SQLException;

	List<SidoDto> getSidoList() throws SQLException;

	List<GugunDto> getGugunList(int sidoCode) throws SQLException;
	
	DetailDto getDetailInfo(int contentId) throws SQLException;
	
	HotPlaceDto getHotPlace(int contentId) throws SQLException;

	List<HotPlaceDto> getAllHotPlace() throws SQLException;
	
	void insertHotPlace(HotPlaceDto hotplaceDto) throws SQLException;
	
	void updateHotPlace(int contentId) throws SQLException;
}
