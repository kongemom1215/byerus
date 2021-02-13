<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bye-rus!</title>
<link rel="stylesheet" type="text/css" href="css/YoungCSS.css?ver=1">
<style type="text/css">
table {
width: 50%;
	height: 200px;
	margin: auto;
	font-weight: bolder;
	border-collapse:collapse;
}
.submit {
	background-color: #0064FF;
	display: inline-block;
	outline: none;
	cursor: pointer;
	text-align: center;
	text-decoration: none;
	padding: .5em 2em .55em;
	text-shadow: 0 1px 1px rgba(0, 0, 0, .3);
	color: white;
	border-radius: .5em;
	box-shadow: 0 1px 2px rgba(0, 0, 0, .2);
	border: 0;
}
.submit:hover {
	background-color: #B9FFFF;
	color: #0000CD;
}
h1{font-weight: 900;}
	#find{text-align: center; width: 300px; overflow: hidden; position: absolute; right: 50%; top: 30%;}
	
	.find{background-color: #0064FF; display: inline-block; outline: none; cursor: pointer; text-align: center;
	text-decoration: none; padding: .5em 2em .55em; text-shadow: 0 1px 1px rgba(0,0,0,.3); color:white; width:200px; height:35px;
	border-radius: .5em; box-shadow: 0 1px 2px rgba(0,0,0,.2); border: 0;}
	.find:hover{background-color: #3CCEFF}
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
	<div class="main" style="display: table;">
	<div style="display: table-cell; text-align: center; vertical-align: middle;">
		<h1>비밀번호 찾기</h1>
			<form action="findPwdPro.do">
				<table style="margin-left: auto; margin-right: auto;">
					<tr><td>이메일</td><td><input type="email" name="semail" required="required" placeholder="이메일" style="width: 300px; height: 20px; text-align: center;"></td></tr>
					<tr><td>이름</td><td><input type="text" name="sename" required="required" placeholder="이름" style="width: 300px; height: 20px; text-align: center;"></td></tr>
					<tr><td>질문</td><td><select name="squestion" style="width: 300px; height: 30px; text-align: center;">
						<option>1. 기억에 남는 추억의 장소는?</option>
            <option>2. 자신의 인생 좌우명은?</option>
            <option>3. 자신의 보물 제1호는?</option>
            <option>4. 가장 기억에 남는 선생님 성함은?</option>
            <option>5. 타인이 모르는 자신만의 신체비밀이 있다면?</option>
            <option>6. 추억하고 싶은 날짜가 있다면?</option>
            <option>7. 받았던 선물 중 기억에 남는 독특한 선물은?</option>
					</select></td></tr>
					<tr><td>답변</td><td><input type="text" name="sanswer" required="required" placeholder="위 질문의 답변" style="width: 300px; height: 20px; text-align: center;"></td></tr>
					<tr><td colspan="2"><input type="submit" value="비밀번호" class="find"></td></tr>
				</table>
			</form>
	</div>
	</div>
	
<!-- <div id="find">
<h1>비밀번호 찾기</h1>
	<form action="findPwdPro.do">
		<table>
			<tr><td><input type="email" name="semail" required="required" placeholder="이메일"></td></tr>
			<tr><td><input type="text" name="sename" required="required" placeholder="이름"></td></tr>
			<tr><td><select name="squestion">
				<option>출신 고등학교는?</option>
				<option>현재 사는 지역은?</option>
				<option>어떻게 오셨나요?</option>
				<option>성별은?</option>
			</select></td></tr>
			<tr><td><input type="text" name="sanswer" required="required" placeholder="위 질문의 답변"></td></tr>
			<tr><td><input type="submit" value="비밀번호" class="find"></td></tr>
		</table>
	</form>
</div> -->

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