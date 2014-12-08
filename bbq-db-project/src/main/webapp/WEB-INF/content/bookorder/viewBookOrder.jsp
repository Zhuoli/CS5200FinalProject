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
<link href="<%=basePath%>css/table.css" rel="stylesheet" type="text/css" />


</head>
<body>
 <h1 class="h1">User Orders</h1>
 <jsp:include page="../user-login.jsp"/>
 <table width="500" border="0" cellspacing="0" cellpadding="0" class="CSSTableGenerator">  
	<tr>  
   	 	<th>Order Id</th>  
    	<th>Status</th>
    	<th>Time</th>
	</tr>  
	<c:forEach var="bookOrder" items="${bookOrders}">
	<tr>
    	<td><a href="<%=basePath%>bookorder/orderDetail.action?bookOrderId=${bookOrder.orderId}" target="_blank">${bookOrder.orderId}</a></td>
    	<td>${bookOrder.orderStatus}</td>
    	<td><s:date name="datetime" format="dd/MM/yyyy"/>${bookOrder.orderTime}</td>
	</tr>
	</c:forEach> 
</table>


</body>
</html>