package com.web.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.board.model.service.BoardService;
import com.web.board.model.vo.Board;
import com.web.board.model.vo.BoardComment;

/**
 * Servlet implementation class BoardDetailServlet
 */
@WebServlet("/board/boardDetail.do")
public class BoardDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int boardNo=Integer.parseInt(request.getParameter("boardNo"));
		
		//
		//조회수 계속 안올라가게 하기 (여기가 젤 처음)
		Cookie[] cookies=request.getCookies();
		String boardRead="";
		boolean readflag=false;
		if(cookies!=null) {
			for(Cookie c: cookies) {
				String name=c.getName(); // key값
				String value=c.getValue(); // value값
				if(name.equals("boardRead")) {
					boardRead=value;
					if(value.contains("|"+boardNo+"|")) {
						readflag=true;						
					}
					
					break;
					
				}
				
			}
			
		}
		if(!readflag) {
			//readflag가 트루면 읽은거! false이면 안읽은거!
			//쿠키에 현재 게시글 번호 저장
			Cookie c=new Cookie("boardRead",(boardRead+"|"+boardNo+"|"));
			c.setMaxAge(60*60*24);
			response.addCookie(c);
		}
		
		//
		
		Board b=new BoardService().selectBoard(boardNo,readflag);
		
		List<BoardComment> comm=new BoardService().viewComment(boardNo);	
				
		request.setAttribute("comm", comm);
		request.setAttribute("board", b);
		request.getRequestDispatcher("/views/board/boardDetail.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
