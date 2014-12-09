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
</head>
<body>
<header>
</header>
	<h1 class="h1">Buy Book In Queue - User Actions</h1>
<div class="CSSTableGenerator">
    <table>
        <tr>
            <td>UserId</td>
            <td>UserName</td>
            <td>UserType</td>
            <td>ActionName</td>
            <td>ActionTime</td>
            <td>Params</td>
        </tr>
        <c:if test="${sessionScope.user.userType == 2}">
            <c:forEach var="userLog" items="${userLogs}">
                <tr>
                    <td><a href="<%=basePath%>user/userInfo.action?userId=${userLog.userId}" target="_blank">${user.userId}</a></td>
                    <td><a href="<%=basePath%>user/userInfo.action?userId=${userLog.userId}" target="_blank">${user.userName}</a></td>
                    <td>${userLog.userType}</td>
                    <td>${userLog.actionName}</td>
                    <td>${userLog.actionTime}</td>
                    <td>${userLog.params}</td>
                </tr>
            </c:forEach>
        </c:if>
   </table>
</div>
</body>
</html>

