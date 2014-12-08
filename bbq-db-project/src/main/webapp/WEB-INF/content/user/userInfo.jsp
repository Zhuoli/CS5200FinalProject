<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>

<html>
<head>
<title>By Book In Queue - User Info</title>
<meta charset="utf-8">
<link href="<%=basePath%>css/table.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath%>js/jquery-2.1.1.min.js"></script>
<script type="text/javascript">
    $(document).ready(function() {


    });
</script>
</head>
<body>
<header>
</header>
	<h1>Buy Book In Queue - User Info</h1>
    <c:if test="${sessionScope.user.userId == user.userId}">
        <a>message</a>          &nbsp;
        <a href="<%=basePath%>bookorder/getAllOrders.action">My Orders</a>
    </c:if>
    <jsp:include page="../user-login.jsp"/>
<fieldset>
    <p>username:${user.userName}</p>
    <p>gender: ${user.gender == 1 ? 'male' : 'female'}</p>
    <p>email: ${user.email}</p>
    <p>telephone: ${user.telephone}</p>
    <c:if test="${sessionScope.user.userId == user.userId}">
        <p>account: ${user.account}</p>
    </c:if>
</fieldset>

<jsp:include page="../show-books.jsp"/>
</body>
</html>

