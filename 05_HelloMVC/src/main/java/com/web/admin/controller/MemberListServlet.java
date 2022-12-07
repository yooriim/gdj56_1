package com.web.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.admin.model.service.AdminService;
import com.web.member.vo.Member;

/**
 * Servlet implementation class MemberListServlet
 */
@WebServlet("/admin/memberList.do")
public class MemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//db에서 member 테이블에 있는 전체 데이터를 가져와
//		m.forEach(v->System.out.println(v));
		
		
		
		//jsp가서!! !!!
		//<%@ page import="java.util.List" %>
		//<%	List<Member> members=(List<Member>)request.getAttribute("members"); %>
		
		//12/01
		//페이징 처리하기
		//클라이언트로부터 2개의 값을 받아옴
		//현재 페이지, 페이지당 출력 개수
		int cPage;
		try {
			cPage=Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e) {
			cPage=1;
			//예외 처리 하는 이유 ...? 처음 페이지 진입시에는 cPage가 없으니게 ㅠ 일단 1로 지정
			//html에서 1로 지정해줄 수도 잇슴
		}
		
		//페이지당 데이터 출력 개수
		int numPerpage;
		
		try {
			
			numPerpage=Integer.parseInt(request.getParameter("numPerpage")) ;	
			
		}catch(NumberFormatException e) {
			
			numPerpage=5;
			
		}
	
		
		System.out.println(numPerpage);

		List<Member> m=new AdminService().searchAllMember(cPage,numPerpage);
		
		//pageBar만들어서 반환하기
		//1. totalData 전체페이지의 수를 알기 위해
		int totalData=new AdminService().selectMemberCount();
		
		//pageVar : html코드를 저장할 수 있는 변수 선언
		String pageBar="";
		
		//1. pageBar의 번호 개수
		int pageBarSize=5;
		
		//2. 총 페이지 수
		int totalPage=(int)Math.ceil((double)totalData/numPerpage);
		//페이지 수가 딱 떨어지지 않고 나머지가 생기더라도 보여줘야하ㅏㅁ
		//ceil -> 올림
		//(int) 형변환
		//3. 출력할 번호 세팅
		int pageNo=((cPage-1)/pageBarSize)*pageBarSize+1; //pageBar를 생성할 때 페이지 번호를 출력해주는 값, 페이지 바의 시작번호로 초기화
		int pageEnd=pageNo+pageBarSize-1; //페이지바에 출력된 마지막 번호를 저장하는 것
		
		//html코드 생성하기
		if(pageNo==1) {
			//1번페이지일때는 [이전] 버튼이 안눌려야댐! 
			pageBar+="<span>[이전]</span>";
		}else {
			pageBar+="<a href='"+request.getContextPath()+"/admin/memberList.do?cPage="+(pageNo-1)+"'>[이전]</a>";
		}
		
		while(!(pageNo>pageEnd||pageNo>totalPage)) {
			if(cPage==pageNo) {
				//현재 보고 있는 페이지 
				pageBar+="<span>"+pageNo+"</span>";
				
			}else {
				pageBar+="<a href='	"+request.getContextPath()+"/admin/memberList.do?cPage="+pageNo+"'>"+pageNo+"</a>";
			}
			
			pageNo++;
		}
		
		if(pageNo>totalPage) { //페이지바에서 마지막페이지까지 딱 찍으면 pageNo++돼서 더 커지니까
			pageBar+="<span>[다음]</span>";
		}else {
			pageBar+="<a href='"+request.getContextPath()+"/admin/memberList.do?cPage="+pageNo+"'>[다음]</a>";
		}
		
		//페이지바 넘겨주기!~!
		request.setAttribute("pageBar", pageBar);
		
		//화면에 전송
		request.setAttribute("members", m);		
		request.getRequestDispatcher("/views/member/memberManage.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
