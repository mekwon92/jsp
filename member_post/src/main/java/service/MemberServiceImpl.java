package service;
//dao, service, servlet은 mvc2패턴에서 필수적인....
import java.util.List;

import dao.MemberDao;
import vo.Member;

public class MemberServiceImpl implements MemberService{
	private MemberDao memberDao = MemberDao.getInstance(); //하는일이 같을 때는 싱글턴하는게 좋음
	
	@Override
	public int register(Member member) {
		return memberDao.insert(member);
	}

	@Override
	public Member findBy(String id) {
		
		return memberDao.selectOne(id);
	}

	@Override
	public boolean login(String id, String pw) {
		Member m = findBy(id);
		return m != null && m.getPw().equals(pw);
	}

	@Override
	public List<Member> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modify(Member member) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
