<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bye-rus!</title>
<link rel="stylesheet" type="text/css" href="css/YoungCSS.css?ver=1">
<style type="text/css">
.member_button {
	position: relative;
	border: 0;
	outline: 0;
	width: 150px; height: 150px;
	background-image: url('./img/admin_member.JPG');
	background-size: contain;
}

.product_button {
	position: relative;
	border: 0;
	outline: 0;
	width: 150px; height: 150px;
	background-image: url('./img/admin_product.JPG');
	background-size: contain;
}

.board_button {
	position: relative;
	border: 0;
	outline: 0;
	width: 150px; height: 150px;
	background-image: url('./img/admin_board.JPG');
	background-size: contain;
}

.order_button {
	position: relative;
	border: 0;
	outline: 0;
	width: 150px; height: 150px;
	background-image: url('./img/admin_order.JPG');
	background-size: contain;
}
.admin_button[type="button"] {
	vertical-align:center;
	border: 0;
	outline: 0;
	float: left;
	background-color: white;
	width:200px;
}
</style>
</head>
<body>
	<!--로고 및 로그인 메뉴  -->
	<div class="main">
	<div style="margin-top: 15px;">
<a href="main.do"><img src="./img/Logo.png"></a>

<c:choose>
<c:when test="${session_stype eq '1'}">
<a class="top_button">위시리스트</a>
<a class="top_button">장바구니</a>
<a class="top_button">주문/배송</a>
<a class="top_button">마이페이지</a>
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
   <div class="main" style="width: 900px; height: 10px; display: table; vertical-align: middle; position: relative;">
      <div style="width: 900px; display: table-cell; text-align: center;">
       <input type="button" style="width: 300px; background-color: white; border: 0px;" value="ADMINISTRATOR SERVICE" onclick="location.href='adminPage.do'">
      </div>
   </div>
 <hr>
	<div class="main">
		<div>
			<table style="width:60%; height: 100px; margin: auto; text-align: center; margin-top:100px;" border="0">
				<tr>
					<td><input type="button" class="member_button" onclick="location.href='memberAdminPage.do'"> </td>
					<td><input type="button" class="product_button" onclick="location.href='productAdminPage.do'"> </td>
					<td><input type="button" class="board_button" onclick="location.href='noticeAdminPage.do'"></td>
					<td><input type="button" class="order_button" onclick="location.href='orderAdminPage.jsp'"></td>
				</tr>
				<tr>
					<td><input type="button" style="font-family: 'Jeju Gothic', sans-serif; font-size:16px;" value="회원 관리" class="admin_button" onclick="location.href='memberAdminPage.do'"> </td>
					<td><input type="button" style="font-family: 'Jeju Gothic', sans-serif; font-size:16px;" value="상품 관리" class="admin_button" onclick="location.href='productAdminPage.do'"></td>
					<td><input type="button" style="font-family: 'Jeju Gothic', sans-serif; font-size:16px;" value="게시판 관리" class="admin_button" onclick="location.href='noticeAdminPage.do'"> </td>
					<td><input type="button" style="font-family: 'Jeju Gothic', sans-serif; font-size:16px;" value="결제&배송 관리" class="admin_button" onclick="location.href='orderAdminPage.jsp'"></td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>