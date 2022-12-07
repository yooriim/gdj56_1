<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>
<%@ page import="java.util.List,com.web.board.model.vo.Board"  %>
<%
	List<Board> boards=(List<Board>)request.getAttribute("boards");
%>
<section class="content">
<style>
	section#board-container{width:600px; margin:0 auto; text-align:center;}
	section#board-container h2{margin:10px 0;}
	table#tbl-board{width:100%; margin:0 auto; border:1px solid black; border-collapse:collapse; clear:both; }
	table#tbl-board th, table#tbl-board td {border:1px solid; padding: 5px 0; text-align:center;} 
	/*글쓰기버튼*/
	input#btn-add{float:right; margin: 0 0 15px;}
	/*페이지바*/
	div#pageBar{margin-top:10px; text-align:center; background-color:rgba(0, 188, 212, 0.3);}
	div#pageBar span.cPage{color: #0066ff;}
	#writebtn{
    	float:right;
    }
</style>
	<section id="board-container">
		<h2>게시판 </h2>
		<div id="writebtn">
        	<input type="button" value="글쓰기" 
        	onclick="location.replace('<%=request.getContextPath()%>/board/writeboard.do')">
        </div>
		<table id="tbl-board">		
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>첨부파일</th>
				<th>조회수</th>
			</tr>
			<%if(boards.isEmpty()){ %>
				<tr>
					<td colspan="6"><h3>조회된 결과가 없습니다.</h3></td>
				</tr>
			<%} else{
				for(int i=0;i<boards.size();i++){%>
			<tr>
				<td><%=boards.get(i).getBoardNo() %></td>
				<td>
					<!-- 누르면 타이틀로 이동 -->
					<a href="<%=request.getContextPath()%>/board/boardDetail.do?boardNo=<%=boards.get(i).getBoardNo()%>">
					<%=boards.get(i).getTitle() %>
					</a>
					
				</td>
				<td><%=boards.get(i).getWriter() %></td>
				<td><%=boards.get(i).getEnrollDate() %></td>
				<td>
				<%if(boards.get(i).getOriFilename()!=null){ %>
					<img src="<%=request.getContextPath()%>/images/file.png" width="20">
				<%} %>
				<!-- 첨부파일이 있으면 이미지출력 / 없으면 공란 -->
				</td>
				<td><%=boards.get(i).getReadCount() %></td>
			</tr>
			<%} 
			}%>
		</table>

		<div id="pageBar">
			<%=request.getAttribute("pageBar") %>
		</div>
	</section>
</section>
<%@ include file="/views/common/footer.jsp"%>