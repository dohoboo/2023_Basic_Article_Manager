package com.koreaIT.java.BAM.controller;

import java.util.List;
import java.util.Scanner;

import com.koreaIT.java.BAM.dto.Member;
import com.koreaIT.java.BAM.util.Util;

public class MemberController {

	int LastMemberId = 0;

	List<Member> members;
	Scanner sc;

	public MemberController(List<Member> members, Scanner sc) {
		this.members = members;
		this.sc = sc;
	}

	public void doJoin() {
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
}
