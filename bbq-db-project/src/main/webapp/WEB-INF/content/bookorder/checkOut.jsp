<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="<%=basePath%>js/jquery-2.1.1.min.js"></script>
<meta charset="utf-8">
<script type="text/javascript" charset="utf-8">
    $(document).ready(function() {
    	$('#addAddress').click(function() {
            $.post("<%=basePath%>address/addNewAddress.action",
                    {
                        street: $('#street').val(),
                        city: $('#city').val(),
                        country: $('#country').val(),
                        zipcode: $('#zipcode').val()
                    },
                    function(data, status){
                        if(data['code'] == 'A00000') {
                        	alert('add address success!!!!');
                        	location.reload();
                        } else {
                            alert('add address failed!');
                        }
                    });
        });
    });
    
    function checkout() {        
    	var bookOrderId = $('#orderId1').val();
    	var amount = $('#amount').val();
    	var index = $('input:radio[name="address"]:checked').val();
    	var addressId = $('#addressId'+ index).val();
    	window.location.href='<%=basePath%>bookorder/checkOut.action?bookOrderId=' + bookOrderId+'&amount='+amount+'&addressId=' + addressId;
    }
</script>
</head>
<body>
check out
<table width="500" border="0" cellspacing="0" cellpadding="0">  
	<tr>  
   	 	<th>Book Title</th>  
    	<th>Seller</th>
    	<th>Quantity</th>  
	</tr>  
	<c:forEach var="bookInOrder" items="${bookInOrders}" varStatus="status">  
	<input type="hidden" id='orderId${status.index+1}' value='${bookInOrder.bookorder.orderId}' />
	<input type="hidden" id='amount' value='${amount}' />
	<tr>  
    	<td>${bookInOrder.book.title}</td>  
    	<td>${bookInOrder.book.user.userName}</td>  
    	<td>${bookInOrder.quantity}</td>
	</tr>  
	</c:forEach>  
	<tr>
	<td>Amount:</td>
	<td>${amount}</td>
	</tr>
</table> 

<table width="500" border="0" cellspacing="0" cellpadding="0">  
	<tr>  
   	 	<th>Address</th> 
	</tr>  
	<c:forEach var="address" items="${addresses}" varStatus="status">
	<input type="hidden" id='addressId${status.index+1}' value='${address.addressId}' />
	<tr>
		<td>
			<input type="radio" name="address" value='${status.index+1}' />${address.street} ${address.city} ${address.country} ${address.zipcode}   
		</td>
	</tr>
	</c:forEach> 	
</table> 
<button id="addAddress">add new address</button>
<table width="500" border="0" cellspacing="0" cellpadding="0">  
	<tr>  
   	 	<th>Street</th> 
   	 	<th>City</th>
   	 	<th>Country</th>
   	 	<th>Zipcode</th>
	</tr>  
	<tr>
		<td>
			<input name='street' id='street' type='text'/>
		</td>
		<td>
			<input name='city' id='city' type='text'/>
		</td>
		<td>
			<input name='country' id='country' type='text'/>
		</td>
		<td>
			<input name='zipcode' id='zipcode' type='text'/>
		</td>
	</tr>
</table>
<button onclick="checkout()">Check Out</button> 
</body>
</html>