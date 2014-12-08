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
                },
                password2: {
                    required: true,
                    minlength: 2,
                    equalTo: '#password'
                },
                email: {
                    required: true,
                    email: true
                },
                telephone: {
                    required: true,
                    number: true
                }
            },

            submitHandler: function() {
                $.post("<%=basePath%>user/addUser.action",
                    {
                        "user.userName": $('#username').val(),
                        "user.password": $('#password').val(),
                        "user.gender": $('input:radio[name="gender"]:checked').val(),
                        "user.email": $('#email').val(),
                        "user.telephone": $('#telephone').val()
                    },
                    function(data, status){
                        if(data['code'] == 'A00000') {
                            alert('register success!');
                            location.href="<%=basePath%>index.action";
                        } else {
                            alert('username has already exist!');
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
</style>
</head>
<body>
<header>
</header>
	<h1>Buy Book In Queue - BBQ</h1>
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
            <p>
                <label for="password2">password again:</label>
                <input type="password" name="password2" id="password2" required/>
            </p>
            <p>
                <input  type="radio" id="gender_male" value="1" name="gender" class="{required:true}" />  male
                <input  type="radio" id="gender_female" value="2" name="gender"/>   female
            </p>
            <p>
                <label for="email">email:</label>
                <input type="email" name="email" id="email" required/>
            </p>
            <p>
                <label for="telephone">telephone:</label>
                <input type="text" name="telephone" id="telephone" required/>
            </p>
            <input type="submit" value="register" />
        </fieldset>
    </form>
</tr>
</body>
</html>

