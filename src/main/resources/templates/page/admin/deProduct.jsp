<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>상품 상세 정보</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/proStyle.css?ver=2">
</head>
<body>
<header>
    <%@include file="../../tags/header.jsp"%>
</header>
    <div style="max-width: 100%">
        <div>
            <h2 style = 'padding-left: 20%';>상품 상세 정보</h2>
        </div>
        <hr>
        <div class="row">
                    <div class="padding-left">
                        <button class = "button" onclick="location.href='/com.solponge/admin/product'" type="button">상품 목록 </button>
                    </div>&nbsp
                    <div>
                        <button class = "button" onclick="location.href='${product.product_num}/edit'" type="button">상품 수정 </button>
                    </div>&nbsp
                    <div>
                        <form id="delete" action="/com.solponge/admin/product/${product.product_num}/delete" method="post">
                            <input type="hidden" name="_method" value="DELETE"/>
                            <button class = "button"  onclick="return confirm('정말 삭제하시겠습니까?')" type="submit">상품 삭제 </button>
                        </form>
                    </div>
                </div>
            </div> <!-- /container -->
        <div>
            <table>
                <tr>
                    <th width="15%"></th>
                    <th width="10%">번호</th>
                    <td width="40%">${product.product_num}</td>
                    <th width="10%"></th>
                </tr>
                <tr>
                    <th width="15%"></th>
                    <th>상품명</th>
                    <td width="40%">${product.product_title}</td>
                    <th width="10%"></th>
                </tr>
                <tr>
                    <th width="15%"></th>
                    <th>부제목</th>
                    <td width="40%">${product.product_subtitle}</td>
                    <th width="10%"></th>
                </tr>
                <tr>
                    <th width="15%"></th>
                    <th>작가</th>
                    <td width="40%">${product.product_writer}</td>
                    <th width="10%"></th>
                </tr>
                <tr>
                    <th width="15%"></th>
                    <th>가격</th>
                    <td width="40%">${product.product_price}</td>
                    <th width="10%"></th>
                </tr>
                <tr>
                    <th width="15%"></th>
                    <th>이미지</th>
                    <td width="40%"><img src=${product.product_img} alt="img"></td>
                    <th width="10%"></th>
                </tr>
                <tr>
                    <th width="15%"></th>
                    <th>출간일</th>
                    <td width="40%">${product.product_date}</td>
                    <th width="10%"></th>
                </tr>
                <tr>
                    <th width="15%"></th>
                    <th>페이지 수</th>
                    <td width="40%">${product.product_page}</td>
                    <th width="10%"></th>
                </tr>
                <tr>
                    <th width="15%"></th>
                    <th>재고</th>
                    <td width="40%">${product.product_stock}</td>
                    <th width="10%"></th>
                </tr>
                <tr>
                    <th width="15%"></th>
                    <th>판매량</th>
                    <td width="40%">${product.product_sale}</td>
                    <th width="10%"></th>
                </tr>
                <tr>
                    <th width="15%"></th>
                    <th>방문 수</th>
                    <td width="40%">${product.product_visit}</td>
                    <th width="10%"></th>
                </tr>
                 <tr>
                     <th width="15%"></th>
                    <th>내용</th>
                    <td width = "40%">${product.product_code}</td>
                     <th width="10%"></th>
                 </tr>
            </table>
        </div>
<footer>
    <%@include file="../../tags/footer.jsp" %>
</footer>
</body>
</html>