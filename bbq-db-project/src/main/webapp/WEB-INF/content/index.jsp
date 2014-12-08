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
<link href="<%=basePath%>css/table.css" rel="stylesheet" type="text/css" />
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
        <div style="margin-right:30px;" ${sessionScope.user == null ? '' : 'hidden=true'}  id="login-div">
           username: <input name='username' id='username' type='text'/>
           password: <input name='password' id='password' type='password'/>
           <button id='login'>login</button>
        </div>
        <div style="margin-right:30px;" ${sessionScope.user == null ? 'hidden=true' : ''} id="userinfo">
           <a href="<%=basePath%>user/userInfo.action?userId=${sessionScope.user.userId}" id='mypage'>${sessionScope.user.userName}</a>
           <button id='logout'>logout</button>
        </div>
	</div>

    <div class="CSSTableGenerator" >
        <table >
            <tr>
                <td>bookTitle</td>
                <td>author</td>
                <td>isbn</td>
                <td>quantity</td>
                <td>price</td>
            </tr>
            <c:forEach var="book" items="${books}">
                <tr>
                    <input type="hidden" name='bookId' value=${book.bookId}/>
                    <td><a href="<%=basePath%>book/viewBook.action?bookId=${book.bookId}" target="_blank">${book.title}</a>
                    <td>${book.author}</td>
                    <td>${book.isbn}</td>
                    <td>${book.quantity}</td>
                    <td>${book.price}</td>
                </tr>
            </c:forEach>
        </table>
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

