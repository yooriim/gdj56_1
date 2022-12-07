package com.web.notice.dao;

import static com.web.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.web.notice.vo.Notice;

public class NoticeDao {
	private Properties sql=new Properties(); 
	
	public NoticeDao() {
		try {
			String path=NoticeDao.class.getResource("/sql/notice/notice_sql.properties").getPath();
			sql.load(new FileReader(path));
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
//	private NoticeDao ndao=new NoticeDao();

	public List<Notice> searchAllNotice(Connection conn,int cPage, int numPerpage) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Notice> list=new ArrayList();
		try {
			pstmt=conn.prepareStatement(sql.getProperty("searchAllNotice"));
			pstmt.setInt(1, (cPage-1)*numPerpage+1);
			pstmt.setInt(2, cPage*numPerpage);
			rs=pstmt.executeQuery();			
			while(rs.next()) {
			Notice n=new Notice().builder()
					.noticeNo(rs.getInt("NOTICE_NO"))
					.noticeTitle(rs.getString("NOTICE_TITLE"))
					.writer(rs.getString("NOTICE_WRITER"))
					.file(rs.getString("FILEPATH"))
					.noticeContent(rs.getString("NOTICE_CONTENT"))
					.noticeDate(rs.getDate("NOTICE_DATE"))
					.build();
			list.add(n);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			close(rs);
			close(pstmt);
		}		
		return list;
	}
	
	public int selectNoticeCount(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectNoticeCount"));
			rs=pstmt.executeQuery();
			if(rs.next()) result=rs.getInt(1);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
				
	}
	
	public int writeNotice(Connection conn, Notice n) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("writeNotice"));
			pstmt.setString(1, n.getNoticeTitle());
			pstmt.setString(2, n.getWriter());
			pstmt.setString(3, n.getNoticeContent());
			pstmt.setString(4, n.getFilepath());
			result=pstmt.executeUpdate();
			
		}catch(SQLException e) {
			
			e.printStackTrace();
		
		}finally {
		
			close(pstmt);
			
		}return result;
	}
	
	public Notice noticeDetail(Connection conn, int no) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;		
		Notice result=null;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("noticeDetail"));
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result=new Notice().builder()
						.noticeNo(rs.getInt("NOTICE_NO"))
						.noticeTitle(rs.getString("NOTICE_TITLE"))
						.writer(rs.getString("NOTICE_WRITER"))
						.file(rs.getString("FILEPATH"))
						.noticeContent(rs.getString("NOTICE_CONTENT"))
						.noticeDate(rs.getDate("NOTICE_DATE"))
						.build();
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return result;
		
	}
	
	
}
