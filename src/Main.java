import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("== 프로그램 시작 ==");

		Scanner sc = new Scanner(System.in);

		int lastArticleId = 0;

		while (true) {
			System.out.printf("명령어 : ");
			String cmd = sc.nextLine();

			if (cmd.equals("article list")) {
				System.out.println("게시글이 없습니다.");
			}

			if (cmd.equals("article write")) {
				int id = lastArticleId+1;

				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();
				System.out.println("제목 : " + title);
				System.out.println("내용 : " + body);
				lastArticleId++;
				
				System.out.println(id + "번 글이 생성되었습니다.");
				
				continue;
			}

			if (cmd.length() == 0) {
				System.out.println("명령어를 입력해주세요.");
				continue;
			}

			else {
				System.out.println("존재하지 않는 명령어입니다.");
			}

			if (cmd.equals("exit")) {
				break;
			}
		}

		System.out.println("== 프로그램 끝 ==");

		sc.close();
	}
}