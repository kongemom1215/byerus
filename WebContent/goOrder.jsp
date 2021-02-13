<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<%
	String context = request.getContextPath();
%>

<head>
<meta charset="UTF-8">
<title>Bye-rus!</title>
<link rel="stylesheet" type="text/css" href="css/cartnbuy.css?ver=1"> 
<link rel="stylesheet" type="text/css" href="css/YoungCSS.css?ver=2">
<script type="text/javascript" src="http://code.jquery.com/jquery-2.1.3.min.js"></script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript" src="./js/jquery.js"></script>
<script type="text/javascript">
$(function() {
	$('#coupon').change(function() {
		var Vcid = $("#coupon").val();
		var sum_value = $("#sum_value").val();
		$.ajax({
			url  		: "<%=context%>/ajaxDiscount.do",
			data 		: {Vcid : Vcid , sum_value : sum_value},
			dataType	: 'text',
			success		: function(msg){
				$('#total_input').val(msg);
				
			}
		});
	})
})

function showHide1(){
    if(document.getElementById("showHide1").style.display =='none'){
        document.getElementById("showHide1").style.display ='block';
    }
    else{
        document.getElementById("showHide1").style.display ='none';
    }
}
function showHide2(){
    if(document.getElementById("showHide2").style.display =='none'){
        document.getElementById("showHide2").style.display ='block';
    }
    else{
        document.getElementById("showHide2").style.display ='none';
    }
}

function send_it(){
	var card = document.frm.credit_chk.value;
	if(card==""){
		alert("카드사를 선택해 주세요");
		return false;
	}
	var bank = document.frm.bank.value;
	if(bank == ""){
		alert("은행사를 선택해 주세요");
		return false;
	}if(card=="해당사항없음" && bank=="해당사항없음"){
		alert("결제수단을 선택해 주세요");
		return false;
	}
}
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
                document.getElementById("sample6_extraAddress").value = extraAddr;
            
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


</script>
<style type="text/css">
.info{
		margin-bottom: 50px;
		margin-left: 50px;
		border-collapse: collapse;
		width : 750px;
		background: white;
		text-align: center;
		
		
	}.cart_tr{
		border-bottom: 1px solid gray;
		height : 60px;
		width:100px;
		text-align: center;
		
	
	}.info_tr{
		border-bottom: 1px solid gray;
		height : 60px;
		width:100px;
		text-align: center;
	
	}





</style>
</head>

<body>
<!--바로 구매 버튼 클릭시 이동하는 페이지  -->

<header>
	<div class="main">
<div style="margin-top: 15px;">
<a href="main.do"><img src="./img/Logo.png"></a>

<c:choose>
<c:when test="${session_stype eq '1'}">
<a class="top_button">위시리스트</a>
<a href="cart.do" class="top_button">장바구니</a>
<a class="top_button">주문/배송</a>
<a href="mypage.do" class="top_button">마이페이지</a>
<a href="main.do?logout=logout" class="top_button">로그아웃</a>
<a class="top_button">${session_sname } 님</a>
</c:when>
<c:when test="${session_stype eq '0'}">
<a href="main.do?logout=logout" class="top_button">로그아웃</a>
<a class="top_button">${session_sname } 님</a>
<a href="adminPage.do" class="top_button">관리페이지</a>
</c:when>
<c:otherwise>
<a class="top_button">위시리스트</a>
<a class="top_button">장바구니</a>
<a class="top_button">주문/배송</a>
<a class="top_button">마이페이지</a>
<a href="login.do" class="top_button">로그인/회원가입</a>
</c:otherwise>
</c:choose>
</div>
</div>
<hr>
<div class="main">
<div style="height: 17.33px;">
<div class="nav_button"><a href="aboutUs.do">ABOUT US</a></div>
<div class="nav_button"><a href="shoppingMain.do">SHOPPING</a></div>
<div class="nav_button"><a href="board.do?type=notice">BOARD</a></div>
</div>
</div>
<hr style="margin-bottom: 0px; border-bottom: 0px;">

</header>
<div class="main">
	<div class="aa">
		<span class="step1" style="color : #848484;"><h2>장바구니</h2></span>
		<span class="step2" ><h2>주문결제</h2></span>
		<span class="step3" style="color : #848484;"><h2>결제완료</h2></span>
	</div>
</div>	
<div class="main">
<div class="bb">
	<div>
<!--주문자정보입력       -->
	<div>
		<br>
