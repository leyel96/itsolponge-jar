<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/css/success.css">
    <link rel="stylesheet" href="/css/hdcss.css">
</head>
<body>
<header>
    <%@include file="../../tags/header.jsp"%>
</header>
<div id="block">

    <h2>결제 성공!</h2>
    <table>
        <tr>
            <td onclick="location.href='/com.solponge/main'" id="left">메인으로</td>
            <td onclick="location.href='/com.solponge/member/${member_No}/paymentList'">주문내역</td>
        </tr>

    </table>
</div>
<footer>
    <%@include file="../../tags/footer.jsp" %>
</footer>

</body>
</html>
