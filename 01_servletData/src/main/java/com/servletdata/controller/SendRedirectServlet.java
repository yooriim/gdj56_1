package com.servletdata.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SendRedirectServlet
 */
@WebServlet("/sendredirect.do")
public class SendRedirectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendRedirectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//sendRedirect() 메소드를 이용하면
		//클라이언트에게 재요청을 하게 만들 수 있다.
		//자동으로 브라우저가 url주소를 변경해서 요청을 하게 됨.
		//
		System.out.println("sendredirect 서블릿 실행!");
		response.sendRedirect("dispatcherview.do");	
		//sendredirect에 한번 응답하게 되면 사용자로부터 받은 data는 증발 ㅋ 
		//-> http 상태 500 내부 서버 오류 생김 
		//(url주소가 바뀌어야 하는 순간. 새로고침해서 어떤 로직이 돌아가면 안될 떄 사용! 입력한 정보를 가지고 계속 뭘 하면 안될 때 
		// sendRedirect사용 (로그아웃할 때, 회원가입할 때-횐정 db에 다 저장하고 주소값이 남아 있으면 안되니까 sendRedirect)
		// sendRedirect 안쓰고 메세지를 주고 페이지 전환해버리는 방법도 있긴 하다~
		//https://jethihmm.tistory.com/264
													
		//dispatcher는 data가 사라지지 않고 계속 유지하면서 다른 요청에 응답. 이점에서 차이가 있다. (대부분의 경우에 request.dispatcer 사용)
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
