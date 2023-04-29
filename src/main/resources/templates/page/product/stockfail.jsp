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


  <h2>상품 재고가 부족합니다. 죄송합니다.</h2>
  <table>
    <tr>
      <td onclick="location.href='/com.solponge/main'"  id="left">메인으로</td>
    </tr>

  </table>


</div>


<footer>
  <%@include file="../../tags/footer.jsp" %>
</footer>
</body>
</html>
