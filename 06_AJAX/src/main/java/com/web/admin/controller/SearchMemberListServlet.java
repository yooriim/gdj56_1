package com.web.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.admin.model.service.AdminService;
import com.web.member.vo.Member;

/**
 * Servlet implementation class SearchMemberListServlet
 */
@WebServlet("/admin/searchMember")
public class SearchMemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchMemberListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type=request.getParameter("searchType");
		String keyword=request.getParameter("searchKeyword");
		
//		List<Member> list=new AdminService().searchMemberList(type, keyword);
		
		String pageBar="";
		int cPage;
		int numPerpage=5;
		try {
			
			cPage=Integer.parseInt(request.getParameter("cPage"));
			
		}catch(NumberFormatException e) {
			cPage=1;
		}
		
		List<Member> list=new AdminService().searchMemberList(cPage,numPerpage,type,keyword);
		request.setAttribute("members", list);
		
		//전체 데이터 가져오기
		//조건이 적용된 전체 데이터 가져오기
		int totalData=new AdminService().selectMemberCount(type,keyword);
		int totalPage=(int)Math.ceil((double)totalData/numPerpage);
		int pageBarSize=5;
		int pageNo=((cPage-1)/pageBarSize)*pageBarSize+1;
		int pageEnd=pageNo+pageBarSize-1;
		
		if(pageNo==1) {
			pageBar+="<span>[이전]</span>";
			
		}else {
			pageBar+="<a href='"+request.getRequestURI()
			+"?searchType="+type+"&searchKeyword="+keyword+"&cPage="+(pageNo-1)+"'>[이전]</a>";
			//request.getRequestURI() -> /admin/searchMember 이게(요청한 주소가) 자동으로 찍힘 
			//?searchType="+type+   : searychType 키값  type 그거 젖아한 변수이름
		}
		
		while(!(pageNo>pageEnd||pageNo>totalPage)) {
			if(pageNo==cPage) {
				pageBar+="<span>"+pageNo+"</span>";
			}else {
				pageBar+="<a href='"+request.getRequestURI()
				+"?searchType="+type+"&searchKeyword="+keyword+"&cPage="+pageNo+"'>"+pageNo+"</a>";
			}
			pageNo++;
		}
		
		if(pageNo>totalPage) {
			pageBar+="<span>[다음]</span>";
		}else {
			pageBar+="<a href='"+request.getRequestURI()
			+"?searchType="+type+"&searchKeyword="+keyword+"&cPage="+pageNo+"'>[다음]</a>";
		}
		
		request.setAttribute("pageBar",pageBar);
		
		RequestDispatcher rd=request.getRequestDispatcher("/views/member/memberManage.jsp");
		rd.forward(request, response);
		
	}
	//
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
