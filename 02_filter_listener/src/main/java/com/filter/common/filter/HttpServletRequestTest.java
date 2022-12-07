package com.filter.common.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class HttpServletRequestTest extends HttpServletRequestWrapper{
	//HttpServletRequest 객체의 메소드를 재정의할 수 있음.
	//반드시 매개변수있는 생성자를 선언하고
	//생성자에서 반드시 매개변수로 받은 값을 부모클래스 생성자의 매개변수로 넣어줘야 한다.
	
	public HttpServletRequestTest(HttpServletRequest request) {
		super(request);	//이렇게 부모클래스 생성자의 매개변수에 매개변수로 넣은 갑승ㄹ 넣엉줘야함
	}
	
	@Override
	public String getParameter(String name) {
		if(name.equals("msg")) {
			return super.getParameter(name)+"-bs-";	//파라미터 호출할 때마다 원본값에 -bs-가 붙게 됨			
		}
		else
			return super.getParameter(name)+"-dm-";
	}
	
	
}
