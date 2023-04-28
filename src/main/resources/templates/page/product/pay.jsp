<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script  src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <!-- iamport.payment.js -->
    <script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
    <script>
        var arrl = [];
        <c:forEach items="${product_num}" var="i" varStatus="kk">
            arrl[${kk.index}] = ${i};
        </c:forEach>
        console.log(arrl)
        var arrl2 = [];
        <c:forEach items="${payment_stock}" var="i" varStatus="kk">
        arrl2[${kk.index}] = ${i};
        </c:forEach>
        var arrl3 = [];
        <c:forEach items="${cartItem_num}" var="i" varStatus="kk">
        arrl3[${kk.index}] = ${i};
        </c:forEach>

        console.log(arrl3);
        console.log("${payment_stock[0]}");
        var lists = {
            payment_num:`${payment_num}`,
            address:`${address}`,
            phone:`${phone}`,
            email:`${email}`,
            delivery_info:`${delivery_info}`,
            product_num:arrl,
            payment_stock:arrl2,
            cartItem_num:arrl3
        }
        console.log("${product_num}");
        console.log(typeof("${product_num}"));

        const IMP = window.IMP;   // 생략 가능
        IMP.init("imp32154105"); // 예: imp00000000
        //결제 및 입력검증
        window.onload = function requestPay() {
        IMP.request_pay({
            pg: "html5_inicis.INIpayTest",
            pay_method: "card",
            merchant_uid: "${payment_num}",   // 주문번호
            name: "결제하기",
            amount: 100,                         // 숫자 타입
            buyer_email: "${email}",
            buyer_name: "${m_name}",
            buyer_tel: "${phone}",
            buyer_addr: "${fn:split(address, "/")[1]}" + "${fn:split(address, "/")[2]}",
            buyer_postcode: "${fn:split(address, "/")[0]}"
        }, function (res) { // callback
            if (true) {
            /*if (rsp.success) {*/
                // axios로 HTTP 요청
                jQuery.ajax({
                    url: "/com.solponge/member/${member_No}/payments/su",
                    method: "post",
                    contentType: "application/json;charset=UTF-8",
                    data: JSON.stringify(lists),
                    success: function(res){
                        alert("결제 성공했습니다.");
                        location.replace("/com.solponge/member/${member_No}/payments/success")
                    },
                    error: function (res){
                        alert("결제 실패했습니다.");
                        location.replace('/com.solponge/member/${member_No}/payments/fail');
                    }
                });
            }else {
                window.location.href ='/com.solponge/member/${member_No}/payments/fail';
            }
        });
        }
        console.log("${payment_num}"+"${email}"+"${m_name}"+"${phone}")
    </script>
</head>
<body>

</body>
</html>