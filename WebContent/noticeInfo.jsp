<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bye-rus!</title>
<link rel="stylesheet" type="text/css" href="css/notice.css?ver=106"/>
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
	td:nth-child(2), th:nth-child(2) {
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
<script type="text/javascript">
function delete_confirm(){
	var delete_confirm=confirm("삭제하시겠습니까?");
	
	if(delete_confirm==true){
		location.href="noticeDeletePro.do?nid=${nid}";
	}
	else{
		location.href="noticeInfo.do?nid=${nid}";
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
					<input type="button" value="● 공지 게시판" class="button" onclick="location.href='noticeAdminPage.do'" style="color:#00B9FF; font-weight:bold;" ></input><br>
					<input type="button" value="● 문의 게시판" class="button" onclick="location.href='qnaAdminPage.do'"></input><br>
					<input type="button" value="● 리뷰 게시판" class="button" onclick="location.href='reviewAdminPage.do'"></input>
				</form>
			</div>
		</div>
		<div id="content">
			<div id="content2">
				<div id="content3">
					<h2 style=" text-align: center;">NOTICE</h2>
					<table>
						<tr>
							<td>SUBJECT</td>
							<td colspan="3">${notice.ntitle }</td>
						</tr>
						<tr>
							<td>NAME</td>
							<td colspan="3">BYE-RUS</td>
						</tr>
						<tr>
							<td>DATE</td>
							<td>${notice.ndate }</td>
							<td>VIEW</td>
							<td>${notice.nhit }</td>
						</tr>
						<tr>
							<td colspan="4">
								${notice.ncontent }
							</td>
						</tr>
						<c:if test="${not empty notice.nfile }">
							<tr>
								<td>첨부 파일</td>
								<td><a class="download" href="${notice.nfile }" style="width:70px;" download>${nfile }</a></td>
							</tr>
						</c:if>
						<c:if test="${not empty nextNotice }">
						<tr>
							<td>▲ 다음글</td>
							<td colspan="3"><a href="noticeInfo.do?nid=${nid+1 }">${nextNotice }</a>
						</tr>
					</c:if>
					<c:if test="${not empty preNotice }">
						<tr>
							<td>▼ 이전글</td>
							<td colspan="3"><a href="noticeInfo.do?nid=${nid-1 }">${preNotice }</a>
						</tr>
					</c:if>
					</table>
				</div>
				<input type="button" class="button3" style="font-size:13px" value="목록" onclick="location.href='noticeAdminPage.do?pageNum=${pageNum }'">
				<input type="button" class="button2" value="글 삭제" onclick="delete_confirm()">
				<input type="button" class="button2" value="글 수정" onclick="location.href='noticeEdit.do?nid=${nid}&pageNum=${pageNum }'">
		</div>
	</div>
	</div>
</body>
</html>