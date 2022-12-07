<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/mypageMain_B.jsp"%>
<link href="<%=request.getContextPath() %>/css/account/updateBrokerMainStyle.css" type="text/css" rel="stylesheet">

<!DOCTYPE html>
<section class="content">
    <div id="divOuter">
        <div id="button-Container">        
            <button class="btns" id="userInfo"><a href="<%=request.getContextPath()%>/account/updateUser.bb" style="color:white">일반 정보 수정</a></button>
            <button class="btns" id="brokerInfo"><a href="<%=request.getContextPath()%>/account/broker/updateBroker.bb" style="color:white">중개사 정보 수정</a></button>
        </div>
    </div>
    </section>
<%-- <%@ include file="/views/common/footer.jsp"%> --%>