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
 * Servlet implementation class UpdatePasswordEndServlet
 */
@WebServlet(name="updatePassword",urlPatterns = "/member/updatePasswordEnd") //EncryptFilter적용ㅇ
public class UpdatePasswordEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePasswordEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//세개 값 받아오기 id, 이전 pw, 새 pw
		String userId=request.getParameter("userId");
		String oriPass=request.getParameter("password");
		String newPass=request.getParameter("password_new");
		
		//현재 비밀번호가 맞는지 확인
		Member m=new MemberService().searchMember(userId,oriPass); 	//아디 비번 일치하는지 찾아주는 메소드 이미 있으니까 그거쓰면 댐 
		
		//m이 null이면 비밀번호가 틀림! m이 null이 아니면 맞다.
		
		String msg="",loc="";
		if(m!=null) {
			//비밀번호 변경 로직 진행
			int result=new MemberService().updatePassword(userId,newPass);
			
			//변경 성공/실패 시 메세지&화면전환
			if(result>0) {
				msg="비밀번호 변경 완료";				
				loc="/logout.do";
//				String script="opener.location.replace('"+request.getContextPath()+"/logout.do');close(); 
//				String script="opener.location.replace('"+request.getContextPath()+"/logout.do');";
				String script="opener.location.replace('"+request.getContextPath()+"/logout.do');close();";
				request.setAttribute("script", script); //비밀번호 변경 완료 후 변경 창 닫히게.... ㅠ script로 close를 줘버리기
			}
			else {
				msg="비밀번호 변경 실패";
				loc="/member/updatePassword.do?userId="+userId; //요청하는 주소의 파라미터까지 맞춰줘ㅑㅇ함 
					
			}			
			
		}else {
			//현재 비밀번호가 다릅니다. 다시 시도하세요!
			msg="현재 비밀번호가 일치하지 않습니다. 다시 시도하세요!";
			loc="/member/updatePassword.do?userId="+userId;
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
