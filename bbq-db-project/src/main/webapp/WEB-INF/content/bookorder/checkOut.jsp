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
<link href="<%=basePath%>css/table.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>css/bootstrap.css" rel="stylesheet" type="text/css" />
<meta charset="utf-8">
<script type="text/javascript" charset="utf-8">
    $(document).ready(function() {
    	$('#addAddress').click(function() {
            $.post("<%=basePath%>address/addNewAddress",
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
    	if (index == null){
    		alert("please select an address!")
    	}else{
    		$.post("<%=basePath%>bookorder/checkQuantityandAmount",
                    {
    			     bookOrderId: $('#orderId1').val(),
    			     amount: $('#amount').val()
                    },
                    function(data, status){
                        if(data['code'] == 'A00000') {
                        	var addressId = $('#addressId'+ index).val();   	
                    		window.location.href='<%=basePath%>bookorder/checkOut?bookOrderId=' + bookOrderId+'&amount='+amount+'&addressId=' + addressId;
                        } else if (data['code'] == 'E00006'){
                            alert('no enough account!');
                        } else if (data['code'] == 'E00007'){
                        	alert('no enough book!');
                        } else {
                        	alert('check out failed!');
                        }
                    });
    		
    	}
    }
</script>
</head>
<body>
<h1 class="h1">check out</h1>
<jsp:include page="../user-login.jsp"/>
<jsp:include page="../nav.jsp"/>
<div>
Order Information
</div>
<table width="500" border="0" cellspacing="0" cellpadding="0" class="CSSTableGenerator">  
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
</table> 
<p />
<div>
Amount:${amount}
</div>
<p />
<table width="500" border="0" cellspacing="0" cellpadding="0" class="CSSTableGenerator">  
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
<p />

<table width="500" border="0" cellspacing="0" cellpadding="0" class="CSSTableGenerator">  
	<tr>  
   	 	<th>Street</th> 
   	 	<th>City</th>
   	 	<th>Country</th>
   	 	<th>Zipcode</th>
   	 	<th></th>
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
			<button id="addAddress" class="btn btn-primary">add new address</button>
		</td>
	</tr>
</table>
<p />
<button onclick="checkout()" class="btn btn-danger">Check Out</button> 
</body>
</html>