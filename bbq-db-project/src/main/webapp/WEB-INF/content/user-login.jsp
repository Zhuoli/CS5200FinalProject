<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>

<script type="text/javascript" charset="utf-8">
    $(document).ready(function() {

        $('#login').click(function() {

            $.post("<%=basePath%>user/login",
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

            $.get("<%=basePath%>user/loginOut",
                    function(data, status){
                        if(data['code'] == 'A00000') {
                            window.location.href='<%=basePath%>index';
                        } else {
                            alert('logout failed, please retry!');
                        }
                    });
        });
    });

    function onRegister() {

        var lastPage = window.location.href;
        window.location.href='<%=basePath%>user/preRegister?lastPage='+lastPage;
    }
</script>

<div class='login-user'>
    <div ${sessionScope.user == null ? '' : 'hidden=true'}  id="login-div">
       username: <input name='username' id='username' type='text'/>
       password: <input name='password' id='password' type='password'/>
       <button id='login'>login</button>  &nbsp;
       <a onclick="onRegister()" href="javascript:void(0)">register</a>
    </div>
    <div ${sessionScope.user == null ? 'hidden=true' : ''} id="userinfo" >
       <a href="<%=basePath%>user/userInfo?userId=${sessionScope.user.userId}" id='mypage'>${sessionScope.user.userName}</a>
       <button id='logout'>logout</button>
    </div>
</div>   <br/>



