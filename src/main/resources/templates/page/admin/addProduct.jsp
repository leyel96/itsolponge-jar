<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/proStyle.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="//cdn.ckeditor.com/4.20.2/standard/ckeditor.js"></script>
        <script>
            function postData() {
                // alert("시도중")
                // Get the CKEditor instance
                var editor = CKEDITOR.instances['product_code'];
                var content = editor.getData();
                // console.log(typeof(content)); // Print the content to the console
                // alert(content)
                // Set the value of the hidden input field
                document.getElementById("myInput").value = content;
                // Submit the form
                document.getElementById("myForm").submit();
            }

        </script>
</head>
<body>
<header>
    <%@include file="../../tags/header.jsp"%>
</header>
<div class="add">
    <div>
        <h2 class="product-title">상품 등록</h2>
    </div>
    <form id="product-form" action="/com.solponge/admin/product/add" method="post">
        <div>
           <label for="product_title" class="input-label">책제목</label>
           <input type="text" id="product_title" name="product_title" placeholder="이름을 입력하세요">
        </div>
        <div>
           <label for="product_subtitle" class="input-label">부제목</label>
           <input type="text" id="product_subtitle" name="product_subtitle" placeholder="부제목을 입력하세요">
        </div>
        <div>
           <label for="product_writer" class="input-label">작가</label>
           <input type="text" id="product_writer" name="product_writer" placeholder="작가를 입력하세요">
        </div>
        <div>
           <label for="product_price" class="input-label">가격</label>
           <input type="text" id="product_price" name="product_price" placeholder="가격을 입력하세요">
        </div>
        <div>
            <label for="product_img" class="input-label">이미지</label>
            <input type="text" id="product_img" name="product_img" placeholder="이미지 URL을 입력하세요">
        </div>
        <div>
            <label for="product_date" class="input-label">출간일</label>
            <input type="text" id="product_date" name="product_date" placeholder="출간일을 입력하세요 (yyyy-MM-dd)">
        </div>
        <div>
            <label for="product_page" class="input-label">페이지 수</label>
            <input type="text" id="product_page" name="product_page" placeholder="페이지 수를 입력하세요">
        </div>
        <div>
            <label for="product_stock" class="input-label">재고</label>
            <input type="text" id="product_stock" name="product_stock" placeholder="재고를 입력하세요">
        </div>
        <div id="product_code">
             <p>${product.product_code}</p>
        </div>
            <input type="hidden" id="myInput" name="myInput">
               <script>
                  $(function () {
                   CKEDITOR.replace('product_code', {
                   filebrowserUploadUrl : '${pageContext.request.contextPath}/adm/fileupload.do'
                        });
                    });
               </script>

        <table>
            <tr>
                <th></th>
            </tr>
        </table>
        <div class="row">
            <div class="padding-left0">
                <button type="submit" onclick="postData()">상품등록</button>
            </div>&nbsp
            <div>
                <button onclick="location.href='/basic/items/product'" type="button">취소</button>
            </div>
        </div>
    </form>
    <table>
        <tr>
            <th></th>
        </tr>
    </table>
</div> <!-- /container -->
<footer>
    <%@include file="../../tags/footer.jsp" %>
</footer>
</body>
</html>
