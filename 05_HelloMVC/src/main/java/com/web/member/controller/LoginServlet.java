package com.web.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.web.member.service.MemberService;
import com.web.member.vo.Member;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(name="login",urlPatterns = "/login.do")
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
		
		// 로그인 처리 기능하는 서블릿
		// 클라이언트가 보낸 id, password를 가지고
		// DB 일치하는 데이터가 있는지 확인하고 
		// 있으면 데이터를 가져오고
		// 없으면 null값을 가져오는 기능
			
		// 클라이언트가 보낸 id, password를 가지고
		String userId=request.getParameter("userId");
		String password=request.getParameter("password");
			
		// DB 일치하는 데이터가 있는지 확인하고 
		Member m=new MemberService().searchMember(userId,password); //id는 pk이므로 가져오는 데이터는 Member 하나
//		System.out.println(m);
		
		//11/25
		//아이디 저장로직 구현하기
		//클라이언트가 아이디저장 checkbox를 체크했으면 저장 관리 -> on
		//checkbox를 체크하지 않았다면 저장하지 않음! ->null
		String saveId=request.getParameter("saveId");
		System.out.println(saveId);	//null이면 체크 안한거 ! on이면 체크 한거!! 
		
		if(saveId!=null) {
			//아이디 저장 체크했을 때
			Cookie c=new Cookie("saveId",userId);
			c.setMaxAge(60*60*24*7);
			response.addCookie(c);			
		}else {
			Cookie c=new Cookie("saveId","");
			c.setMaxAge(0); //saveId 자체를 없애야 댐  -> 쿠키 삭제할 때는 MaxAge를 0으로 주면 사라진당~!~!~!
			response.addCookie(c);
		}
		
		
//		if(saveId.equals("on")) {
//			Cookie c=new Cookie("id",userId);
//			response.addCookie(c);
//		}
		
		
		
			
		//웹 애플리케이션에서 로그인처리하기
		if(m!=null) {
			//로그인 성공
			HttpSession session=request.getSession(); //세션만들고
			session.setAttribute("loginMember", m); // 세션에 loginMember라는 이름(key)으로 m(value)을 저장 
			
			//응답할 페이지를 구성 -> jsp
			//dispatcher or sendredirect? -> 디스패처 쓰면 로그인하고 새로고침 누르면 계속 로그인창만 돌게됨  
			response.sendRedirect(request.getContextPath());
			
		}else {
			//로그인 실패
			request.setAttribute("msg", "아이디 또는 비밀번호가 일치하지 않습니다.");
			request.setAttribute("loc","/");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
			
			
		}


		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
