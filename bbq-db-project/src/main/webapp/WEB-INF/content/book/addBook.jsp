<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>

<html>
<head>
<title>By Book In Queue - BBQ</title>
<meta charset="utf-8">
<link href="<%=basePath%>css/uploadify.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath%>js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="<%=basePath%>uploadify/jquery.uploadify.min.js"></script>
<script type="text/javascript">
    $(document).ready(function() {

        $("#fileupload").uploadify({
            'swf'     : '<%=basePath%>uploadify/uploadify.swf',
            'uploader': '<%=basePath%>book/uploadImg.action',
            'folder': '<%=basePath%>uploads',
            'cancelImg': '<%=basePath%>img/uploadify-cancel.png',
            'queueID'        : 'fileQueue',
            'fileObjName'    : 'uploadify',
            'auto'           : true,
            'multi'          : false,
            'buttonText'     : 'Browse',
            'sizeLimit'      : 19871202,
            'queueSizeLimit' : 1,
            'fileDesc'       : 'support file type:jpg/gif/jpeg/png/bmp.',
            'fileExt'        : '*.jpg;*.gif;*.jpeg;*.png;*.bmp',
            'onUploadSuccess':function(file, data, response){
                  alert(data);
            }
        });
    });
</script>
</head>
<body>
<header>
</header>
	<h1>Buy Book In Queue - BBQ</h1>
<tr>
    <form id="form">

        <div>
             book title: <input type="text" name="book.title" id="title"/>
        </div>
        <div>
            book title: <input type="text" name="book.title" id="title"/>
        </div>
        <div>upload book pic：
            <input type="file" name="fileupload" id="fileupload" />
            <div id="fileQueue"></div>
            <ol class="files"></ol>
        </div>
    </form>
</tr>
</body>
</html>

