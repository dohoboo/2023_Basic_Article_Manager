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
	private Member loginedMember = null;

	public MemberController(Scanner sc) {
		this.members = new ArrayList<>();
		this.sc = sc;
	}

	public void doAction(String cmd, String methodName) {
		switch (methodName) {
		case "join":
			doJoin();
			break;

		case "login":
			doLogin();
			break;

		case "logout":
			doLogout();
			break;

		case "profile":
			showProfile();
			break;

		default:
			System.out.println("존재하지 않는 명령어입니다.");
			break;
		}
	}

	private void showProfile() {
		if (isLogined() == false) {
			System.out.println("로그인 후 이용해주세요.");
			return;
		}
		
		System.out.println("== 내 정보 ==");
		System.out.println("ID : "+loginedMember.loginId);
		System.out.println("이름 : "+loginedMember.name);
	}

	private void doLogin() {

		if (isLogined()) {
			System.out.println("이미 로그인 상태입니다.");
			return;
		}

		System.out.println("ID : ");
		String loginId = sc.nextLine().trim();
		System.out.println("비밀번호 : ");
		String passWord = sc.nextLine().trim();

		Member member = getMemberByLoginId(loginId);

		if (member == null) {
			System.out.println("존재하지 않는 아이디 입니다");
			return;
		}

		if (member.passWord.equals(passWord) == false) {
			System.out.println("비밀번호를 확인해주세요");
			return;
		}

		loginedMember = member;
		System.out.printf("로그인 성공! %s님 환영합니다\n", member.name);
	}

	private void doLogout() {

		if (isLogined() == false) {
			System.out.println("로그인 상태가 아닙니다.");
			return;
		}

		loginedMember = null;
		System.out.println("로그아웃 되었습니다.");
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

		Member member = getMemberByLoginId(loginId);

		if (member == null) {
			return true;
		}

		return false;
	}

	private Member getMemberByLoginId(String loginId) {

		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return member;
			}
		}
		return null;
	}

	private boolean isLogined() {

		return loginedMember != null;
	}

	public void makeTestDate() {
		members.add(new Member(1, Util.getNowDate(), "test1", "1234", "user1"));
		members.add(new Member(2, Util.getNowDate(), "test2", "1234", "user2"));
		members.add(new Member(3, Util.getNowDate(), "test3", "1234", "user3"));

		System.out.println("테스트 회원이 생성되었습니다.");
	}
}