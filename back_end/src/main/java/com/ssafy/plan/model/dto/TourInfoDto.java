package com.ssafy.plan.model.dto;

public class TourInfoDto {
	private int id;
	private String title;
	private String content;
	private String userId;
	private String PlanId;
	private int readcount;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPlanId() {
		return PlanId;
	}
	public void setPlanId(String planId) {
		PlanId = planId;
	}
	public int getReadcount() {
		return readcount;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	public TourInfoDto(int id, String title, String content, String userId, String planId, int readcount) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.userId = userId;
		PlanId = planId;
		this.readcount = readcount;
	}
	
	public TourInfoDto() {
		super();
	}
	@Override
	public String toString() {
		return "TourInfoDto [id=" + id + ", title=" + title + ", content=" + content + ", userId=" + userId
				+ ", PlanId=" + PlanId + ", readcount=" + readcount + "]";
	}
	
	

	
}
