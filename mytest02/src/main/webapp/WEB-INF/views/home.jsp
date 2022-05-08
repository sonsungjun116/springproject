<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="false" %>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>

<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>

<input type="button" id="button" name="button" value="결제테스트">

<script>

$("#button").click(function () {

	출처: https://minaminaworld.tistory.com/78 [미나미 블로그]
IMP.init("imp01626965");

IMP.request_pay({
    pg : "html5_inicis", // version 1.1.0부터 지원.
    pay_method : "card",
    merchant_uid : "merchant_" + new Date().getTime(),
    name : "주문명:결제테스트",
    amount : 500, //판매 가격
    buyer_email : "iamport@siot.do",
    buyer_name : "구매자이름",
    buyer_tel : "010-1234-5678",
    buyer_addr : "서울특별시 강남구 삼성동",
    buyer_postcode : "123-456"
}, function(rsp) {
    if ( rsp.success ) {
        var msg = "결제가 완료되었습니다.";
        msg += "고유ID : " + rsp.imp_uid;
        msg += "상점 거래ID : " + rsp.merchant_uid;
        msg += "결제 금액 : " + rsp.paid_amount;
        msg += "카드 승인번호 : " + rsp.apply_num;
    } else {
        var msg = "결제에 실패하였습니다.";
        msg += "에러내용 : " + rsp.error_msg;
    }
    alert(msg);
});
});


</script>

</body>
</html>
