package com.servletdata.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ShareDataServlet
 */
@WebServlet("/sharedata.do")
public class ShareDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShareDataServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//데이터 저장객체에 데이터를 저장하기 
		//1. HttpServletRequest 객체에 저장 (거의 모든 경우에 사용)
		request.setAttribute("request", "requestData");
		
		//2. HttpSession객체에 저장 (오늘본상품 <-이런 기능 같은 경우에는 session을 사용할 수도 있다.(세션,쿠키,자스에서 제공하는 로컬스토리지) )
		//	1)HttSession객체를 가져와야 함(생성) -> request.getSession();
		HttpSession session=request.getSession();
		session.setAttribute("session", "sessionData");
		
		//3.ServletContext객체에 저장
		//	1)ServletContext객체를 가져와야 함(생성) ->request.getServletContext();
		ServletContext context=request.getServletContext();
		context.setAttribute("context", "contextData");
		
		RequestDispatcher rd=request.getRequestDispatcher("sharedatacheck.do");
		rd.forward(request,response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
