package com.web.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.common.AESEncrypt;
import com.web.member.service.MemberService;
import com.web.member.vo.Member;

/**
 * Servlet implementation class EnrollMemberEnd
 */
@WebServlet(name="enrollMemberEnd",urlPatterns = "/enrollMemberEnd.do")
public class EnrollMemberEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnrollMemberEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//클라이언트가 보낸 데이터를 가져오고 
		//member 테이블에 저장하기
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String id=request.getParameter("userId");
		String pw=request.getParameter("password");
		String name=request.getParameter("userName");
		int age=Integer.parseInt(request.getParameter("age"));
		String email=request.getParameter("email");
		//전달된 이메일 암호화 처리하기
		try {
			
			email=AESEncrypt.encryptData(email); //암호화되어 넘어온 값 email에 저장 
			
		}catch(Exception e) {			
			
			e.printStackTrace();
			
		}
		
		
		String phone=request.getParameter("phone");
		String address=request.getParameter("address");
		String gender=request.getParameter("gender");
		String hobby[]=request.getParameterValues("hobby");
		
//		System.out.println(id);
//		System.out.println(gender);
//		System.out.println(hobby[0]);		
		
		Member m=Member.builder()
				.userId(id)	// 멤버변수(위에 만든 변수)
				.password(pw)
				.userName(name)
				.age(age)
				.email(email)
				.phone(phone)
				.address(address)
				.gender(gender.charAt(0))
				.hobby(hobby)
				.build();
		
		System.out.println(m);
		int result=new MemberService().insertMember(m);
		
//		System.out.println(result);
		//회원가입 성공하면 메세지 출력 후 main 화면으로
		//회원가입 실패하면 메세지 출력 후 회원가입화면으로
		String msg="",loc="";
		
		if(result>0) {
			msg="회원가입 성공! 가입을 축하합니다.";
			loc="/";
		}else {
			msg="회원가입에 실패했습니다. 다시 시도해주세요.";
			loc="/enrollMember.do";
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
