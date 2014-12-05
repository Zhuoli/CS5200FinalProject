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
<title>By Book In Queue - BBQ</title>
<script type="text/javascript" src="<%=basePath%>js/jquery-2.1.1.min.js"></script>
<meta charset="utf-8">
<script type="text/javascript" charset="utf-8">
    $(document).ready(function() {
    	$('#addBookToOrder').click(function() {
            $.post("<%=basePath%>book/addBookToOrder.action",
                    {
                        bookId: $('#bookId').val(),
                        quantity: $('#quantity').val(),
                    },
                    function(data, status){
                        if(data['code'] == 'A00000') {
                            $('#mypage').html(data['user']['userName'])
                            $('#userinfo').show()
                            $('#login-div').hide()
                        } else {
                            alert('login failed, please retry!');
                        }
                    });
        });
    });
</script>
</head>
<body>
<header>
</header>
	<h1>Buy Book In Queue - BBQ</h1>	
	<div>
       <div>${book.title}</div>
       <div>${book.author}</div>
       <div>${book.isbn}</div>
       <div>${book.quantity}</div>
       <div>${book.price}</div>
       <div>${book.publisher}</div>
       <div>${book.user.userName}</div>
	</div>
</body>
</html>

