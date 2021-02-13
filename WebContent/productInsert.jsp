<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bye-rus!</title>
<link rel="stylesheet" type="text/css" href="css/productadmin.css?ver=103">
<link rel="stylesheet" type="text/css" href="css/YoungCSS.css?ver=1">
<style type="text/css">
#content{
	margin-top:30px;
	margin-left:30px;
	width:650px;
	height:750px;
	float:left;
	position:relative;
	background-color:#d2d2d2;
}
table{
	display: inline-block;
	width: 550px;
	height:200px;
	margin-left:0px;
	margin-top:30px;
	table-layout: fixed;
	text-align:left;
}
td, th{
	padding:10px;
	background-color: white;
}
td:first-child{
	width:130px;
}
td:nth-child(2){
	width:300px;
}

.spinner1
{
    list-style: none;
    display: inline-block;
    line-height: 0;
    vertical-align: middle;
    padding:0;
    margin:0;
}
.spinner1 input
{            
	font-size: .45em;
    border-width: .5px;
    height: 1.5em;
    width: 2em;
    padding:0;
    margin:0;
}
.spinner2
{
    list-style: none;
    display: inline-block;
    line-height: 0;
    vertical-align: middle;
    padding:0;
    margin:0;
}
.spinner2 input
{            
	font-size: .45em;
    border-width: .5px;
    height: 1.5em;
    width: 2em;
    padding:0;
    margin:0;
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
	function upNdown(spinnerId, up) {
        document.getElementById(spinnerId).value = up ? 
       	parseInt(document.getElementById(spinnerId).value) + 1 : 
       	parseInt(document.getElementById(spinnerId).value) - 1;
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
			<img src="./img/admin_product.JPG" id="img1">
			<h2 class="productMenu">상품 관리</h2>
			<div class="productMenuButton">
				<form>
					<input type="button" value="● 상품 검색" class="button" onclick="location.href='productAdminPage.do'" ></input><br>
					<input type="button" value="● 상품 추가" class="button" style="color:#00B9FF; font-weight:bold;" onclick="location.href='productInsert.do'"></input>
				</form>
			</div>
		</div>
		<div id="content">
			<div id="insert_content">
			<div id="insert_content2">
				<form action="productInsertPro.do" method="post" enctype="Multipart/form-data">
					<table>
						<tr>
							<td>상품 종류</td>
							<td><select name="ptype">
										<option value="sodok">소독</option>
										<option value="mask">마스크</option>
										<option value="alco">알코올</option>
										<option value="sejung">세정</option>
										<option value="bangdok">방독</option>
										<option value="cheon">체온</option>
										<option value="home">재택용품</option>
										<option value="etc">기타</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>상품 이름</td>
							<td><input type="text" name="pname" required="required">
						</tr>
						<tr>
							<td>상품 가격</td>
							<td><input type="text" name="pprice" required="required">
						</tr>
						<tr>
							<td>상품재고</td>
							<td><input id="spinner1" type="text" name="pinventory" value="0" maxlength="5" size="5"/>
    							<ul class="spinner1">
     							   <li>
        						   <input type="button" value="&#9650;" onclick="upNdown('spinner1', true)" />
     							   </li>
     							   <li>
     						       <input type="button" value="&#9660;" onclick="upNdown('spinner1', false)" />
       								</li>
    							</ul> 개
    						</td>
						</tr>
						<tr>
							<td>썸네일 이미지</td>
							<td><input type="file" name="pthumbimg" required="required"></td>
						</tr>
						<tr>
							<td>상세 이미지1</td>
							<td><input type="file" name="col1" multiple="multiple" required="required"></td>
						</tr>
						<tr>
							<td>상세 이미지2</td>
							<td><input type="file" name="col2" multiple="multiple"></td>
						</tr>
						<tr>
							<td>상세 이미지3</td>
							<td><input type="file" name="col3" multiple="multiple"></td>
						</tr>
						<tr>
							<td>할인율</td>
							<td><input id="spinner2" type="text" name="pdiscount" value="0" pattern="[0-99]" maxlength="2" size="1"/>
    							<ul class="spinner2">
     							   <li>
        						   <input type="button" value="&#9650;" onclick="upNdown('spinner2', true)" />
     							   </li>
     							   <li>
     						       <input type="button" value="&#9660;" onclick="upNdown('spinner2', false)" />
       								</li>
    							</ul> %
    						</td>
						</tr>
						<tr>
							<td>공개여부</td>
							<td><input type="radio" name="ppublic" value="0" required>비공개
								<input type="radio" name="ppublic" value="1">공개
							</td>
						</tr>
						<tr>
							<td>옵션</td>
							<td><input type="text" name="poption" placeholder="옵션은 ,로 나눠주세요"></td>
						</tr>
						<tr>
							<td><input type="submit" value="등록" class="button2"></td>
						</tr>
					</table>
				</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>