<!-- 전체입력값 form 으로 보내기 -->
<form action="goOrderResult.do?pid=${pid}&howmany=${howmany}&total=${total}&sum=${sum}" method="post" name="frm" onsubmit="return send_it();">
		<br>
		
		<div style="font-size: 20px;margin-left: 20px; margin-bottom: 10px " >주문리스트확인</div>
		<table class="info">
		<thead>
		<tr class="cart_tr" >
			<th class="cart_td">상품코드</th>
			<th class="cart_td">상품명</th>
			<th class="cart_td">옵션</th>
			<th class="cart_td">판매가격</th>
			<th class="cart_td">할인판매가</th>
			<th class="cart_td">수량</th>
			<th class="cart_td">주문금액</th>
		</tr>
		</thead>
		<tbody>
			<tr class="cart_tr">
				<td class="cart_td">${pid}</td>
				<td class="cart_td" style="width: 200px;">${product.pname}</td>
				<td class="cart_td">${option}</td>
				<td class="cart_td"><fmt:formatNumber value="${product.pprice}" pattern="#,###"></fmt:formatNumber></td>

				<c:if test="${product.pdiscount > 0 }">
					<c:set var="discount" value="0"></c:set>
					<c:set var="discount" value="${product.pprice - (product.pdiscount / 100 * product.pprice) }"></c:set>
					<td class="cart_td"><fmt:formatNumber value="${discount}"  pattern="#,###"></fmt:formatNumber></td>
					<td class="cart_td">${howmany}</td>
					<td class="cart_td"><fmt:formatNumber value="${discount*howmany}" pattern="#,###"></fmt:formatNumber></td>
				</c:if>
				<c:if test="${product.pdiscount == 0 }">
					<td class="cart_td"><fmt:formatNumber value=""  pattern="#,###"></fmt:formatNumber></td>
					<td class="cart_td">${howmany}</td>
					<td class="cart_td"><fmt:formatNumber value="${product.pprice*howmany}" pattern="#,###"></fmt:formatNumber></td>
				</c:if>
			</tr>		
			<tr class="cart_tr">
					<td align="right" colspan="6">예상 결제 상품 총액 :</td>
					<td align="center">
 							<c:set var="sum" value="0"/>
						<c:if test="${product.pdiscount > 0 }">
							<c:set var="sum" value="${product.pprice - (product.pdiscount / 100 * product.pprice) }"/>
						</c:if>
						<c:if test="${product.pdiscount == 0 }">
							<c:set var="sum" value="${product.pprice*howmany}"/>
						</c:if>
							<fmt:formatNumber value="${sum}" pattern="#,###"></fmt:formatNumber>
							
							<input type="hidden" value="${pid}">
							<input type="hidden" value="${product.pdiscount}">
							<input type="hidden" value="${product.pprice}">
					</td>	
					
							
			</tr>
		</tbody>
		</table>
		<div style="font-size: 20px;margin-left: 20px; margin-bottom: 10px " >주문자정보입력</div>
		<table class="info" >
			<tr class="info_tr" >
				<td align="center">주문고객</td><td><input class="input_info" type="text" name="buyerName" value="${user.sname }" required="required"/></td>
			</tr>
<%-- 			<tr class="info_tr">
				<td rowspan="2" align="center">주소</td>
				<td><input class="input_info" type="text" name="buyerPost" value="${user.spost }" required="required" size="4"/><input type="button" name="post" value="우편주소확인" onclick=""></td>
			<tr class="info_tr">
				<td><input class="input_info" type="text" name="buyerAddress" value="${user.saddress }" required="required"/></td>
			</tr>
 --%>			
			<tr>
			 	<td>주소</td>
			 	<td>
			 		<input type="text" name="spost" id="sample6_postcode" placeholder="우편번호" style="border:none;">
					<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기" style="cursor: pointer;"><br>
					<input type="text" name="saddress" id="sample6_address" placeholder="주소" style="border:none;"><br>
					<input type="text" id="sample6_extraAddress" placeholder="참고항목" style="border:none;">
					<input type="text" name="saddress2" id="sample6_detailAddress" placeholder="상세주소" style="border:none;">
				</td>
			</tr>
			
		
			<tr class="info_tr" height="20px" >
				<td align="center">연락처</td>
				<td>
					<input type="tel" name="buyerTel" value="${user.scontact }"  class="input_info" pattern="\d{2,3}-\d{3,4}-\d{4}">
				
				</td>
			</tr>
			<tr class="info_tr">
				<td align="center" >이메일</td><td><input class="input_info" type="email" value="${session_semail }" required="required"/></td>
			</tr>
		</table>
		<div>
<!--할인정보        -->
		<div style="font-size: 20px; margin-left: 20px; margin-bottom: 10px " >할인정보</div>
		<div>
		<table class="info">
			<tr class="info_tr">
				<td align="center">쿠폰</td>
 				<td>
 				<select name="coupon" id="coupon">
 					<option value="">=================</option>
 					<option value="0">사용하지말기</option>
 					<c:forEach var="coupon" items="${cu}">
							<option value="${coupon.cid}" id="cid">${coupon.cid}번 쿠폰: ${coupon.cid}% 할인 ${coupon.cstartdate }부터${coupon.cenddate}까지</option>
					</c:forEach>
				</select>
				</td>
			</tr>
		</table>
		</div>
