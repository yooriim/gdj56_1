package com.web.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.web.member.service.MemberService;
import com.web.member.vo.Member;

/**
 * Servlet implementation class DeleteMemberServlet
 */
@WebServlet("/member/DeleteMember.do")
public class DeleteMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteMemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Member m=Member.builder().userId(request.getParameter("userId")).build();
		int result=new MemberService().deleteMember(m);
		
		//한번 잡아주는... 알림창 띄우기
		
		String msg="", loc="";
		
		if(result>0) {
			msg="잘가고~";
			loc="/logout.do";
		}else {
			msg="넌못가~";
			loc="/";
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
