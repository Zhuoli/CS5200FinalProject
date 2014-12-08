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
<link href="<%=basePath%>css/table.css" rel="stylesheet" type="text/css" />
<title>By Book In Queue - BBQ</title>
<script type="text/javascript" src="<%=basePath%>js/jquery-2.1.1.min.js"></script>
<meta charset="utf-8">
<script type="text/javascript" charset="utf-8">
    function previousPage(){
        var pageNo = ${pageInfo.currentPageNo - 1};
        if(pageNo <= 0) {
            pageNo = 1;
        }
        window.location.href='<%=basePath%>index.action?pageInfo.currentPageNo=' + pageNo;
    }
    function nextPage(){
        var pageNo = ${pageInfo.currentPageNo + 1};
        if(pageNo > ${pageInfo.totalPage}) {
            pageNo = ${pageInfo.totalPage};
        }
        window.location.href='<%=basePath%>index.action?pageInfo.currentPageNo=' + pageNo;
    }
    function jumpToPage() {
        var pageNo = $('#pageNo').val();
        if(pageNo <= 0 || isNaN(pageNo)) {
            pageNo = 1;
        } else if(pageNo > ${pageInfo.totalPage}) {
            pageNo = ${pageInfo.totalPage};
        }
        window.location.href='<%=basePath%>index.action?pageInfo.currentPageNo=' + pageNo;
    }
</script>

</head>
<body>
<header>
</header>
	<h1 class="h1">Buy Book In Queue - Welcome</h1>
	<jsp:include page="user-login.jsp"/>

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

    <div style="margin-top: 20px; text-align: center">
        CurrentPage: ${pageInfo.currentPageNo}     &nbsp;&nbsp;  Total : ${pageInfo.totalPage}
    </div>
    <div style="margin-top: 15px; text-align: center">
        <a onclick="javascript:window.location.href='<%=basePath%>index.action?pageInfo.currentPageNo=1'" href="javascript:void(0)">firstPage</a>      &nbsp;
        <a onclick="previousPage()" href="javascript:void(0)">previousPage</a>   &nbsp;
        <a onclick="nextPage()" href="javascript:void(0)">nextPage</a>  &nbsp;
        <a onclick="javascript:window.location.href='<%=basePath%>index.action?pageInfo.currentPageNo=${pageInfo.totalPage}'" href="javascript:void(0)">lastPage</a>  &nbsp;&nbsp;
    </div>
    <div style="margin-top: 10px; text-align: center">
        <a onclick="jumpToPage()" href="javascript:void(0)">go</a>
        <input type='text' name="pageNo" id="pageNo" style="width:15px;"/>
    </div>

</body>
</html>
