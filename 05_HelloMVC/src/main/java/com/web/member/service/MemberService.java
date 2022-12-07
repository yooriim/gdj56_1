package com.web.member.service;
import static com.web.common.JDBCTemplate.close;
import static com.web.common.JDBCTemplate.commit;
import static com.web.common.JDBCTemplate.getConnection;
import static com.web.common.JDBCTemplate.rollback;

import java.sql.Connection;

import com.web.member.dao.MemberDao;
import com.web.member.vo.Member;

public class MemberService {
	
	private MemberDao dao=new MemberDao();
	
	public Member searchMember(String userId, String password) {
		Connection conn=getConnection();
		Member m=dao.searchMember(conn,userId,password);
		close(conn);
		return m;
	}
	 
	public int insertMember(Member m) {
		Connection conn=getConnection();
		int result=dao.insertMember(conn,m);
		if(result>0) commit(conn);
		else rollback(conn);
		return result;
	}
	
	public Member searchMemberId(String userId) {
		Connection conn=getConnection();
		Member m=dao.searchMemberId(conn,userId);
		close(conn);
		return m;
	}
	
	public int updateMember(Member m) {
		Connection conn=getConnection();
		int result=dao.updateMember(conn,m);
		if(result>0) commit(conn);
		else rollback(conn);
		return result;
	}
	
	public int deleteMember(Member m) {
		//Member 말고 id만 받아도 삭제가 가능하긴함
		Connection conn=getConnection();
		int result=dao.deleteMember(conn,m);
		if(result>0) commit(conn);
		else rollback(conn);
		return result;				
	}
	
	public int updatePassword(String userId, String password) {
		Connection conn=getConnection();
		int result=dao.updatePassword(conn,userId,password);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	
}
