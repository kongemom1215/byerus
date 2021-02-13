<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 반응형 웹 -->
<meta name="viewport" content="width=device-width, initial-scale=1.0"> 
<!-- CSS 파일 -->
<link rel="stylesheet" type="text/css" href="css/YoungCSS.css?ver=1">
<!--  -->
<meta charset="UTF-8">
<!--  -->
<title>Insert title here</title>
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

<div class="main">
<div class="search_box1">
<div class="search_box2">
무엇을 찾으시나요? (상품코드, 상품명, 초성 등)
</div>
</div>
<div class="search_box1_1">
<div class="search_box2">
<form action="search.do">
<img class="search_side" src="./img/S_side.png">
<input name="search_text" type="text" class="search_text">
<input type="submit" class="seacrh_button" value="">
</form>
</div>
</div>
</div>

<div class="main" style="margin-top: 50px;">
검색결과 : ${page_full_num } 개
<div style="float: right;">
<script type="text/javascript">
function sub() {
	document.frm.submit();
}
</script>
<form name="frm" action="search.do">
<select name="shopping_select" onchange="sub()">
    <option selected="selected">정렬기준</option>
    <option value="sell_hit" ${selected1}>인기상품순</option>
    <option value="pregdate" ${selected2}>최신순</option>
    <option value="price_high" ${selected3}>높은가격순</option>
    <option value="price_low" ${selected4}>낮은가격순</option>
</select>
<input type="hidden" name="search_text" value="${search_text }">
</form>
</div>
</div>
<hr style="width: 900px; border-bottom: 0xp;">
<div class="main" style="text-align: center;">
	<table style="text-align: center;">
		<tr>
			<c:forEach var="main_img" items="${main_img }" begin="${(16*select_page) }" end="${3 + (16*select_page) }">
			<td><a href="productDetail.do?pid=${main_img.pid }">
			<img src="${main_img.pthumbimg}" class="search_img">
			<br> <c:if test="${fn:length(main_img.pname)>15}">${fn:substring(main_img.pname,0,15) } ...</c:if><c:if test="${fn:length(main_img.pname)<=15}">${main_img.pname }</c:if> <c:if test="${main_img.pdiscount != 0 }"><br>${main_img.pdiscount}% 할인</c:if>
			<br> <fmt:formatNumber value="${main_img.pprice }" pattern="#,###" /> 원 <p> </a> </td>
			</c:forEach>
		</tr>
		<tr>
			<c:forEach var="main_img" items="${main_img }" begin="${4 + (16*select_page) }" end="${7 + (16*select_page) }">
			<td><a href="productDetail.do?pid=${main_img.pid }">
			<img src="${main_img.pthumbimg}" class="search_img">
			<br> <c:if test="${fn:length(main_img.pname)>15}">${fn:substring(main_img.pname,0,15) } ...</c:if><c:if test="${fn:length(main_img.pname)<=15}">${main_img.pname }</c:if> <c:if test="${main_img.pdiscount != 0 }"><br>${main_img.pdiscount}% 할인</c:if>
			<br> <fmt:formatNumber value="${main_img.pprice }" pattern="#,###" /> 원 <p> </a> </td>
			</c:forEach>
		</tr>
		<tr>
			<c:forEach var="main_img" items="${main_img }" begin="${8 + (16*select_page) }" end="${11 + (16*select_page) }">
			<td><a href="productDetail.do?pid=${main_img.pid }">
			<img src="${main_img.pthumbimg}" class="search_img">
			<br> <c:if test="${fn:length(main_img.pname)>15}">${fn:substring(main_img.pname,0,15) } ...</c:if><c:if test="${fn:length(main_img.pname)<=15}">${main_img.pname }</c:if> <c:if test="${main_img.pdiscount != 0 }"><br>${main_img.pdiscount}% 할인</c:if>
			<br> <fmt:formatNumber value="${main_img.pprice }" pattern="#,###" /> 원 <p> </a> </td>
			</c:forEach>
		</tr>
		<tr>
			<c:forEach var="main_img" items="${main_img }" begin="${12 + (16*select_page) }" end="${15 + (16*select_page)  }">
			<td><a href="productDetail.do?pid=${main_img.pid }">
			<img src="${main_img.pthumbimg}" class="search_img">
			<br> <c:if test="${fn:length(main_img.pname)>15}">${fn:substring(main_img.pname,0,15) } ...</c:if><c:if test="${fn:length(main_img.pname)<=15}">${main_img.pname }</c:if> <c:if test="${main_img.pdiscount != 0 }"><br>${main_img.pdiscount}% 할인</c:if>
			<br> <fmt:formatNumber value="${main_img.pprice }" pattern="#,###" /> 원 <p> </a> </td>
			</c:forEach>
		</tr>
	</table>
	<c:if test="${startPage > blockSize }">
	
						<a href='search.do?pageNum=${startPage-blockSize }&page=${i}&search_text=${search_text }&shopping_select=${shopping_select }'>[이전]</a>

					</c:if>
					<c:forEach var="i" begin="${startPage }" end="${endPage }">
						<a
							href='search.do?pageNum=${i}&display_select=${display_select }&cate=${cate}&page=${i}&search_text=${search_text }&shopping_select=${shopping_select }'>[${i }]
						</a>
					</c:forEach>
					<c:if test="${endPage < pageCnt }">
						<a href='search.do?pageNum=${startPage+blockSize }&page=${i}&search_text=${search_text }&shopping_select=${shopping_select }'>[다음]</a>
					</c:if>
</div>
<div style="height: 170px;">
</div>
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
</body>
</html>