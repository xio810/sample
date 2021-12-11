package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dto.Article;
import util.Util;

public class ArticleController extends Controller {
    private Scanner sc;
    private List<Article> articles;
    private String command;
    private String actionMethodName;

    public ArticleController(Scanner sc) {
        this.sc = sc;

        articles = new ArrayList<Article>();
    }

    public void doAction(String command, String actionMethodName) {
        this.command = command;
        this.actionMethodName = actionMethodName;

        switch (actionMethodName) {
            case "list":
                showList();
                break;
            case "detail":
                showDetail();
                break;
            case "write":
                doWrite();
                break;
            case "modify":
                doModify();
                break;
            case "delete":
                doDelete();
                break;
        }
    }

    public void makeTestData() {
        System.out.println("테스트를 위한 데이터를 생성합니다.");

        articles.add(new Article(1, Util.getNowDateStr(), "제목1", "내용1", 10));
        articles.add(new Article(2, Util.getNowDateStr(), "제목2", "내용2", 22));
        articles.add(new Article(3, Util.getNowDateStr(), "제목3", "내용3", 33));
    }

    private void doWrite() {
        int id = articles.size() + 1;
        String regDate = Util.getNowDateStr();
        System.out.printf("제목 : ");
        String title = sc.nextLine();
        System.out.printf("내용 : ");
        String body = sc.nextLine();

        Article article = new Article(id, regDate, title, body);
        articles.add(article);

        System.out.printf("%d번 글이 생성되었습니다.\n", id);
    }

    private void showList() {
        if (articles.size() == 0) {
            System.out.println("게시물이 없습니다.");
            return;
        }

        String searchKeyword = command.substring("article list".length()).trim();

        List<Article> forListArticles = articles;

        if (searchKeyword.length() > 0) {
            forListArticles = new ArrayList<>();

            for (Article article : articles) {
                if (article.title.contains(searchKeyword)) {
                    forListArticles.add(article);
                }
            }

            if (articles.size() == 0) {
                System.out.println("검색결과가 존재하지 않습니다.");
                return;
            }
        }

        System.out.println("번호 | 조회 | 제목");
        for (int i = forListArticles.size() - 1; i >= 0; i--) {
            Article article = forListArticles.get(i);

            System.out.printf("%4d | %4d | %s\n", article.id, article.views, article.title);
        }
    }

    private void showDetail() {
        String[] commandBits = command.split(" ");
        int id = Integer.parseInt(commandBits[2]);

        Article foundArticle = getArticleById(id);

        if (foundArticle == null) {
            System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
            return;
        }

        foundArticle.increaseViews();

        System.out.printf("번호 : %d\n", foundArticle.id);
        System.out.printf("날짜 : %s\n", foundArticle.regDate);
        System.out.printf("제목 : %s\n", foundArticle.title);
        System.out.printf("내용 : %s\n", foundArticle.body);
        System.out.printf("조회 : %d\n", foundArticle.views);
    }

    private Article getArticleById(int id) {
        int index = getArticleIndexById(id);

        if (index != -1) {
            return articles.get(index);
        }

        return null;
    }

    private void doModify() {
        String[] commandBits = command.split(" ");
        int id = Integer.parseInt(commandBits[2]);

        Article foundArticle = getArticleById(id);

        if (foundArticle == null) {
            System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
            return;
        }

        System.out.printf("제목 : ");
        String title = sc.nextLine();
        System.out.printf("내용 : ");
        String body = sc.nextLine();

        foundArticle.title = title;
        foundArticle.body = body;

        System.out.printf("%d번 게시물이 수정되었습니다.\n", id);
    }

    private int getArticleIndexById(int id) {
        int i = 0;
        for (Article article : articles) {
            if (article.id == id) {
                return i;
            }
            i++;
        }

        return -1;
    }

    private void doDelete() {
        String[] commandBits = command.split(" ");
        int id = Integer.parseInt(commandBits[2]);

        int foundIndex = getArticleIndexById(id);

        if (foundIndex == -1) {
            System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
            return;
        }

        articles.remove(foundIndex);
        System.out.printf("%d번 게시물이 삭제되었습니다.\n", id);
    }
}