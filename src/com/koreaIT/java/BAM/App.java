package com.koreaIT.java.BAM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.koreaIT.java.BAM.controller.ArticleController;
import com.koreaIT.java.BAM.controller.MemberController;
import com.koreaIT.java.BAM.dto.Article;
import com.koreaIT.java.BAM.dto.Member;
import com.koreaIT.java.BAM.util.Util;

public class App {

	private List<Article> articles = new ArrayList();
	private List<Member> members = new ArrayList();

	public void run() {
		System.out.println("== 프로그램 시작 ==");

		makeTestDate();

		Scanner sc = new Scanner(System.in);

		MemberController memberController = new MemberController(members, sc);
		ArticleController articleController = new ArticleController(articles, sc);

		while (true) {

			System.out.printf("명령어) ");
			String cmd = sc.nextLine().trim();

			if (cmd.length() == 0) {
				System.out.println("명령어를 입력해주세요");
				continue;
			}

			if (cmd.equals("exit")) {
				break;
			}

			if (cmd.equals("member join")) {
				memberController.doJoin();
			}

			else if (cmd.equals("article write")) {
				articleController.doWrite();
			}

			else if (cmd.startsWith("article list")) {
				articleController.showList(cmd);
			}

			else if (cmd.startsWith("article detail ")) {
				articleController.showDetail(cmd);
			}

			else if (cmd.startsWith("article delete ")) {
				articleController.doDelete(cmd);
			}

			else if (cmd.startsWith("article modify ")) {
				articleController.doModify(cmd);
			}

			else {
				System.out.println("존재하지 않는 명령어 입니다");
			}

		}

		System.out.println("== 프로그램 끝 ==");

		sc.close();

	}

	private void makeTestDate() {
		articles.add(new Article(1, Util.getNowDate(), Util.getNowDate(), "test title", "test body", 10));
		articles.add(new Article(2, Util.getNowDate(), Util.getNowDate(), "test title", "test body", 20));
		articles.add(new Article(3, Util.getNowDate(), Util.getNowDate(), "test title", "test body", 30));

		System.out.println("테스트 데이터가 생성되었습니다.");
	}
}
