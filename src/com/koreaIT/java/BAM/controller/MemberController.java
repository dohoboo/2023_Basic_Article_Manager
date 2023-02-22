package com.koreaIT.java.BAM.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.koreaIT.java.BAM.dto.Member;
import com.koreaIT.java.BAM.util.Util;

public class MemberController extends Controller {

	private int LastMemberId = 3;
	private List<Member> members;
	private Scanner sc;
	
	public MemberController(Scanner sc) {
		this.members = new ArrayList<>();
		this.sc = sc;
	}

	public void doAction(String cmd, String methodName) {
		switch (methodName) {
		case "join":
			doJoin();
			break;
		}
	}

	private void doJoin() {
		int id = LastMemberId + 1;
		LastMemberId = id;
		String regDate = Util.getNowDate();

		String loginId = null;
		while (true) {
			System.out.printf("ID : ");
			loginId = sc.nextLine().trim();

			if (loginId.length() == 0) {
				System.out.println("ID를 입력해주십시오.");
				continue;
			}

			if (loginIdDupChk(loginId) == false) {
				System.out.println(loginId + "은(는) 이미 사용중인 ID입니다.");
				continue;
			}
			System.out.println(loginId + "은(는) 사용 가능한 ID입니다.");
			break;
		}

		String passWord = null;
		String ChkpassWord = null;
		while (true) {
			System.out.printf("비밀번호 : ");
			passWord = sc.nextLine().trim();
			System.out.printf("비밀번호 확인: ");
			ChkpassWord = sc.nextLine();

			if (passWord.length() == 0) {
				System.out.println("비밀번호를 입력해주십시오.");
				continue;
			}

			if (passWord.equals(ChkpassWord) == false) {
				System.out.println("비밀번호를 다시 입력해주세요.");
				continue;
			}
			break;
		}

		String name = null;
		while (true) {
			System.out.printf("이름 : ");
			name = sc.nextLine().trim();

			if (name.length() == 0 || name.length() == 1) {
				System.out.println("이름을 입력해주십시오.");
				continue;
			}
			break;
		}

		Member member = new Member(id, regDate, loginId, passWord, name);
		members.add(member);

		System.out.printf("%s 회원님 환영합니다.\n", loginId);
	}

	private boolean loginIdDupChk(String loginId) {

		for (Member member : members) {
			if (member.loginId.equals(loginId) == true) {
				return false;
			}
		}

		return true;
	}
	
	public void makeTestDate() {
		members.add(new Member(1, Util.getNowDate(),"test1", "1234", "test"));
		members.add(new Member(2, Util.getNowDate(),"test2", "1234", "test"));
		members.add(new Member(3, Util.getNowDate(),"test3", "1234", "test"));

		System.out.println("테스트 회원이 생성되었습니다.");
	}
}