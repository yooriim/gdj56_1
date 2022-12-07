<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.web.notice.vo.Notice" %>
<%
	List<Notice> notices=(List<Notice>)request.getAttribute("notices");
%>

<%@ include file="/views/common/header.jsp"%>

<style>
    section#notice-container{width:600px; margin:0 auto; text-align:center;}
    section#notice-container h2{margin:10px 0;}
    table#tbl-notice{width:100%; margin:0 auto; border:1px solid black; border-collapse:collapse;}
    table#tbl-notice th, table#tbl-notice td {border:1px solid; padding: 5px 0; text-align:center;}
    #noticebtn{
    	float:right;
    }
</style>

<section id="notice-container">
        <h2>공지사항</h2>
        <%if(loginMember.getUserId().equals("admin")){ %>
        <div id="noticebtn">
        	<input type="button" value="글쓰기" 
        	onclick="location.replace('<%=request.getContextPath()%>/notice/writeNotice.do')">
        </div>
        <%} else{%>
        	
        <%} %>
        
        <table id="tbl-notice">
            <tr>            
                <th>번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>첨부파일</th>
                <th>작성일</th>
            </tr>
             <%if(notices.isEmpty()){ %>
            	<tr>
            		<td colspan="5"><h3>조회된 결과가 없습니다.</h3></td>
            	</tr>
            <%} else{
            	for(int i=0;i<notices.size();i++){      		
            %>            		
            	<tr>
      				<td><%=notices.get(i).getNoticeNo() %></td>
      				<td>
      					<a href="<%=request.getContextPath() %>/noticeDetail.do?noticeNo=<%=notices.get(i).getNoticeNo()%>">
      						<%=notices.get(i).getNoticeTitle() %>
      					</a>
      				</td>
      				<td><%=notices.get(i).getWriter() %></td>
      				<td>
      				<%if(notices.get(i).getFile()!=null){ %>
      					<img src="<%=request.getContextPath() %>/images/file.png" width="20" height="20">
      				
      				<%-- <td><%=notices.get(i).getFile() %></td> --%>
      				<%} else{ %>
      					첨부파일 없음
      				<%} %>
      				</td>
      				<td><%=notices.get(i).getNoticeDate() %></td>
            	</tr>
            
            <%} 
            } %>
<!-- 	내용출력할것
	첨부파일 있으면 이미지, 없으면 공란으로 표시
	이미지파일은 web/images/file.png에 저장 -->
        </table>
        <div id="pageBar">
        	<%=request.getAttribute("pageBar") %>
        </div>
</section>


<%@ include file="/views/common/footer.jsp"%>
