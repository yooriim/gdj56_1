package com.web.member.service;

import static com.web.common.JDBCTemplate.*;


import java.sql.Connection;

import com.web.member.dao.MemberDao;
import com.web.member.vo.Member;


public class MemberService {
	public int insertMember(Member m) {
		Connection conn=getConnection();
		int result=new MemberDao().insertMember(conn,m);
		if(result>0) commit(conn);
		else rollback(conn);
		return result;		
	}
}
