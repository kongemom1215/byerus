<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bye-rus!</title>
<link rel="stylesheet" type="text/css" href="css/qna.css?ver=113"/>
<link rel="stylesheet" type="text/css" href="css/YoungCSS.css?ver=1">
<style type="text/css">
	table{
		width:600px;
		background-color: white;
		border: 1px solid black;
		border-collapse: collapse;
	}
	th, td{
		border: 1px solid black;
		padding:10px;
	}
	a{
		color:black;
	}
	a:visited {
		color:black;
	}

	a:link{
		color:black;
	}
	#select{
		text-align: center;
	}
</style>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	function displayCMT(){
		$(".cmt1").css("display","");
		$(".cmt2").css("display","none");
		$(".bt1").css("display","none");
		$(".bt2").css("display","none");
		$(".bt3").css("display","");
		$(".bt4").css("display","");
	}
	function displayCMT2(){
		$(".cmt1").css("display","none");
		$(".cmt2").css("display","");
		$(".bt1").css("display","");
		$(".bt2").css("display","");
		$(".bt3").css("display","none");
		$(".bt4").css("display","none");
	}
	function delete_confirm(){
		var delete_confirm=confirm("삭제하시겠습니까?");
		
		if(delete_confirm==true){
			location.href="reviewCMTDeletePro.do?rid=${rid}&pageNum=${pageNum}";
		}
		else{
			location.href="reviewCMTDeletePro.do?rid=${rid}&pageNum=${pageNum}";
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
					<input type="button" value="● 문의 게시판" class="button" onclick="location.href='qnaAdminPage.do'" ></input><br>
					<input type="button" value="● 리뷰 게시판" class="button" onclick="location.href='reviewAdminPage.do'" style="color:#00B9FF; font-weight:bold;"></input>
				</form>
			</div>
		</div>
		<div id="content">
			<div id="content2">
				<div id="content3">
					<h2 style=" text-align: center;">REVIEW</h2>
					<table>
						<tr>
							<c:if test="${review.rimg ne null }">
								<td rowspan="4">
									<img src="${review.rimg }" style="width:170px; height:170px;" onclick="window.open(this, '_blank'); return false;">
								</td>
							</c:if>
							<td>주문번호</td>
							<td><fmt:formatDate pattern="yyyyMMdd" value="${review.odate }" />
							-<fmt:formatNumber value="${review.oid }" pattern="00000"></fmt:formatNumber></td>
						</tr>
						<tr>
							<td>회원번호/이름</td>
							<td>[${review.sid }] ${review.rwriter }</td>
						</tr>
						<tr>
							<td>상품명</td>
							<td><a href="productDetail.do?pid=${review.pid }">[${review.pid }] ${review.pname }</a></td>
						</tr>
						<tr>
							<td>리뷰 게시일</td>
							<td><fmt:formatDate pattern="yyyy.MM.dd HH:mm" value="${review.rdate}"/></td>
						</tr>
					</table>
					<table>
						<tr>
							<td>제목</td>
							<td>${review.rtitle }</td>
						</tr>
						<tr>
							<td>내용</td>
							<td>${review.rcontent }</td>
						</tr>
					</table>
					<br>
					<table>
						<tr>
							<td>답변란</td>
						</tr>
						<tr>
							<td>Bye-rus</td>
						</tr>
						<c:choose>
						<c:when test="${not empty review.rcmt }">
							<tr>
								<td>
								<form action="reviewCMTEdit.do?rid=${review.rid }&pageNum=${pageNum}" method="post">
								<textarea class="cmt1" name="rcmt" style="display:none" rows="5" cols="60">${review.rcmt}</textarea>
								<div class="cmt2">${review.rcmt }</div>
								<input type="button" value="삭제" class="bt1" style="float:right;" onclick="delete_confirm()">
								<input type="button" value="수정" class="bt2" onclick="displayCMT()" style="float:right;">
								<input type="button" value="취소" class="bt4" onclick="displayCMT2()" style="float:right; display:none;">
								<input type="submit" value="수정" class="bt3" style="float:right; display:none;"></form></td>
							</tr>
							<tr>
								<td style="font-size:11px;"><fmt:formatDate pattern="yyyy.MM.dd HH:mm" value="${review.rcmtdate }"/></td>
							</tr>
						</c:when>
						<c:otherwise>
							<tr>
								<td>
								<form action="reviewCMTEdit.do?rid=${review.rid }&pageNum=${pageNum}&rctg=${rctg}" method="post">
								<textarea name="rcmt" rows="5" cols="70"></textarea><br>
								<input type="submit" value="등록"></form></td>
							</tr>
						</c:otherwise>
						</c:choose>
					</table>
					<c:choose>
						<c:when test="${rctg eq 'wait' }">
							<input type="button" value="뒤로가기" onclick="location.href='reviewWaitPage.do?pageNum=${pageNum}'">
						</c:when>
						<c:when test="${rctg eq 'search'}">
							<input type="button" value="뒤로가기" onclick="location.href='reviewSearch.do?pageNum=${pageNum}&pid=${pid }'">
						</c:when>
						<c:otherwise>
							<input type="button" value="뒤로가기" onclick="location.href='reviewAdminPage.do?pageNum=${pageNum}'">
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</div>
</body>
</html>