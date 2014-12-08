<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<base href="<%=basePath%>">
<link href="<%=basePath%>css/view.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="utf-8">
<style>
body{
    background-color: #d0e4fe;
 }
 </style>
<title>Message: Send message</title>
<script type="text/javascript" charset="utf-8">
    $(document).ready(function() {
        $('#send').click(function() {

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
</script>
</head>
<body>
	<h3>Send messages</h3>

    <jsp:include page="../user-login.jsp"/>

<div id="comment_form">
	
	<div>
		<input type="text" name="name" id="name" value="" placeholder="Name">
	</div>
	<div>
		<input type="text" name="title" id="title" value="" placeholder=Title>
	</div>
	<div>
		<textarea rows="10" name="comment" id="comment" placeholder="Comment"></textarea>
	</div>
	<div>
		<!-- <input type="submit" name="submit" value="Add Comment"> -->
		<button id='send'>send</button>  &nbsp;
	</div>
	
</div>



</body>
</html>