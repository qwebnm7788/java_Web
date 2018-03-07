package spms.dao;

import java.util.List;
import java.util.Map;

import spms.vo.Member;

public interface MemberDao {
	public List<Member> selectList(Map<String, Object> paramMap) throws Exception;
	public int insert(Member member) throws Exception;
	public Member selectOne(int no) throws Exception;
	public int update(Member member) throws Exception;
	public int delete(int no) throws Exception;
	public Member exist(String email, String password) throws Exception;
}
