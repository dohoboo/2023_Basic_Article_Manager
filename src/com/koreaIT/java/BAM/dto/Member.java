package com.koreaIT.java.BAM.dto;

public class Member extends Dto {
	
	public String loginId;
	public String passWord;
	public String name;

	public Member(int id, String regDate, String loginId, String passWord, String name) {
		this.id = id;
		this.regDate = regDate;
		this.loginId = loginId;
		this.passWord = passWord;
		this.name = name;
	}
}
