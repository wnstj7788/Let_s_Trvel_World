package com.ssafy.plan.model.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DayPlanDto {
	
	private int id;
	private LocalDate date;
	private int planId;
	private List<PlaceDto> places = new ArrayList();
	
	public List<PlaceDto> getPlaces() {
		return places;
	}

	public void setPlaces(List<PlaceDto> places) {
		this.places = places;
	}

	public DayPlanDto() {
	}
	
	public DayPlanDto(int id, LocalDate date,int planId, List<PlaceDto> places) {
		this.id = id;
		this.date = date;
		this.planId = planId;
		this.places = places;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getPlanId() {
		return planId;
	}
	public void setPlanId(int planId) {
		this.planId = planId;
	}

	@Override
	public String toString() {
		return "DayPlanDto [id=" + id + ", date=" + date +   ", planId=" + planId + ", places="
				+ places + "]";
	}
	
}
