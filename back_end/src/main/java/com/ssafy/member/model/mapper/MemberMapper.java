package com.ssafy.member.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.member.model.dto.MemberDto;
import com.ssafy.plan.model.dto.PlanDto;

@Mapper
public interface MemberMapper {

	MemberDto selectOne(@Param("id")String id,@Param("pw") String pw);
	
	String selectId(String id);
	
	int insert(MemberDto memberInfo);
	
	int delete(String id);
	
	int selectCheckId(String userId);

	List<Map<String,Object>> getPlanListForJournal(String userId);

	MemberDto getUserInfo(String id);

	List<PlanDto> getMyPlan(String id);

	String getUserImage(String memberId);

	List<Map<String, Object>> getJournalList(String id);
	
	int modifyUserInfo(MemberDto memberInfo);

	List<Map<String, Object>> getUserJournal(String id);
}
