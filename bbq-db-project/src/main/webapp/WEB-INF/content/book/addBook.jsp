<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>

<html>
<head>
<title>By Book In Queue - add book</title>
<meta charset="utf-8">
<link href="<%=basePath%>css/uploadify.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath%>js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.validate.js"></script>
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
                  $("#pic").val(data);
            }
        });

        $(':button').click(function() {

            $("#form").validate({
                rules: {
                    title: "required",
                    author: "required",
                    isbn: "require",
                    quantity: "required",
                    price: "required",
                    publisher: "required",
                    pic:"required"
                },
                messages: {
                    pic: "Please upload your book pic"
                }
            });

            $.post("<%=basePath%>book/addBook.action",

                    {
                        "book.title": $('#title').val(),
                        "book.author": $('#author').val(),
                        "book.isbn": $('#isbn').val(),
                        "book.quantity": $('#quantity').val(),
                        "book.price": $('#price').val(),
                        "book.publisher": $('#publisher').val(),
                        "book.pic": $('#pic').val()
                    },
                    function(data, status){
                        if(data['code'] == 'A00000') {
                            location.href="<%=basePath%>/index.action";
                        } else {
                            alert('please login in first!');
                        }
                    });
        });
    });
</script>
</head>
<body>
<header>
</header>
	<h1>Buy Book In Queue - BBQ</h1>
<tr>
    <form id="form" action="book/addBook.action">
        <fieldset>
            <p>
                <label for="title">book title:</label>
                <input type="text" name="title" id="title" class="input required"/>
            </p>
            <p>
                <label for="author">book author:</label>
                <input type="text" name="author" id="author" class="input required"/>
            </p>
            <p>
                <label for="isbn">book isbn:</label>
                <input type="text" name="isbn" id="isbn" required/>
            </p>
            <p>
                <label for="quantity">book quantity:</label>
                <input type="text" name="quantity" id="quantity" class="input required"/>
            </p>
            <p>
                <label for="price">book price:</label>
                <input type="text" name="price" id="price" class="input required"/>
            </p>
            <p>
                <label for="publisher">book publisher:</label>
                <input type="text" name="publisher" id="publisher" class="input required"/>
            </p>
            <p>upload book pic：
                <input type="file" name="fileupload" id="fileupload" />
                <div id="fileQueue"></div>
                <ol class="files"></ol>
            </p>
            <input type="hidden" name="pic" id="pic" class="input required"/>
            <input type="button" value="add book" />
        </fieldset>
    </form>
</tr>
</body>
</html>

