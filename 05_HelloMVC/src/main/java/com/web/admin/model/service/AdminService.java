package com.web.admin.model.service;

import static com.web.common.JDBCTemplate.close;
import static com.web.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.web.admin.model.dao.AdminDao;
import com.web.member.vo.Member;

public class AdminService {
	
	private AdminDao dao=new AdminDao();
	
	public List<Member> searchAllMember(int cPage,int numPerpage) {
		Connection conn=getConnection();
		List<Member> result=dao.searchAllMember(conn,cPage,numPerpage);
		close(conn);
		return result;	
		
	}
	
	public int selectMemberCount() {
		Connection conn=getConnection();
		int result=dao.selectMemberCount(conn);
		close(conn);
		return result;
				
	}
	
	public List<Member> searchMemberList(int cPage,int numPerpage,String type, String keyword){
		Connection conn=getConnection();
		List<Member> list=dao.searchMemberList(conn,cPage,numPerpage,type,keyword);
		close(conn);
		return list;
	}
	
	public int selectMemberCount(String type, String keyword) {
		Connection conn=getConnection();
		int result=dao.selectMemberCount(conn,type,keyword);
		close(conn);
		return result;
	}
	

	
}
