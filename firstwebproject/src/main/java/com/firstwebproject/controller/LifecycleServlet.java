package com.firstwebproject.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LifecycleServlet
 */
@WebServlet("/life.do")	//서블릿등록
public class LifecycleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;	//id등록
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LifecycleServlet() {
        super();
        // TODO Auto-generated constructor stub
        System.out.println("Lifecycle 서블릿 생성");
        
    }
    @Override
    public void init(ServletConfig config) throws ServletException{
    	System.out.println("init 메소드 호출");
    }
    
    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) 
    		throws IOException,ServletException{
    	System.out.println("service 메소드 호출");
    	super.service(req,res);
    	
    }
    
    @Override
    public void destroy() {
    	System.out.println("destroy 메소드 호출");
    }
    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    //doGet, doPost 자동생성
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("doGet 메소드 실행");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("doPost 메소드 실행");
	}

}
