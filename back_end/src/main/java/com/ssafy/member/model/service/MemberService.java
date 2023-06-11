package com.ssafy.member.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.member.model.dto.MemberDto;
import com.ssafy.member.model.mapper.MemberMapper;
import com.ssafy.plan.model.dto.PlanDto;
import com.ssafy.util.security.JwtService;

@Service
public class MemberService {

	@Autowired
	private MemberMapper dao;
	@Autowired
	private JwtService jwtService;

	public MemberDto login(String id, String pw) {
		return dao.selectOne(id, pw);
	}

	public String findId(String id) {
		return dao.selectId(id);
	}

	public int save(MemberDto memberInfo) {

		System.out.println(memberInfo.toString());
		return dao.insert(memberInfo);

	}

	public int delete(String token) {
		String id = jwtService.getMemberId(token.split(" ")[1]);
		return dao.delete(id);

	}

	public int checkId(String userId) {
		return dao.selectCheckId(userId);
	}

	public List<Map<String, Object>> getPlanListForJournal(String token) {

		System.out.println(token);
		String id = jwtService.getMemberId(token.split(" ")[1]);
		return dao.getPlanListForJournal(id);
	}

	public MemberDto getUserInfo(String token) {

		String id = jwtService.getMemberId(token.split(" ")[1]);
		return dao.getUserInfo(id);

	}

	public List<PlanDto> getMyPlan(String token) {
		String id = jwtService.getMemberId(token.split(" ")[1]);
		return dao.getMyPlan(id);
	}

	public String getUserImage(String token) {
		System.out.println(token);
		String id = jwtService.getMemberId(token.split(" ")[1]);

		return dao.getUserImage(id);
	}

	public List<Map<String, Object>> getJournalList(String token) {
		String id = jwtService.getMemberId(token.split(" ")[1]);

		return dao.getJournalList(id);
	}

	public int userModify(MemberDto memberInfo, String token) {
		System.out.println(token);
		String id = jwtService.getMemberId(token.split(" ")[1]);
		memberInfo.setId(id);
		System.out.println(memberInfo.toString());
		return dao.modifyUserInfo(memberInfo);

	}

	public List<Map<String, Object>> userJournal(String token) {
		String id = jwtService.getMemberId(token.split(" ")[1]);
		List<Map<String, Object>> list = dao.getUserJournal(id);
		return list;

	}

}
