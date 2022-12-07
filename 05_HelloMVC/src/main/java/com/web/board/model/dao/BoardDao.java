package com.web.board.model.dao;

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

import com.web.board.model.vo.Board;
import com.web.board.model.vo.BoardComment;

public class BoardDao {
	
	private Properties sql=new Properties();
	public BoardDao() {
		try {
			String path=BoardDao.class.getResource("/sql/board/board_sql.properties").getPath();
			sql.load(new FileReader(path));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<Board> searchAllBoard(Connection conn,int cPage,int numPerpage){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Board> list=new ArrayList();
		try {
			pstmt=conn.prepareStatement(sql.getProperty("searchAllBoard"));
			pstmt.setInt(1, (cPage-1)*numPerpage+1);
			pstmt.setInt(2, cPage*numPerpage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Board b=new Board().builder()
						.boardNo(rs.getInt("BOARD_NO"))
						.title(rs.getString("BOARD_TITLE"))
						.writer(rs.getString("BOARD_WRITER"))
						.content(rs.getString("BOARD_CONTENT"))
						.oriFilename(rs.getString("BOARD_ORIGINAL_FILENAME"))
						.reFilename(rs.getString("BOARD_RENAMED_FILENAME"))
						.enrollDate(rs.getDate("BOARD_DATE"))
						.readCount(rs.getInt("BOARD_READCOUNT"))
						.build();						
				
				list.add(b);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			close(rs);
			close(pstmt);
		}		
		return list;
	}
	
	
	public int selectBoardCount(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectBoardCount"));
			rs=pstmt.executeQuery();
			if(rs.next()) result=rs.getInt(1);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return result;
		
	}
	
	public int writeBoard(Connection conn,Board b) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("writeBoard"));
			pstmt.setString(1, b.getTitle());
			pstmt.setString(2, b.getWriter());
			pstmt.setString(3, b.getContent());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
		
	}
	
	private Board getBoard(ResultSet rs) throws SQLException {
		return Board.builder()
				.boardNo(rs.getInt("board_no"))
				.title(rs.getString("board_title"))
				.writer(rs.getString("board_writer"))
				.content(rs.getString("board_content"))
				.oriFilename(rs.getString("board_original_filename"))
				.reFilename(rs.getString("board_renamed_filename"))
				.enrollDate(rs.getDate("board_date"))
				.readCount(rs.getInt("board_readcount"))
				.build();
		
	}
	
	public Board selectBoard(Connection conn,int boardNo) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Board b=null;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectBoard"));
			pstmt.setInt(1, boardNo);
			rs=pstmt.executeQuery();
			if(rs.next()) b=getBoard(rs);
								
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return b;
		
	}
	
	public int updateReadCount(Connection conn,int boardNo) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("updateReadCount"));
			pstmt.setInt(1, boardNo);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}
	
	public int insertBoardComment(Connection conn, BoardComment bcm) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("insertComment"));
			pstmt.setInt(1, bcm.getBoardCommentLevel());
			pstmt.setString(2, bcm.getBoardCommentWriter());
			pstmt.setString(3, bcm.getBoardCommentContent());
			pstmt.setInt(4, bcm.getBoardRef());
//			pstmt.setInt(5, bcm.getBoardCommentref()==0?null:bcm.getBoardCommentref());
			pstmt.setString(5, bcm.getBoardCommentref()==0?null:String.valueOf(bcm.getBoardCommentref()));
			result=pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}
	
	public List<BoardComment> viewComment(Connection conn,int boardNo){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<BoardComment> result=new ArrayList();
		try {
			pstmt=conn.prepareStatement(sql.getProperty("viewComment"));
			pstmt.setInt(1, boardNo);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				BoardComment bcc=new BoardComment().builder()
						.boardCommentNo(rs.getInt("BOARD_COMMENT_NO"))
						.boardCommentLevel(rs.getInt("BOARD_COMMENT_LEVEL"))
						.boardCommentWriter(rs.getString("BOARD_COMMENT_WRITER"))
						.boardCommentContent(rs.getString("BOARD_COMMENT_CONTENT"))
						.boardRef(rs.getInt("BOARD_REF"))
						.boardCommentref(rs.getInt("BOARD_COMMENT_REF"))
						.boardCommentDate(rs.getDate("BOARD_COMMENT_DATE"))
						.build();			
				result.add(bcc);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return result;
	}
	
	
	private BoardComment getBoardComment(ResultSet rs) throws SQLException{
		return BoardComment.builder()
				.boardCommentNo(rs.getInt("BOARD_COMMENT_NO"))
				.boardCommentLevel(rs.getInt("BOARD_COMMENT_LEVEL"))
				.boardCommentWriter(rs.getString("BOARD_COMMENT_WRITER"))
				.boardCommentContent(rs.getString("BOARD_COMMENT_CONTENT"))
				.boardRef(rs.getInt("BOARD_REF"))
				.boardCommentref(rs.getInt("BOARD_COMMENT_REF"))
				.boardCommentDate(rs.getDate("BOARD_COMMENT_DATE"))
				.build();
	}
	
}
