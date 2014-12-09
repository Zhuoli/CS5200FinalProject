<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
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
        window.location.href='<%=basePath%>index?pageInfo.currentPageNo=' + pageNo;
    }
    function nextPage(){
        var pageNo = ${pageInfo.currentPageNo + 1};
        if(pageNo > ${pageInfo.totalPage}) {
            pageNo = ${pageInfo.totalPage};
        }
        window.location.href='<%=basePath%>index?pageInfo.currentPageNo=' + pageNo;
    }
    function jumpToPage() {
        var pageNo = $('#pageNo').val();
        if(pageNo <= 0 || isNaN(pageNo)) {
            pageNo = 1;
        } else if(pageNo > ${pageInfo.totalPage}) {
            pageNo = ${pageInfo.totalPage};
        }
        window.location.href='<%=basePath%>index?pageInfo.currentPageNo=' + pageNo;
    }
</script>

</head>
<body>
<header>
</header>
	<h1 class="h1">Buy Book In Queue - Welcome</h1>
	<jsp:include page="user-login.jsp"/>
    <jsp:include page="nav.jsp"/>
    <jsp:include page="show-books.jsp"/>

    <div style="margin-top: 20px; text-align: center">
        CurrentPage: ${pageInfo.currentPageNo}     &nbsp;&nbsp;  Total : ${pageInfo.totalPage}
    </div>
    <div style="margin-top: 15px; text-align: center">
        <a onclick="javascript:window.location.href='<%=basePath%>index?pageInfo.currentPageNo=1'" href="javascript:void(0)">firstPage</a>      &nbsp;
        <a onclick="previousPage()" href="javascript:void(0)">previousPage</a>   &nbsp;
        <a onclick="nextPage()" href="javascript:void(0)">nextPage</a>  &nbsp;
        <a onclick="javascript:window.location.href='<%=basePath%>index?pageInfo.currentPageNo=${pageInfo.totalPage}'" href="javascript:void(0)">lastPage</a>  &nbsp;&nbsp;
    </div>
    <div style="margin-top: 10px; text-align: center">
        <a onclick="jumpToPage()" href="javascript:void(0)">go</a>
        <input type='text' name="pageNo" id="pageNo" style="width:15px;"/>
    </div>

</body>
</html>
