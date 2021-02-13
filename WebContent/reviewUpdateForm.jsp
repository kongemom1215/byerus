<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bye-rus!</title>
<link rel="stylesheet" type="text/css" href="css/YoungCSS.css?ver=1">
<link rel="stylesheet" type="text/css" href="css/update.css?ver=4">
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
         <div class="nav_button">
            <a href="aboutUs.do">ABOUT US</a>
         </div>
         <div class="nav_button">
            <a href="shoppingMain.do">SHOPPING</a>
         </div>
         <div class="nav_button">
            <a href="board.do">BOARD</a>
         </div>
      </div>
   </div>
   <hr style="width: 900px; margin-bottom: 0px; border-bottom: 0px;">





<form action="reviewUpdatePro.do?sid=${sid }&rid=${rid}&rimg=${rimg}" method="post" enctype="multipart/form-data">
	<input type="hidden" name="rimg" value="${review.rimg }">
	<input type="hidden" name="rid" value="${review.rid}">
	<input type="hidden" name="sid" value="${review.sid }">
	<input type="hidden" name="pageNum" value="${pageNum }">
	<table>
		<caption><h2>게시판 수정</h2></caption>
		<tr><td class="update">유저번호</td><td>${review.sid }</td></tr>
		<tr><td class="update">제목</td><td><input type="text" name="rtitle" required="required" placeholder="제목을 입력해주세요."></td></tr>
		<tr><td class="update">작성자</td><td>${rwriter } </td></tr>
		<tr><td class="update">파일</td><td><input type="file" name="rimg"></td></tr>
		<tr><td class="update">내용</td><td><pre><textarea rows="10" cols="40" name="rcontent"
		required="required">${review.rcontent }</textarea></pre></td></tr>
		<tr><td colspan="2"><input type="submit" value="수정완료">
	</table>
</form>



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