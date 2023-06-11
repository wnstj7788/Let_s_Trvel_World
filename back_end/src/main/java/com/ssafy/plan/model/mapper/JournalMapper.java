package com.ssafy.plan.model.mapper;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.plan.model.dto.TourInfoDto;

@Mapper
public interface JournalMapper {
	int insertPlanWrite(TourInfoDto write);
	List<Map<String, Object>> getJournalList(int page);
	List<Map<String, Object>> getJournalListByKeyword(@Param("page") int page,@Param("keyword") String keyword);
	Map<String, Object> getJournalInfo(int journalId);
	
}
