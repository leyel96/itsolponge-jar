<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <style>
        #shopbody {
            background-color: #f0f0f0;
            margin: 0 auto;
            width: 1050px;
        }
        .box {
            margin-top: 30px;
            padding: 30px 0px 20px 50px;
            background-color: #fff;
            border-radius: 10px;
            width:100%;
        }

        .box h2 {
            margin-top: 0;
            margin-bottom: 20px;
            font-size: 24px;
        }

        .fff {
            font-weight: bold;
            width: 120px;
        }

        .eee {
            padding-left: 20px;
            width:600px;
        }

        input[type="text"] {
            padding: 8px;
            border-radius: 5px;
            border: 1px solid #ccc;
            width: 150px;
            margin-bottom: 10px;
        }

        #emails, #firstnum, #firstnum2{
            width: 100px;
        }

        #delivery_info{
            width: 200px
        }

        input[type="checkbox"] {
            margin-right: 10px;
        }

        select {
            width: 100%;
            margin-bottom: 10px;
            padding: 8px;
            border-radius: 5px;
            border: 1px solid #ccc;
        }
        #paybutton {
            background-color: #1c7ae6;
            text-align:center;
            margin: 10px auto;
            width:100%;
            border:none;
            height: 50px;
            font-size:25px;
        }
        #delivery_info{
            width:300px;
        }
        #memo{
            width:300px
        }
        .product_image{
            margin-left:10px;
            padding: 10px;
        }
        .product_images{
            width:50px;
            height:75px;
            object-fit: cover;
        }
        .product_title{
            padding-left:50px;
            width:500px;
        }
        .stock{
            margin-left:10px;
            text-align:center;
        }

    </style>
    <!-- jQuery -->
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
    <!-- iamport.payment.js -->
    <script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script>
        //체크박스 클릭시 멤버 정보 뿌려줌
        $(document).ready(function(){
            $("#checkboxs").change(function(){
                if($("#checkboxs").is(":checked")){
                    $("#m_name").attr("value", "${minfo.MEMBER_NAME}");
                    $("#secnum2").attr("value", "${fn:split(minfo.MEMBER_PHONE,"-")[1]}");
                    $("#thrnum2").attr("value", "${fn:split(minfo.MEMBER_PHONE,"-")[2]}");
                    $("#sample6_postcode").attr("value", "${fn:split(minfo.MEMBER_ADDRESS,"/")[0]}");
                    $("#sample6_address").attr("value", "${fn:split(minfo.MEMBER_ADDRESS,"/")[1]}");
                    $("#sample6_detailAddress").attr("value", "${fn:split(minfo.MEMBER_ADDRESS,"/")[2]}");
                }
                else{
                    $("#m_name").attr("value", "");
                    $("#secnum2").attr("value", "");
                    $("#thrnum2").attr("value", "");
                    $("#sample6_postcode").attr("value", "");
                    $("#sample6_address").attr("value", "");
                    $("#sample6_detailAddress").attr("value", "");
                }
            });
        });

        //주소찾기 스크립트
        function sample6_execDaumPostcode() {
            new daum.Postcode({
                oncomplete: function(data) {
                    // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                    // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                    // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                    var addr = ''; // 주소 변수
                    var extraAddr = ''; // 참고항목 변수

                    //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                    if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                        addr = data.roadAddress;
                    } else { // 사용자가 지번 주소를 선택했을 경우(J)
                        addr = data.jibunAddress;
                    }

                    // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                    if(data.userSelectedType === 'R'){
                        // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                        // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                        if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                            extraAddr += data.bname;
                        }
                        // 건물명이 있고, 공동주택일 경우 추가한다.
                        if(data.buildingName !== '' && data.apartment === 'Y'){
                            extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                        }
                        // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                        if(extraAddr !== ''){
                            extraAddr = ' (' + extraAddr + ')';
                        }
                        // 조합된 참고항목을 해당 필드에 넣는다.
                        document.getElementById("sample6_address").value += extraAddr;

                    } else {
                        document.getElementById("sample6_extraAddress").value = '';
                    }

                    // 우편번호와 주소 정보를 해당 필드에 넣는다.
                    document.getElementById('sample6_postcode').value = data.zonecode;
                    document.getElementById("sample6_address").value = addr;
                    // 커서를 상세주소 필드로 이동한다.
                    document.getElementById("sample6_detailAddress").focus();
                }
            }).open();
        }

        var time = new Date();
        var yyyy = time.getFullYear();
        var mm = time.getMonth()+1;
        var dd = time.getDay();
        var hh = time.getHours();
        var MM = time.getMinutes();
        var ss = time.getSeconds();
        var ms = time.getMilliseconds();
        var total = ''+yyyy + mm+dd+hh+MM+ss+ms+${minfo.MEMBER_NO};
        $(document).ready(function(){
            console.log(total);
            // $('payment_num').val(total);
            document.getElementById("payment_num").value = total;
        });

        function delivering(){
            if(document.frm.memo.value === "6"){
                document.frm.email2.readOnly = false;
                frm.delivery_info.value = "";
                frm.delivery_info.type = "text"
                frm.delivery_info.focus();
            }else if(document.frm.memo.value === "1"){
                frm.delivery_info.readOnly = true;
                frm.delivery_info.type = "text"
                frm.delivery_info.value = "직접 수령하겠습니다.";
            }else if(document.frm.memo.value === "2"){
                frm.delivery_info.readOnly = true;
                frm.delivery_info.type = "text"
                frm.delivery_info.value = "배송 전 연락바랍니다.";
            }else if(document.frm.memo.value === "3"){
                frm.delivery_info.readOnly = true;
                frm.delivery_info.type = "text"
                frm.delivery_info.value = "부재 시 경비실에 맡겨주세요.";
            }else if(document.frm.memo.value === "4"){
                frm.delivery_info.readOnly = true;
                frm.delivery_info.type = "text"
                frm.delivery_info.value = "부재 시 문 앞에 놓아주세요.";
            }else if(document.frm.memo.value === "5"){
                frm.delivery_info.readOnly = true;
                frm.delivery_info.type = "text"
                frm.delivery_info.value = "부재 시 택배함에 넣어주세요.";
            }else{
                frm.delivery_info.readOnly = true;
                frm.delivery_info.type = "hidden"
                frm.delivery_info.value = "";
            }
        }
        function emailchang(){
            if(frm.emails.value === "1"){
                document.frm.email2.readOnly = false;
                frm.delivery_info.value = "";
                frm.delivery_info.focus();
            }else {
                frm.email2.readOnly = true;
                frm.email2.value = frm.emails.value;
            }
        }

        $(document).ready(function (){
            $("#paybutton").click(function (){
                var m_name = document.getElementById("m_name").value;
                var secnum2 = document.getElementById("secnum2").value;
                var thrnum2 = document.getElementById("thrnum2").value;
                var sample6_postcode = document.getElementById("sample6_postcode").value;
                var sample6_address = document.getElementById("sample6_address").value;
                var sample6_detailAddress = document.getElementById("sample6_detailAddress").value;
                var delivery_info = document.getElementById("delivery_info").value;
                if(m_name.length===0||secnum2.length===0||thrnum2.length===0||sample6_postcode.length===0||
                    sample6_address.length===0|| sample6_detailAddress.length===0||delivery_info.length===0){
                    alert("배송지 정보를 잘 입력해 주세요")
                    return false;
                }
            })
        })
    </script>
