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
 * Servlet implementation class UpdateMemberServlet
 */
@WebServlet("/member/UpdateMember.do")
public class UpdateMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateMemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//클라이언트가 전달한 데이터로 해당 회원 정보를 수정한다.
		request.setCharacterEncoding("utf-8");
	
//		System.out.println("z"+Integer.parseInt(request.getParameter("age")));

		Member m=Member.builder()
				.userId(request.getParameter("userId")) //받아온 값을 바로 userId로 저장
				.userName(request.getParameter("userName"))
				.age(Integer.parseInt(request.getParameter("age")))
				.gender(request.getParameter("gender").charAt(0))
				.email(request.getParameter("email"))
				.phone(request.getParameter("phone"))
				.address(request.getParameter("address"))							
				.hobby(request.getParameterValues("hobby"))
				.build();
		
		
		//원하는 값 받아서 vo객체에 넣기 완 
		
		int result=new MemberService().updateMember(m);
		String msg="", loc="";
		if(result>0) {
			msg="회원정보 수정완료";
			loc="/";
			
			//회원정보에서 이름을 수정해도 헤더에 유저이름부분이 안바뀜 ㅠ
			//db에만 변경이 되고 session은 수정이 안됐기 때무넹!
			//session에 저장된 데이터를 변경해주ㅓ야 한다.~! -> 키 똑같이하고 값만 바꿔서 덮어씌우깅 
			request.getSession().setAttribute("loginMember", m);
			
		}else {
			msg="회원정보 수정실패";
			loc="/member/memberView.do?id="+m.getUserId();
			
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
