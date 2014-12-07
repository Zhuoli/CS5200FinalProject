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
    <p>
        account: ${user.account}
    </p>
</fieldset>
<div>
    <c:forEach var="book" items="${myBooks}">
        <div>
            bookId: <c:out value="${book.bookId}"/>
            bookTitle: <a href="<%=basePath%>book/viewBook.action?bookId=${book.bookId}" target="_blank"> <c:out value="${book.title}"/> </a>
            author: <c:out value="${book.author}"/>
            isbn: <c:out value="${book.isbn}"/>
            quantity: <c:out value="${book.quantity}"/>
            price: <c:out value="${book.price}"/>
        </div>
    </c:forEach>
</div>
</body>
</html>

