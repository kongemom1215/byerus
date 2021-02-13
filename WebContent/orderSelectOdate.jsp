<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bye-rus!</title>
<link rel="stylesheet" type="text/css" href="css/order.css?ver=20">
<link rel="stylesheet" type="text/css" href="css/YoungCSS.css?ver=1">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript">
	function chk(){
		if(!frm.pid.value){
			alert("검색어를 입력해주세요.");
			return false;
		}
		if(isNaN(frm.pid.value)){
			alert("숫자만 입력해주세요");
			return false;
		}
	}
	function display1(){
		$(".1").css("display","");
		$(".2").css("display","none");
		$(".3").css("display","none");
	}
	function display2(){
		$(".1").css("display","none");
		$(".2").css("display","");
		$(".3").css("display","none");
	}
	function display3(){
		$(".1").css("display","none");
		$(".2").css("display","none");
		$(".3").css("display","");
	}
	function selectOstate(obj){
		var ostate=$(obj).val();
		if(ostate!="none")
			location.href="orderSelectOstate.do?ostate="+ostate;
		if(ostate=="none")
			location.href="orderAllPage.do";
	}
</script>
<style type="text/css">
#search_table{
	display: inline-block;
    margin-top: 20px;
    margin-left:20px;
    width:600px;
    height:520px;
    overflow:auto;
}
#select{
	display: inline-block;
    margin-top: 10px;
    margin-left:20px;
    text-align:center;
}
a {
	text-decoration: none;
	color: black;
}
</style>
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
			<img src="./img/admin_order.JPG" id="img1">
			<h2 class="memberMenu">결제&배송 관리</h2>
			<div class="memberMenuButton">
				<form>
					<input type="button" value="● 회원/주문번호 검색" class="button" onclick="location.href='orderAdminPage.jsp'" ></input><br>
					<input type="button" value="● 결제 내역 전체 조회" class="button"  style="color:#00B9FF; font-weight:bold;" onclick="location.href='orderAllPage.do'"></input><br>
					<input type="button" value="● 매출 조회" class="button" onclick="location.href='salesAdminPage.do'"></input>
				</form>
			</div>
		</div>
		<div id="content">
				<div id="content2">
					<table id="select">
						<tr>
							<td style="width:200px;"><input type="button" value="진행상태별" onclick="display1()"></td>
							<td style="width:200px;"><input type="button" value="날짜별" onclick="display2()"></td>
							<td style="width:200px;"><input type="button" value="상품별" onclick="display3()"></td>
						</tr>
						<tr>
							<td class="1" style="text-align=center; display:none;" colspan="3">
								<select name="ostate" id="ostate" onchange="selectOstate(this)">
									<option value="none">==전체==</option>
									<option value="0"<c:if test="${ostate eq '0' }">selected="seleted"</c:if>>구매 취소</option>
									<option value="1"<c:if test="${ostate eq '1' }">selected="seleted"</c:if>>입금 대기중</option>
									<option value="2"<c:if test="${ostate eq '2' }">selected="seleted"</c:if>>입금 완료</option>
									<option value="3"<c:if test="${ostate eq '3' }">selected="seleted"</c:if>>배송중</option>
									<option value="4"<c:if test="${ostate eq '4' }">selected="seleted"</c:if>>배송 완료</option>
									<option value="5"<c:if test="${ostate eq '5' }">selected="seleted"</c:if>>구매 확정</option>
								</select>
							</td>
						</tr>
						<tr>
							<td class="2" style="text-align=center;" colspan="3">
							<form action="orderSelectOdate.do" autocomplete="off">
							<input type="text" name="datepicker" id="datepicker" value="${datepicker }" placeholder="시작날짜" required="required">
							<script>
								$(function () {
									$("#datepicker").datepicker();
								});
								$.datepicker.setDefaults({
								    dateFormat: 'yy-mm-dd' //Input Display Format 변경
								});
							</script>
							~ <input type="text" name="datepicker2" id="datepicker2" value="${datepicker2 }"placeholder="끝날짜" required="required">
							<script>
								$(function () {
									$("#datepicker2").datepicker();
								});
							</script>
							<input type="submit" value="검색">
							</form>
							</td>
						</tr>
						<tr>
							<td class="3" style="text-align=center; display:none;" colspan="3">
								<form action="orderSelectProduct.do" name="frm" onsubmit="return chk()">
								<input type="text" name="pid" placeholder="상품번호로 검색" required="required">
								<input type="submit" value="검색">
								</form>
							</td>
						</tr>
					</table>
					<table id="search_table">
						<tr>
							<th>주문일</th>
							<th>주문번호</th>
							<th>주문자명</th>
							<th>주문금액</th>
							<th>주문상태</th>
						</tr>
						<c:forEach var="order" items="${orderlist }">
							<tr>
								<td><fmt:formatDate pattern="yyyy.MM.dd HH:mm" value="${order.odate }" /></td>
								<td><a href="orderSelectInfo.do?oid=${order.oid }&datepicker=${datepicker}&datepicker2=${datepicker2}" style="text-decoration: underline;"> <fmt:formatDate pattern="yyyyMMdd" value="${order.odate }"/>
										-<fmt:formatNumber value="${order.oid }" pattern="00000"/></a></td>
								<td>${order.sname }</td>
								<td><fmt:formatNumber value="${order.oamount }" pattern="#,###"/></td>
								<td><c:choose>
									<c:when test="${order.ostate eq '0' }">구매 취소 </c:when>
										<c:when test="${order.ostate eq '1' }">입금 대기중 </c:when>
										<c:when test="${order.ostate eq '2' }">입금 완료 </c:when>
										<c:when test="${order.ostate eq '3' }">배송중 </c:when>
										<c:when test="${order.ostate eq '4' }">배송 완료</c:when>
										<c:when test="${order.ostate eq '5' }">구매확정</c:when>
								</c:choose></td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
</body>
</html>