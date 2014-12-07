<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<base href="<%=basePath%>">
<title>By Book In Queue - BBQ</title>
<script type="text/javascript" src="<%=basePath%>js/jquery-2.1.1.min.js"></script>
<meta charset="utf-8">
<script type="text/javascript" charset="utf-8">
    $(document).ready(function() {

    });

    function updateBookQuantity(index) {

         alert($('#orderId' + index).val());
         alert($('#quantity' + index).val());

       // window.location.href='<%=basePath%>bookinorder/updateQuantity.action?bookOrderId=${bookInOrder.bookorder.orderId}&book=${bookInOrder.book.bookId}&quantity=document.getElementById('${status.index+1}').value'
    }
</script>
</head>
<body>
 Order Details
 <table width="500" border="0" cellspacing="0" cellpadding="0">  
	<tr>  
		<th style="display:none;">orderId</th>
   	 	<th>Book Title</th>  
    	<th>Seller</th>
    	<th>Quantity</th>
    	<th></th> 
    	<th></th> 
	</tr>  
	<c:forEach var="bookInOrder" items="${bookInOrders}" varStatus="status">
    <input type="hidden" id='orderId${status.index+1}' value='${bookInOrder.bookorder.orderId}' />
	<tr>
    	<td>${bookInOrder.book.title}</td>
    	<td>${bookInOrder.book.user.userName}</td>  
    	<td><input id='quantity${status.index+1}' type='text' value= "${bookInOrder.quantity}"/></td>
    	<td><button onclick="updateBookQuantity(${status.index+1})">Update</button></td>
	</tr>
	</c:forEach>  
</table>  
	
	<button id="cancle">Cancle</button>
	<button id="checkout">Check Out</button>
</body>
</html>