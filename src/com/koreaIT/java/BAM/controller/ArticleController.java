package com.koreaIT.java.BAM.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.koreaIT.java.BAM.dto.Article;
import com.koreaIT.java.BAM.util.Util;

public class ArticleController extends Controller {

	List<Article> articles;
	Scanner sc;
	String cmd;

	public ArticleController(Scanner sc) {
		this.articles = new ArrayList<>();
		this.sc = sc;
	}
	
	public void doAction(String cmd, String methodName) {
		this.cmd = cmd;

		switch (methodName) {
		case "list":
			showList(cmd);
			break;
		}

		switch (methodName) {
		case "detail":
			showDetail(cmd);
			break;
		}

		switch (methodName) {
		case "write":
			doWrite();
			break;
		}

		switch (methodName) {
		case "modify":
			doModify(cmd);
			break;
		}

		switch (methodName) {
		case "delete":
			doDelete(cmd);
			break;
		}

	}

	public void doWrite() {
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

	public void showList(String cmd) {

		if (articles.size() == 0) {
			System.out.println("게시글이 없습니다.");
			return; // 리턴으로 함수를 종료시키지만 넘겨주는 값은 없다.
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
				return;
			}
		}

		System.out.println("번호 | 제목 | 작성일              |조회수");
		Collections.reverse(printArticles); // List printArticles를 역순으로 뒤집기

		for (Article article : printArticles) {
			System.out.println(article.id + "    |  " + article.title + " | " + article.regDate + " | " + article.hit);
		}
	}

	public void showDetail(String cmd) {

		String[] commandBits = cmd.split(" ");
		int id = Integer.parseInt(commandBits[2]);

		Article foundArticle = getArticleById(id);

		if (foundArticle == null) {
			System.out.println(id + "번 게시글은 존재하지 않습니다.");
			return;
		}

		foundArticle.increaseHit();
		System.out.println("번호 : " + foundArticle.id);
		System.out.println("작성일 : " + foundArticle.regDate);
		System.out.println("수정일 : " + foundArticle.updateDate);
		System.out.println("조회수 :" + foundArticle.hit);
		System.out.println("제목 : " + foundArticle.title);
		System.out.println("내용 : " + foundArticle.body);
	}

	public void doDelete(String cmd) {

		String[] cmdBits = cmd.split(" ");
		int id = Integer.parseInt(cmdBits[2]);

		Article foundArticle = getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);
			return;
		}

		articles.remove(articles.indexOf(foundArticle));

		System.out.printf("%d번 게시글을 삭제했습니다\n", id);
	}

	public void doModify(String cmd) {

		String[] commandBits = cmd.split(" ");
		int id = Integer.parseInt(commandBits[2]);

		Article foundArticle = getArticleById(id);

		if (foundArticle == null) {
			System.out.println(id + "번 게시글은 존재하지 않습니다.");
			return;
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

	private Article getArticleById(int id) {

		for (Article article : articles) {
			if (article.id == id) {
				return article;
			}
		}

		return null;
	}
	
	public void makeTestDate() {
		articles.add(new Article(1, Util.getNowDate(), Util.getNowDate(), "test title", "test body", 10));
		articles.add(new Article(2, Util.getNowDate(), Util.getNowDate(), "test title", "test body", 20));
		articles.add(new Article(3, Util.getNowDate(), Util.getNowDate(), "test title", "test body", 30));

		System.out.println("테스트 데이터가 생성되었습니다.");
	}
}