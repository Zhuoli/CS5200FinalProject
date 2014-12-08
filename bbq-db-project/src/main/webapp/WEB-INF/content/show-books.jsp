<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="CSSTableGenerator" >
    <table >
        <tr>
            <td>bookTitle</td>
            <td>author</td>
            <td>isbn</td>
            <td>quantity</td>
            <td>price</td>
            <td>Operation</td>
        </tr>
        <c:forEach var="book" items="${books}">
            <tr>
                <input type="hidden" name='bookId' value=${book.bookId}/>
                <td><a href="<%=basePath%>book/viewBook.action?bookId=${book.bookId}" target="_blank">${book.title}</a>
                <td>${book.author}</td>
                <td>${book.isbn}</td>
                <td>${book.quantity}</td>
                <td>${book.price}</td>
                <td>
                    <c:choose>
                        <c:when test="${sessionScope.user.userType == 2 || sessionScope.user.userId == book.user.userId}">
                            <a href="<%=basePath%>book/preAddBook.action?bookId=${book.bookId}">Update</a>
                        </c:when>
                        <c:otherwise>
                            Can't update
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>