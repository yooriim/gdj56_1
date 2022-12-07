package com.filter.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

//일반클래스를 필터로 만드려면 Filter인터페이스를 구현
public class EncodingFilter implements Filter{
	
	//alt shift s v : 재정의해야하는 것들 알랴줌 
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		System.out.println("필터실행!");
		//그 다음 지정을 해줘야 함 web.xml로 가기
		
		//한글깨지는거 해결 (post쓰면 한글깨짐 get쓰며ㅓㄴ 아깨짐 )
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		//wrapper클래스 적용하기
//		HttpServletRequestTest hrt=new HttpServletRequestTest((HttpServletRequest)request);		
		
		//다음 서블릿이나 필터가 실행 
		chain.doFilter(request, response);
//		chain.doFilter(hrt, response);
					//생성된 request를 넣어주기(wrapper적용) 
	}
 
	
}
