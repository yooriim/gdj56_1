<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List,com.jsp.model.vo.Animal"%>
<%
	List<Animal> list=List.of(
			Animal.builder().name("뽀삐").age(10).gender('남').height(25.5).weight(5.2).build(),
			Animal.builder().name("아롱이").age(8).gender('여').height(20.2).weight(6).build(),
			Animal.builder().name("햄찌").age(1).gender('여').height(5).weight(1.5).build()
			
			
		);	//임포트해줘야 하는데 못해서 오류남 ㅠ 페이지 속성에다 임포트 할 수 잇어용 //builder 패턴
		
	request.setAttribute("animals", list);
	request.setAttribute("msg","jsp내장객체 활용");
	session.setAttribute("sessionMsg","난 Session데이터야");
	application.setAttribute("applicationMsg", "난 Application데이터야");
	
	request.getRequestDispatcher("datacheck.jsp").forward(request,response);
	


%>>