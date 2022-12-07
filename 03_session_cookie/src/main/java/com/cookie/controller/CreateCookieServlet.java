package com.cookie.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateCookieServlet
 */
@WebServlet("/createcookie.do")
public class CreateCookieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateCookieServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Cookie c=new Cookie("bs","cookie");
		//옵션설정
		c.setMaxAge(60*60*24);
		
		//저장시키는 응답처리
		response.addCookie(c); 
		//이 코드를 읽는 즉시 브라우저는 내가 설정한 bs cookie라는 
		//key value형식의 값을 브라우저 특정공간에 저장
		//쿠키는 다수의 값을 가질 수 있따. (문자열만 되고 객체는 XXXXX)
		
		Cookie c1=new Cookie("readMovie","1|2|3|4");
		//쿠키는 아이디 저장, 최근 본 상품 같은 기능 만들 때 사용
		c1.setMaxAge(60*60*24);
		response.addCookie(c1);
		
		response.setContentType("text/html;charset=utf-8");
		String html="<html>";
		html+="<body>";
		html+="<h2><a href='cookiecheck.do'>쿠키확인하기</a></h2>";
		html+="<h2><a href='cookiedelete.do'>쿠키삭제하기</a></h2>";
		html+="<h2><a href='headerdata.do'>이전페이지확인</a></h2>";
		String admin=getServletContext().getInitParameter("admin");
		String initdata=getInitParameter("headerServlet");
		html+="<h2>context-param : "+admin+"</h2>";
		html+="<h2>init-param : "+initdata+"</h2>";
		html+="</body>";
		html+="</html>";
		
		response.getWriter().write(html);
		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
