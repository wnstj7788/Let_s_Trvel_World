package com.ssafy.plan.model.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.plan.model.dto.DayPlanDto;
import com.ssafy.plan.model.dto.PlaceDto;
import com.ssafy.plan.model.dto.PlanDto;
import com.ssafy.plan.model.mapper.PlanMapper;
import com.ssafy.util.security.JwtService;

@Service
public class PlanService {
	public PlanService() {
		super();
	}

	private PlanMapper planMapper;
	private JwtService jwtService;
	@Autowired
	public PlanService(PlanMapper planMapper,JwtService jwtService) {
		super();
		this.planMapper = planMapper;
		this.jwtService = jwtService;
	}

	public List<PlanDto> getPlanList(int pageNo) {
		return planMapper.getPlanList(pageNo * 9);
	}

	public List<PlanDto> getPlanListByKeyword(String keyword, int pageNo) {
		List<PlanDto> list = planMapper.getPlanListByKeyword(keyword, pageNo * 9);
		return list;
	}

	public List<PlaceDto> getLocationList(int page) {
		List<PlaceDto> list = planMapper.getLocationList(page * 9);
		return list;
	}

	public List<PlaceDto> getLocationListByKeyword(int page, String keyword) {
		List<PlaceDto> list = planMapper.getLocationListByKeyword(page < 0 ? 99999 : page * 9, keyword);
		return list;
	}

	public List<Map<String, Object>> getHotLocation() {
		List<Map<String, Object>> list = planMapper.getHotLocation();
		return list;
	}

	@Transactional
	public int addPlan(PlanDto plan,String token) {
		Map<String, Integer> codes = planMapper.getCode(plan.getRegionName());
		plan.setSidoCode(codes.get("sidoCode"));
		String id = jwtService.getMemberId(token.split(" ")[1]);
		System.out.println(id);
		plan.setUserId(id);
		System.out.println(plan.getUserId());
		int p = planMapper.insertPlan(plan);
		LocalDate startDay = plan.getStartAt();
		int i = 0;
		for (DayPlanDto dto : plan.getPlans()) {
			// 프론트에서 주면 안해도됨
			dto.setDate(startDay.plusDays(i++));
			dto.setPlanId(plan.getId());
			planMapper.insertDayPlan(dto);
		}
		return p;
	}

	public List<Map<String, String>> getRegion() {
		return planMapper.getRegion();
	}

	public List<PlaceDto> getRegionPlace(int sidoCode, int gugunCode) {
		return planMapper.getMapPlace(sidoCode, gugunCode);
	}

	public Map<String, Object> getLocationInfo(int contentId) {
		return planMapper.getLocationInfo(contentId);
	}

	

	public PlanDto getPlanInfo(int planId) {
		PlanDto dto = planMapper.getPlanInfo(planId);
		return dto;
	}
	public List<HashMap<String,Object>> getPlanInfoList(int planId) {
		List<HashMap<String,Object>> plans = planMapper.getDayList(planId);
		return plans;
	}

	public List<PlaceDto> getLocationListByLocationNameAndKeyword(String locationName, String keyword) {
		List<PlaceDto> list = planMapper.getLocationListByLocationNameAndKeyword(locationName, keyword);
		return list;
	}

}
