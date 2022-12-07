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
 * Servlet implementation class SearchMemberAllServlet
 */
@WebServlet("/searchmemberall.do")
public class SearchMemberAllServlet extends HttpServlet {
	private MemberService service=new MemberService(); //
	
	
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchMemberAllServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//DB에 저장되어 있는 회원전체를 가져오는 기능
		List<Member> allMember=service.searchMemberAll();
		//화면은 jsp가 구현해야하는데... ㅠ Member정보를 jsp에 정보를 어케 줄거? -> request로 setattribute (조회기능 눌렀을때 한번만 나오면 되니가 request)
		request.setAttribute("members", allMember);
		
		//페이지 전환
		request.getRequestDispatcher("/views/member/memberAll.jsp").forward(request, response);
		//서블릿에서 /에서 webapp부터 시작~! 프로젝트 이름까지 들어감! | 페이지에서는 /하면 서버 이름부터 시작 
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