</head>
<body>
<header>
    <%@include file="../../tags/header.jsp"%>
</header>
<div id="shopbody">
    <form id="frm" name="frm" method="post" action="/com.solponge/member/${minfo.MEMBER_NO}/payments/pay">
        <div class="box">
            <h2>
                주문상품
            </h2>
            <input id="payment_num" name="payment_num" type="hidden"/>
            <table id="payinfo">
                <c:forEach items="${oinfo}" var="o">
                    <c:set var="pimg" value="img_${o.PRODUCT_NUM}"/>
                    <c:set var="ptit" value="title_${o.PRODUCT_NUM}"/>
                    <c:set var="ppri" value="price_${o.PRODUCT_NUM}"/>
                    <c:set var="psto" value="stock_${o.PRODUCT_NUM}"/>
                    <c:set var="cait" value="cartItem_${o.PRODUCT_NUM}"/>
                    <tr>
                        <td rowspan="2" class="product_image"><img src="${pinfo.get(pimg)}" class="product_images"> </td>
                        <td class="product_title">${pinfo.get(ptit)}</td>
                        <td class="stock">주문수량</td>
                    </tr>
                    <tr>
                        <td class="product_title">단가 : ${pinfo.get(ppri)}</td>
                        <td class="stock">${pinfo.get(psto)}</td>
                    </tr>
                    <input type="hidden" value="${o.PRODUCT_NUM}" class="product_num" name="product_num">
                    <input type="hidden" value="${pinfo.get(psto)}" class="payment_stock" name="payment_stock">
                    <input type="hidden" value="${pinfo.get(ppri)}" class="product_price" name="product_price">
                    <input type="hidden" value="${pinfo.get(cait)}" class="cartItem_num" name="cartItem_num">
                </c:forEach>
                <tr>
                    <td colspan="3" style="text-align: right; padding-right: 50px;">합계 금액 : ${total_price}(배송비 포함)</td>
                    <input type="hidden" value="${total_price}" name="total_price">
                </tr>

            </table>
        </div>

        <div class="box">
            <h2>
                주문자 정보
            </h2>
            <table>
                <tr>
                    <td class="fff">주문자 명</td>
                    <td class="eee">
                        <input type="text" value="${minfo.MEMBER_NAME}" size="20" id="member_name" name="member_name"/>
                    </td>
                </tr>
                <tr>
                    <td class="fff">연락처</td>
                    <td class="eee">
                        <select name="firstnum" size="1" id="firstnum">
                            <option value="010">010</option>
                            <option value="011">011</option>
                            <option value="013">013</option>
                            <option value="017">017</option>
                            <option value="018">018</option>
                        </select>-&nbsp;<input type="text" size="4" maxlength="4" minlength="4" name="secnum" id="secnum" value="${fn:split(minfo.MEMBER_PHONE,"-")[1]}">-
                        <input type="text" size="4" maxlength="4" minlength="4" name="thrnum" id="thrnum" value="${fn:split(minfo.MEMBER_PHONE,"-")[2]}">
                    </td>
                </tr>
                <tr>
                    <td class="fff">이메일주소</td>
                    <td class="eee">
                        <input type="text" value="${fn:split(minfo.MEMBER_EMAIL,"@")[0]}" size="16" id="email1" name="email1"/> @
                        <input type="text" size="20" id="email2" value="${fn:split(minfo.MEMBER_EMAIL,"@")[1]}" name="email2"/>
                        <select name="emails" id="emails" size="1" onchange="emailchang()">
                            <option value="" selected>선택하세요</option>
                            <option value="naver.com">naver.com</option>
                            <option value="hanmail.net">hanmail.net</option>
                            <option value="gmail.com">gmail.com</option>
                            <option value="nate.com">nate.com</option>
                            <option value="1">직접입력</option>
                        </select>
                    </td>
                </tr>
            </table>
        </div>
        <div class="box">
            <h2>
                배송지 정보
            </h2>
            <table>
                <tr>
                    <td colspan="2">
                        <input type="checkbox" id="checkboxs"/> 주문자 정보와 동일
                    </td>
                </tr>
                <tr>
                    <td class="fff">이름</td>
                    <td class="eee">
                        <input type="text" size="20" id="m_name" name="m_name"/>
                    </td>
                </tr>
                <tr>
                    <td class="fff">연락처</td>
                    <td class="eee">
                        <select name="firstnum2" size="1" id="firstnum2">
                            <option value="010">010</option>
                            <option value="011">011</option>
                            <option value="013">013</option>
                            <option value="017">017</option>
                            <option value="018">018</option>
                        </select> -&nbsp;<input type="text" size="4" maxlength="4" minlength="4" name="secnum2" id="secnum2">-
                        <input type="text" size="4" maxlength="4" minlength="4" name="thrnum2" id="thrnum2">
                    </td>
                </tr>
                <tr>
                    <td class="fff">주소</td>
                    <td class="eee">
                        <input type="text" id="sample6_postcode" placeholder="우편번호" readonly name="sample6_postcode">
                        <input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"/><br/>
                        <input type="text" id="sample6_address" placeholder="주소" readonly name="sample6_address">
                        <input type="text" id="sample6_detailAddress" placeholder="상세주소" name="sample6_detailAddress">
                    </td>
                </tr>
                <tr>
                    <td class="fff">요청사항</td>
                    <td class="eee">
                        <select id="memo" name="memo" onchange="delivering()">
                            <option value="0" selected>배송시 요청사항을 선택해 주세요.</option>
                            <option value="1">직접 수령하겠습니다.
                            </option>
                            <option value="2">배송 전 연락바랍니다.
                            </option>
                            <option value="3">부재 시 경비실에 맡겨주세요.
                            </option>
                            <option value="4">부재 시 문 앞에 놓아주세요.
                            </option>
                            <option value="5">부재 시 택배함에 넣어주세요.
                            </option>
                            <option value="6">직접 입력</option>
                        </select>
                        <input type="hidden" placeholder="최대 50자 입력이 가능합니다." id="delivery_info" name="delivery_info"/>
                    </td>
                </tr>
            </table>
        </div>
        <div>
            <button id="paybutton" type="submit"> 결제하기 </button>
        </div>
    </form>
</div>
<footer>
    <%@include file="../../tags/footer.jsp" %>
</footer>
</body>
</html>