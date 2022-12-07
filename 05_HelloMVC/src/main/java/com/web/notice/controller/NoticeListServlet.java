package com.web.notice.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.notice.service.NoticeService;
import com.web.notice.vo.Notice;

/**
 * Servlet implementation class NoticeList
 */
@WebServlet("/notice/noticeList.do")
public class NoticeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cPage;
		try {
			cPage=Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e) {
			cPage=1;
		}
		
		int numPerpage=5;
		
		//보여줠데이터
		List<Notice> notices=new NoticeService().searchAllNotice(cPage,numPerpage);
		
		int totalData=new NoticeService().selectNoticeCount();
		String pageBar="";
		int pageBarSize=5;
		int totalpage=(int)Math.ceil((double)totalData/numPerpage);
		
		int pageNo=(cPage-1)/pageBarSize*pageBarSize+1;
		int pageEnd=pageNo+pageBarSize-1;
		
		//이전
		if(pageNo==1) {
			pageBar+="<span>[이전]</span>";
		}else {
			pageBar+="<a href='"+request.getContextPath()+"/notice/noticeList.do?cPage="+(pageNo-1)+"'>[이전]</a>";
		}
		
		//번호목록
		while(!(pageNo>pageEnd||pageNo>totalpage)) {
			if(cPage==pageNo) {
				pageBar+="<span>"+pageNo+"</span>";
			}else {
				pageBar+="<a href='"+request.getContextPath()+"/notice/noticeList.do?cPage="+(pageNo)+"'>"+pageNo+"</a>";
			}

			pageNo++;
		}
		
		//다음
		if(pageNo>totalpage) {
			pageBar+="<span>[다음]</span>";
		}else {
			pageBar+="<a href='"+	request.getContextPath()+"/notice/noticeList.do?cPage="+(pageNo)+"'>"+"[다음]</a>";
		}
		
		
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("notices", notices);		
		
		request.getRequestDispatcher("/views/notice/noticeList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
