package service;

import container.Container;
import dao.MemberDao;
import dto.Member;

public class MemberService {
    private MemberDao memberDao;

    public MemberService() {
        memberDao = Container.memberDao;
    }

    public Member getMemberByLoginId(String loginId) {
        return memberDao.getMemberByLoginId(loginId);
    }

    public int getMemberIndexByLoginId(String loginId) {
        return memberDao.getMemberIndexByLoginId(loginId);
    }

    public void join(Member member) {
        memberDao.add(member);
    }
}
