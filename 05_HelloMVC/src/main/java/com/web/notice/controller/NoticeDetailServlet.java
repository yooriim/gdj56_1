package com.web.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.notice.service.NoticeService;
import com.web.notice.vo.Notice;

/**
 * Servlet implementation class NoticeDetailServlet
 */
@WebServlet("/noticeDetail.do")
public class NoticeDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//
		int no=Integer.parseInt(request.getParameter("noticeNo"));
//		System.out.println(no);
		
		Notice result=new NoticeService().noticeDetail(no);
		
		request.setAttribute("noticeDetail", result);
		request.getRequestDispatcher("/views/notice/noticeDetail.jsp").forward(request, response);
		//글삭되면 noticeNo 사라지니까 분기처리해서 오류메세지 띄울수도잇슴 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
