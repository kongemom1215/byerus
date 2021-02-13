<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bye-rus!</title>
<link rel="stylesheet" type="text/css" href="css/YoungCSS.css?ver=1">
<!-- autoload=false 파라미터를 이용하여 자동으로 로딩되는것을 막는다 -->
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">
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

<%
	String context = request.getContextPath();
%>

		<script type="text/javascript" src="./js/jquery.js"></script>
				<script type="text/javascript">
				
				$(function() {
					
					$('#semail').change(function() {
						var semail = $('#semail').val();
						$.ajax({
							url:"<%=context%>/ajaxSemail.do",  
							data:{semail : semail},
							dataType:'text',
							success:function(msg){ 
								$('#msg').html(msg);
							}
						})
					})
				})
				
				<%-- $(function() {
					$('#chk').click(function() {
						alert("야");
					var semail = $('#semail').val();
					$.ajax({
						url:"<%=context%>/ajaxSemail.do",  
						data:{semail : semail},
						dataType:'text',
						success:function(msg){
							$('#msg').html(msg);     /* span  id Tag */
						}
					});
					}
				}) --%>
				
				/* $(function() {
					$('#chk').click(function() {
						var semail = $('#semail').val();
						var sendData = "semail=" + semail
						$.post('confirmId.jsp', sendData, function(msg) {
							$('#msg').html(msg);
						});
					});
				}); */
				
				$(document).ready(function(){
				    $('.main2 i').on('click',function(){
				        $('input').toggleClass('active');
				        if($('input').hasClass('active')){
				            $(this).prev('input').attr('type',"text");
				        }else{
				            $(this).prev('input').attr('type','password');
				        }
				    });
				});
				
				</script>

<style type="text/css">
	h1{font-weight: 900;}
	#join{ text-align: center; }
	
	table{width: 50%;
		  height: 400px;
		  margin: auto;
		  font-weight: bolder;
		  font-size: 12px;
		  }
	th:nth-child(2n), td:nth-child(2n){
	background-color: #D6F4FF;}
	
	.semail{width: auto; height: 100%;	background-color: #D6F4FF; border: 0px; font-size: 30px; outline: none;}
	.spwd{width: auto; height: 100%;	background-color: #D6F4FF; border: 0px; font-size: 30px; outline: none;}
	.sname{width: auto; height: 100%;	background-color: #D6F4FF; border: 0px; font-size: 30px; outline: none;}
	.scontact{width: auto; height: 100%;	background-color: #D6F4FF; border: 0px; font-size: 30px; outline: none;}
	.saddress{width: auto; height: 100%;	background-color: #D6F4FF; border: 0px; font-size: 30px; outline: none;}
	.spost{width: auto; height: 100%;	background-color: #D6F4FF; border: 0px; font-size: 30px; outline: none;}
	.button{background-color: #0A6EFF; display: inline-block; outline: none; cursor: pointer; text-align: center; width:150px;
	text-decoration: none; padding: 10px 10px 10px 10px; text-shadow: 0 1px 1px rgba(0,0,0,.3);
	border-radius: .5em; box-shadow: 0 1px 2px rgba(0,0,0,.2); border: 0; color:white;}
	.button:hover{background-color: #3CCEFF;}
	
	.sagree{background-color: white;}
	::placeholder{
	text-align:center;
	font-size: 12px;	
	font-weight: 100;
	}
	.button2{background-color: #288CFF; display: inline-block; outline: none; cursor: pointer; text-align: center;
	text-decoration: none; border:none; text-shadow: 0 1px 1px rgba(0,0,0,.3);
	border-radius: .5em; box-shadow: 0 1px 2px rgba(0,0,0,.2); border: 0; color:white;}
	.button2:hover{background-color: #3CCEFF;}
</style>
</head>
<body>
<div class="main">
<div style="margin-top: 15px;">
<a href="main.do"><img src="./img/Logo.png"></a>

<c:choose>
<c:when test="${session_stype eq '1'}">
<a href="jjimForm.do" class="top_button">위시리스트</a>
<a href="cart.do" class="top_button">장바구니</a>
<a href="mypageOrder.do" class="top_button">주문/배송</a>
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
<a href="login.do?url=jjimForm.do" class="top_button">위시리스트</a>
<a href="login.do?url=cart.do" class="top_button">장바구니</a>
<a href="login.do?url=mypageOrder.do" class="top_button">주문/배송</a>
<a href="login.do?url=mypage.do" class="top_button">마이페이지</a>
<a href="login.do?url=main.do" class="top_button">로그인/회원가입</a>
</c:otherwise>
</c:choose>

</div>
</div>
<hr style="width: 900px; border-bottom: 0xp;">
<div class="main">
<div style="height: 17.33px;">
<div class="nav_button"><a href="aboutUs.do">ABOUT US</a></div>
<div class="nav_button"><a href="shoppingMain.do">SHOPPING</a></div>
<div class="nav_button"><a href="board.do?type=notice">BOARD</a></div>
</div>
</div>
<hr style="width: 900px; margin-bottom: 0px; border-bottom: 0px;">

<!-- <div id="join">
<h1>SIGN UP</h1>
 <form action="joinPro.do">
		<table>
			<tr><td>이메일 </td><td><input   type="email" name="semail"  class="semail" required="required" placeholder="아이디로 사용할 이메일 주소를 입력해주세요."></td></tr>
			<td><input type="button" value="중복확인" ></td>
	
			<tr><td>비밀번호 </td><td>   <input  type="password" name="spwd" class="spwd" required="required" placeholder="비밀번호를 입력해주세요."></td></tr>
			<tr><td>이름 </td><td>   <input  type="text" name="sname" class="sname" required="required" placeholder="한글 15자, 영문 30자까지 가능합니다."></td></tr>
			<tr><td>연락처 </td><td>   <input  type="text" name="scontact" class="scontact" required="required"
			pattern="\d{2,3}-\d{3,4}-\d{4}"
			placeholder="XXX   -   XXXX   -   XXXX"
			 title="2,3자리-3,4자리-4자리"></td></tr>
			 
			 <tr><td>주소 </td><td>   <input  type="text" name="saddress" class="saddress" required="required"></td></tr>
			 <tr><td>우편번호 </td><td>   <input  type="number" name="spost" class="spost"
			 title="숫자만 입력" placeholder="숫자만 입력가능합니다."
			 required="required"></td></tr> 
			 <tr><td>마케팅동의</td><td>   Y<input  type="radio" name="sagree" value="y">
			N<input  type="radio" name="sagree" value="n" > </td></tr>
		</table>
		<input type="submit" value="회원가입" class="button">
  </form><p>
 </div> -->
 
 
   <div class="main" style="text-align: center;">
   <c:if test="${pwsdsame eq 'no' }">
   	<script type="text/javascript">
   	alert("비밀번호가 동일하지 않습니다");
   	</script>
   </c:if>
<h1>SIGN UP</h1>
 <form action="joinPro.do">
<input type="hidden" name="stype" value="1">
		<table>
			<tr><td>이메일</td> <td><input type="email" name="semail" id="semail" class="semail" required="required" placeholder="아이디로 사용할 이메일 주소를 입력해주세요."></td></tr>
			<tr><td></td><td style="background-color: white;"></td></tr>
	<!-- 		<td><input type="button" value="중복확인" ></td> -->
			
			<tr><td>비밀번호 </td><td>  <div class="main2"><input  type="password" id="spwd" name="spwd" class="spwd" required="required" placeholder="비밀번호는 영문+숫자만 가능합니다" pattern="^(?=.*[a-z])(?=.*[0-9]).{1,10}$"><br><i>확인</i></div></td></tr>
         <tr><td>비밀번호 </td><td>  <div class="main2"><input  type="password" id="spwd2" name="spwd2" class="spwd" required="required" placeholder="비밀번호는 영문+숫자만 가능합니다" pattern="^(?=.*[a-z])(?=.*[0-9]).{1,10}$"><br><i>확인</i></div></td></tr>
		
			
			<tr><td>이름 </td><td>   <input  type="text" name="sname" class="sname" required="required" placeholder="한글 15자까지 가능합니다." maxlength="15"></td></tr>
			<tr><td>연락처 </td><td>   <input  type="text" name="scontact" class="scontact" required="required"
			pattern="\d{2,3}-\d{3,4}-\d{4}"
			placeholder="XXX   -   XXXX   -   XXXX"
			 title="2,3자리-3,4자리-4자리"></td></tr>
			 
			 <tr><td>주소</td><td><input type="text" name="spost" id="sample6_postcode" placeholder="우편번호" style="border:none; margin-left: none;">
								 <input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기" style="cursor: pointer;" class="button2"><br>
								 <input type="text" name="saddress" id="sample6_address" placeholder="주소" style="border:none;  margin-left: none;"><br>
								 <input type="text" id="sample6_extraAddress" placeholder="참고항목" style="border:none;  margin-left: none;">
								 <input type="text" name="saddress2" id="sample6_detailAddress" placeholder="상세주소" style="border:none;  margin-left: none;"></td></tr>
			<tr><td>질문</td><td><select name="squestion">
				<option>1. 기억에 남는 추억의 장소는?</option>
            <option>2. 자신의 인생 좌우명은?</option>
            <option>3. 자신의 보물 제1호는?</option>
            <option>4. 가장 기억에 남는 선생님 성함은?</option>
            <option>5. 타인이 모르는 자신만의 신체비밀이 있다면?</option>
            <option>6. 추억하고 싶은 날짜가 있다면?</option>
            <option>7. 받았던 선물 중 기억에 남는 독특한 선물은?</option>
			</select></td></tr>
			<tr><td>답변</td><td><input type="text" name="sanswer" required="required" placeholder="위 질문의 답변"></td></tr>
			 <tr><td>마케팅<br>동의</td><td>   Y<input  type="radio" name="sagree" value="y">
			N<input  type="radio" name="sagree" value="n" > </td></tr>
		</table>
		<p>
		<input type="submit" value="회원가입" class="button">
  </form><p>
 <div style="width: 300px;  position: relative; bottom: 565px; left:655px;  text-align: left;"> <span id="msg" style="color: red; font-size: 18px;"></span></div>
 </div>

<div style="margin-top: 200px;">
<div class="main" style="height: 50px; background-color: #767171; display: table;">
<div style="width: 10px;">
</div>
<div style="display: table-cell; vertical-align: middle; margin-left: 5px;">
대표 : 임주혜 / 사업자등록번호 : 123-45-67899
</div>
<div style="display: table-cell; vertical-align: middle; text-align: right;">
<a>이용약관</a> /
<a>개인정보처리방침</a> /
<a>입점문의</a>
</div>
<div style="width: 10px;">
</div>
</div>
</div>
</body>
</html>