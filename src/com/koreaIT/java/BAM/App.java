package com.koreaIT.java.BAM;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.koreaIT.java.BAM.dto.Article;
import com.koreaIT.java.BAM.util.Util;

public class App {

	private List<Article> articles = new ArrayList();
	private List<Member> members = new ArrayList();

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

			else if (cmd.startsWith("article list")) {

				if (articles.size() == 0) {
					System.out.println("게시글이 없습니다.");
					continue;
				}

				String searchKeyWord = cmd.substring("article list".length()).trim(); // 명령어를 잘라 담아둘 변수 searchKeyWord 선언

				List<Article> printArticles = new ArrayList<>(articles); // articles를 복사해 담을 printArticles를 선언

				if (searchKeyWord.length() > 0) { // 만일 변수 searchKeyWord의 길이가 0보다 길때,
					System.out.println("검색어 : " + searchKeyWord);

					printArticles.clear(); // 변수 printArticles에 들어있는 값을 비운 뒤 삭제하고,

					for (Article article : articles) { // ArrayList articles를 순회해,
						if (article.title.contains(searchKeyWord)) { // 만일 title에 searchKeyWord를 포함한 articles 객체가 있다면,
							printArticles.add(article); // printArticles에 위의 조건에 부합하는 article 객체를 저장한다.
						}
					}

					if (printArticles.size() == 0) {
						System.out.println("검색 결과가 없습니다.");
						continue;
					}
				}

				System.out.println("번호 | 제목 | 작성일              |조회수");
				Collections.reverse(printArticles); // List printArticles를 역순으로 뒤집기

				for (Article article : printArticles) {
					System.out.println(
							article.id + "    |  " + article.title + " | " + article.regDate + " | " + article.hit);
				}

			}

			else if (cmd.startsWith("article detail ")) {

				String[] commandBits = cmd.split(" ");
				int id = Integer.parseInt(commandBits[2]);

				Article foundArticle = getArticleById(id);

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

				String[] cmdBits = cmd.split(" ");
				int id = Integer.parseInt(cmdBits[2]);

				Article foundArticle = getArticleById(id);

				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);
					continue;
				}

				articles.remove(articles.indexOf(foundArticle));

				System.out.printf("%d번 게시글을 삭제했습니다\n", id);
			}

			else if (cmd.startsWith("article modify ")) {

				String[] commandBits = cmd.split(" ");
				int id = Integer.parseInt(commandBits[2]);

				Article foundArticle = getArticleById(id);

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

			else if (cmd.equals("member join")) {

				int id = 1;
				String regDate = Util.getNowDate();
				System.out.printf("ID : ");
				String loginId = sc.nextLine();
				System.out.printf("비밀번호 : ");
				String passWord = sc.nextLine();
				System.out.printf("이름 : ");
				String name = sc.nextLine();

				Member member = new Member(id, regDate, loginId, passWord, name);
				members.add(member);

				System.out.printf("%s 회원님 환영합니다.\n", name);
			}

			else {
				System.out.println("존재하지 않는 명령어 입니다");
			}

		}

		System.out.println("== 프로그램 끝 ==");

		sc.close();

	}

	private Article getArticleById(int id) {

		for (Article article : articles) {
			if (article.id == id) {
				return article;
			}
		}

		return null;
	}

	void fondArticle() {

	}

	private void makeTestDate() {
		articles.add(new Article(1, Util.getNowDate(), Util.getNowDate(), "test title", "test body", 10));
		articles.add(new Article(2, Util.getNowDate(), Util.getNowDate(), "test title", "test body", 20));
		articles.add(new Article(3, Util.getNowDate(), Util.getNowDate(), "test title", "test body", 30));

		System.out.println("테스트 데이터가 생성되었습니다.");
	}
}

class Member {

	public int id;
	public String regDate;
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
