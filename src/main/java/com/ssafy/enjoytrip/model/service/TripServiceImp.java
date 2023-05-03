package com.ssafy.enjoytrip.model.service;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.model.dao.TripDao;
import com.ssafy.enjoytrip.model.dto.AreaCodeDto;
import com.ssafy.enjoytrip.model.dto.AttractionDto;
import com.ssafy.enjoytrip.model.dto.DetailDto;
import com.ssafy.enjoytrip.model.dto.GugunDto;
import com.ssafy.enjoytrip.model.dto.HotPlaceDto;
import com.ssafy.enjoytrip.model.dto.SidoDto;

@Service
public class TripServiceImp implements TripService {
	
	TripDao dao;

	public TripServiceImp(TripDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public List<AttractionDto> getAttractionList(AreaCodeDto areaCode) {
		try {
			return dao.getAttractionList(areaCode);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public List<GugunDto> getGugunList(int sidoCode) {
		try {
			return dao.getGugunList(sidoCode);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public List<SidoDto> getSidoList() {
		try {
			return dao.getSidoList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public DetailDto getDetailInfo(int contentId) {
		try {
			return dao.getDetailInfo(contentId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
			// TODO: handle exception
		}

	}
	
	@Override
	public List<HotPlaceDto> getAllHotPlace() {
		try {
			List<HotPlaceDto> hotplace = dao.getAllHotPlace();
			Collections.sort(hotplace, (o1,o2)->{
				return o2.getLikes() - o1.getLikes();
 			});
			return hotplace;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public DetailDto getHotPlace(int contentId) {
		try {
			DetailDto attraction = dao.getDetailInfo(contentId);
			return attraction;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void hotRegi(HotPlaceDto hotplace) {
		try{
			dao.insertHotPlace(hotplace);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
