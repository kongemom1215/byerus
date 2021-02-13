<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bye-rus!</title>
<link rel="stylesheet" type="text/css" href="css/productedit.css?ver=101">
<link rel="stylesheet" type="text/css" href="css/YoungCSS.css?ver=1">
<style type="text/css">
a{
	color:black;
	text-decoration:none;
}

a:visited {
	color:black;
}

a:link{
	color:black;
}
table{
	table-layout:fixed;
	width:700px;
}
td{
	white-space: nowrap;
}
</style>
<script type="text/javascript">
function chk(){
	if(frm.option.value=="none" || frm.option.value==""){
		alert("검색 옵션을 선택해 주십시오");
		return false;
	}
	if(!frm.search_value.value){
		alert("검색어를 입력해주세요.");
		return false;
	}
}
	function removeThumb(){
		var v=document.getElementById("pthumbimg");
		
		v.value="";
	}
	function changeThumb(change){
		var v=document.getElementById("pthumbimg");
		
		v.value="./pdimg/"+change.substr(12);
	}
	
	function removeCol1(){
		var v=document.getElementById("col1");
		
		v.value="";
		
	}
	function changeCol1(change){
		var v=document.getElementById("col1");
		
		v.value="./pdimg/"+change.substr(12);
	}
	
	function removeCol2(){
		var v=document.getElementById("col2");
		
		v.value="";
		
	}
	function changeCol2(change){
		var v=document.getElementById("col2");
		
		v.value="./pdimg/"+change.substr(12);
	}
	function removeCol3(){
		var v=document.getElementById("col3");
		
		v.value="";
		
	}
	function changeCol3(change){
		var v=document.getElementById("col3");
		
		v.value="./pdimg/"+change.substr(12);
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
	<div class="main" style="width:1200px">
		<div id="sidebar">
			<img src="./img/admin_product.JPG" id="img1">
			<h2 class="productMenu">상품 관리</h2>
			<div class="productMenuButton">
				<form>
					<input type="button" value="● 상품 검색" class="button" style="color:#00B9FF; font-weight:bold;" onclick="location.href='productAdminPage.do'" ></input><br>
					<input type="button" value="● 상품 추가" class="button" onclick="location.href='productInsert.do'"></input>
				</form>
			</div>
		</div>
		<div id="content">
			<div id="search">
				<div id="select">
					<form name="frm" action="productSearch.do" onsubmit="return chk()">
						<select name="option" class="option">
							<option value="none">==선택==</option>
							<option value="pid">상품번호</option>
							<option value="pname">상품이름</option>
						</select>
						<input type="text" name="search_value" size="50px" placeholder="검색어를 입력해주세요.">
						<input type="submit" value="검색">
					</form>
				</div>
			</div>
			<br>
			<div id="search_content">
				<div id="search_content2">
					<div id="search_table">
						<form action="productEditPro.do?pid=${pid }&pageNum=${pageNum}&option=${option }&search_value=${search_value }" method="post" enctype="Multipart/form-data">
						<table>
							<tr>
								<td rowspan="3" colspan="2"><img src="${product.pthumbimg }" id="img2"></td>
								<td>상품번호</td>
								<td>${product.pid }</td>
							</tr>
							<tr>
								<td>상품 이름</td>
								<td><input type="text" name="pname" value="${product.pname }" size="15" required="required"></td>
							</tr>
							<tr>
								<td>상품종류</td>
								<td><select name="ptype">
										<option value="sodok" <c:if test="${product.ptype eq 'sodok'}">selected="selected"</c:if>>소독</option>
										<option value="mask" <c:if test="${product.ptype eq 'mask'}">selected="selected"</c:if>>마스크</option>
										<option value="alco" <c:if test="${product.ptype eq 'alco'}">selected="selected"</c:if>>알코올</option>
										<option value="sejung" <c:if test="${product.ptype eq 'sejung'}">selected="selected"</c:if>>세정</option>
										<option value="bangdok" <c:if test="${product.ptype eq 'bangdok'}">selected="selected"</c:if>>방독</option>
										<option value="cheon" <c:if test="${product.ptype eq 'cheon'}">selected="selected"</c:if>>체온</option>
										<option value="home" <c:if test="${product.ptype eq 'home'}">selected="selected"</c:if>>재택용품</option>
										<option value="etc" <c:if test="${product.ptype eq 'etc'}">selected="selected"</c:if>>기타</option>
									</select>
								</td>
							</tr>
							<tr>
								<td>가격</td>
								<td colspan="3"><input type="text" name="pprice" value="${product.pprice }" required="required" size="8">원</td>
							</tr>
							<tr>
								<td>재고</td>
								<td><input type="text" name="pinventory" value="${product.pinventory }" required="required" size="5">개</td>
								<td>판매량</td>
								<td>${product.psell }개</td>
							</tr>
							<tr>
								<td>할인율</td>
								<td><input type="text" name="pdiscount" value="${product.pdiscount }" required="required"size="4">%</td>
								<td>옵션</td>
								<td><input type="text" name="poption" value="${product.poption }" placeholder=",로 나눠서 입력하세요" size="15"></td>
							</tr>
							<tr>
								<td>썸네일</td>
								<td style="white-space:pre-line"><input type="file" name="pthumbimgE" onchange="changeThumb(this.value)">
								<input type="text" name="pthumbimg" id="pthumbimg" value="${product.pthumbimg }" size="15" readonly="readonly"> 
								<input type="button" value="삭제" onclick="removeThumb()">
								</td>
								<td>상세이미지1</td>
								<td style="white-space:pre-line"><input type="file" name="col1E" onchange="changeCol1(this.value)">
									<input type="text" name="col1" id="col1" value="${product.col1 }" size="15" readonly="readonly"> 
									<input type="button" value="삭제" onclick="removeCol1()"></td>
							</tr>
							<tr>
								<td>상세이미지2</td>
								<td style="white-space:pre-line"><input type="file" name="col2E" onchange="changeCol2(this.value)">
									<input type="text" name="col2" id="col2" value="${product.col2 }" size="15" readonly="readonly"> 
									<input type="button" value="삭제" onclick="removeCol2()"></td>
								<td>상세이미지3</td>
								<td style="white-space:pre-line"><input type="file" name="col3E" onchange="changeCol3(this.value)">
									<input type="text" name="col3" id="col3" value="${product.col3 }" size="15" readonly="readonly"> 
									<input type="button" value="삭제" onclick="removeCol3()"></td>
							</tr>
							<tr>
								<td>공개여부</td>
								<td>
									<c:if test="${product.ppublic eq 0 }">
										<input type="radio" name="ppublic" value="1"> 공개
										<input type="radio" name="ppublic" value="0" checked="checked"> 비공개
									</c:if>
								<c:if test="${product.ppublic eq 1 }">
									<input type="radio" name="ppublic" value="1" checked="checked"> 공개
									<input type="radio" name="ppublic" value="0"> 비공개</c:if>
								</td>
								<td>상품등록일</td>
								<td>${product.pregdate }</td>
							</tr>
						</table>
						<br>
							<input type="submit" class="button2" value="수정">
							<input type="button" value="뒤로가기" class="button2" onclick="location.href='productInfo.do?pid=${pid}&pageNum=${pageNum }&option=${option }&search_value=${search_value }'">
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>