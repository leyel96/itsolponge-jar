<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sign Up</title>
    <link rel="stylesheet" href="/css/hdcss.css">
    <link rel="stylesheet" href="/css/mypageForm.css">
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script src="${pageContext.request.contextPath}/js/login.js"></script>
    <style>
        #sidebar td{
            text-align: center;
        }
        #sidebar td a{
            text-align: center;
        }
    </style>
</head>
<body data-spy="scroll" data-target=".navbar" data-offset="40" id="home">
<header>
    <%@include file="../../tags/header.jsp"%>
</header>

<div id="mypagebody">
    <div id="sidebar">
        <table>
            <tr>
                <td style="background-color: #007bff;"><a class="link" href="/com.solponge/member/${member.MEMBER_NO}/myPage" style="color:white;">정보수정</a></td>
            </tr>
            <tr>
                <td><a href="/com.solponge/member/${member.MEMBER_NO}/paymentList">구매목록</a></td>
            </tr>
            <tr>
                <td><a href="/com.solponge/member/${member.MEMBER_NO}/myPage/jobScrap">스크랩</a></td>
            </tr>
        </table>

    </div>
    <form method="post" action="/com.solponge/member/${member.MEMBER_NO}/myPage" name="frm">
        <input type="hidden" name="MEMBER_NO" id="MEMBER_NO" value="${member.MEMBER_NO}">
        <div id="memberinfo">
            <table>
                <tr>
                    <th>아이디</th>
                    <td><input type="text" id="member_id" name="member_id" value="${member.MEMBER_ID}" disabled></td>
                </tr>
                <tr>
                    <th>비밀번호</th>
                    <td><input type="password" id="member_pwd" name="member_pwd" value="${member.MEMBER_PWD}"></td>
                </tr>
                <tr>
                    <th>비밀번호 확인</th>
                    <td><input type="password" id="member_pwdCheck" name="member_pwdCheck" value="${member.MEMBER_PWD}"></td>
                </tr>
                <tr>
                    <th>이름</th>
                    <td><input type="text" name="MEMBER_NAME" id="MEMBER_NAME" value="${member.MEMBER_NAME}" disabled></td>
                </tr>
                <tr>
                    <th>이메일</th>
                    <td><input type="text" value="${fn:split(member.MEMBER_EMAIL, "@")[0]}" size="16" id="email1" name="email1"> @
                        <input type="text" size="20" id="email2" value="${fn:split(member.MEMBER_EMAIL, "@")[1]}" name="email2">
                        <select name="emails" id="emails" size="1" onchange="mailCheck()">
                            <option value="${fn:split(member.MEMBER_EMAIL, "@")[1]}" selected>선택하세요</option>
                            <option value="naver.com">naver.com</option>
                            <option value="hanmail.net">hanmail.net</option>
                            <option value="gmail.com">gmail.com</option>
                            <option value="nate.com">nate.com</option>
                            <option value="1">직접입력</option>
                        </select></td>
                </tr>
                <tr>
                    <th>휴대폰 번호</th>
                    <td><select name="firstnum" size="1" id="firstnum">
                        <option value="010">010</option>
                        <option value="011">011</option>
                        <option value="013">013</option>
                        <option value="017">017</option>
                        <option value="018">018</option>
                    </select>-&nbsp;<input type="text" size="4" maxlength="4" minlength="4" name="secnum" id="secnum" value="${fn:split(member.MEMBER_PHONE, "-")[1]}">-
                        <input type="text" size="4" maxlength="4" minlength="4" name="thrnum" id="thrnum" value="${fn:split(member.MEMBER_PHONE, "-")[2]}"></td>
                </tr>
                <tr>
                    <th>주소</th>
                    <td>
                        <input type="text" id="sample6_postcode" placeholder="우편번호" readonly value="${fn:split(member.MEMBER_ADDRESS, "/")[0]}" name="sample6_postcode">
                        <input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
                        <input type="text" id="sample6_address" placeholder="주소" readonly value="${fn:split(member.MEMBER_ADDRESS, "/")[1]}" name="sample6_address">
                        <input type="text" id="sample6_detailAddress" placeholder="상세주소" value="${fn:split(member.MEMBER_ADDRESS, "/")[2]}" name="sample6_detailAddress">
                        <!--<input type="text" id="sample6_extraAddress" placeholder="참고항목">-->
                    </td>
                </tr>
                <tr>
                    <td colspan="2"  id="buttons">
                        <%--                        <input type="submit" value="변경" id="submitbtn" onclick="infocheck()">--%>
                        <input type="submit" value="변경" id="submitbtn">
                        <input type="button" onclick="location.href='/com.solponge/main'" value="돌아가기">
                        <input type="button" onclick="location.href='/com.solponge/member/${member.MEMBER_NO}/myPage/delete'" value="회원 탈퇴"> </td>
                </tr>
            </table>
            <script>
                document.getElementById('submitbtn').onclick = function(){
                    var emailExp = /^[a-z0-9]{1}[a-z0-9\-]+[a-z0-9]{1}\.(([a-z]{1}[a-z.]+[a-z]{1}[a-z]+)|([a-z]+))$/;
                    var phonExp = /^[0-9]{4}$/;
                    if(document.frm.MEMBER_NAME.value === "") {
                        alert("MEMBER_NAME 입력해주세요.");
                        return false;
                    }
                    if (document.frm.member_pwd.value === ""){
                        alert("비밀번호를 입력해주세요.");
                        document.frm.member_pwd.focus();
                        return false;
                    }
                    if(document.frm.member_pwd.value !== document.frm.member_pwdCheck.value){
                        alert("비밀번호가 틀렸습니다.");
                        document.frm.member_pwd.focus();
                        return false;
                    }
                    if (document.frm.MEMBER_NAME.value === ""){
                        alert("이름을 입력해주세요.");
                        document.frm.MEMBER_NAME.focus();
                        return false;
                    }
                    if (document.frm.email1.value === "" || document.frm.email2.value === ""){
                        alert("이메일을 입력해주세요.");
                        document.frm.email1.focus();
                        return false;
                    }
                    if (emailExp.test(document.frm.email2.value) === false){
                        alert("정확한 이메일 주소를 입력해주세요");
                        document.frm.email1.focus();
                        return false;
                    }
                    if (document.frm.secnum.value === "" || document.frm.thrnum.value === ""){
                        alert("전화번호를 입력해주세요.");
                        document.frm.secnum.focus();
                        return false;
                    }
                    if (document.frm.secnum.value.match(phonExp) === null){
                        alert("전화번호가 틀렸습니다.");
                        document.frm.secnum.focus();
                        return false;
                    }
                    if (document.frm.sample6_postcode.value === "" || document.frm.sample6_detailAddress.value === "" || document.frm.sample6_address.value === ""){
                        alert("주소를 입력해주세요.");
                        document.frm.sample6_detailAddress.focus();
                        return false;
                    }
                    alert("변경이 완료됐습니다.");
                };
            </script>
        </div>
    </form>
</div>
<footer>
    <%@include file="../../tags/footer.jsp" %>
</footer>

</body>
</html>