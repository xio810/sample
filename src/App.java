
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controller.ArticleController;
import controller.MemberController;
import dto.Article;
import dto.Member;
import util.Util;

public class App {

    private List<Article> articles;
    private List<Member> members;

    public App() {
        articles = new ArrayList<>();
        members = new ArrayList<>();
    }

    public void start() {
        System.out.println("== 프로그램 시작 ==");

        makeTestData();

        Scanner sc = new Scanner(System.in);

        MemberController memberController = new MemberController(sc, members);
        ArticleController articleController = new ArticleController(sc, articles);

        int lastArticleId = 0;

        while (true) {
            System.out.printf("명령어) ");
            String command = sc.nextLine();

            command = command.trim();

            if (command.length() == 0) {
                continue;
            }

            if (command.equals("exit")) {
                break;
            }
            if (command.equals("member join")) {
                memberController.doJoin();

            } else if (command.equals("write")) {
                articleController.doWrite();

            } else if (command.startsWith("list")) {
                articleController.showList(command);

            } else if (command.equals("detail")) {
                articleController.showDetail(command);

            } else if (command.equals("delete")) {
                articleController.doDelete(command);

            } else if (command.startsWith("article modify ")) {
                articleController.doModify(command);

            } else {
                System.out.printf("%s(은)는 존재하지 않는 명령어 입니다.\n", command);
            }
        }

        sc.close();

        System.out.println("== 프로그램 끝 ==");

    }

    private void makeTestData() {
        System.out.println("test data");
        articles.add(new Article(1, Util.getNowDateStr(), "제목1", "내용1", 11));
        articles.add(new Article(2, Util.getNowDateStr(), "제목2", "내용2", 22));
        articles.add(new Article(3, Util.getNowDateStr(), "제목3", "내용3", 33));
    }
}
