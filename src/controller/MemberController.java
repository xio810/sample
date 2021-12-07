package controller;

import java.util.List;
import java.util.Scanner;

import dto.Member;
import util.Util;

public class MemberController {
    private Scanner sc;
    private List<Member> members;

    public MemberController(Scanner sc, List<Member> members) {
        this.sc = sc;
        this.members = members;
    }

    public void doJoin() {

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
}