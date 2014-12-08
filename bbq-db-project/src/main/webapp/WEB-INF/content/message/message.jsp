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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="utf-8">
<style>
body{
    background-color: #d0e4fe;
 }
 </style>
<title>Message: Send message</title>
<script type="text/javascript">
    $(document).ready(function() {
        $("#comment_form").validate({
            rules: {
            	receiver: {
                    required: true,
                    minlength: 2
                },
                title: {
                    required: true,
                    minlength: 2
                },
                content: {
                    required: true,
                    minlength: 2,
                }
            },

            submitHandler: function() {
                $.post("<%=basePath%>user/addUser.action",
                    {
                        "msg.receiver": $('#receiver').val(),
                        "msg.title": $('#title').val(),
                        "msg.content": $('#content').val(),
                        "msg.time": new Date().toString();
                    },
                    function(data, status){
                        if(data['code'] == 'A00000') {
                            alert('register success!');
                            var jumpPage = <%=request.getParameter("lastPage")%>
                            if(jumpPage == null) {
                                jumpPage = '<%=basePath%>index.action';
                            }
                            window.location.href=jumpPage;
                        } else {
                            alert('username has already exist!');
                        }
                    });
            }
        });

    });
</script>
</head>
<body>
	<h3>Send messages</h3>

    <jsp:include page="../user-login.jsp"/>

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
		<button id='send'>send</button>  &nbsp;
	</div>
	
</div>



</body>
</html>