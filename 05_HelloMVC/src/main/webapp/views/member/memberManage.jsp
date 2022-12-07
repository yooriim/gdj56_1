<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%@ page import="java.util.List" %>

<%
	List<Member> members=(List<Member>)request.getAttribute("members");
%>

    
<%@ include file="/views/common/header.jsp" %>

<style type="text/css">
    section#memberList-container {text-align:center;}
    
    section#memberList-container table#tbl-member {width:100%; border:1px solid gray; border-collapse:collapse;}
    section#memberList-container table#tbl-member th, table#tbl-member td {border:1px solid gray; padding:10px; }
    
        /* 검색창에 대한 스타일 */
    div#search-container {margin:0 0 10px 0; padding:3px; 
    background-color: rgba(0, 188, 212, 0.3);}
    div#search-userId{display:inline-block;}
    div#search-userName{display:none;}
    div#search-gender{display:none;}
    div#numPerpage-container{float:right;}
    form#numperPageFrm{display:inline;}
 
</style>

    <section id="memberList-container">
        <h2>회원관리</h2>
                <div id="search-container">
        	검색타입 : 
        	<select id="searchType" > 
        		<option value="userId" >아이디</option>
        		<option value="userName" >회원이름</option>
        		<option value="gender" >성별</option>
        	</select>
        	
        	<script>
        		$("#searchType").change(e=>{
        			const type=$(e.target).val();
					$("#search-container>div").hide();
        			$("#search-"+type).css("display","inline-block")();					
					
        		});   		
        	</script>
        	
        	<div id="search-userId">
        		<form action="<%=request.getContextPath()%>/admin/searchMember">
        			<input type="hidden" name="searchType" value="userId" >
        			<input type="text" name="searchKeyword" size="25" 
        			placeholder="검색할 아이디를 입력하세요" >
        			<button type="submit">검색</button>
        		</form>
        	</div>
        	
        	<div id="search-userName">
        		<form action="<%=request.getContextPath()%>/admin/searchMember">
        			<input type="hidden" name="searchType" value="userName">
        			<input type="text" name="searchKeyword" size="25" 
        			placeholder="검색할 이름을 입력하세요">
        			<button type="submit">검색</button>
        		</form>
        	</div>
        	
        	<div id="search-gender">
        		<form action="<%=request.getContextPath()%>/admin/searchMember">
        			<input type="hidden" name="searchType" value="gender">
        			<label><input type="radio" name="searchKeyword" value="M" >남</label>
        			<label><input type="radio" name="searchKeyword" value="F" >여</label>
        			<button type="submit">검색</button>
        		</form>
        	</div>
        	
        </div>
        
        <div id="numPerpage-container">
        	페이지당 회원수 : 
        	<form id="numPerFrm" action="<%=request.getContextPath() %>/admin/memberList.do">
        		<select name="numPerpage" id="numPerpage" onchange="this.form.submit()" >     			
        			<option value="15" >선택</option>
        			<option value="10" >10</option>
        			<option value="5" >5</option>
        			<option value="3" >3</option>
        		</select>   

        	</form>

        </div>
        
        
<%--         <div id="numPerpage-container">
           페이지당 회원수 : 
           <form id="numPerFrm" action="<%=request.getContextPath()%>/admin/memberList.do">
              <select name="numPerpage" id="numPerpage" onchange="this.form.submit()">
                 <option value="10">10</option>
                 <option value="5" >5</option>
                 <option value="3" >3</option>
              </select>
              
           </form>
        </div> --%>
		

        <table id="tbl-member">
            <thead>
                <tr>
		            <th>아이디</th>
				    <th>이름</th>
				    <th>성별</th>
				    <th>나이</th>
				    <th>이메일</th>
				    <th>전화번호</th>
				    <th>주소</th>
				    <th>취미</th>
				    <th>가입날짜</th>
                </tr>
            </thead>
            <tbody>
            <%if(members.isEmpty()){ %>
            	<tr>
            		<td><h3>조회된 결과가 없습니다.</h3></td>
            	</tr>
            <%} else{
            	for(int i=0;i<members.size();i++){      		
            %>            		
            	<tr>
            		<td><%=members.get(i).getUserId() %></td>
            		<td><%=members.get(i).getUserName() %></td>
            		<td><%=members.get(i).getGender() %></td>
            		<td><%=members.get(i).getAge() %></td>
            		<td><%=members.get(i).getEmail() %></td>
            		<td><%=members.get(i).getPhone() %></td>
            		<td><%=members.get(i).getAddress() %></td>
            		<td><%=String.join(",",members.get(i).getHobby()) %></td>
            		<td><%=members.get(i).getEnrollDate() %></td>
            	</tr>
            
            <%} 
            } %>
            
			<!-- 조회된 결과가 없으면 한줄(row)로 결과가 없습니다 출력하고
	    	조회된 결과가 있으면 각 객체를 tr로 출력하는 구문을 작성할것 -->
	    	
	    	<!-- jsp 파일 템플릿 만들어서 쓸 수 잇음!.!.!>>!>1!! 그럼 header footer 계속 안써두댐!! -->
	    	<!-- List 공부하기! ***********************-->
	    	
            </tbody>
        </table>
        <div id="pageBar">
        	<%=request.getAttribute("pageBar") %>
        </div>
    </section>  

    
<%@ include file="/views/common/footer.jsp" %>
    