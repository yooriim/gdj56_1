<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/views/common/header.jsp" %> 	<!-- header.jsp파일을 불러오지 않으면 headerData 변수 불러오기 불가넝 -->

	<section>
		<h1>notice내용<%=headerData %></h1>
	</section>	
<%@ include file="/views/common/footer.jsp" %>

</html>