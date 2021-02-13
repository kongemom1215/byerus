<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/mypageDeleteForm.css?ver=1">
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/YoungCSS.css?ver=1">
</head>
<body>
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
<div class="main">
	<div class="down">
	<div class="mypagemainside">
	<div class="mypageside">
		<br>
		<a href="mypageOrder.do?sid=${shoppinguser.sid }" style="text-decoration: none; color: black;">
		<span>주문조회</span>
		<span style="float:right;">></span>
		</a><p>
		<a href="mypageCoupon.do?sid=${shoppinguser.sid }" style="text-decoration: none; color: black;">
		<span>쿠폰</span>
		<span style="float:right;">></span>
		</a><p>
		<a href="mypageReview.do" style="text-decoration: none; color: black;">
		<span>리뷰/문의</span>
		<span style="float:right;">></span>
		</a><p>
		<a href="mypageEditForm.do?sid=${shoppinguser.sid }" style="text-decoration: none; color: black;">
		<span>회원정보수정</span>
		<span style="float:right;">></span>
		</a><p>
		<a href="mypageDeleteForm.do?sid=${shoppinguser.sid }" style="text-decoration: none; color: black;">
		<span>회원탈퇴</span>
		<span style="float:right;">></span>
		</a><p>
	</div>
	</div>
<div class="userdelete">
		<form action="mypageDelete.do" method="post">
		<input type="hidden" name="sid" value="${sid }">
	<table>
			<caption><h2>회원 탈퇴</h2></caption>
			<tr><td style="text-align: center; margin-top:5px;">비밀번호</td><td>></td><td><input type="text" name="spwd" placeholder="비밀번호를 입력해주세요." class="input" required="required"></td></tr>
			<tr><td></td><td></td><td><input type="submit" value="확인" class="button">
					<input type="reset" value="입력취소" class="button"></td></tr>
	</table>
	</form>
</div>
</div>
</div>
</body>
</html>