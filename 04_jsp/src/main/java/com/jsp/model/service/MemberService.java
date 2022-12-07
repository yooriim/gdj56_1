package com.jsp.model.service;
import static com.jsp.common.JDBCTemplate.getConnection;
import static com.jsp.common.JDBCTemplate.close;
import java.sql.Connection;
import java.util.List;

import com.jsp.model.vo.Member;
import com.model.dao.MemberDao;
public class MemberService {
	
	private MemberDao dao=new MemberDao();
	
	public List<Member> searchMemberAll(){
		
		Connection conn=getConnection();
		List<Member> result=dao.searchMemberAll(conn);
		close(conn);
		return result;
		
	}
	
	public List<Member> searchMemberName(String name){
		Connection conn=getConnection();
		List<Member> result=dao.searchMemberName(conn,name);
		close(conn);
		return result;
		
	}
	
}
