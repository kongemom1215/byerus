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

		table{width: 35%;
		  height: 100px;
		  margin: auto;
		  font-weight: bolder;
		  }
		  
	#login{text-align: center; width: 300px; overflow: hidden; position: absolute; right: 50%; top: 20%;}
	#login2{text-align: center; width: 300px; overflow: hidden; position: absolute; right: 15%; top: 35%;}
	
	.semail{width: auto; height: auto;	background-color: #B9FFFF; border: 0px; font-size: 30px; outline: none;}
	.spwd{width: auto; height: auto;	background-color: #B9FFFF; border: 0px; font-size: 30px; outline: none;}
	.join{background-color: #00EBFF; display: inline-block; outline: none; cursor: pointer; text-align: center;
	text-decoration: none; padding: .5em 2em .55em; text-shadow: 0 1px 1px rgba(0,0,0,.3); color:white;
	border-radius: .5em; box-shadow: 0 1px 2px rgba(0,0,0,.2); border: 0;}
	.join:hover{background-color: #3CCEFF;}
	
	.Login{background-color: #00EBFF; display: inline-block; outline: none; cursor: pointer; text-align: center;
	text-decoration: none; padding: .5em 2em .55em; text-shadow: 0 1px 1px rgba(0,0,0,.3); color:white; width:295px;
	border-radius: .5em; box-shadow: 0 1px 2px rgba(0,0,0,.2); border: 0;}
	.Login:hover{background-color: #3CCEFF}
	
	
	::placeholder{
	text-align:left;
	font-size: 0.5em;	
	font-weight: 100;
	}
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
<a href="login.do?url=${url }" class="top_button">로그인/회원가입</a>
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
	
	<div class="main" style="text-align: center;">
	<br><br><br>
	<h1>LOG IN</h1>
	<h6>등록하신 이메일과 비밀번호를 입력해주세요.<p>
	비밀번호는 대소문자를 구분합니다.</h6>
	<form action="loginPro.do">
		<table>
			<tr><td><input type="email" name="semail" class="semail" required="required"
			placeholder="이메일"></td></tr>
			
			<tr><td><input type="password" name="spwd" class="spwd" required="required"
			placeholder="비밀번호"></td></tr>
		</table>
		<input type="hidden" name="url" value="${url }">
		<input type="hidden" name="pid" value="${pid }">
		<input type="hidden" name="type" value="${type }">
		<input type="submit" value="로그인" class="Login">
	</form>
	<p>
	
	<form action="findemail.do">
		<input type="submit" value="아이디찾기" style="background-color: white; border: none; cursor: pointer;">
	</form>
	<p>
	<form action="findpwd.do">
		<input type="submit" value="비밀번호찾기" style="background-color: white; border: none; cursor: pointer;">
	</form>
	</div>
	
	<div class="main" style="text-align: center;">
	<h5>아직 회원이 아니신가요?<p>
	지금 가입하고 다양한 혜택을 누려보세요!</h5>
		<form action="join.do">
			<input type="submit" value="회원가입" class="join">
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