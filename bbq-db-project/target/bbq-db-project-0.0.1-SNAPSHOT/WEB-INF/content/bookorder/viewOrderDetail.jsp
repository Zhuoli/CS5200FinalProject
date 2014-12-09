<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<script type="text/javascript" src="<%=basePath%>js/jquery-2.1.1.min.js"></script>
<link href="<%=basePath%>css/table.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
 <h1 class="h1">Order Details</h1>
 <jsp:include page="../user-login.jsp"/>
 <table width="500" border="0" cellspacing="0" cellpadding="0" class="CSSTableGenerator">  
	<tr>  
   	 	<th>Book Title</th>  
    	<th>Seller</th>
    	<th>Quantity</th>  
	</tr>  
	<c:forEach var="bookInOrder" items="${bookInOrders}" varStatus="status">  
	<tr>  
    	<td>${bookInOrder.book.title}</td>  
    	<td>${bookInOrder.book.user.userName}</td>  
    	<td>${bookInOrder.quantity}</td>
	</tr>  
	</c:forEach>  
</table>  
	
</body>
</html>