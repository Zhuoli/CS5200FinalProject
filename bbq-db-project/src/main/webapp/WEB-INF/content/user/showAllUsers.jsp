<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>

<html>
<head>
<title>By Book In Queue - All Users</title>
<meta charset="utf-8">
<link href="<%=basePath%>css/table.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>css/popWindow.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath%>js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/popup.js"></script>
<script type="text/javascript">

    function updateUser(userId){


        $.post("<%=basePath%>user/getUserById.action",
                {
                    userId: userId
                },
                function(data, status){
                    if(data['code'] == 'A00000') {
                        var userName = data['user']['userName'];
                        var password = data['user']['password'];
                        var gender = data['user']['gender'];
                        var email = data['user']['email'];
                        var telephone = data['user']['telephone'];
                        var account = data['user']['account'];
                        popup("UpdateUser " + userId,"userName: <input type='text' id='userName' disabled value=" + userName + " /><br/>" +
                                "password: <input type='text' id='password' value=" + password + " /><br/>" +
                                "gender(1:male;2:female): <input type='text' id='gender' value=" + gender + " /><br/>" +
                                "email: <input type='text' id='email' value=" + email + " /><br/>" +
                                "telephone: <input type='text' id='telephone' value=" + telephone + " /><br/>" +
                                "account: <input type='text' id='account' value=" + account + " /> <br/>" +
                                "<input type='button' onclick='submitUpdate(" + userId + ")' value='Update' />",
                                "400","300","");
                    } else {
                        alert("Cann't find the User!");
                    }
                });
    };

    function submitUpdate(userId) {

        $.post("<%=basePath%>user/addUser.action",
                {
                    "user.userId"  : userId,
                    "user.userName": $('#userName').val(),
                    "user.password": $('#password').val(),
                    userRoleId  : 1,
                    "user.gender": $('#gender').val(),
                    "user.email": $('#email').val(),
                    "user.telephone": $('#telephone').val(),
                    "user.account": $('#account').val()
                },
                function(data, status){
                    if(data['code'] == 'A00000') {
                        alert('Update user success!');
                        location.reload();
                    } else {
                        alert('Update user error!');
                    }
                });
    }
</script>
</head>
<body>
<header>
</header>
	<h1 class="h1">Buy Book In Queue - All Users</h1>
<div class="CSSTableGenerator">
    <table>
        <tr>
            <td>UserName</td>
            <td>Gender</td>
            <td>Email</td>
            <td>Telephone</td>
            <td>Account</td>
            <td>Operation</td>
        </tr>
        <c:if test="${sessionScope.user.userRole.roleId == 2}">
            <c:forEach var="user" items="${users}">
                <tr>
                    <td><a href="<%=basePath%>user/userInfo.action?userId=${user.userId}" target="_blank">${user.userName}</a>
                    <td>${user.gender == 1 ? 'male' : 'female'}</td>
                    <td>${user.email}</td>
                    <td>${user.telephone}</td>
                    <td>${user.account}</td>
                    <td><a onclick="updateUser(${user.userId})" href="javascript:void(0)">Update</a></td>
                </tr>
            </c:forEach>
        </c:if>
   </table>
</div>
</body>
</html>

