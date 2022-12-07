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
 * Servlet implementation class EditMemberServlet
 */
@WebServlet("/member/memberView.do")
public class MemberViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
//		System.out.println(id);
		Member m=new MemberService().searchMemberId(id);
		
		//이메일 복호화
		try {
			m.setEmail(AESEncrypt.decryptData(m.getEmail()));
		}catch(Exception e) {
			e.printStackTrace();
		}		
		
		//비밀번호는 단방향 암호화이기 때문에 복호화가 안됨!.!
		
		request.setAttribute("member", m);
		request.getRequestDispatcher("/views/member/editMember.jsp").forward(request, response);
	
		//아이디에 이름 엄청 길게 치고 정보수정하면 nullpointError 왜?? m이 null!!! 
		//http://localhost:9090/05_HelloMVC/member/memberView.do?id=yurim
		//http://localhost:9090/05_HelloMVC/member/UpdateMember.do
		//updateMemberServlet에 id=? 추가
		//근데... 이제 ?id=___에 아이디 넣으면 로그인 안해도 회원정보 수정 페이지 뜸
		//흐ㅡㅡㅡㅡㅁㅁㅁㅁㅁ~~~~~~~ 로그인이 되어 잇어야 ... 수정페이지 뜨게 ........?...?
		//로그인햇는지 어케알지... 세션...? 
		//헤더에 loginMember잇음		
		//-> filter 처리로 차단! : 로그인한 세션의 아이디와 파라미터로 넘어오는 아이디가 같을 때만 보여줘야 함
		
		//~~할 일~~
		//탈퇴하면 자동으로 로그아웃 처리
		//
		
		//pw수정
		//
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
