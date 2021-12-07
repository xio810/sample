
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
                int id = members.size() + 1;
                String regDate = Util.getNowDateStr();

                String loginId = null;
                while (true) {

                    System.out.printf("아이디 : ");
                    loginId = sc.nextLine();

                    if (isJoinableLoginId(loginId) == false) {
                        System.out.println("해당 아이디는 사용중 입니다.");
                        continue;
                    }

                    break;
                }

                String loginPw = null;
                String loginPwConfirm = null;

                while (true) {

                    System.out.printf("비밀번호 : ");
                    loginPw = sc.nextLine();
                    System.out.printf("비밀번호 확인 : ");
                    loginPwConfirm = sc.nextLine();

                    if (loginPw.equals(loginPwConfirm) == false) {
                        System.out.println("비밀번호가 일치하지 않습니다.");
                        continue;

                    }
                    break;
                }

                System.out.println("이름 : ");
                String name = sc.nextLine();

                Member member = new Member(id, regDate, loginId, loginPw, name);
                members.add(member);

                System.out.printf("%d번 회원이 가입되었습니다.\n", id);
            } else if (command.equals("write")) {
                int id = articles.size() + 1;
                String regDate = Util.getNowDateStr();
                System.out.printf("제목 : ");
                String title = sc.nextLine();
                System.out.printf("내용 : ");
                String body = sc.nextLine();

                Article article = new Article(id, regDate, title, body);
                articles.add(article);

                System.out.printf("%d번글이 생성되었습니다.\n", id);

            } else if (command.startsWith("list")) {

                if (articles.size() == 0) {
                    System.out.println("게시물이 없습니다.");
                    continue;
                }

                String searchKeyword = command.substring("list".length()).trim();

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
                        continue;
                    }

                }

                System.out.println("번호 | 조회 | 제목");
                for (int i = 0; i < forListArticles.size(); i++) {
                    Article article = forListArticles.get(i);

                    System.out.printf("%4d | %4d | %s\n", article.id, article.views, article.title);
                }

            } else if (command.equals("detail")) {
                System.out.println("번호입력");
                int id = sc.nextInt();

                Article foundArticle = getArticleById(id);

                if (foundArticle == null) {
                    System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
                    continue;
                }
                foundArticle.increaseViews();

                System.out.printf("번호 : %d\n", foundArticle.id);
                System.out.printf("날짜 : %s\n", foundArticle.regDate);
                System.out.printf("제목 : %s\n", foundArticle.title);
                System.out.printf("내용 : %s\n", foundArticle.body);
                System.out.printf("조회 : %s\n", foundArticle.views);

            } else if (command.equals("delete")) {
                System.out.println("번호입력");
                int id = sc.nextInt();

                int foundIndex = getArticleIndexById(id);

                if (foundIndex == -1) {
                    System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
                    continue;
                }
                articles.remove(foundIndex);

                System.out.printf("%d번 게시물이 삭제되었습니다.\n", id);
            } else if (command.startsWith("article modify ")) {
                String[] commandBits = command.split(" ");
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
                    System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
                    continue;
                }

                System.out.printf("제목 : ");
                String title = sc.nextLine();
                System.out.printf("내용 : ");
                String body = sc.nextLine();

                foundArticle.title = title;
                foundArticle.body = body;

                System.out.printf("%d번 게시물이 수정되었습니다.\n", id);
            } else {
                System.out.printf("%s(은)는 존재하지 않는 명령어 입니다.\n", command);
            }
        }

        sc.close();

        System.out.println("== 프로그램 끝 ==");
    }

    private boolean isJoinableLoginId(String loginId) {

        int index = getMemberIndexByLoginId(loginId);

        if (index == -1) {
            return true;
        }
        return false;
    }

    private int getMemberIndexByLoginId(String loginId) {
        int i = 0;
        for (Member member : members) {
            if (member.loginId.equals(loginId)) {
                return i;
            }
        }
        return -1;
    }

    private Article getArticleById(int id) {
        int index = getArticleIndexById(id);

        if (index != -1) {
            return articles.get(index);
        }

        return null;
    }

    private int getArticleIndexById(int id) {

        for (int i = 0; i < articles.size(); i++) {
            Article article = articles.get(i);

            if (article.id == id) {
                return i;
            }

        }
        return -1;
        /*
         * int i = 0;
         * for (Article article : articles) {
         * if (article.id == id) {
         * return i;
         * }
         * i++;
         * }
         * 
         * return -1;
         */
    }

    private void makeTestData() {
        System.out.println("test data");
        articles.add(new Article(1, Util.getNowDateStr(), "제목1", "내용1", 11));
        articles.add(new Article(2, Util.getNowDateStr(), "제목2", "내용2", 22));
        articles.add(new Article(3, Util.getNowDateStr(), "제목3", "내용3", 33));
    }
}
