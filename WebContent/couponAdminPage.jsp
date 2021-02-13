<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bye-rus!</title>
<link rel="stylesheet" type="text/css" href="css/memberadmin.css?ver=145"/>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript">
	$(document).ready(function(){
		$("#pushSid").hide();
		$("input[name=usertype]").change(function(){
			var radio_v=$(':radio[name="usertype"]:checked').val();
			if(radio_v=="all")
				$("#pushSid").hide();
			else if(radio_v=="part")
				$("#pushSid").show();
		});
	});
</script>
<style type="text/css">
table{
	display: inline-block;
	table-layout: fixed;
	text-align:left;
}
td, th{
	padding:10px;
	background-color: white;
}
</style>
<link rel="stylesheet" type="text/css" href="css/YoungCSS.css?ver=1">
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
	<div class="main" style="width:1000px">
		<div id="sidebar">
			<img src="./img/admin_member.JPG" id="img1">
			<h2 class="memberMenu">회원 관리</h2>
			<div class="memberMenuButton">
				<form>
					<input type="button" value="● 회원 정보 조회" class="button" onclick="location.href='memberAdminPage.do'" ></input><br>
					<input type="button" value="● 회원 추가" class="button" onclick="location.href='memberInsert.do'"></input><br>
					<input type="button" value="● 쿠폰 등록" class="button" style="color:#00B9FF; font-weight:bold;" onclick="location.href='couponAdminPage.do'"></input>
				</form>
			</div>
		</div>
		<div id="couponContent">
			<div id="couponContent2">
				<form action="couponInsert.do" method="post" enctype="Multipart/form-data" autocomplete="off">
				<table id="coupontable">
					<tr>
						<td>회원 종류 : </td>
						<td><input type="radio" name="usertype" id="all" value="all" required><label for='all'>전체 회원</label>
							<input type="radio" name="usertype" id="part" value="part"><label for='part'>특정 회원</label></td>
					</tr>
					<tr id="pushSid">
						<td>회원 번호 : </td>
						<td><input type="text" name="sids" placeholder=",로 회원번호를 구분해주세요."></td>
					</tr>
					<tr>
						<td>쿠폰 할인율 : </td>
						<td><input type="number" name="cdiscount" min="1" max="100" maxlength="3" required="required">%</td>
					</tr>
					<tr>
						<td>날짜 : </td>
						<td><input type="text" name="cstartdate" id="cstartdate" placeholder="시작일자" required="required" size="8">
							<script>
								$(function () {
									$("#cstartdate").datepicker();
								});
								$.datepicker.setDefaults({
								    dateFormat: 'yy-mm-dd' //Input Display Format 변경
								});
							</script>
							~ <input type="text" name="cenddate" id="cenddate" placeholder="만료일자" required="required" size="8">
							<script>
								$(function () {
									$("#cenddate").datepicker();
								});
							</script>
							</td>
					</tr>
					<tr>
						<td>쿠폰 이미지 : </td>
						<td><input type="file" name="couponimg" required="required">
					</tr>
					<tr>
						<td colspan="2" style="text-align:right;"><input type="submit" class="button2" value="쿠폰 등록"></td>
					</tr>
				</table>
				</form>
			</div>
		</div>
	</div>
</body>
</html>