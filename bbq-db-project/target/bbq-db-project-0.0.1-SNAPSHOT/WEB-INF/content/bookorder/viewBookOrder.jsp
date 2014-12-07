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
<title>Insert title here</title>



</head>
<body>
 User Orders
	<div>
		<c:forEach var="bookOrder" items="${bookOrders}">
			<div>
				orderId:<a href="<%=basePath%>bookorder/orderDetail.action?bookOrderId=${bookOrder.orderId}" target="_blank"> <c:out value="${bookOrder.orderId}"/></a>
				status: <c:out value="${bookOrder.orderStatus}"/>
				time: <c:out value="${bookOrder.orderTime}"/>
			</div>
		</c:forEach>
	</div> 



</body>
</html>