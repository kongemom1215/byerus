<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<!-- 네이버 차트  -->
<script src="https://d3js.org/d3.v5.min.js"></script>
<script src="js/billboard.js"></script>
<link rel="stylesheet" href="css/billboard.css">
<!-- 반응형 웹 -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- CSS 파일 -->
<link rel="stylesheet" type="text/css" href="css/YoungCSS.css?ver=1">
<!--  -->
<meta charset="UTF-8">
<!--  -->
<title>Bye-rus</title>
</head>

<body onload="init();">

	<script type="text/javascript">
	
	function getCookie(name) { 
		var cookie = document.cookie; 
		if (document.cookie != "") { 
			var cookie_array = cookie.split("; "); 
			for ( var index in cookie_array) { 
				var cookie_name = cookie_array[index].split("="); 
				if (cookie_name[0] == "popupYN") { 
					return cookie_name[1]; 
					} 
				} 
			} 
		return ; 
		}
	
		function init() {
			var cookieCheck = getCookie("popupYN"); 
			if (cookieCheck != "N") {
			window.open("popup.jsp", "noticepopup", "width=300, height=400");
			}
		}
</script>

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
			<div class="nav_button">
				<a href="aboutUs.do">ABOUT US</a>
			</div>
			<div class="nav_button">
				<a href="shoppingMain.do">SHOPPING</a>
			</div>
			<div class="nav_button">
				<div class="nav_button"><a href="board.do?type=notice">BOARD</a></div>
			</div>
		</div>
	</div>
	<hr style="width: 900px; margin-bottom: 0px; border-bottom: 0px;">

	<div class="main">
		<div class="search_box1">
			<div class="search_box2">무엇을 찾으시나요? (상품코드, 상품명, 초성 등)</div>
		</div>
		<div class="search_box1_1">
			<div class="search_box2">
				<form action="search.do">
					<img class="search_side" src="./img/S_side.png"> <input
						name="search_text" type="text" class="search_text"> <input
						type="submit" class="seacrh_button" value="">
				</form>
			</div>
		</div>
	</div>
	<hr style="width: 900px; margin-top: 0px;">
	<div class="main">Bye-rus 인기 상품</div>
	<hr style="width: 900px;">
	<div class="main">
		<div class="slide">
			<ul>
				<c:forEach var="main_img" items="${main_img }">
					<li style="text-align: center;"><a
						href="productDetail.do?pid=${main_img.pid }"><img
							src="${main_img.pthumbimg}" class="main_img"><br>
							${main_img.pname }</a></li>
				</c:forEach>
			</ul>
		</div>
	</div>
	<hr style="width: 900px;">
	<div class="main">코로나 LIVE</div>
	<hr style="width: 900px;">
	<div class="main">
		<div
			style="width: 450px; height: 100px; float: left; margin-top: 100px;">
			<table style="width: 100%; text-align: center;">
				<tr>
					<td>확진환자</td>
					<td>검사중</td>
					<td>격리해제</td>
					<td>사망자</td>
				</tr>
				<tr>
					<td><fmt:formatNumber value="${confirmed }" pattern="#,###" /></td>
					<td><fmt:formatNumber value="${check }" pattern="#,###" /></td>
					<td><fmt:formatNumber value="${clear }" pattern="#,###" /></td>
					<td><fmt:formatNumber value="${death }" pattern="#,###" /></td>
				</tr>
				<tr>
					<td><c:if test="${confirmed_oneday > 0 }">
		△ <fmt:formatNumber value="${confirmed_oneday }" pattern="#,###" />
						</c:if> <c:if test="${confirmed_oneday == 0 }">
		- <fmt:formatNumber value="${confirmed_oneday }" pattern="#,###" />
						</c:if> <c:if test="${confirmed_oneday < 0 }">
		▽ <fmt:formatNumber value="${confirmed_oneday }" pattern="#,###" />
						</c:if></td>
					<td><c:if test="${check_oneday > 0 }">
		△ <fmt:formatNumber value="${check_oneday }" pattern="#,###" />
						</c:if> <c:if test="${check_oneday == 0 }">
		- <fmt:formatNumber value="${check_oneday }" pattern="#,###" />
						</c:if> <c:if test="${check_oneday < 0 }">
		▽ <fmt:formatNumber value="${check_oneday }" pattern="#,###" />
						</c:if></td>
					<td><c:if test="${clear_oneday > 0 }">
		△ <fmt:formatNumber value="${clear_oneday }" pattern="#,###" />
						</c:if> <c:if test="${clear_oneday == 0 }">
		- <fmt:formatNumber value="${clear_oneday }" pattern="#,###" />
						</c:if> <c:if test="${clear_oneday < 0 }">
		▽ <fmt:formatNumber value="${clear_oneday }" pattern="#,###" />
						</c:if></td>
					<td><c:if test="${death_oneday > 0 }">
		△ <fmt:formatNumber value="${death_oneday }" pattern="#,###" />
						</c:if> <c:if test="${death_oneday == 0 }">
		- <fmt:formatNumber value="${death_oneday }" pattern="#,###" />
						</c:if> <c:if test="${death_oneday < 0 }">
		▽ <fmt:formatNumber value="${death_oneday }" pattern="#,###" />
						</c:if></td>
				</tr>
				<tr>
					<td colspan="4"><br> 업데이트 시간<br> ${day }</td>
				</tr>
			</table>
		</div>
		<div style="width: 450px; float: left;">
			<div id="xAxisTickCount"></div>
			<script type="text/javascript">
var chart = bb.generate({
	  data: {
	    x: "x",
	    columns: [
		["x", 	"${date_first_time_format2}", 
				"${calendar_1_7_format2}", 
				"${calendar_2_7_format2}", 
				"${calendar_3_7_format2}", 
				"${calendar_4_7_format2}", 
				"${calendar_5_7_format2}", 
				"${calendar_6_7_format2}", 
				"${now_time_format2}"], 
		["확진자", 	${confirmed_date_first_time},
					${confirmed_covid_calendar_1_7}, 
					${confirmed_covid_calendar_2_7}, 
					${confirmed_covid_calendar_3_7},
					${confirmed_covid_calendar_4_7}, 
					${confirmed_covid_calendar_5_7}, 
					${confirmed_covid_calendar_6_7},
					${confirmed}],
		["격리해제", 	${clear_date_first_time},
						${clear_covid_calendar_1_7}, 
						${clear_covid_calendar_2_7}, 
						${clear_covid_calendar_3_7}, 
						${clear_covid_calendar_4_7},
						${clear_covid_calendar_5_7}, 
						${clear_covid_calendar_6_7}, 
						${clear}],
	    ],
	    type: "spline", // for ESM specify as: line()
	  },
	  axis: {
	    x: {
	      type: "timeseries",
	      tick: {
	        count: 8,
	        format: "%Y-%m-%d"
	      }
	    }
	  },
	  bindto: "#xAxisTickCount"
	});
</script>
		</div>
	</div>


	<div style="margin-top: 200px;">
		<div class="main"
			style="height: 50px; background-color: #767171; display: table;">
			<div style="width: 10px;"></div>
			<div
				style="display: table-cell; vertical-align: middle; margin-left: 5px;">
				대표 : 임주혜 / 사업자등록번호 : 123-45-67899</div>
			<div
				style="display: table-cell; vertical-align: middle; text-align: right;">
				<a>이용약관</a> / <a>개인정보처리방침</a> / <a>입점문의</a>
			</div>
			<div style="width: 10px;"></div>
		</div>
	</div>
</body>
</html>