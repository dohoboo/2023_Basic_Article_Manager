package com.koreaIT.java.BAM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.koreaIT.java.BAM.dto.Article;
import com.koreaIT.java.BAM.util.Util;

public class App {

	private List<Article> articles = new ArrayList();

	public void run() {
		System.out.println("== 프로그램 시작 ==");
		
		makeTestDate();

		Scanner sc = new Scanner(System.in);
		
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

			else if (cmd.equals("article write")) {
				int id = articles.size() + 1;
				String regDate = Util.getNowDate();
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();

				Article article = new Article(id, regDate, regDate, title, body, 0);
				articles.add(article);

				System.out.printf("%d번 글이 생성되었습니다\n", id);

			}

			else if (cmd.equals("article list")) {
				if (articles.size() == 0) {
					System.out.println("게시글이 없습니다.");
					continue;
				}

				System.out.println("번호 | 제목 | 작성일              |조회수");

				for (int i = articles.size() - 1; i >= 0; i--) {
					Article article = articles.get(i);
					System.out.println(
							article.id + "    |  " + article.title + " | " + article.regDate + " | " + article.hit);
				}
			}

			else if (cmd.startsWith("article detail ")) {
				String[] commandBits = cmd.split(" ");
				int id = Integer.parseInt(commandBits[2]);
				Article foundArticle = null;

				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);

					if (article.id == id) {
						foundArticle = article;
						break;
					}
				}

				if (foundArticle == null) {
					System.out.println(id + "번 게시글은 존재하지 않습니다.");
					continue;
				}

				foundArticle.increaseHit();
				System.out.println("번호 : " + foundArticle.id);
				System.out.println("작성일 : " + foundArticle.regDate);
				System.out.println("수정일 : " + foundArticle.updateDate);
				System.out.println("조회수 :" + foundArticle.hit);
				System.out.println("제목 : " + foundArticle.title);
				System.out.println("내용 : " + foundArticle.body);

			}

			else if (cmd.startsWith("article delete ")) {
				String[] commandBits = cmd.split(" ");
				int id = Integer.parseInt(commandBits[2]);
				int foundIndex = -1;

				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);

					if (article.id == id) {
						foundIndex = i;
						break;
					}
				}

				if (foundIndex == -1) {
					System.out.println(id + "번 게시글은 존재하지 않습니다.");
					continue;
				}

				articles.remove(foundIndex);
				System.out.println(id + "번 게시글을 삭제했습니다.");
			}

			else if (cmd.startsWith("article modify ")) {
				String[] commandBits = cmd.split(" ");
				int id = Integer.parseInt(commandBits[2]);
				Article foundArticle = null;

				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);

					if (article.id == id) {
						foundArticle = article;
						break;
					}
				}

				if (foundArticle == null) {
					System.out.println(id + "번 게시글은 존재하지 않습니다.");
					continue;
				}

				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();
				String updateDate = Util.getNowDate();

				foundArticle.title = title;
				foundArticle.body = body;
				foundArticle.updateDate = updateDate;

				System.out.println(id + "번 게시글을 수정했습니다.");
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
