<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>

<script type="text/javascript" charset="utf-8">
    $(document).ready(function() {

    });

    function onRegister() {

        var lastPage = window.location.href;
        window.location.href='<%=basePath%>user/preRegister?lastPage='+lastPage;
    }
</script>

<div>
    <div class="nav-index">
        <a href="<%=basePath%>index">Index</a>                   &nbsp;
    <c:if test="${sessionScope.user.userRole.roleId == 2}">
        <a href="<%=basePath%>user/showAllUsers">All Users</a>   &nbsp;
        <a href="<%=basePath%>user/userLogs">Action Logs</a>

    </c:if>
    </div>
</div>   <br/>



