package dao;

import java.util.ArrayList;
import java.util.List;

import dto.Member;

public class MemberDao extends Dao {
    public List<Member> members;

    public MemberDao() {
        members = new ArrayList<>();
    }

    public void add(Member member) {
        members.add(member);
        lastId = member.id;
    }

    public int getMemberIndexByLoginId(String loginId) {
        int i = 0;
        for (Member member : members) {
            if (member.loginId.equals(loginId)) {
                return i;
            }

            i++;
        }

        return -1;
    }

    public Member getMemberByLoginId(String loginId) {
        int index = getMemberIndexByLoginId(loginId);

        if (index == -1) {
            return null;
        }

        return members.get(index);
    }

    public String getMemberNameById(int id) {
        for (Member member : members) {
            if (member.id == id) {
                return member.name;
            }
        }

        return "";
    }

}