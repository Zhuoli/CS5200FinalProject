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
        $('#login').click(function() {
            $.post("<%=basePath%>user/login.action",
                    {
                        userName: $('#username').val(),
                        password: $('#password').val()
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
		Buy Book In Queue - BBQ
        <div id='login-div'>
           username: <input name='username' id='username' type='text'/>
           password: <input name='password' id='password' type='password'/>
           <button id='login'>login</button>
        </div>
        <div hidden="true" id='userinfo'>
           <a id='mypage'></a>
           <button id='logout'>logout</button>
        </div>
	</div>

    <div>
        <c:forEach var="book" items="${books}">
            ID <c:out value="${book.bookId}"/>
        </c:forEach>
    </div>
</body>
</html>

