package com.servletdata.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//post방식으로 알파벳, 숫자를 제외한 문자(예시 : 한글)를 받았을 경우 
		//데이터에 대한 인코딩 처리를 해줘야 한다.
		//파라미터로 받아오기 전에 인코딩 설정을 해줘야 함.
		request.setCharacterEncoding("utf-8");
		
		
		
		//클라이언트가 보낸 데이터 확인하기
		//HttpServletRequest 클래스가 제공하는 메소드를 이용해서 
		//데이터를 가져올 수 있음!!
		//getParameter("name속성의 값을 String으로 입력") -> String으로 value값을 반환해줌
		//그래서 input 태그에 name속성을 반드시 설정해줘야 함
		System.out.println("//get방식//");
		
		String userId=request.getParameter("userId");
		System.out.println(userId);
		String password=request.getParameter("password");
		System.out.println(password);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("//post방식//");
		doGet(request,response);
//		String userId=request.getParameter("userId");
//		String password=request.getParameter("password");
//		System.out.println(userId);
//		System.out.println(password);
	}

}
