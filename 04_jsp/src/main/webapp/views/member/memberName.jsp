<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.List,com.jsp.model.vo.Member" %>  
  
<%
	List<Member> list=(List<Member>)request.getAttribute("nameMembers");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원이름으로 조회</title>
</head>
<body>
	<h2><%=request.getParameter("keyword") %>이름으로 회원 조회</h2>	
		<table>
			<tr>
				<th>아이디</th>
				<th>이름</th>
				<th>나이</th>
				<th>성별</th>
			</tr>
			
			<%for(Member m:list){ %>
				<tr>
					<td><%=m.getMemberId() %></td>
					<td><%=m.getMemberName() %></td>
					<td><%=m.getAge() %></td>
					<td><%=m.getGender()=='M'?"남":"여" %></td>
				</tr>
			<%} %>
		</table>
	

</body>
</html>