<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="h" tagdir="/WEB-INF/tags"%>
<html>
<head>
    <title>장바구니</title>
    <style>
        /* 장바구니 스타일 */
        .cart {
            max-width: 1000px;
            margin: 0 auto;
            padding: 20px;
        }

        .cart__item {
            display: flex;
            align-items: center;
            margin-bottom: 20px;
            padding: 10px;
            border: 1px solid #ccc;
        }

        .cart__item-img {
            width: 100px;
            height: 100px;
            margin-right: 20px;
        }

        .cart__item-info {
            flex-grow: 1;
        }

        .cart__item-title {
            font-weight: bold;
            font-size: 1.2rem;
        }

        .cart__item-price {
            margin-top: 10px;
        }

        .cart__item-quantity {
            margin-top: 10px;
        }

        .cart__item-total-price {
            margin-top: 10px;

        }


        .cart__item-remove {
            margin-left: 20px;
            cursor: pointer;
        }


        .deleteButton{
            border: none;
            background-color: snow;
        }
        .cart-total-price {
            width: 500px;
            padding: 10px;
            margin: 30px 0px 30px 0px;
            font-weight: normal;
            font-size: larger;
        }

        .cart-total-price div {
            display: flex;
            justify-content: space-between;
            margin-bottom: 5px;
        }


    </style>
</head>
<body>
<header>
    <%@include file="../../tags/header.jsp"%>
</header>

<%-- 장바구니가 비어있는 경우 메시지 출력 --%>
<c:if test="${empty cart.cartItems}">
    <div class="cart" style="min-height: 500px">
        <h1>${loginMember.MEMBER_NAME}님의 장바구니</h1>
        <p style="text-align: center; font-size: 1.5rem; font-weight: bold;">장바구니가 비어있습니다</p>
        <div style="
  display: flex;
  justify-content: center;
  align-items: center;
  height: 200px;
">
            <button onclick="location.href='/com.solponge/productList'"
                    style="
      width: 500px;
      padding: 10px;
      background-color: rgb(102,153,255);
      color: #fff;
      border: none;
      font-size: 16px;
      font-weight: bold;
      cursor: pointer;
      border-radius: 10px;
    ">
                상품 보러 가기
            </button>
        </div>
    </div>
</c:if>
<%-- 장바구니 아이템이 있는 경우 --%>
<c:if test="${not empty cart.cartItems}">
<div class="cart">
    <h1>${loginMember.MEMBER_NAME}님의 장바구니</h1>
    <hr>
    <form method="post" action="/com.solponge/member/${loginMember.MEMBER_NO}/myPage/cart">
        <c:forEach items="${cart.cartItems}" var="entry">
            <c:set var="product" value="${entry.value.product}" />
            <div class="cart__item" >
                <img class="cart__item-img" src="${product.product_img}" alt="${cartItem.product.product_title}" onclick="location.href='/com.solponge/product/${product.product_num}'">
                <div class="cart__item-info">
                    <div class="cart__item-num">${entry.value.CART_ITEM_NUM}</div>
                    <div class="cart__item-title" onclick="location.href='/com.solponge/product/${product.product_num}'">${product.product_title}</div>
                    <div class="cart__item-price">가격: ${product.product_price}원</div>
                    <div class="cart__item-total-price">소계: ${entry.value.totalPrice}원</div>
                    <div class="cart__item-quantity">상태: <input type="text" value="${entry.value.CART_ITEM_STOCK}" style="width: 20px;"></div>
                    <input type="hidden" name="cartItems" value="${product.product_num},${entry.value.CART_ITEM_STOCK},${loginMember.MEMBER_NO},${entry.value.CART_ITEM_NUM}">
                </div>
                <div class="cart_Item_check">
                    <input type="checkbox" checked name="order" value="${entry.key}"><img src="${pageContext.request.contextPath}/img/basket.png" alt="cart.ico" style="width: 25px;">
                </div>
                <div class="cart__item-remove">
                    <button type="submit" class="deleteButton" formaction="/com.solponge/member/${loginMember.MEMBER_NO}/myPage/cart/deleteCartItem" name="cartItemNum" value="${entry.value.CART_ITEM_NUM}">
                        <img src="${pageContext.request.contextPath}/img/delete.png" alt="delete.ico" style="width: 25px;"/>
                    </button>
                </div>
            </div>
        </c:forEach>
        <br>
        <div class="cart-total-price">
            <div><span>총 상품 가격 :</span> <span>${cart.totalPrice} 원</span></div>
            <div><span>총 배송비 :
            </span> <span>
               <c:choose>
                   <c:when test="${cart.totalPrice >= 50000}">
                       0 원
                   </c:when>
                   <c:otherwise>
                       +2,500 원
                   </c:otherwise>
               </c:choose>
            </span></div>
            <hr>
            <div><span>총 결제 금액 :</span> <span>${cart.totalPrice+2500} 원</span></div>
        </div>
        <br>


        <button type="submit" formaction="/com.solponge/member/${loginMember.MEMBER_NO}/payments">주문하기</button>

    </form>



</div>
</c:if>

<footer>
    <%@include file="../../tags/footer.jsp" %>
</footer>
</body>
</html>
