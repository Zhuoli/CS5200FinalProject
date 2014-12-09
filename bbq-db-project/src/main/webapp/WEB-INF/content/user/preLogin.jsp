<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>

<html>
<head>
<title>By Book In Queue - add book</title>
<meta charset="utf-8">
<link href="<%=basePath%>css/uploadify.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath%>js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.validate.js"></script>
<script type="text/javascript">
    $(document).ready(function() {

        $("#form").validate({
            rules: {
                username: {
                    required: true,
                    minlength: 2
                },
                password: {
                    required: true,
                    minlength: 2
                }
            },

            submitHandler: function() {
                $.post("<%=basePath%>user/login",
                        {
                            userName: $('#username').val(),
                            password: $('#password').val()
                        },
                        function(data, status){
                            if(data['code'] == 'A00000') {
                               alert('login success');
                                location.href="<%=basePath%>index";
                            } else {
                                alert('login failed, please retry!');
                            }
                        });
            }
        });

    });
</script>
<style>
    input.error { border: 1px solid red; }
    label.error {
        padding-left: 16px;
        padding-bottom: 2px;
        font-weight: bold;
        color: #EA5200;
    }
    .error-msg{padding-left: 16px;
            padding-bottom: 2px;
            font-weight: bold;
            color: #EA5200;}
</style>
</head>
<body>
<header>
</header>
	<h1 class="h1">Buy Book In Queue - Login</h1>
    <jsp:include page="../nav.jsp"/>
    <h4 class="error-msg">${message}</h4>
<tr>
    <form id="form">
        <fieldset>
            <p>
                <label for="username">username:</label>
                <input id="username" name="username" minlength="2" type="text" required/>
            </p>
            <p>
                <label for="password">password:</label>
                <input type="password" name="password" id="password" required/>
            </p>
            <input type="submit" value="register" />
        </fieldset>
    </form>
</tr>
</body>
</html>

