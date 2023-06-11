package com.ssafy.plan.model.dto;

public class PlaceDto {
	
	private int contentId;
	private String title;
	private String addr;
	private String firstImage;
	private String firstImage2;
	private String readCount;
	private String sidoName;
	private String sidoCode;
	private String gugunCode;
	private String gugunName;
	private double latitude;
	private double longitude;
	
	
	public String getSidoCode() {
		return sidoCode;
	}
	public void setSidoCode(String sidoCode) {
		this.sidoCode = sidoCode;
	}
	public String getGugunCode() {
		return gugunCode;
	}
	public void setGugunCode(String gugunCode) {
		this.gugunCode = gugunCode;
	}
	@Override
	public String toString() {
		return "PlaceDto [contentId=" + contentId + ", title=" + title + ", addr=" + addr + ", firstImage=" + firstImage
				+ ", firstImage2=" + firstImage2 + ", readCount=" + readCount + ", sidoName=" + sidoName
				+ ", gugunName=" + gugunName + ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}
	public PlaceDto() {
		super();
	}
	
	public PlaceDto(int contentId, String title, String addr, String firstImage, String firstImage2, String readCount,
			String sidoName, String gugunName, double latitude, double longitude) {
		super();
		this.contentId = contentId;
		this.title = title;
		this.addr = addr;
		this.firstImage = firstImage;
		this.firstImage2 = firstImage2;
		this.readCount = readCount;
		this.sidoName = sidoName;
		this.gugunName = gugunName;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	public int getContentId() {
		return contentId;
	}
	public void setContentId(int contentId) {
		this.contentId = contentId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getFirstImage() {
		return firstImage;
	}
	public void setFirstImage(String firstImage) {
		this.firstImage = firstImage;
	}
	public String getFirstImage2() {
		return firstImage2;
	}
	public void setFirstImage2(String firstImage2) {
		this.firstImage2 = firstImage2;
	}
	public String getReadCount() {
		return readCount;
	}
	public void setReadCount(String readCount) {
		this.readCount = readCount;
	}
	public String getSidoName() {
		return sidoName;
	}
	public void setSidoName(String sidoName) {
		this.sidoName = sidoName;
	}
	public String getGugunName() {
		return gugunName;
	}
	public void setGugunName(String gugunName) {
		this.gugunName = gugunName;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	
	
	
	
	

}
