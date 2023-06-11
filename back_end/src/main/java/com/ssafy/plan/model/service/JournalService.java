package com.ssafy.plan.model.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.plan.model.dto.DayPlanDto;
import com.ssafy.plan.model.dto.PlaceDto;
import com.ssafy.plan.model.dto.PlanDto;
import com.ssafy.plan.model.dto.TourInfoDto;
import com.ssafy.plan.model.mapper.JournalMapper;
import com.ssafy.plan.model.mapper.PlanMapper;
import com.ssafy.util.security.JwtService;

@Service
public class JournalService {
	public JournalService() {
		super();
	}

	private JournalMapper journalMapper;
	private JwtService jwtService;
	@Autowired
	public JournalService(JournalMapper journalMapper,JwtService jwtService) {
		super();
		this.journalMapper = journalMapper;
		this.jwtService = jwtService;
	}
	

	public int setPlanWrite(TourInfoDto write,String token) {
		String id = jwtService.getMemberId(token.split(" ")[1]);
		write.setUserId(id);
		return journalMapper.insertPlanWrite(write);
	}
	
	public List<Map<String, Object>> getJournalList(int page) {
		return journalMapper.getJournalList(page * 9);
	}

	public List<Map<String, Object>> getJournalListByKeyword(int page, String keyword) {
		return journalMapper.getJournalListByKeyword(page * 9, keyword);
	}

	public Map<String, Object> getJournalInfo(int journalId) {
		return journalMapper.getJournalInfo(journalId);
	}
}
