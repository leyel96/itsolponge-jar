<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>


</head>
<body>
<header>
    <%@include file="../../tags/header.jsp"%>
</header>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/proStyle.css">
    <div style="max-width: 100%">
        <div class="py-5 text-center">
            <br><h2>상품 목록</h2>
        </div>
        <div class="row">
            <table>
                <tr>
                    <td width="1%"></td>
                    <td><div>
                        <form action = "/com.solponge/admin/product/search" accept-charset="utf-8" name = "book_info" method = "get">
                            <select name="SearchSelect" class="search-select">
                                <option value="all">전체</option>
                                <option value="product_title">도서명</option>
                                <option value="product_writer">저자</option>
                            </select>
                            <input type="text" name="SearchValue" size="15" value="검색 내용">
                            <button type="submit">검색</button>
                        </form>
                    </div></td>
                    <td width="55%"></td>
                <td>
                    <div>
                        <button class = "button" onclick="location.href='product/add'" type="button">상품 등록 </button>
                    </div>
                </td>
                <td width="10%"></td>
            </tr>
            </table>
        </div>
        <hr>
        <div>
            <table class="yogi">
                <thead>
                    <tr>
                        <th width="10%"></th>
                        <th>번호</th>
                        <th>이미지</th>
                        <th width = "20%">상품명</th>
                        <th width = "10%">작가</th>
                        <th>가격</th>
                        <th>페이지 수</th>
                        <th>재고</th>
                        <th>판매량</th>
                        <th>조회수</th>
                        <th></th>
                        <th></th>
                        <th width="10%"></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="product" items="${paginatedProducts}">
                        <tr>
                            <td width="10%"></td>
                            <td>${product.product_num}</td>
                            <td><img src=${product.product_img} style="width: 50px; alt="img"></td>
                            <td>${product.product_title}</td>
                            <td>${product.product_writer}</td>
                            <td>${product.product_price} 원</td>
                            <td>${product.product_page}</td>
                            <td>${product.product_stock}</td>
                            <td>${product.product_sale}</td>
                            <td>${product.product_visit}</td>
                            <td>
                               <div class="col">
                                 <c:url value="../admin/product/${product.product_num}" var="deUrl"/>
                                 <button class="w-100 btn btn-primary btn-lg" onclick="location.href='${deUrl}'" type="button">상세보기</button>
                                </div>
                            </td>
                            <td>
                               <div class="col">
                                <form id="delete" action="/com.solponge/admin/product/${product.product_num}/delete" method="post">
                                    <input type="hidden" name="_method" value="DELETE"/>
                                    <button onclick="return confirm('정말 삭제하시겠습니까?')" type="submit">삭제 </button>
                                </form>
                               </div>
                            </td>
                            <td width="10%"></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div> <!-- /container -->
    <nav id="paging" aria-label="Page navigation example" style="clear: both;">
                <ul class="pagination justify-content-center" style="padding: 0">
                    <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">
                        <a class="page-link" href="${url}page=${currentPage - 1}" tabindex="-1"
                           aria-disabled="${currentPage == 1}">Previous</a>
                    </li>
                    <c:choose>
                        <c:when test="${totalPages <= 10}">
                            <c:set var="startPage" value="1"/>
                            <c:set var="endPage" value="${totalPages}"/>
                        </c:when>
                        <c:when test="${currentPage <= 6}">
                            <c:set var="startPage" value="1"/>
                            <c:set var="endPage" value="10"/>
                        </c:when>
                        <c:when test="${currentPage > totalPages - 6}">
                            <c:set var="startPage" value="${totalPages - 9}"/>
                            <c:set var="endPage" value="${totalPages}"/>
                        </c:when>
                        <c:otherwise>
                            <c:set var="startPage" value="${currentPage - 5}"/>
                            <c:set var="endPage" value="${currentPage + 4}"/>
                        </c:otherwise>
                    </c:choose>
                    <c:forEach begin="${startPage}" end="${endPage}" var="pageNum">
                        <li class="page-item ${currentPage == pageNum ? 'active' : ''}">
                            <a class="page-link" href="${url}page=${pageNum}">${pageNum}</a>
                        </li>
                    </c:forEach>
                    <li class="page-item ${currentPage == totalPages ? 'disabled' : ''}" style="margin-right: 44px">
                        <a class="page-link" href="${url}page=${currentPage + 1}" aria-disabled="${currentPage == totalPages}">Next</a>
                    </li>
                </ul>
            </nav><br>
<footer>
    <%@include file="../../tags/footer.jsp" %>
</footer>
</body>
</html>
