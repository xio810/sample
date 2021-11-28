import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        int lastArticleId = 0;

        while (true) {
            System.out.println("명령어 입력");
            String command = sc.nextLine();

            command = command.trim();

            if (command.equals("exit")) {
                break;
            }

            if (command.equals("write")) {
                int id = lastArticleId + 1;
                lastArticleId = id;
                System.out.println("제목입력");
                String title = sc.nextLine();
                System.out.println("내용입력");
                String body = sc.nextLine();

                System.out.printf("%d번 글 생성\n", lastArticleId);
            } else if (command.equals("list")) {
                System.out.println("없음");
            } else {
                System.out.println(command + "은(는) 존재하지 않는 명령어 입니다.");
            }
        }
        sc.close();

        System.out.println("프로그램 종료");
    }
}
