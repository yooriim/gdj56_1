package com.firstwebproject.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//클래스를 서블릿으로 만드려면 서블릿규약을 준수
//HttpServlet 클래스는 상속받아야 함. (import 해주어ㅑ 함)-> Servlet 클래스가 됨.


public class FirstServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7430755562405914654L;
	//서블릿은 사용자가 요청한 정보에 대한걸 받아오고 응답을 다시 전달해주는 ... 특수한 클래스
	//Http로 사용자의 요청을 받아서 응답처리하는 클래스
	
	//정해진 메소드를 구현(재정의)하여 사용함	
	//2가지의 메소드가 있음! (정해져 있따~ 매개변수도 정해져 있다!)
	//get방식||post방식으로 받았냐에 따라서  
	//1. doGet(HttpServletRequest, HttpServletResponse) 
	// - 사용자(client)가 get 방식으로 요청한 것을 받을 때 이용하는 메소드 
	//2. doPost(HttpServletRequest, HttpServletResponse) 
	// - 사용자(client)가 post 방식으로 요청한 것을 받을 때 이용하는 메소드
	
	@Override
	public void doGet(HttpServletRequest req,HttpServletResponse response) 
			throws ServletException,IOException{
		//import 필요
		//Override해도 에러 안남(부모객체에 선언된게 없으면 에러나지)
		//Exception 처리까지 해줘야 함
		System.out.println("get 메소드로 요청!");
	}
	
	@Override
	public void doPost(HttpServletRequest req,HttpServletResponse res) 
			throws ServletException,IOException{
		System.out.println("post 메소드로 요청!");
		
	}
	
	//서버가 요청주소에 따라 서블릿을 실행할 수 있게 설정하기
	//1. web.xml 파일을 이용하기
	//	- <servlet> 태그를 이용해서 생성한 servlet 클래스를 등록
	//	- <servlet-mapping> 태그를 이용해서 URL주소와 servlet 클래스를 연결
	
	//2. @(어노테이션)을 이용하기
	//
}
