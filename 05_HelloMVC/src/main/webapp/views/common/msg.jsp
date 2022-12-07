<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String msg=(String)request.getAttribute("msg");
	String loc=(String)request.getAttribute("loc");
	String script=(String)request.getAttribute("script"); //script라는 키가 있으면 값이나오고 없으면 null 이나옴 이걸로 분기처리
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메세지 페이지</title>
</head>
<body>

	<script>
		alert('<%=msg%>');	
		
		//  /05_HelloMVC/
		location.replace("<%=request.getContextPath()%><%=loc%>");
		<%=script!=null?script:""%>  //UpdatePasswordEndServlet에서 만든 script
		
		
	</script>
	
</body>
</html>