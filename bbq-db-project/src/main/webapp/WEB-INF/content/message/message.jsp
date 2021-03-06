<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<base href="<%=basePath%>">
<link href="<%=basePath%>css/view.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>css/table.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath%>js/jquery-2.1.1.min.js"></script>
<meta charset="utf-8">
<style>
body{
    background-color: #d0e4fe;
 }
 </style>
<title>Message: Send message</title>
</head>
<body>
	<h3 class="h1">Send messages</h3>
    <jsp:include page="../user-login.jsp"/>
    <jsp:include page="../nav.jsp"/>
    <div id="comment_form">
	
	<div>
		<input type="text" name="receiver" id="receiver" value="" placeholder="receiver">
	</div>
	<div>
		<input type="text" name="title" id="title" value="" placeholder=title>
	</div>
	<div>
		<textarea rows="10" name="content" id="content" placeholder="content"></textarea>
	</div>
	<div>
		<!-- <input type="submit" name="submit" value="Add Comment"> -->
		<button onclick="myFunction()">Click me</button>  &nbsp;
	</div>
	
</div>


<script>
function myFunction() {
    $.post("<%=basePath%>message/send",
            {
                "receiver": $('#receiver').val(),
                "title": $('#title').val(),
                "content": $('#content').val()
            },
            function(data, status){
                 if(data['code'] == 'A00000') {
                    alert('register success!');
                    var jumpPage = <%=request.getParameter("lastPage")%>
                    if(jumpPage == null) {
                        jumpPage = '<%=basePath%>index';
                    }
                    window.location.href=jumpPage;
                } else {
                    alert("send failed" );
                }
            });
		
	}
</script>


</body>
</html>