package myweb;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyfirstServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5138093717752451549L;

	@Override
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException {
		System.out.println("우와 내가 만든 게 실행되다니~~~~");
	}
	
}
