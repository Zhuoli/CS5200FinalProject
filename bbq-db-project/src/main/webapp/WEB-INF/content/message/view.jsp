<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="utf-8">
<title>User's Message Box</title>



</head>
<body>
	<div>
		<h3>View Alll Messages</h3>
		<c:forEach var="message" items="${msgs}">
			<div>
				<%-- sender:<a href="<%=basePath%>bookorder/orderDetail.action?bookOrderId=${bookOrder.orderId}" target="_blank"> <c:out value="${bookOrder.orderId}"/></a> --%>
				title: <c:out value="${message.title}"/>
				content:<c:out value = "${message.content}"/>
				time: <c:out value="${message.time}"/>
			</div>
		</c:forEach>
	</div> 



</body>
</html>