package com.web.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.member.service.MemberService;
import com.web.member.vo.Member;

/**
 * Servlet implementation class MemberEnrollEnd
 */
@WebServlet("/memberenrollend.do")
public class MemberEnrollEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberEnrollEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String id=request.getParameter("userId");
		String pw=request.getParameter("password");
		String name=request.getParameter("userName");
		int age=Integer.parseInt(request.getParameter("age"));
		String email=request.getParameter("email");
		String phone=request.getParameter("phone");
		String address=request.getParameter("address");		
		char gender=request.getParameter("gender").charAt(0);
		String[] hobby=request.getParameterValues("hobby");
		
		Member m=Member.builder()
				.userid(id)
				.password(pw)
				.username(name)
				.age(age)
				.email(email)
				.phone(phone)
				.address(address)
				.gender(gender)
				.hobby(hobby)
				.build();
//		System.out.println(m);
		
		int result=new MemberService().insertMember(m);
		
		
		String msg="";
		String loc="";
		if(result>0) {
			msg="회원가입 성공";
			loc="/";
		}else {
			msg="회원가입 실패";
			loc="/memberenroll.do";
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
