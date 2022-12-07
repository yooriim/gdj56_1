package com.jsp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.model.service.MemberService;
import com.jsp.model.vo.Member;

/**
 * Servlet implementation class SearchMemberNameServlet
 */
@WebServlet("/searchmembername.do")
public class SearchMemberNameServlet extends HttpServlet {
	
//	private MemberService service=new MemberService(); //

	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchMemberNameServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//클라이언트가 보낸 데이터를 기준으로 회원을 조회하는 기능
		String name=request.getParameter("keyword");
		System.out.println(name);	//이름 넘어오는지 확인!
		
		//DB에 name값이랑 일치하는 데이터를 가져오기		
		List<Member> list=new MemberService().searchMemberName(name);	
		
		//가져온 데이터 저장
		request.setAttribute("nameMembers", list);
		
		//데이터를 가지고 화면을 처리할 jsp로 연결
		request.getRequestDispatcher("/views/member/memberName.jsp").forward(request, response);
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
