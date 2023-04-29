
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="h" tagdir="/WEB-INF/tags"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<header>
    <%@include file="../../tags/header.jsp"%>
</header>
${member.MEMBER_NAME}의 마이페이지
<div class="sidebar">
    <ul>
        <li><a href="/com.solponge/member/${member.MEMBER_NO}/myPage/update" class="btn btn-primary btn-sm">회원정보 수정</a></li>
        <li><a href="/com.solponge/member/${member.MEMBER_NO}/myPage/order">주문 관리</a></li>
        <li><a href="/com.solponge/member/${member.MEMBER_NO}/myPage/cart">장바구니</a></li>
    </ul>
</div>

</body>
</html>
