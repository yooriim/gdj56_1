<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/views/common/header.jsp" %>

<section id="enroll-container">
        <h2>회원 가입 정보 입력</h2>
        <form name="enrollMemberFrm" action="<%=request.getContextPath()%>/enrollMemberEnd.do" method="post" onsubmit="return fn_invalidate();" >
        <table>
			<tr>
				<th>아이디</th>
				<td>
					<input type="text" placeholder="4글자이상" name="userId" id="userId_" >
					<input type="button" value="중복확인" onclick="fn_idduplicate();">
					<!-- 
					<button type="button">중복확인</button> 
					form 안에 버튼이 있으면 submit 해버림 ㅠ type으로 button을 줘서 얘가 버튼인걸 알랴줘야 함	-->
				</td>

				<script>
				const fn_idduplicate=()=>{
					const userId=$("#userId_").val();
					if(userId.trim().length<4){
						alert('아이디는 4글자 이상입력해야 합니다!');
						$("#userId_").val('');
						$("#userId_").focus();
					}else{
						<%-- open("<%=request.getContextPath()%>/member/idDuplicate.do?userId="+userId,
								"_blank","width=300,height=300"); --%>
						const title="idDuplicateFrm";
						open("",title,"width=300,height=300");
						console.log(duplicateId);
						duplicateId.userId.value=userId;
						duplicateId.method="post";
						duplicateId.action="<%=request.getContextPath()%>/member/idDuplicate.do";
						duplicateId.target=title;
						duplicateId.submit();
					}
				}
 					
				</script>
			</tr>
			<tr>
				<th>패스워드</th>
				<td>
					<input type="password" name="password" id="password_" ><br>
				</td>
			</tr>
			<tr>
				<th>패스워드확인</th>
				<td>	
					<input type="password" id="password_2" ><br>
					<span id="pwresult"></span>
					<!-- 비밀번호가 일치하지 않으면 회원가입이 되지 않는 로직 필요!! -->
					
				</td>
			</tr>  
			<tr>
				<th>이름</th>
				<td>	
				<input type="text"  name="userName" id="userName" ><br>
				</td>
			</tr>
			<tr>
				<th>나이</th>
				<td>	
				<input type="number" name="age" id="age"><br>
				</td>
			</tr> 
			<tr>
				<th>이메일</th>
				<td>	
					<input type="email" placeholder="abc@xyz.com" name="email" id="email"><br>
				</td>
			</tr>
			<tr>
				<th>휴대폰</th>
				<td>	
					<input type="tel" placeholder="(-없이)01012345678" name="phone" id="phone" maxlength="11" required><br>
				</td>
			</tr>
			<tr>
				<th>주소</th>
				<td>	
					<input type="text" placeholder="" name="address" id="address"><br>
				</td>
			</tr>
			<tr>
				<th>성별 </th>
				<td>
					<input type="radio" name="gender" id="gender0" value="M">
					<label for="gender0">남</label>
					<input type="radio" name="gender" id="gender1" value="F">
					<label for="gender1">여</label>
				</td>
			</tr>
			<tr>
				<th>취미 </th>
				<td>
					<input type="checkbox" name="hobby" id="hobby0" value="운동"><label for="hobby0">운동</label>
					<input type="checkbox" name="hobby" id="hobby1" value="등산"><label for="hobby1">등산</label>
					<input type="checkbox" name="hobby" id="hobby2" value="독서"><label for="hobby2">독서</label><br />
					<input type="checkbox" name="hobby" id="hobby3" value="게임"><label for="hobby3">게임</label>
					<input type="checkbox" name="hobby" id="hobby4" value="여행"><label for="hobby4">여행</label><br />
				</td>
			</tr>
		</table>
		<input type="submit" value="가입" >
		<input type="reset" value="취소">
        </form>
</section>
				<form name="duplicateId">
					<input type="hidden" name="userId">
				</form>
<script>
	$(()=>{
		$("#password_2").blur(e=>{
			const pw=$("#password_").val();
			const pwck=$(e.target).val();
			//pw8글자 이상 조건 걸기
			if(pw==pwck){
				$("#pwresult").css("color","green").text("비밀번호가 일치합니다.");
			}else{
				$("#pwresult").css("color","red").text("비밀번호가 일치하지 않습니다.")
			}
		});
	})
	
	const fn_invalidate=()=>{
		//아이디가 4글자 이상 입력
		const userId=$("#userId_").val().trim();
		//패스워드가 8글자 이상 입력
		const password=$("#password_").val().trim();
		if(userId.length<4){
			alert("아이디는 4글자 이상입력하세요.");
			return false;
		
		}
		
		const passwordReg=/^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*()])[A-Za-z\d!@#$%^&*()]{8,}$/;
		//console.log(!passwordReg.test(password));
		if(!passwordReg.test(password)){
			alert("패스워드는 8글자 이상, 소문자로 시작하고 숫자, 특수기호를 포함해야 합니다.");
			return false;
		}
		
		
	}
</script>
    
<%@ include file="/views/common/footer.jsp" %>
