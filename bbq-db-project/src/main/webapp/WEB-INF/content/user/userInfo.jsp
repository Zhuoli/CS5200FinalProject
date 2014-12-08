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
    <p>
        username:${user.userName}
    </p>
    <p>
        gender: ${user.gender == 1 ? 'male' : 'female'}
    </p>
    <p>
        email: ${user.email}
    </p>
    <p>
        telephone: ${user.telephone}
    </p>
    <c:if test="${sessionScope.user.userId == user.userId}">
        account: ${user.account}
    </c:if>
</fieldset>

<div class="CSSTableGenerator" >
    <table >
        <tr>
            <td>bookTitle</td>
            <td>author</td>
            <td>isbn</td>
            <td>quantity</td>
            <td>price</td>
        </tr>
        <c:forEach var="book" items="${myBooks}">
            <tr>
                <td><a href="<%=basePath%>book/viewBook.action?bookId=${book.bookId}" target="_blank">${book.title}</a>
                <td>${book.author}</td>
                <td>${book.isbn}</td>
                <td>${book.quantity}</td>
                <td>${book.price}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>

