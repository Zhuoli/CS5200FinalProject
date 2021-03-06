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
<link href="<%=basePath%>css/table.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>css/bootstrap.css" rel="stylesheet" type="text/css" />
<meta charset="utf-8">
<script type="text/javascript" charset="utf-8">
    $(document).ready(function() {
    });
    
    function checkout() {  
    	$.post("<%=basePath%>bookinorder/orderAmount",
                {
       	         bookOrderId: $('#orderId1').val()
                },
                function(data, status){
                    if(data['code'] == 'A00000') {
                    	alert("amount"+data['amount']);
                    	var myDiv=document.getElementById("test");
                    	myDiv.innerText=data['amount'];
                    } else {
                    	alert("get amount failed!");
                    }
                });       
    }  

    function updateBookQuantity(index) {
         $.post("<%=basePath%>bookinorder/updateQuantity",
                 {
        	         bookOrderId: $('#orderId' + index).val(),
        	         quantity: $('#quantity' + index).val(),
        	         bookId: $('#bookId' + index).val()
                 },
                 function(data, status){
                     if(data['code'] == 'A00000') {
                         alert('update success!');
                         location.reload();
                     } else {
                         alert('update fail!');
                     }
                 });
         }
    
    
    function deleteBookInOrder(index) {        
        $.post("<%=basePath%>bookinorder/deleteBookInOrder",
                {
       	         bookOrderId: $('#orderId' + index).val(),
       	         bookId: $('#bookId' + index).val()
                },
                function(data, status){
                    if(data['code'] == 'A00000') {
                        alert('delete success!');
                        location.reload();
                    } else {
                        alert('delete fail!');
                    }
                });       
   }
    
    function cancleOrder() {        
    	var bookOrderId = $('#orderId1').val();
    	window.location.href='<%=basePath%>bookorder/updateStatus?bookOrderId=' + bookOrderId;
    	
    }
    
    function checkOut() {        
    	var bookOrderId = $('#orderId1').val();
    	window.location.href='<%=basePath%>bookinorder/orderAmount?bookOrderId=' + bookOrderId;
    	
    }
</script>
</head>
<body>
 <h1 class="h1">Order Details</h1>
 <jsp:include page="../user-login.jsp"/>
 <jsp:include page="../nav.jsp"/>
 <div align='center'>
 <table width="200" border="0" cellspacing="0" cellpadding="0" class="CSSTableGenerator" >  
	<tr>  
   	 	<th>Book Title</th>  
    	<th>Seller</th>
    	<th>Quantity</th>
	</tr>  
	<c:forEach var="bookInOrder" items="${bookInOrders}" varStatus="status">
    <input type="hidden" id='orderId${status.index+1}' value='${bookInOrder.bookorder.orderId}' />
    <input type="hidden" id='bookId${status.index+1}' value='${bookInOrder.book.bookId}' />
	<tr>
    	<td>${bookInOrder.book.title}</td>
    	<td>${bookInOrder.book.user.userName}</td>  
    	<td>
    		<input id='quantity${status.index+1}' type='text' value= "${bookInOrder.quantity}"/>
    		<button onclick="updateBookQuantity(${status.index+1})" class="btn btn-primary">Update</button>
    		<button onclick="deleteBookInOrder(${status.index+1})" class="btn btn-danger">Delete</button>
    	</td>
	</tr>
	</c:forEach> 
</table> 
</div>
<div align='right'>
	<button onclick="cancleOrder()" class="btn btn-warning">Cancle</button>
	<button onclick="checkOut()" class="btn btn-success">Check Out</button>
</div>
	 
</body>
</html>