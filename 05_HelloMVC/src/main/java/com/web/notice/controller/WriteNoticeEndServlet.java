package com.web.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.web.notice.service.NoticeService;
import com.web.notice.vo.Notice;

import oracle.jdbc.diagnostics.DebugFirstFailureClioSupport;

/**
 * Servlet implementation class WriteNoticeEndServlet
 */
@WebServlet("/notice/writenoticeEnd.do")
public class WriteNoticeEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WriteNoticeEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파일업로드 처리하기 cos.jar 라이브러리 이용
		//1. 요청방식 multipart/form-data 방식으로 왔는지 확인!
		//ServletFileUpload.isMultipartContent(request)
		
		if(!ServletFileUpload.isMultipartContent(request)) {
			response.sendRedirect(request.getContextPath());
		}else {		
				
		//2. 요청방식이 맞다면
		// cos.jar에서 제공하는 클래스를 이용해서 지정된 위치에 전송된 파일을 저장
		//MultipartRequest 클래스 이용! -> 자동으로 전달된 파일을 지정된 위치에 저장
		//MultipartRequest 클래스 매개변수 있는 생성자를 이용하면 됨.
		//얘 생성할때 다섯개의 매개변수를 받음
		//첫번째 매개변수 : HttpServletRequest 객체
		//두번째 매개변수 : 저장위치 설정(String) *
		//		절대경로로 해서 드라이브부터 가져와야함
		//세번째 매개변수 : 업로드 된 파일의 최대 크기 설정(int)
		//네번째 매개변수 : 인코딩 방식(String) *UTF-8
		//		작성자, 글내용 같은거 같이 가져올때 어던 인코딩 방식 사용하럭냐 
		//다섯번째 매개변수(젤 중요) :  rename규칙 클래스	*적용 기본 클래스 제공/ 커스터마이징 가능
		
			//저장할 위치 설정하기
			String path=request.getServletContext().getRealPath("/upload/notice");
//					String path=request.getServletContext().getRealPath("/") -> webapp까지 가져옴 
			//저장할 파일 크기 설정
			//byte -> mb -> gb -> TB  : 한개의 단위 올라갈떌 1024씩 증가
			int maxSize=1024*1024*10; //10MB
			//인코딩 설정
			String encoding="UTF-8";
			//rename 클래스
			DefaultFileRenamePolicy dfr=new DefaultFileRenamePolicy();
			
			//매개변수 있는 생성자로 MultipartRequest 클래스를 생성
			MultipartRequest mr=new MultipartRequest(request,path,maxSize,encoding,dfr); //매개변수 다섯개 넣기	
		
			//클라이언트가 보낸 데이터를 DB에 저장하는 기능
			//파일을 저장하면서 재정의 된 파일명을 저장해야한다.
			//MutipartRequest 생성한 후에는 이거 이용해서 파일 넣으면 댐 
			String title=mr.getParameter("noticeTitle"); //프론트에서 지정해놓은 이름 키값
			String writer=mr.getParameter("writer");
			String content=mr.getParameter("noticeContent");
			
			//리네임된 파일이름 가져오기
			String fileName=mr.getFilesystemName("upfile"); // 네임값 주면됨 
			String oriName=mr.getOriginalFileName("upfile");
			
			Notice n=Notice.builder()
					.noticeTitle(title)
					.writer(writer)
					.noticeContent(content)
					.filepath(fileName).build();
			System.out.println(n);
			System.out.println(oriName);
			
			int result=new NoticeService().writeNotice(n);
			
			System.out.println("result : "+result);
					
			String msg="",loc="";
			if(result>0) {
				msg="공지 등록 성공";
				loc="/notice/noticeList.do?cPage=1";
				
			}else {
				msg="공지 등록 실패";
				loc="/notice/writeNotice.do";
				
			}
			
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
			

		}
			
			
//		String title=request.getParameter("noticeTitle");
//		String wirter=request.getParameter("writer");
//		String file=request.getParameter("file");
//		String content=request.getParameter("noticeContent");
//		System.out.println("dd"+title);
		
//		Notice n=Notice.builder()
//				.noticeTitle(title)
//				.writer(wirter)
//				.file(file)
//				.noticeContent(content)
//				.build();
		
//		System.out.println("n : "+n);
		
//		int result=new NoticeService().writeNotice(n);
//		
//		System.out.println("result : "+result);
//		
//		
//		String msg="",loc="";
//		if(result>0) {
//			msg="공지 등록 성공";
//			loc="/notice/noticeList.do?cPage=1";
//			
//		}else {
//			msg="공지 등록 실패";
//			loc="/notice/writeNotice.do";
//			
//		}
//		
//		request.setAttribute("msg", msg);
//		request.setAttribute("loc", loc);
//		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
