<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>

<html>
<head>
<base href="<%=basePath%>">
<link href="<%=basePath%>css/table.css" rel="stylesheet" type="text/css" />
<title>By Book In Queue - BBQ</title>
<script type="text/javascript" src="<%=basePath%>js/jquery-2.1.1.min.js"></script>
<meta charset="utf-8">
<script type="text/javascript" charset="utf-8">
    $(document).ready(function() {
    	$('#addBookToOrder').click(function() {

            var quantity = $('#quantity').val();
            if(isNaN(quantity)) {
                alert('please enter a number!');
                return false;
            } else if(quantity > ${book.quantity}) {
                alert('please enter a number not greater than the book total quantity!');
                return false;
            }

            $.post("<%=basePath%>bookorder/addBookToOrder.action",
                    {
                        bookId: ${book.bookId},
                        quantity: quantity
                    },
                    function(data, status){
                        if(data['code'] == 'A00000') {
                        	alert('add order success!!!!');
                        } else {
                            alert('add order failed!');
                        }
                    });
        });
    });
</script>
</head>
<body>
<header>
</header>
	<h1 class="h1">Buy Book In Queue - ViewBook</h1>
    <jsp:include page="../user-login.jsp"/>
	<div>
        <div class="pic">
           <img src="<%=basePath%>uploads/${book.pic}" width="500" height="400">
        </div>
         <div class='book'>
             <ul>
                 <li>Title: ${book.title}</li>   <br/>
                 <li>Author: ${book.author}</li> <br/>
                 <li>ISBN: ${book.isbn}</li>     <br/>
                 <li>Quantity: ${book.quantity}</li>  <br/>
                 <li>Price: ${book.price / 100}</li>  <br/>
                 <li>Publisher: ${book.publisher}</li> <br/>
                 <li>Book owner: <a href="<%=basePath%>user/userInfo.action?userId=${book.user.userId}">${book.user.userName}</a></li> <br/>
                 <li>How many do I want? <input name='quantity' id='quantity' type='text'/>
                 <button id='addBookToOrder'>AddToCart</button></li>
             </ul>
        </div>
    </div>

</body>
</html>

