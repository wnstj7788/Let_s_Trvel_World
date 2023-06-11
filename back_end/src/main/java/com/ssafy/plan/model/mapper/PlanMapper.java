package com.ssafy.plan.model.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.plan.model.dto.DayPlanDto;
import com.ssafy.plan.model.dto.PlaceDto;
import com.ssafy.plan.model.dto.PlanDto;

@Mapper
public interface PlanMapper {
	List<PlanDto> getPlanList(int pageNo);
	List<PlanDto> getPlanListByKeyword(@Param("keyword")String keyword,@Param("pageNo") int pageNo);
	List<HashMap<String,Object>> getDayList(int planId);
	int insertPlan(PlanDto plan);
	int insertDayPlan(DayPlanDto plan);
	PlanDto getPlanInfo(int planId);
	
	List<PlaceDto> getLocationList(int page);
	List<PlaceDto> getLocationListByKeyword(@Param("pageNo") int page,@Param("keyword")String keyword);
	Map<String, Object> getLocationInfo(int contentId);
	List<Map<String, Object>> getHotLocation();
	Map<String, String> getSidoGugun(@Param("sido") String sido, @Param("gugun")String gugun);
	List<Map<String,String>> getRegion();
	List<PlaceDto> getMapPlace(@Param("sido")int sido, @Param("gugun") int gugun);
	Map<String, Integer> getCode(String locationName);
	List<PlaceDto> getLocationListByLocationNameAndKeyword( @Param("locationName")String locationName, @Param("keyword") String keyword);
	

	

	
}
