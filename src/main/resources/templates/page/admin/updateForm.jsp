<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/proStyle.css?ver=2">

    <style>
        .container {
            max-width: 560px;
        }
    </style>
</head>
<body>
<header>
    <%@include file="../../tags/header.jsp"%>
</header>
<div class="container" style="max-width: 600px">
    <div class="py-5 text-center">
        <h2>회원 수정</h2>
    </div>
        <form class = "member-form" method="post" action="/com.solponge/admin/member/${member_No}/update">
          <div>
             <label class = "member-se" for="member_id">아이디</label>
              <p><input class = "member-label"  type="text" id="member_Id" name="member_Id" value="${member.MEMBER_ID}" readonly/></p>
          </div>
          <div>
             <label class = "member-se" for="member_pwd">비밀번호</label>
              <p><input class = "member-label" type="password" id="member_Pwd" name="member_Pwd" value="${member.MEMBER_PWD}"/></p>
          </div>
          <div>
             <label class = "member-se" for="member_name">이름</label>
              <p><input class = "member-label" type="text" id="member_Name" name="member_Name" value="${member.MEMBER_NAME}" readonly/></p>
          </div>
          <div>
             <label class = "member-se" for="member_address">주소</label>
              <p><input class = "member-label" type="text" id="member_Address" name="member_Address" value="${member.MEMBER_ADDRESS}" /></p>
          </div>
          <div>
             <label class = "member-se" for="member_phone">전화번호</label>
              <p><input class = "member-label" type="text" id="member_Phone" name="member_Phone" value="${member.MEMBER_PHONE}" /></p>
          </div>
          <div>
             <label class = "member-se" for="member_email">이메일</label>
              <p><input class = "member-label" type="email" id="member_Email" name="member_Email" value="${member.MEMBER_EMAIL}" /></p>
          </div>
            </table>
          <hr class="my-4">
          <div class="row">
              <div class = "padding-left1">
                    <button class = "button" type="submit">저장</button>
              </div>&nbsp
              <div>

                    <button class = "button" onclick="location.href='/com.solponge/admin/member'" type="button">목록</button>
              </div>&nbsp
              <div>
                    <button class = "button" onclick="location.href='/com.solponge/admin/member/${member.MEMBER_NO}/delete'" type="button">삭제</button>
              </div>
              <div></div>
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
