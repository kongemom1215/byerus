<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bye-rus!</title>
<link rel="stylesheet" type="text/css" href="css/YoungCSS.css?ver=1">
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

<div class="main" style="margin-top: 20px;">
<div style="margin-left: 20px;">
나의 찜
</div>
<div style="display: table; height: 75px; margin-left: 30px;">
<div style="display: table-cell; vertical-align: middle;">
>찜 목록에 담긴 상품은 바로 장바구니에 담으실 수 있습니다. <br>
>찜 목록에 담은 시점과 구매시점에서 상품가격 및 이벤트가 변경될 수 있으며 조기품절 될 수 있습니다.
</div>
</div>
</div>
<div class="main">
<form action="JJimGoCart.do">
<c:set var="name" value="0"></c:set>
<c:forEach var="list" items="${list }">
<c:set var="name" value="${name + 1 }"></c:set>
<table>
<tr>
	<td rowspan="4"><img src=${list.pthumbimg } class="main_img"></td>
</tr>
<tr>
	<td style="font-size: 25px; height: 100px;">${list.pname }</td>
</tr>
<tr>
	<td style="font-size: 25px; height: 10px;"><fmt:formatNumber value="${list.pprice }" pattern="#,###" /> 원</td>
</tr>
<tr>
	<td><input type="checkbox" name="wish" value="${list.pid }" style="zo">
	<input type="number" name="${name }"></td>
</tr>
</table>
</c:forEach>

<input type="submit" value="장바구니로">
</form>
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