package com.web.board.model.service;

import static com.web.common.JDBCTemplate.close;
import static com.web.common.JDBCTemplate.commit;
import static com.web.common.JDBCTemplate.getConnection;
import static com.web.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.web.board.model.dao.BoardDao;
import com.web.board.model.vo.Board;
import com.web.board.model.vo.BoardComment;
import com.web.member.dao.MemberDao;

public class BoardService {
	private BoardDao dao=new BoardDao();

	
	public List<Board> searchAllBoard(int cPage,int numPerpage){
		Connection conn=getConnection();
		List<Board> list=dao.searchAllBoard(conn,cPage,numPerpage);
		close(conn);
		return list;
	}
	
	public int selectBoardCount() {
		Connection conn=getConnection();
		int result=dao.selectBoardCount(conn);
		close(conn);
		return result;
	}
	
	public int writeBoard(Board b) {
		Connection conn=getConnection();
		int result=dao.writeBoard(conn,b);
		close(conn);
		return result;
	}
	
	public Board selectBoard(int boardNo,boolean readflag) {
		Connection conn=getConnection();
		Board b=dao.selectBoard(conn,boardNo);
		
		if(b!=null&&!readflag) {
//			b.setComments(dao.viewComment(conn, boardNo));
			//b.setWriter(new MemberDao().searchMemberId(conn, b.getWriter()));
			//조회수를 증가해주기 !
			//readflag가 false가 나와야(게시글 안읽엇을때 ) 아래 연산 처리
			
			
			int result=dao.updateReadCount(conn,boardNo);
			if(result>0) {
				commit(conn);
				b.setReadCount(b.getReadCount()+1);  //누르자마자 늘어나게 하려면 요러케
			}
			else rollback(conn);
			
		}
		
		close(conn);
		return b;
				
	}
	
	public int insertBoardComment(BoardComment bcm) {
		Connection conn=getConnection();
		int result=dao.insertBoardComment(conn,bcm);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public List<BoardComment> viewComment(int boardNo){
		Connection conn=getConnection();
		List<BoardComment> result=dao.viewComment(conn,boardNo);
		close(conn);
		return result;
		
	}
	
}
