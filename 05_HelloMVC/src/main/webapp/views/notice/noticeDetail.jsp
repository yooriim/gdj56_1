<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>

<%@ page import="com.web.notice.vo.Notice" %>
<%
	Notice noticeDetail=(Notice)request.getAttribute("noticeDetail");

%>

<style>
    section#notice-container{width:600px; margin:0 auto; text-align:center;}
    section#notice-container h2{margin:10px 0;}
    table#tbl-notice{width:500px; margin:0 auto; border:1px solid black; border-collapse:collapse; clear:both; }
    table#tbl-notice th {width: 125px; border:1px solid; padding: 5px 0; text-align:center;} 
    table#tbl-notice td {border:1px solid; padding: 5px 0 5px 10px; text-align:left;}
</style>

<section class="content">
	<div id="notice-container">
        <table id="tbl-notice">
	        <tr>
	            <th>제 목</th>
	            <td><%=noticeDetail.getNoticeTitle() %></td>
	        </tr>
	        <tr>
	            <th>작성자</th>
	            <td><%=noticeDetail.getWriter() %></td>
	        </tr>
	        <tr>
	            <th>첨부파일</th>
	            <td>
	           <!-- 	있으면 이미지출력하기 없으면 공란 -->
	           <%-- <%=noticeDetail.getFilepath()==null?"":"" %> --%>
	           <%if(noticeDetail.getFile()!=null){ %>
	           		<img src="<%=request.getContextPath()%>/images/file.png" 
	           		width="20" onclick="fn_fileDown('<%=noticeDetail.getFile() %>');"><%=noticeDetail.getFile()%>
	           <%}else{ %>
	           		첨부파일 없음
	           <%} %>
	            </td>
	        </tr>
	        <tr>
	            <th>내 용</th>
	            <td><%=noticeDetail.getNoticeContent() %></td>
	        </tr>
	        <%if(loginMember!=null&&loginMember.getUserId().equals("admin")){ %>
	        <!-- loginMembe!=null을 계속 써주는게 좋다  -->
	        <tr>
	            <th colspan="2">
	                <input type="button" value="수정하기" onclick="">
	                <input type="button" value="삭제하기" onclick="fn_deleteNotice('<%=noticeDetail.getNoticeNo()%>','<%=noticeDetail.getFile() %>');">
	            </th>
	        </tr>
	        <%} %>
	        
    	</table>
    	<script>
    		const fn_fileDown=(fileName)=>{
    			//다운로드 서비스 호출
    			/* console.log(fileName); */
    			
    			location.assign("<%=request.getContextPath()%>/notice/fileDown.do?filename="+fileName);
    		}
    		
    		const fn_deleteNotice=(noticeNo,fileName)=>{
    			location.replace("<%=request.getContextPath()%>/notice/noticeDelete.do?no="+noticeNo+"&fileName="+fileName);
    		}
    		
    	</script>
    </div>
</section>
<%@ include file="/views/common/footer.jsp"%>