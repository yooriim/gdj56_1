package com.model.dao;

import static com.jsp.common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jsp.model.vo.Member;

public class MemberDao {

	public List<Member> searchMemberAll(Connection conn){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Member> list=new ArrayList();
		String sql="SELECT * FROM MEMBER";
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				list.add(Member.builder()
						.memberId(rs.getString("member_id"))
						.memberName(rs.getString("member_name"))
						.gender(rs.getString("gender").charAt(0))
						.age(rs.getInt("age"))
						.email(rs.getString("email"))
						.phone(rs.getString("phone"))
						.address(rs.getString("address"))
						.hobby(rs.getString("hobby").split(","))
						.enrolldate(rs.getDate("enroll_date"))
						.build()
						);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return list;
				
	}
	
	public List<Member> searchMemberName(Connection conn,String name){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Member> result=new ArrayList();				
		String sql="SELECT * FROM MEMBER WHERE member_name LIKE ?";		
//		String sql="SELECT * FROM MEMBER WHERE member_name
//		LIKE '%'||?||'%'";		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,"%"+name+"%");
			rs=pstmt.executeQuery();
			while(rs.next()) {
				result.add(Member.builder()
						.memberId(rs.getString("member_id"))
						.memberName(rs.getString("member_name"))
						.gender(rs.getString("gender").charAt(0))
						.age(rs.getInt("age"))
						.email(rs.getString("email"))
						.phone(rs.getString("phone"))
						.address(rs.getString("address"))
						.hobby(rs.getString("hobby").split(","))
						.enrolldate(rs.getDate("enroll_date"))
						.build()
						);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return result;
	}
}
