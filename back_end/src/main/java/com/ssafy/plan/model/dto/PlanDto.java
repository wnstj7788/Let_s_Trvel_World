package com.ssafy.plan.model.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PlanDto {
	private int id;
	private String userId;
	private LocalDate startAt;
	private LocalDate endAt;
	private String regionName;
	private int sidoCode;
	private String title;
	private String userName;
	private List<DayPlanDto> plans = new ArrayList<DayPlanDto>();
	
	public PlanDto() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public LocalDate getStartAt() {
		return startAt;
	}

	public void setStartAt(LocalDate startAt) {
		this.startAt = startAt;
	}

	public LocalDate getEndAt() {
		return endAt;
	}

	public void setEndAt(LocalDate endAt) {
		this.endAt = endAt;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public int getSidoCode() {
		return sidoCode;
	}

	public void setSidoCode(int sidoCode) {
		this.sidoCode = sidoCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<DayPlanDto> getPlans() {
		return plans;
	}

	public void setPlans(List<DayPlanDto> plans) {
		this.plans = plans;
	}

	public PlanDto(int id, String userId, LocalDate startAt, LocalDate endAt, String regionName, int sidoCode,
			String title, String userName, List<DayPlanDto> plans) {
		super();
		this.id = id;
		this.userId = userId;
		this.startAt = startAt;
		this.endAt = endAt;
		this.regionName = regionName;
		this.sidoCode = sidoCode;
		this.title = title;
		this.userName = userName;
		this.plans = plans;
	}
	
	
	
	
}
