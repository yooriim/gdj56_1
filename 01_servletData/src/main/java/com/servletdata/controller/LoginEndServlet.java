package com.servletdata.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginEndServlet
 */
@WebServlet("/loginend.do")
public class LoginEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId=request.getParameter("userId");
		String password=request.getParameter("password");
		//원래 가져오면 db에 있는 정보랑 같은지 확인해야 하지만 지금은 생략!
		
		//admin/1234가 맞으면 로그인 성공 아니면 실패
		if(userId.equals("admin")&&password.equals("1234")) {
			System.out.println("로그인 성공");
//			HttpSession session=request.getSession();
//			session.setAttribute("userId", userId);
			request.setAttribute("userId", userId);
		}else {
			System.out.println("로그인 실패");
		}
		
//		response.sendRedirect("mainview.do");
		RequestDispatcher rd=request.getRequestDispatcher("mainview.do");
		rd.forward(request, response);	//dispatcher 쓰게 되면 로그인은 되지만 다시 메인으로 돌아갔을 때 로그아웃 상태로 변해버림. 계속 주소가 바뀌기 때문에 ㅠ  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
