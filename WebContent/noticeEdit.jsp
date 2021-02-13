<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bye-rus!</title>
<link rel="stylesheet" type="text/css" href="css/notice.css?ver=107"/>
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
	td:first-child{
		width:150px;
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

function removeFile(){
	var v=document.getElementById("nfile");
	
	v.value="";
}
function changeFile(change){
	var v=document.getElementById("nfile");
	
	v.value="./noticefile/"+change.substr(12);
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
					<form action="noticeEditPro.do?nid=${nid }&pageNum=${pageNum}" method="post" enctype="Multipart/form-data">
						<input type="hidden" name="nhit" value="${notice.nhit }">
					<table>
						<tr>
							<td>SUBJECT</td>
							<td colspan="3"><input type="text" name="ntitle" value="${notice.ntitle }" required="required"></td>
						</tr>
						<tr>
							<td>NAME</td>
							<td colspan="3">BYE-RUS</td>
						</tr>
						<tr>
							<td colspan="4" style="text-align:center">
								<textarea rows="15" cols="70" name="ncontent" required="required">
									${notice.ncontent }
								</textarea>
							</td>
						</tr>
						<tr>
							<td>공개 여부</td>
							<td colspan="3">
							<c:if test="${notice.npublic eq 0 }">
										<input type="radio" name="npublic" value="1"> 공개
										<input type="radio" name="npublic" value="0" checked="checked"> 비공개
									</c:if>
								<c:if test="${notice.npublic eq 1 }">
									<input type="radio" name="npublic" value="1" checked="checked"> 공개
									<input type="radio" name="npublic" value="0"> 비공개</c:if>
							</td>
						</tr>
						<tr>
							<td>파일</td>
							<td><input type="file" name="nfileE" onchange="changeFile(this.value)"><br>
								<input type="text" name="nfile" id="nfile" value="${notice.nfile}" size="15" readonly="readonly"> 
								<input type="button" value="삭제" onclick="removeFile()"></td>
						</tr>
					</table>
						<input type="submit" value="수정">
					</form>
				</div>
				</div>
				</div>
	</div>
</body>
</html>