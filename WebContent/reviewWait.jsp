<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bye-rus!</title>
<link rel="stylesheet" type="text/css" href="css/notice.css?ver=112"/>
<link rel="stylesheet" type="text/css" href="css/YoungCSS.css?ver=1">
<style type="text/css">
	table{
		width:600px;
		background-color: white;
		border-top: 3px solid #d2d2d2;
		border-collapse: collapse;
	}
	th, td{
		border-bottom : 3px solid #d2d2d2;
		padding:10px;
	}
	td:nth-child(1), th:nth-child(1) {
		width:300px;
	}
	a{
		color:black;
		text-decoration: none;
	}
	a:visited {
		color:black;
	}

	a:link{
		color:black;
	}
</style>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
function displaySearch(){
	$(".search").css("display","");
}
function chk(){
	if(!isNaN(frm.product.value))
		return true;
	else{
		alert("숫자로 입력해주세요.");
		return false;
	}
}
</script>
</head>
<body>
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
		<img src="./img/admin_board.JPG" id="img1">
		<h2 class="boardMenu">게시판 관리</h2>
		<div class="boardMenuButton">
			<form>
				<input type="button" value="● 공지 게시판" class="button" onclick="location.href='noticeAdminPage.do'"></input><br>
				<input type="button" value="● 문의 게시판" class="button" onclick="location.href='qnaAdminPage.do'"></input><br>
				<input type="button" value="● 리뷰 게시판" class="button" onclick="location.href='reviewAdminPage.do'" style="color:#00B9FF; font-weight:bold;"></input>
			</form>
		</div>
	</div>
	<div id="content">
		<div id="content2">
			<div id="content3">
				<h2 style=" text-align: center;">REVIEW</h2>
				<table style="text-align:center;">
					<tr>
						<td style="width:160px;"><input type="button" value="전체 리뷰 보기" onclick="location.href='reviewAdminPage.do'"></td>
						<td><input type="button" value="미답변 리뷰 보기" onclick="location.href='reviewWaitPage.do'"></td>
						<td><input type="button" value="상품별로 보기" onclick="displaySearch()"></td>
					</tr>
					<tr>
						<td class="search" style="text-align=center; display:none;" colspan="3">
							<form action="reviewSearch.do" name="frm" onsubmit="return chk()">
							<input type="text" name="pid" placeholder="상품번호로 검색" required="required">
							<input type="submit" value="검색">
							</form>
						</td>
					</tr>
					</table>
					<table>
						<tr>
							<th>SUBJECT</th>
							<th>NAME</th>
							<th>DATE</th>
							<th>VIEW</th>
						</tr>
						<c:forEach var="wait" items="${waitlist }">
							<tr>
								<td><c:if test="${not empty wait.rcmt }">
									[답변완료]</c:if>
									<a href="reviewInfo.do?rid=${wait.rid }&rctg=wait&pageNum=${pageNum}">${wait.rtitle }</a></td>
								<td>${wait.rwriter }</td>
								<td><fmt:formatDate value="${wait.rdate }" pattern="yyyy-MM-dd"/></td>
								<td>${wait.rhit }</td>
							</tr>
						</c:forEach>
					</table>
					<div style="text-align:center">
						<c:if test="${startPage>blockSize }">
							<a href="reviewWaitPage.do?pageNum=${startPage-blockSize }">[이전]</a>
						</c:if>
						<c:forEach var="i" begin="${startPage }" end="${endPage}">
							<a href="reviewWaitPage.do?pageNum=${i }">[${i }]</a>
						</c:forEach>
						<c:if test="${endPage< pageCnt}">
							<a href="reviewWaitPage.do?pageNum=${startPage+blockPage }">[다음]</a>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>