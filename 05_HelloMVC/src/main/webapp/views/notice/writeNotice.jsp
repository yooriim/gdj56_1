<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>
<%@ page import="com.web.member.vo.Member" %>

<%
	Member m=(Member)session.getAttribute("loginMember");
%>

<style>
    section#notice-container{width:600px; margin:0 auto; text-align:center;}
    section#notice-container h2{margin:10px 0;}
    table#tbl-notice{width:500px; margin:0 auto; border:1px solid black; border-collapse:collapse; clear:both; }
    table#tbl-notice th {width: 125px; border:1px solid; padding: 5px 0; text-align:center;} 
    table#tbl-notice td {border:1px solid; padding: 5px 0 5px 10px; text-align:left;}
    #noticeContent{
    	resize:none;
    }
</style>

<section class="content">
  <div id="notice-container">
    <form action="<%=request.getContextPath()%>/notice/writenoticeEnd.do" method="post" 
    enctype="multipart/form-data">
    <!-- 인풋 타입 파일이 있으면 반드시 포스트타입으로 넘겨야함 : method="post" enctype="multipart/form-data" 꼭 이거 두개 다 설정해야함!!!!@!@!@!@! -->
        <table id="tbl-notice">
        <tr>
            <th>제 목</th>
            <td><input type="text" name="noticeTitle" required></td>
        </tr>
        <tr>
            <th>작성자</th>
            <td>
            	<input type="text" name="writer" value="<%=m.getUserId()%>" readonly>
            	<%-- <input type="hidden" name="writer" value="<%=m.getUserId()%>" readonly> --%>
            </td>
        </tr>
        <tr>
            <th>첨부파일</th>
            <td><input type="file" name="upfile"></td>
        </tr>
        <tr>
            <th>내 용</th>
            <td><textarea id="noticeContent" name="noticeContent" cols="47" rows="5" required></textarea></td>
        </tr>
        <tr>
            <th colspan="2">
                <input type="submit" value="등록하기" >
            </th>
        </tr>
    </table>
    </form>
    </div>
</section>
<%@ include file="/views/common/footer.jsp"%>