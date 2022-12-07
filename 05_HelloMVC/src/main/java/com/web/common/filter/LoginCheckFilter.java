package com.web.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.web.member.vo.Member;

/**
 * Servlet Filter implementation class LoginCheckFilter
 */
@WebFilter("/member/memberView.do")
public class LoginCheckFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public LoginCheckFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
			
		//요청한 사용자에 대한 정보(로그인 여부)를 확인 후 처리
		HttpSession session=((HttpServletRequest)request).getSession(false);
		//getSession(),getSession(true) : 존재하면 현재 세션을 반환하고 존재하지 않으면 새로 생성 
		//getSession(false) : 세션이 존재하면 현재 세션을 반환하고 존지하지 ㅇ낳으면 null반환
		
		Member loginMember=(Member)session.getAttribute("loginMember"); //로그인이 되어 있으면 값이 넘어오고 안되어 있으면 null
		//loginMember loginServlet에 만들어놧슴 로그인하면 생김
		if(loginMember!=null&&loginMember.getUserId().equals(request.getParameter("id"))) {
			//로그인 O && 회원보기 요청한 아이디랑 일치하면 
			
			//두 조건이 일치하면 화면보여주기
			chain.doFilter(request, response);
						
		}else {
			//로그인 X || 횐정잉 ㅏㄴ맞으면
			request.setAttribute("msg", "잘못된 접근입니다 :(");
			request.setAttribute("loc","/");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
			
		}
		
		
		
		// pass the request along the filter chain
//		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
