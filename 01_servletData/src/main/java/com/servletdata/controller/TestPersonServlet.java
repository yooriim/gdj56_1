package com.servletdata.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestPersonServlet
 */
@WebServlet("/testperson.do")
public class TestPersonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestPersonServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//전송된 데이터 인코딩처리하기
		request.setCharacterEncoding("utf-8");
		
		//1. 단일데이터 값 받아오기 (딱 하나의 값만 받아오는 거 -> 여러값이 나올수 있는 chkbox 이런거 x)
		// request.getParameter() -> 기본적인 데이터를 받아올 때 사용
		String name=request.getParameter("name");
		int age=Integer.parseInt(request.getParameter("age")); //반환값은 무조건 String이기 때문에 형변환 필요
		double height=Double.parseDouble(request.getParameter("height"));
		String color=request.getParameter("color");
		String lunchMenu=request.getParameter("lunch");
		
		//2. 하나의 이름으로 여러값이 전송될 때 값 받아오기(ex : checkbox)
		//request.getParemeterValues() -> 다수의 값을 받을 때 사용
		//다수의 값이기 때문에 반환형이 String[]임! 배열!
		String[] animals=request.getParameterValues("animal");
		System.out.println("이름: "+name);
		System.out.println("나이: "+age);
		System.out.println("키: "+height);
		System.out.println("좋아하는 색: "+color);
		System.out.println("점심메뉴: "+lunchMenu);
		System.out.println("좋아하는 동물: "+Arrays.toString(animals));	
		
		//서블릿이 추가되면 무조건 서버를 내렸다 올려야 한다.
	
		//단일값도 getparameterValues() 메소드를 이용해서 가져올 수 있다.
		String[] names=request.getParameterValues("name");
		//단일값을 배열의 0번 인덱스에 값을 대입후 반환.
		System.out.println("values로 가져온 name : "+Arrays.toString(names));
		
		
		//클라이언트에서 전송된 name값을 모를 때 name값만 가져오기
		//getParameterNames() 메소드 이용 -> Enumeration클래스로 반환
		//Enumeration은 이더레이터라고 생각하면 됨.
		Enumeration<String> paramName=request.getParameterNames();
		while(paramName.hasMoreElements()) {
			String key=paramName.nextElement();
			String[] values=request.getParameterValues(key);
			System.out.println(Arrays.toString(values));
		}
		
		//클라이언트가 전송한 데이터를 key:value 형식으로 반환받기 -> Map
		//request.getParameterMap();
		Map<String,String[]> param=request.getParameterMap();
		System.out.println("==== map으로 데이터 받기 ====");
		Set<String> keys=param.keySet();
		Iterator<String> it=keys.iterator();
		while(it.hasNext()) {
			String key=it.next();
			System.out.println(key);
			System.out.println(Arrays.toString(param.get(key)));
		}
		
		//ㅇㅔㄴ..트리..셋..? 으로....?? 집에서 해보기 ^^;;
		
		//클라이언트의 요청에 대한 응답페이지를 작성
		//HttpServletResponse 클래스를 이용한다.		
		//1. 응답데이터에 대한 타입을 설정 -> MIME 타입
		response.setContentType("text/html;charset=utf-8");
		
		//2. 클라이언트 Stream을 가져온다.
		// 1) getWriter() : html코드 전송할 때, 일반 문자열데이터 보낼 때
		// 2) getOutputStream() : 파일 전송할 때
		PrintWriter out=response.getWriter();
		// 3) 연결된 스트림으로 원하는 데이터를 전송
		//write(), append() 메소드를 이용
		String html="<html>";
		html+="<head>";
		html+="<title>당신의 취향 분석</title>";
		html+="</head>";
		html+="<body>";
		html+="<h1>"+name+"님의 취향은 </h1>";
		html+="<h2>당신의 이름은 "+name+"이고, 나이는 "+age+"살 입니다. </h2>";
		html+="<h3>키는 "+height+"cm이고 좋아하는 색은 <span style='color:"+color+";'>"+color+"</span>입니다.</h3>";
		html+="<ul> 좋아하는 동물은";
		for(String a : animals) {
			html+="<li>";
			String src="";
			switch(a) {
				case "강아지" :
					src="data:image/jpeg;base64,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";break;
				case "고양이" : 
					src="http://www.chemicalnews.co.kr/news/photo/202106/3636_10174_4958.jpg";
					break;
				
			}
			html+="<img src='"+src+"' width='100' height='100'>";
			html+="</li>";
			
			
			
			
		}
		html+="</ul>";
		html+="<h3>오늘의 점심은 "+lunchMenu+"이었습니다.</h3>";
		html+="</body>";
		html+="</html>";
				
		out.write(html);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
