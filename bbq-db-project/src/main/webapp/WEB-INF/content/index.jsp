<%@ page import="com.bbq.db.project.model.User" %>
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
<title>By Book In Queue - BBQ</title>
<script type="text/javascript" src="<%=basePath%>js/jquery-2.1.1.min.js"></script>
<meta charset="utf-8">
<script type="text/javascript" charset="utf-8">
    $(document).ready(function() {

        $('#login').click(function() {

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

        $('#logout').click(function() {

            $.get("<%=basePath%>user/loginOut.action",
                    function(data, status){
                        if(data['code'] == 'A00000') {
                            $('#userinfo').hide()
                            $('#login-div').show()
                        } else {
                            alert('logout failed, please retry!');
                        }
                    });
        });
    });

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
	<h1>Buy Book In Queue - BBQ</h1>
	<div>
		Buy Book In Queue - BBQ
        <div ${sessionScope.user == null ? '' : 'hidden=true'}  id="login-div">
           username: <input name='username' id='username' type='text'/>
           password: <input name='password' id='password' type='password'/>
           <button id='login'>login</button>
        </div>
        <div ${sessionScope.user == null ? 'hidden=true' : ''} id="userinfo">
           <a href="<%=basePath%>bookorder/getAllOrders.action" id='mypage'>${sessionScope.user.userName}</a>
           <button id='logout'>logout</button>
        </div>
	</div>

    <div>
        <c:forEach var="book" items="${books}">
            <div>
                <input type="hidden" name='bookId' value=${book.bookId}/>
                bookTitle: <a href="<%=basePath%>book/viewBook.action?bookId=${book.bookId}" target="_blank"> <c:out value="${book.title}"/> </a>
                author: <c:out value="${book.author}"/>
                isbn: <c:out value="${book.isbn}"/>
                quantity: <c:out value="${book.quantity}"/>
                price: <c:out value="${book.price}"/>
            </div>
        </c:forEach>
    </div>

    <p style="margin-left:180px;">
        currentPage: ${pageInfo.currentPageNo}     &nbsp;&nbsp;
        <span onclick="javascript:window.location.href='<%=basePath%>index.action?pageInfo.currentPageNo=1'" style="cursor:pointer;">firstPage</span>      &nbsp;
        <span onclick="previousPage()" style="cursor:pointer;">previousPage</span>   &nbsp;
        <span onclick="nextPage()" style="cursor:pointer;">nextPage</span>  &nbsp;
        <span onclick="javascript:window.location.href='<%=basePath%>index.action?pageInfo.currentPageNo=${pageInfo.totalPage}'" style="cursor:pointer;">lastPage</span>  &nbsp;&nbsp;
        <input type='text' name="pageNo" id="pageNo" style="width:15px;"/> <a onclick="jumpToPage()">go</a>
        total : ${pageInfo.totalPage}
    </p>

</body>
</html>

