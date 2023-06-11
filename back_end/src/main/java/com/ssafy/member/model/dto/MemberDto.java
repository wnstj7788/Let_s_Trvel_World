package com.ssafy.member.model.dto;

public class MemberDto {
	private String id;
	private String pw;
	private String userName;
	private String phone;
	private String nickName;
	private String email;
	private String userImg;
	
	public MemberDto() {
		super();
	}
	public MemberDto(String id, String pw, String userName, String phone, String nickName, String email, String userImg) {
		super();
		this.id = id;
		this.pw = pw;
		this.userName = userName;
		this.phone = phone;
		this.nickName = nickName;
		this.email = email;
		this.userImg = userImg;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	
	public String getUserImg() {
		return userImg;
	}
	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "MemberDto [id=" + id + ", pw=" + pw + ", userName=" + userName + ", phone=" + phone + ", nickName="
				+ nickName + ", email=" + email + ", userImg=" + userImg + "]";
	}

	
	
	
	
	
	
	
	
	

}