<!--결제내역      -->
		
		<div style="font-size: 20px; margin-left: 20px; margin-bottom: 10px " >결제금액</div>
		<div>
		<table class="info">
			<tr  class="info_tr">
				<td align="center">총상품금액</td>
				<td align="center"><fmt:formatNumber value="${sum}" pattern="#,###"></fmt:formatNumber>원</td>
				<input type="hidden" id="sum_value" value="${sum}" name="sum_value">
			</tr>
			<tr  class="info_tr">
				<td align="center">배송비</td>
				<c:if test="${sum >= 30000 }">
					<td align="center">0원</td>
				</c:if>
				<c:if test="${sum < 30000 }">
					<td align="center">3,000원</td>
				</c:if>
				
			</tr>
			<tr  class="info_tr">
<!-- 쿠폰적용값 나중에 하기 -->
				<td align="center">최종결제금액</td>
				<td align="center">
				
				<input style="text-align: center;" type="text" name="total_input" id="total_input" readonly="readonly">원
				</td>
			</tr>
		</table>
		</div>
<!--결제 수단            -->
		<div style="font-size: 20px; margin-left: 20px; margin-bottom: 10px " >결제수단</div>
		<div>
       		 <span><input type="button" id="cardb" class="pay_way" value="신용카드" onclick="showHide1()" style="margin: 0px 80px 20px 200px;"></span>
      		 <span><input type="button" id="cardb" class="pay_way" value="무통장" onclick="showHide2()"  style="margin: 0px 80px 20px 200px;"></span>
		</div>
		<br>
		<br>
<!--신용카드            -->
    <div id="showHide1" align="middle" style="display: none;">
		<div>
		<div  style="font-size: 20px; margin-left: 20px; margin-bottom: 10px; " >신용카드</div>
		<div style="text-align: left;">
			<h4>유의사항</h4>
			<p> 신용카드 결제 시 화면 아래 '결제하기'버튼을 클릭하시면 신용카드 결제 창이 나타납니다.
			<p> 신용카드 결제 창을 통해 입력되는 고객님의 카드 정보는 128bit로 안전하게 암호화되어 전송되며,
			 승인 처리 후 카드 정보는 승인 성공 / 실패 여부에 상관없이 자동으로 폐기되므로, 안전합니다.
		</div>
		
		<table class="info" name="credit_tb">
			<tr  class="info_tr">
			<td>카드사</td>
			<td>
				<select name="credit_chk" style="width: 350px;">
					<option value="">=====================</option>
					<option value="해당사항없음">해당사항없음</option>
					<option value="국민카드">국민카드</option>
					<option value="신한카드">신한카드</option>
					<option value="농협카드">농협카드</option>
					<option value="우리카드">우리카드</option>
				</select>
			</td>
			</tr>
			<tr  class="info_tr">
				<td>카드번호</td>
				<td><input type="text" class="input_info" name="creditNum" maxlength="16" ></td>
			</tr>
			<tr  class="info_tr">
				<td>비밀번호</td>
				<td><input type="password" class="input_info" name="creditPass" maxlength="4" ></td>
			</tr>
		</table>
		</div>
    </div>

     <div id="showHide2" align="middle" style="display: none;">
    	<div style="font-size: 20px; margin-left: 20px; margin-bottom: 10px; " >무통장입금</div>
		<div >
			<div style="text-align: left;">
			<h4>유의사항</h4>
			<p> 무통장 입금 시 사용되는 가상계좌는 매 주문 시마다 새로운 계좌번호(개인전용)가 부여되며 해당 주문에만 유효합니다.
			<p> 통장 입금은 입금 후 1시간 이내에 확인되며, 입금 확인시 배송이 이루어 집니다.
		</div>
		
		<table  class="info" name="bank_tb" >
			<tr  class="info_tr">
			<td>입금하실 은행</td>
			<td>
				<select name="bank" style="width: 350px;" >
					<option value="">=====================</option>
					<option value="해당사항없음">해당사항없음</option>
					<option value="국민은행">국민은행</option>
					<option value="신한은행">신한은행</option>
					<option value="농협은행">농협은행</option>
					<option value="우리은행">우리은행</option>
				</select>예금주 : 임주혜
			</td>
			
			</tr>
			<tr  class="info_tr">
				<td>계좌번호</td>
				<td><input type="text" class="input_info" name="backNum" maxlength="13"></td>
			</tr>
			<tr  class="info_tr">
				<td>입금자명</td>
				<td><input type="text" class="input_info" name="backNum" maxlength="6"></td>
			</tr>
		</table>
		</div>
		</div>
    </div>

   
    </div>
		
 		</div>
 		<div>
<!--결제할래??       -->
			<span><input type="button" class="pay" id="cancel" value="이전페이지" onclick="location.href='cart.do'" ></span>
			<span><input type="submit" class="pay" id="pay" value="결제하기" ></span>
		
		</div>


</form>

<br>
</div>
</div>

</body>
</html>