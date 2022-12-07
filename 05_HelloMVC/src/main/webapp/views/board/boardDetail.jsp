<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>
<%@ page import="com.web.board.model.vo.*,java.util.List" %>
<%
	Board b=(Board)request.getAttribute("board");
	List<BoardComment> comm=(List<BoardComment>)request.getAttribute("comm");
%>
<section class="content">
<style>
 	div#comment-container button#btn-insert{width:60px;height:50px; color:white;
    background-color:#3300FF;position:relative;top:-20px;}
 /*댓글테이블*/
    table#tbl-comment{width:580px; margin:0 auto; border-collapse:collapse; clear:both; } 
    table#tbl-comment tr td{border-bottom:1px solid; border-top:1px solid; 
    padding:5px; text-align:left; line-height:120%;}
    table#tbl-comment tr td:first-of-type{padding: 5px 5px 5px 50px;}
    table#tbl-comment tr td:last-of-type {text-align:right; width: 100px;}
    table#tbl-comment button.btn-reply{display:none;}
    table#tbl-comment button.btn-delete{display:none;}
    table#tbl-comment tr:hover {background:lightgray;}
    table#tbl-comment tr:hover button.btn-reply{display:inline;}
    table#tbl-comment tr:hover button.btn-delete{display:inline;}
    table#tbl-comment tr.level2 {color:gray; font-size: 14px;}
    table#tbl-comment sub.comment-writer {color:navy; font-size:14px}
    table#tbl-comment sub.comment-date {color:tomato; font-size:10px}
    table#tbl-comment tr.level2 td:first-of-type{padding-left:100px;}
    table#tbl-comment tr.level2 sub.comment-writer {color:#8e8eff; font-size:14px}
    table#tbl-comment tr.level2 sub.comment-date {color:#ff9c8a; font-size:10px}
/*답글관련*/
    table#tbl-comment textarea{margin: 4px 0 0 0;}
    table#tbl-comment button.btn-insert2{width:60px; height:23px; color:white; 
    background:#3300ff; position:relative; top:-5px; left:10px;}

    section#board-container{width:600px; margin:0 auto; text-align:center;}
    section#board-container h2{margin:10px 0;}
    table#tbl-board{width:500px; margin:0 auto; border:1px solid black; 
    border-collapse:collapse; clear:both; }
    table#tbl-board th {width: 125px; border:1px solid; padding: 5px 0; text-align:center;} 
    table#tbl-board td {border:1px solid; padding: 5px 0 5px 10px; text-align:left;}
</style>
   
		<section id="board-container">
		<h2>게시판</h2>
		<table id="tbl-board">
			<tr>
				<th>글번호</th>
				<td><%=b.getBoardNo()%></td>
			</tr>
			<tr>
				<th>제 목</th>
				<td><%=b.getTitle() %></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><%=b.getWriter() %></td>
			</tr>
			<tr>
				<th>조회수</th>
				<td><%=b.getReadCount() %></td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td>
				<%if(b.getOriFilename()!=null){ %>
					<img src="<%=request.getContextPath()%>/images.file.png" 
					width="20">
				<%}else{ %>
					첨부파일 없음
				<%} %>
				 
				</td>
			</tr>
			<tr>
				<th>내 용</th>
				<td><%=b.getContent() %></td>
			</tr>
			<%--글작성자/관리자인경우 수정삭제 가능 --%>
			<%if(loginMember!=null
					&&(loginMember.getUserId().equals("admin")
					||loginMember.getUserId().equals(b.getWriter()))){ %>
			<tr>
				<th colspan="2">
					<button>수정하기</button>
					<button>삭제하기</button>
				</th>
			</tr>
			<%} %>			
		</table>
		
   	<div id="comment-container">
   		<div class="comment-editor">
   			<form action="<%=request.getContextPath() %>/board/commentWrite.do" method="post">
   				<textarea name="content" cols="55" rows="3"></textarea>
   				<input type="hidden" name="boardref" value="<%=b.getBoardNo()%>">
   				<input type="hidden" name="level" value="1">
   				<input type="hidden" name="commentref" value="0">
   				<!-- db에 저장될 값을 프론트에서 받아줘야함 -->
   				<input type="hidden" name="commentWriter" value="<%=loginMember!=null?loginMember.getUserId():""%>">
   				
   				<button type="submit" id="btn-insert">등록</button>
   			</form>
   		</div>	
   	 	<table id="tbl-comment">
   	 		<%if(comm.isEmpty()){ %>
   	 			<tr>
   	 				<td>작성된 댓글이 없습니다.</td>
   	 			</tr>
   	 		<%}else {%>
   	 		<%for(int i=0;i<comm.size();i++){ 
   	 		if(comm.get(i).getBoardCommentLevel()==1){%>
			<tr class="level1" >
				<td>
					<sub class="comment-writer"><%=comm.get(i).getBoardCommentWriter() %></sub>					
					<sub class="comment-date"><%=comm.get(i).getBoardCommentDate() %></sub>				
					<br>										
					<%=comm.get(i).getBoardCommentContent() %>	
				</td>
				<td>
					<%if(loginMember!=null){ %>
					
					<button class="btn-reply" value="<%=comm.get(i).getBoardCommentNo() %>" >답글</button>
					<!-- 태그 만든 후 필요한 값 넣은다음에 사용자에게 보이지 않게 설정해도 됨  -->
					
					<%} %>
					
					<%if(loginMember!=null
							&&(loginMember.getUserId().equals(b.getWriter())
							||loginMember.getUserId().equals("admin"))){ %>
							
					<button class="btn-delete">삭제</button>
					
					<!-- 댓글(로그인한 사용자만), 삭제버튼만들기 (작성자, 관리자만삭제가능) -->
					<%} %>
				</td>
			</tr>
			<%}else{ %>
				<tr class="level2">
					<td>
						<sub><%=comm.get(i).getBoardCommentWriter() %></sub>
						<sub><%=comm.get(i).getBoardCommentDate() %></sub>
						<br>
						<%=comm.get(i).getBoardCommentContent() %>
					</td>
					<td></td>
				</tr>
			<%	} %>			
			<%} 
			}%>	 			
	   	</table>
   		
   	</div>
   	
   	
    </section>
    <script>
    	$(()=>{
    		//비로그인이 댓글달려고 할때 알ㄹ밈
    		$(".comment-editor>form>textarea").focus(e=>{
    			if(<%=loginMember==null%>){
    				alert("로그인 후 이용할 수 있습니다.");
    				$("#userId").focus();
    			}
    		});
    		
    		//대댓달기 기능
    		$(".btn-reply").click(e=>{
    			const tr=$("<tr>");
    			const form=$(".comment-editor>form").clone(); //인풋태그 만들기 귀찬흔데 위에 만들어둔거 있으니까 클론 ㄱ 
    			form.find("textarea").attr({"cols":"50","rows":"1"}); //find() : 후손들 중에 특정한 엘리먼트 찾기 가능
    			form.find("input[name=level]").val("2"); //댓글은 level 1, 대댓은 level 2
    			form.find("input[name=commentref]").val($(e.target).val());
    			form.find("button").removeAttr("id").addClass("btn-insert2"); //id값을 없애고 class를 추가
    			const td=$("<td>").attr("conlspan","2").append(form);
    			tr.append(td);
    			tr.find("td").css("display","none");
    			tr.insertAfter($(e.target).parents("tr")).children("td").slideDown(800);
    			$(e.target).off("click"); //한번 실행하면 click이벤트 off
    		})
    		
    	})
    </script>
</section>
<%@ include file="/views/common/footer.jsp"%>