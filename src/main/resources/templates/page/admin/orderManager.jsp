<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="com.solponge.domain.admin.AdminOrderVo" %>
<%@ page import="java.util.List" %>


<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
    <%-- <script>
         function submitForm(button) {
             const form = document.querySelector('#contact-form');
             form.action = "/admin/order/${order.payment_num}/update";
             form.method = "post";
             form.submit();
         }
     </script>--%>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/proStyle.css">

    <script>
        function sendPostRequest(btnId) {
            const url = "https://example.com/api/endpoint";
            const data = { key1: "value1", key2: "value2" }; // POST 요청에 포함할 데이터

            fetch(url, {
                method: "POST",
                body: JSON.stringify(data),
                headers: {
                    "Content-Type": "application/json"
                }
            })
                .then(response => {
                    if (!response.ok) {
                        throw new Error("Network response was not ok");
                    }
                    return response.json();
                })
                .then(data => {
                    console.log(data); // POST 요청이 성공하면 받은 응답 데이터 출력
                })
                .catch(error => {
                    console.error("There was a problem with the fetch operation:", error);
                });
        }
    </script>
</head>
<body>
<header>
    <%@include file="../../tags/header.jsp"%>
</header>
<div class="container" style="max-width: 100%; padding-right: 0px; padding-left: 0px;">
    <div class="py-5 text-center">
        <h2>주문목록</h2>
    </div>
    <div class="row">
        &nbsp;&nbsp;&nbsp;
        <div class="col">
            <form method="get" action="/com.solponge/admin/order/search">
                <select name="SearchSelect" class="search-select">
                    <option value="payment_num">주문번호</option>
                    <option value="MEMBER_ID">아이디</option>
                </select>
                <input type="text" name="SearchValue">
                <button type="submit">검색</button>
            </form>
        </div>
    </div>
    <hr class="my-4">
    <div>
        <table class="yogi">
            <thead>
            <tr>
                <th width="10%">주문번호</th>
                <th width="7%">아이디</th>
                <th width="10%">상품명</th>
                <th width="4%">수량</th>
                <th width="10%">주문일자</th>
                <th width="10%">주문자번호</th>
                <th width="13%">주소</th>
                <th width="10%">배송메세지</th>
                <th width="10%">운송장번호</th>
                <th width="7%">상태</th>
                <th></th>
            </tr>
            </thead>

            <c:forEach var="order" items="${paymentEntities}">
                <c:if test="${order.success == 1}">
                <form method="post" action="/com.solponge/admin/order/${String.valueOf(order.payment.paymentNum)}/update">

                        <tr>
                            <td>${order.payment.paymentNum}</td>
                            <td>${order.member.MEMBER_ID}</td>
                            <td>${order.product.product_title}</td>
                            <td>${order.payment.paymentStockNumber}</td>
                            <td>${order.payment.paymentDate}</td>
                            <td>${order.payment.paymentPhone}</td>
                            <td>${order.payment.paymentAddress}</td>
                            <td>${order.delivery.deliveryInfo}</td>
                            <td>
                                <input type="hidden" name="payment_num" value="${order.payment.paymentNum}">
                                <input type="text" id="deliveryNum" name="deliveryNum" value="${order.delivery.deliveryNum}">
                            </td>
                            <td>
                                <p id="demo">
                                    <c:choose>
                                        <c:when test="${order.success==1}">결제완료</c:when>
                                        <c:when test="${order.success==2}">발송완료</c:when>
                                        <c:otherwise>알수없음</c:otherwise>
                                    </c:choose>
                                </p></td>
                            <td>
                                <div><button type="submit">발송</button></div>
                            </td>
                        </tr>
                </form>
                </c:if>
            </c:forEach>
        </table>
        <br>
    </div>
    <div>
        <table class="yogi">
            <thead>
            <tr>
                <th width="10%">주문번호</th>
                <th width="7%">아이디</th>
                <th width="10%">상품명</th>
                <th width="4%">수량</th>
                <th width="10%">주문일자</th>
                <th width="10%">주문자번호</th>
                <th width="13%">주소</th>
                <th width="10%">배송메세지</th>
                <th width="10%">운송장번호</th>
                <th width="7%">상태</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="order" items="${paymentEntities}">
                <c:if test="${order.success == 2}">
                    <tr>
                        <td>${order.payment.paymentNum}</td>
                        <td>${order.member.MEMBER_ID}</td>
                        <td>${order.product.product_title}</td>
                        <td>${order.payment.paymentStockNumber}</td>
                        <td>${order.payment.paymentDate}</td>
                        <td>${order.payment.paymentPhone}</td>
                        <td>${order.payment.paymentAddress}</td>
                        <td>${order.delivery.deliveryInfo}</td>
                        <td>${order.delivery.deliveryNum}</td>
                        <td>
                            <p id="Completion">
                                <c:choose>
                                    <c:when test="${order.success==1}">결제완료</c:when>
                                    <c:when test="${order.success==2}">발송완료</c:when>
                                    <c:otherwise>알수없음</c:otherwise>
                                </c:choose>
                            </p></td>
                        <td></td>
                    </tr>
                </c:if>
            </c:forEach>
            </tbody>
        </table>
        <br>
    </div>
</div> <!-- /container -->
<footer>
    <%@include file="../../tags/footer.jsp" %>
</footer>
</body>
</html>