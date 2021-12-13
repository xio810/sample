package dao;

import java.util.ArrayList;
import java.util.List;

import dto.Member;

public class MemberDao {
    public List<Member> members;

    public MemberDao() {
        members = new ArrayList<>();
    }
}