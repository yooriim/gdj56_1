package com.filter.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.filter.common.filter.HttpServletRequestTest;

/**
 * Servlet implementation class FilterTestServlet
 */
@WebServlet(name = "filterTestServlet",urlPatterns = "/filtertest")
public class FilterTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FilterTestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpServletRequestTest hrt=new HttpServletRequestTest(request);
		String data=hrt.getParameter("data");
		hrt.setAttribute("test", "data");
		System.out.println(data);
		response.sendRedirect(request.getContextPath());
		//request.getContextPath 하면 프로젝트 명 (루트)가 찍힘

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
