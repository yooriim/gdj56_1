package com.web.notice.service;

import static com.web.common.JDBCTemplate.getConnection;
import static com.web.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.web.notice.dao.NoticeDao;
import com.web.notice.vo.Notice;

public class NoticeService {
	private NoticeDao dao=new NoticeDao();

	
	public List<Notice> searchAllNotice(int cPage,int numPerpage){
		Connection conn=getConnection();
		List<Notice> n=dao.searchAllNotice(conn,cPage,numPerpage);
		close(conn);		
		return n;
	}
	
	public int selectNoticeCount() {
		Connection conn=getConnection();
		int result=dao.selectNoticeCount(conn);
		close(conn);
		return result;
	}
	
	public int writeNotice(Notice n) {
		Connection conn=getConnection();
		int result=dao.writeNotice(conn,n);
		close(conn);
		return result;
	}
	
	public Notice noticeDetail(int no) {
		Connection conn=getConnection();
		Notice result=dao.noticeDetail(conn,no);
		close(conn);
		return result;
	}
	
}
