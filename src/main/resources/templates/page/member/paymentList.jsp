<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="${pageContext.request.contextPath}/js/paymentList.js"></script>
    <style>

        /* Pagination container */
        #paging {
            margin-top: 20px;
            text-align: center;
        }

        /* Pagination links */
        .pagination {
            display: inline-block;
            margin-bottom: 0;
        }

        .pagination .page-item {
            display: inline;
        }

        .pagination .page-link {
            color: #007bff;
            background-color: #fff;
            border: 1px solid #dee2e6;
            padding: 6px 12px;
            margin: 0 4px;
        }

        .pagination .page-item.disabled .page-link {
            color: #6c757d;
            pointer-events: none;
            cursor: default;
        }

        .pagination .page-item.active .page-link {
            color: #fff;
            background-color: #007bff;
            border-color: #007bff;
        }

        .pagination {
            display: inline-block;
            margin: 0 auto;
        }

        .pagination li {
            display: inline;
            margin: 0 1px;
        }

        .pagination li.active a {
            color: #fff;
            background-color: #007bff;
            border-color: #007bff;
        }

        .pagination li a {
            color: #007bff;
            border: 1px solid #007bff;
            padding: 6px 12px;
        }

        .pagination li a:hover {
            background-color: #007bff;
            color: #fff;
            border-color: #007bff;
        }

        .pagination li.disabled a {
            color: #6c757d;
            cursor: not-allowed;
            background-color: #fff;
            border-color: #6c757d;
        }

        #paymentlist {
            width: 1300px;
            margin-left: auto;
            margin-right: auto;
            margin-top: 1%;
            margin-bottom: 10%;
        }
        img{
            width: 100px;
        }
        .tittxt, .pritxt{
            width: 500px;
        }
        .del{
            width: 150px;
        }
        #sidebar{
            display:inline-block;
            float:left;
            width:150px;
            margin-top: 20px;
            margin-right: 10px;
            padding: 10px;
            text-align: center;
        }
        #productinfo{
            display:inline-block;
            margin-top: 20px;
            border: 1px solid black;
            padding: 10px;
            width: 1050px;
            left: 52px;
            position:relative;
        }
        .suctxt{
            padding-left: 20px;
            height: 20px;
        }
        .pritxt, .tittxt{
            padding-left: 20px;
        }
        .remove, .del, .propage{
            text-align: center;
        }
        .visi{
            cursor: pointer;
        }
        .del, .propage{
            background-color: darkgrey;
            color: white;
            cursor: pointer;
        }
        .tdbody{
            margin-top: 10px;
        }
        table {
            border-collapse: collapse;
            width: 100%;
            margin-bottom: 10px;
        }

        th,
        td {
            padding: 8px;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #f2f2f2;
        }
        .iiimm{
            width: 150px;
            text-align: center;
        }

    </style>
    <script>

    </script>

</head>
<body>

<header>
    <%@include file="../../tags/header.jsp"%>
</header>

<div id="paymentlist">
    <div id="sidebar">
        <table>
            <tr>
                <td><a class="link" href="/com.solponge/member/${member.MEMBER_NO}/myPage">정보수정</a></td>
            </tr>
            <tr>
                <td class="link" style="background-color: #007bff;"><a href="/com.solponge/member/${member.MEMBER_NO}/paymentList" style="color:white;">구매목록</a></td>
            </tr>
            <tr>
                <td class="link"><a href="/com.solponge/member/${member.MEMBER_NO}/myPage/jobScrap">스크랩</a></td>
            </tr>
        </table>
    </div>
    <div id="productinfo">

        <%--        <c:forEach var="productNew" items="${paginatedProducts}">--%>
        <c:forEach items="${paginatedProducts}" var="i">
            <c:set var="title" value="title${i.product_num}"/>
            <c:set var="stock" value="stock${i.product_num}"/>
            <c:set var="price" value="price${i.product_num}"/>
            <c:set var="img" value="img${i.product_num}"/>
            <c:set var="date" value="date${i.product_num}"/>
            <c:set var="paynum" value="${i.payment_num}"/>
            <table class="tdbody">
                <tr>
                    <td colspan="2" class="suctxt">결제완료</td>
                    <td class="remove"><span class="visi" onclick="location.href='/com.solponge/member/${member_No}/paymentList/delete?payment_num=${i.payment_num}&product_num=${i.product_num}'">내역삭제</span></td>
                </tr>
                <tr>
                    <td rowspan="2" class="iiimm"><img class="proimg" src="${productinfo.get(img)}"></td>
                    <td class="tittxt">${productinfo.get(title)}</td>
                    <td onclick="window.open('https://tracker.delivery/#/kr.cjlogistics/${i.delivery_num}','배송조회','width=430, height=600')" class="del">배송조회</td>
                </tr>
                <tr>
                    <td class="pritxt">${productinfo.get(price)}원</td>
                    <td class="propage" onclick="location.href = '/com.solponge/product/${i.product_num}'">상품페이지</td>
                </tr>
            </table>
        </c:forEach>
        <%--        </c:forEach>--%>
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
        </nav>
    </div>
</div>
<footer>
    <%@include file="../../tags/footer.jsp" %>
</footer>

</body>
</html>