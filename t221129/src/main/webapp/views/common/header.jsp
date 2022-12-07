<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>UI 디자인 테스트</title>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">
<script src="<%=request.getContextPath()%>/js/jquery-3.6.1.min.js"></script>

</head>
<body>
	<div id="container">
		<header>
			<h1>UI디자인</h1>
			<div class="login-container">

				<form id="loginFrm" action="" method="post">
					<table>
						<tr>
							<td>
								<input type="text" name="userId" id="userId" placeholder="아이디" 
								v>
							</td>
						</tr>
						<tr>
							<td>
								<input type="password" name="password" id="password" placeholder="비밀번호">
							</td>						
							<td>
								<input type="submit" value="로그인" style="background-color: cornflowerblue">
							</td>						
						</tr>
						<tr>
							<td>
								<input type="checkbox" name="saveId" id="saveId">
								<label for="saveId">아이디저장</label>
								<input type="button" value="회원가입"
								onclick="location.replace('<%=request.getContextPath()%>/memberenroll.do')" style="background-color: cornflowerblue">
							</td>						
						</tr>
					</table>					
				</form>

			</div>
			<nav>
				<ul class="main-nav">
					<li class="home"><a href="">Home</a></li>
					<li id="notice"><a href="">공지사항</a></li>
					<li id="board"><a href="">게시판</a></li>
				</ul>
			</nav>
		</header>