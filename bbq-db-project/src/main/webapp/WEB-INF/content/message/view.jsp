<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<%-- <base href="<%=basePath%>">
<link href="<%=basePath%>css/view.css" rel="stylesheet" type="text/css" /> --%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="utf-8">
<title>User's Message Box</title>



</head>
<body>
	<h1>User Message Box</h1>
    <jsp:include page="../user-login.jsp"/>
	<c:if test="${sessionScope.user.userId == user.userId}">
        <a href="<%=basePath%>message/newMessage.action"> Create new Message</a>
    </c:if>
	<h3>View Alll Messages</h3>
	<c:forEach var="message" items="${msgs}">
		<fieldset>
			<%-- sender:<a href="<%=basePath%>bookorder/orderDetail.action?bookOrderId=${bookOrder.orderId}" target="_blank"> <c:out value="${bookOrder.orderId}"/></a> --%>
			<div id="title"> 
				<p>Title: <c:out value="${message.title}"/></p>
			</div>
			<div id="sender">
				From: <c:out value="${message.sender}"/>
			</div>
			<div id="date">
				Time: <c:out value="${message.time}"/>
			</div>
			        &nbsp;
			<div id="content"> 
				<c:out value = "${message.content}"/>
			</div>
			<button id='reply'>reply</button>  &nbsp;
		</fieldset>
	</c:forEach>



</body>
</html>