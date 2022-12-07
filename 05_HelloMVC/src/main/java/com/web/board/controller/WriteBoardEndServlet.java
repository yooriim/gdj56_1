package com.web.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.board.model.service.BoardService;
import com.web.board.model.vo.Board;

/**
 * Servlet implementation class WriteBoardEndServlet
 */
@WebServlet("/writeboardEnd.do")
public class WriteBoardEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WriteBoardEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title=request.getParameter("title");
		String writer=request.getParameter("writer");
		String file=request.getParameter("file");
		String content=request.getParameter("content");
//		System.out.println(title);
		
		Board b=Board.builder()
		.title(title)
		.writer(writer)
		.oriFilename(file)
		.content(content)
		.build();
//		System.out.println(b);
		
		int result=new BoardService().writeBoard(b);//		System.out.println(result);
		
		String msg="",loc="";
		if(result>0) {
			msg="게시글 등록 성공";
			loc="/board/viewboard.do?cPage=1";
		}else {
			msg="게시글 등록 실패";
			loc="/board/writeboard.do";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
