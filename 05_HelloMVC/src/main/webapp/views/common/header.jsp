<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.web.member.vo.Member" %>
    
<%
    Member loginMember=(Member)session.getAttribute("loginMember");

	Cookie[] cookies=request.getCookies();
	String saveId=null;
	if(cookies!=null){
		for(Cookie c:cookies){
			if(c.getName().equals("saveId")){
				saveId=c.getValue();
				break;	//찾는거 나오면 끝내버령 굳이 끝까지 돌릴필요 x  
			}
		}
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HelloMvc 프로젝트</title>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">
<script src="<%=request.getContextPath()%>/js/jquery-3.6.1.min.js"></script>

</head>
<body>
	<div id="container">
		<header>
			<h1>HelloMVC</h1>
			<div class="login-container">
			<%if(loginMember==null){ %> <!-- if문 조건은 session유무 -->
			<!-- 로그인 후 f12-application 가면 session생겨있음. 
			근데 session 삭제했다가 새로고침하면 새로생기는데 그건 그냥 내장객체라서 
			자동으로 생기는 것임. 페이지 설정에 session false 주면 안생김-->
				<form id="loginFrm" action="<%=request.getContextPath() %>/login.do" method="post">
					<table>
						<tr>
							<td>
								<input type="text" name="userId" id="userId" placeholder="아이디" 
								value="<%=saveId!=null?saveId:""%>" 	>
							</td>
						</tr>
						<tr>
							<td>
								<input type="password" name="password" id="password" placeholder="비밀번호">
							</td>						
							<td>
								<input type="submit" value="로그인">
							</td>						
						</tr>
						<tr>
							<td>
								<input type="checkbox" name="saveId" id="saveId"
								<%=saveId!=null?"checked":""%> >	<!-- 아이디 저장 누르고 로그인 성공하면 담부터 계속 아이디 저장에 표시되도록 -->
								<label for="saveId">아이디저장</label>
								<input type="button" value="회원가입"
								onclick="location.replace('<%=request.getContextPath()%>/enrollMember.do')">
							</td>						
						</tr>
					</table>					
				</form>
			<%}else{%>
				<table id="logged-in">
					<tr>
						<td colspan="2">
							<%=loginMember.getUserName() %> 님, 어서오고?
						</td>
					</tr>
					<tr>
						<td>
							<input type="button" value="내 정보보기"
							onclick="location.replace('<%=request.getContextPath()%>/member/memberView.do?id=<%=loginMember.getUserId()%>');">
						</td>
						<td>
							<input type="button" value="로그아웃" 
							onclick="location.replace('<%=request.getContextPath()%>/logout.do');">
							<!-- replace하면 뒤로가기 X 히스토리 남기지않음 -->
						</td>
					</tr>
				</table>
			<%} %>
			</div>
			
			<%if(loginMember==null){ %>
				<nav>
					<ul class="main-nav">
						<li class="home"><a href="">Home</a></li>						
						<li id="notice" onclick="dologin();"><a href="">공지사항</a></li>						
						<li id="board"><a href="<%=request.getContextPath() %>/board/viewboard.do">게시판</a></li>
						<li id="gallery"><a href="">갤러리</a></li>
					</ul>
				</nav>
			<%} else if(loginMember.getUserId().equals("admin")){ %>
				<nav>
					<ul class="main-nav">
						<li class="home"><a href="">Home</a></li>
						<li id="notice"><a href="<%=request.getContextPath()%>/notice/noticeList.do">공지사항</a></li>
						<li id="board"><a href="<%=request.getContextPath() %>/board/viewboard.do">게시판</a></li>
						<li id="gallery"><a href="">갤러리</a></li>
						<li id="memberManage"><a href="<%=request.getContextPath()%>/admin/memberList.do">회원관리</a></li>						
					</ul>
				</nav>
			<%} else{ %>
				<nav>
					<ul class="main-nav">
						<li class="home"><a href="">Home</a></li>
						<li id="notice"><a href="<%=request.getContextPath()%>/notice/noticeList.do">공지사항</a></li>
						<li id="board"><a href="<%=request.getContextPath() %>/board/viewboard.do">게시판</a></li>
						<li id="gallery"><a href="">갤러리</a></li>
					</ul>
				</nav>
			<%} %>
		</header>
		<script>
			const dologin=()=>{
				alert("로그인하세요.");
				location.replace('<%=request.getContextPath()%>')
			}
		</script>