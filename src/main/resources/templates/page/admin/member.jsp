<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/proStyle.css">
</head>
<body>
<header>
    <%@include file="../../tags/header.jsp"%>
</header>
<div class="container" style="max-width: 100%;padding-right: 0px;padding-left: 0px;">
    <div class="py-5 text-center">
        <h2>회원목록</h2>
    </div>
    <div class="row">
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<form method="get" action="/com.solponge/admin/member/search">
            <select name="SearchSelect" class="search-select">
                <option value="ALL">전체</option>
                <option value="MEMBER_NAME">이름</option>
                <option value="MEMBER_ID">아이디</option>
            </select>
            <input type="text" name="SearchValue">
            <button type="submit">검색</button>
        </form>
        </div>
    <hr class="my-4">
    <div>
        <table class="yogi">
            <thead>
            <tr>
               <th width="10%"></th>
               <th>번호</th>
               <th>아이디</th>
               <th>비밀번호</th>
               <th width="20%">주소</th>
               <th>이메일</th>
               <th>전화번호</th>
               <th>이름</th>
               <th>권한</th>
               <th>가입일</th>
               <th></th>
               <th width="10%"></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="member" items="${members}">
                <tr>
                    <td width="10%"></td>
                    <td>${member.MEMBER_NO}</td>
                    <td>${member.MEMBER_ID}</td>
                    <td>${member.MEMBER_PWD}</td>
                    <td>${member.MEMBER_ADDRESS}</td>
                    <td>${member.MEMBER_EMAIL}</td>
                    <td>${member.MEMBER_PHONE}</td>
                    <td>${member.MEMBER_NAME}</td>
                    <td>${member.MEMBER_GRADE}</td>
                    <td>${fn:substring(member.MEMBER_DATE, 0, 10)}</td>
                    <td>
                    <div class="col">
                        <button class="w-100 btn btn-primary btn-lg"
                                onclick="location.href='/com.solponge/admin/member/${member.MEMBER_NO}/update'" type="button">정보수정</button>
                    </div>
                    <td width="10%"></td>
                </tr>
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
