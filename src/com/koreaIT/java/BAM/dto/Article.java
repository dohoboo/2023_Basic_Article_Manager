package com.koreaIT.java.BAM.dto;

public class Article extends Dto {

	public String updateDate;
	public int memberId;
	public String title;
	public String body;
	public int hit;

	public Article(int id, String regDate, String updateDate, int memberId, String title, String body, int hit) {
		this.id = id;
		this.regDate = regDate;
		this.updateDate = updateDate;
		this.memberId = memberId;
		this.title = title;
		this.body = body;
		this.hit = hit;
	}

	public void increaseHit() {
		this.hit++;
	}
}
