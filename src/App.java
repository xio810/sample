
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controller.ArticleController;
import controller.Controller;
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

        Scanner sc = new Scanner(System.in);

        MemberController memberController = new MemberController(sc);
        ArticleController articleController = new ArticleController(sc);

        articleController.makeTestData();
        memberController.makeTestData();

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

            String[] commandBits = command.split(" ");

            if (commandBits.length == 1) {
                System.out.println("존재하지 않는 명령어 입니다.");
                continue;
            }

            String controllerName = commandBits[0];
            String actionMethondName = commandBits[1];

            Controller controller = null;

            if (controllerName.equals("article")) {
                controller = articleController;
            } else if (controllerName.equals("member")) {
                controller = memberController;
            } else {
                System.out.println("존재하지 않는 명령어 입니다.");
                continue;
            }
            controller.doAction(command, actionMethondName);

        }

        sc.close();

        System.out.println("== 프로그램 끝 ==");
    }

}