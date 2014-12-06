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

    $.validator.setDefaults({
        submitHandler: function() {
            alert("submitted!");
        }
    });

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

        $("#form").validate({

            rules: {
                title: {
                    required: true,
                    minlength: 2
                },
                author: {
                    required: true,
                    minlength: 2
                },
                isbn: {
                    required: true,
                    minlength: 2
                },
                quantity: {
                    required: true,
                    number: true
                },
                price: {
                    required: true,
                    number: true
                },
                publisher: {
                    required: true,
                    minlength: 2
                },
                pic: {
                    required: true,
                    minlength: 2
                }
            },

            submitHandler: function() {
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
            }
        });

    });
</script>
<style>
    input.error { border: 1px solid red; }
    label.error {
        padding-left: 16px;
        padding-bottom: 2px;
        font-weight: bold;
        color: #EA5200;
    }
</style>
</head>
<body>
<header>
</header>
	<h1>Buy Book In Queue - BBQ</h1>
<tr>
    <form id="form">
        <fieldset>
            <p>
                <label for="title">book title:</label>
                <input id="title" name="title" minlength="2" type="text" required/>
            </p>
            <p>
                <label for="author">book author:</label>
                <input type="text" name="author" id="author" required/>
            </p>
            <p>
                <label for="isbn">book isbn:</label>
                <input type="text" name="isbn" id="isbn" required/>
            </p>
            <p>
                <label for="quantity">book quantity:</label>
                <input type="text" name="quantity" id="quantity" required/>
            </p>
            <p>
                <label for="price">book price:</label>
                <input type="text" name="price" id="price" required/>
            </p>
            <p>
                <label for="publisher">book publisher:</label>
                <input type="text" name="publisher" id="publisher" required/>
            </p>
            <p>upload book pic：
                <input type="file" name="fileupload" id="fileupload" />
                <div id="fileQueue"></div>
                <ol class="files"></ol>
            </p>
            <input type="hidden" name="pic" id="pic" required/>
            <input type="submit" value="add book" />
        </fieldset>
    </form>
</tr>
</body>
</html>

