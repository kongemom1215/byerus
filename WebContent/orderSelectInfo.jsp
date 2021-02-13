<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!--
회원번호 검색
1. 회원이 존재하는지
2. 존재하면 주문내역 조회 
   회원이 존재하지 않으면 result = -1;
3. 주문내역이 없으면 result=0;
   주문내역이 있으면 result=1;

주문번호 검색
1. 해당 주문번호가 있는지
2. 있으면 result=1;
없으면 result=-1;  
-->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bye-rus!</title>
<link rel="stylesheet" type="text/css" href="css/order.css?ver=21">
<link rel="stylesheet" type="text/css" href="css/YoungCSS.css?ver=1">
<script type="text/javascript">
	function chk() {
		if (frm.option.value == "none" || frm.option.value == "") {
			alert("검색 옵션을 선택해 주십시오");
			return false;
		}
		if (!frm.search_value.value) {
			alert("검색어를 입력해주세요.");
			return false;
		}
		if (isNaN(frm.search_value.value)) {
			alert("숫자만 입력해주세요");
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
			<img src="./img/admin_order.JPG" id="img1">
			<h2 class="memberMenu">결제&배송 관리</h2>
			<div class="memberMenuButton">
				<form>
					<input type="button" value="● 회원/주문번호 검색" class="button" onclick="location.href='orderAdminPage.jsp'"></input><br> <input
						type="button" value="● 결제 내역 전체 조회" class="button" style="color: #00B9FF; font-weight: bold;" onclick="location.href='orderAllPage.do'"></input><br>
						<input type="button" value="● 매출 조회" class="button" onclick="location.href='salesAdminPage.do'"></input>
				</form>
			</div>
		</div>
		<div id="content">
			<div id="content2">
				<div id="search_table">
					<table>
						<tr>
							<th colspan="2">주문 정보</th>
						</tr>
						<tr>
							<td>주문번호</td>
							<td><fmt:formatDate pattern="yyyyMMdd"
									value="${order.odate }" /> - <fmt:formatNumber
									value="${order.oid }" pattern="00000" /></td>
						</tr>
						<tr>
							<td>진행상태</td>
							<td><c:choose>
									<c:when test="${order.ostate eq '0' }">구매 취소</c:when>
									<c:when test="${order.ostate eq '1' }">
											입금 대기중 <input type="button" value="입금완료" onclick="location.href='orderChangeOstate2.do?oid=${order.oid}'">
										</c:when>
										<c:when test="${order.ostate eq '2' }">
											입금 완료 <input type="button" value="배송출발"  onclick="location.href='orderChangeOstate2.do?oid=${order.oid}'">
										</c:when>
										<c:when test="${order.ostate eq '3' }">
											배송중 <input type="button" value="배송완료"  onclick="location.href='orderChangeOstate2.do?oid=${order.oid}'">
										</c:when>
									<c:when test="${order.ostate eq '4' }">배송 완료</c:when>
									<c:when test="${order.ostate eq '5' }">구매확정</c:when>
								</c:choose></td>
						</tr>
						<tr>
							<td>주문일</td>
							<td><fmt:formatDate pattern="yyyy.MM.dd HH:mm"
									value="${order.odate }" /></td>
						</tr>
						<tr>
							<td>주문고객번호</td>
							<td>${order.sid }</td>
						</tr>
						<tr>
							<td>결제 방법</td>
							<td><c:if test="${order.opay eq '2' }">무통장</c:if>
								<c:if test="${order.opay eq '1' }">신용카드</c:if>
							</td>
						</tr>
						<tr>
							<td>총 구매액</td>
							<td><fmt:formatNumber
									value="${order.oamount }" pattern="#,###" />원
								(배송비 : <fmt:formatNumber value="${order.odelivery }"
									pattern="#,###" /> 원)</td>
						</tr>
					</table>
					<table>
						<tr>
							<th colspan="2">수령자 정보</th>
						</tr>
						<tr>
							<td>이름</td>
							<td>${order.oname }</td>
						</tr>
						<tr>
							<td>연락처</td>
							<td>${order.ocontact }</td>
						</tr>
						<tr>
							<td>우편번호</td>
							<td>${order.opost }</td>
						</tr>
						<tr>
							<td>수령자 주소</td>
							<td>${order.oaddress }</td>
						</tr>
					</table>
					<table>
						<tr>
							<th colspan="2">주문내역</th>
						</tr>
						<c:forEach var="detail" items="${orderdetaillist }">
							<tr>
								<td rowspan="3"><img src="${detail.pthumbimg }"
									style="width: 100px; height: 100px; vertical-align: center;"></td>
								<td>상품명 : ${detail.pname }</td>
							</tr>
							<tr>
								<td>상품 가격 : <fmt:formatNumber value="${detail.pprice }"
										pattern="#,###" />원
								</td>
							</tr>
							<tr>
								<td>수량 : ${detail.dqty } 개 <c:if test="${detail.poption ne null }">
										| 옵션 : [${detail.poption }]</c:if></td>
							</tr>
						</c:forEach>
					</table>
				</div>
				<c:choose>
				<c:when test="${pid ne null }">
					<input type="button" style="float:right;" value="뒤로가기" onclick="location.href='orderSelectProduct.do?pid=${pid}'">
				</c:when>
				<c:when test="${ostate ne null }">
					<input type="button" style="float:right;" value="뒤로가기" onclick="location.href='orderSelectOstate.do?ostate=${ostate}'">
				</c:when>
				<c:when test="${datepicker ne null }">
					<input type="button" style="float:right;" value="뒤로가기" onclick="location.href='orderSelectOdate.do?datepicker=${datepicker}&datepicker2=${datepicker2 }'">
				</c:when>
				<c:otherwise>
					<input type="button" style="float:right;" value="뒤로가기" onclick="location.href='orderAllPage.do'">
				</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
</body>
</html>