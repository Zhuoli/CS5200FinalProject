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
<title>By Book In Queue - add book</title>
<meta charset="utf-8">
<link href="<%=basePath%>css/uploadify.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>css/table.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath%>js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="<%=basePath%>uploadify/jquery.uploadify.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.validate.js"></script>
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
                        "book.bookId":$('#id').val(),
                        "book.title": $('#title').val(),
                        "book.author": $('#author').val(),
                        "book.isbn": $('#isbn').val(),
                        "book.quantity": $('#quantity').val(),
                        "book.price": $('#price').val(),
                        "book.publisher": $('#publisher').val(),
                        "book.pic": $('#pic').val(),
                        categoryId : $('#bookCategory').val()
                    },
                    function(data, status){
                        if(data['code'] == 'A00000') {
                            alert('success!');
                            location.href="<%=basePath%>book/viewBook.action?bookId=" + data['bookId'];
                        } else if(data['code'] == 'E00005') {
                            alert('Can not change the book!');
                        } else {
                            alert('Please login in first!');
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
	<h1>Buy Book In Queue -
        <s:if test="book.bookId != null">
            Update Book
        </s:if>
        <s:else>
            Add Book
        </s:else>
    </h1>
    <jsp:include page="../user-login.jsp"/>
<tr>
    <form id="form">
        <fieldset>
          <div class="add-book">
            <p>
                <label for="title">book title:</label>
                <input id="title" name="title" minlength="2" type="text" value="${book.title}" required/>
            </p>
            <p>
                <label for="author">book author:</label>
                <input type="text" name="author" id="author" value="${book.author}" required/>
            </p>
            <p>
                <label for="isbn">book isbn:</label>
                <input type="text" name="isbn" id="isbn" value="${book.isbn}" required/>
            </p>
            <p>
                <label for="quantity">book quantity:</label>
                <input type="text" name="quantity" id="quantity" value="${book.quantity}" required/>
            </p>
            <p>
                <label for="price">book price(cent):</label>
                <input type="text" name="price" id="price" value="${book.price}" required/>
            </p>
            <p>
                <label for="publisher">book publisher:</label>
                <input type="text" name="publisher" id="publisher" value="${book.publisher}" required/>
            </p>
             <p>
             	<label for="publisher">book category:</label>
            	<select name="bookCategory" id="bookCategory" size="1">
            		<c:forEach var="category" items="${categories}" varStatus="status">
            			<option <c:if test="${category.categoryId == book.category.categoryId}"> selected="selected" </c:if> value='${category.categoryId}' >${category.categoryName}</option>
            		</c:forEach>
            	</select>
            </p>
            <p>upload book pic：
                <input type="file" name="fileupload" id="fileupload" />
                <div id="fileQueue"></div>
                <ol class="files"></ol>
            </p>
           
            <input type="hidden" id="id" name="id" value="${book.bookId}"/>
            <input type="hidden" name="pic" id="pic" value="${book.pic}" required/>
            <input type="submit" value="add book" />
          </div>

        <s:if test="book.pic != null">
        <div class="update-pic">
          <img src="<%=basePath%>uploads/${book.pic}" width="300" height="200">
          <div style="text-align: center">Current Pic</div>
        </div>
        </s:if>

        </fieldset>
    </form>
</tr>
</body>
</html>

