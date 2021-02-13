<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	function chk(){
		if(frm.option.value=="none" || frm.option.value==""){
			alert("검색 옵션을 선택해 주십시오");
			return false;
		}
		if(!frm.search_value.value){
			alert("검색어를 입력해주세요.");
			return false;
		}
		if(isNaN(frm.search_value.value)){
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
					<input type="button" value="● 회원/주문번호 검색" class="button" style="color:#00B9FF; font-weight:bold;" onclick="location.href='orderAdminPage.jsp'" ></input><br>
					<input type="button" value="● 결제 내역 전체 조회" class="button" onclick="location.href='orderAllPage.do'"></input><br>
					<input type="button" value="● 매출 조회" class="button" onclick="location.href='salesAdminPage.do'"></input>
				</form>
			</div>
		</div>
		<div id="content">
			<div id="search">
				<div id="select">
					<form name="frm" action="orderMemberSearch.do" onsubmit="return chk()">
						<select name="option" class="option">
							<option value="none">==선택==</option>
							<option value="sid" <c:if test="${option eq 'sid' }">selected="selected"</c:if>>회원번호</option>
							<option value="orderCode"<c:if test="${option eq 'orderCode' }">selected="selected"</c:if>>주문번호</option>
						</select>
						<input type="text" name="search_value" size="50px" placeholder="검색어를 입력해주세요." value="${search_value }">
						<input type="submit" value="검색">
					</form>
				</div>
			</div>
			<br>
			<div id="search_content">
				<div id="search_table">
					<c:if test="${result eq '-1'}">
						해당 번호는 존재하지 않습니다.
					</c:if>
					<c:if test="${result eq '0' }">
						해당 회원은 주문내역이 없습니다.
					</c:if>
					<c:if test="${result eq '1' }">
						<c:if test="${option eq 'sid' }">
							<table>
								<tr>
									<td><b>${sname }</b>님 결제 정보</td>
								</tr>
							</table>
							<table>
								<tr>
									<td>주문번호</td>
									<td>주문일</td>
									<td>주문가격</td>
									<td>주문상태</td>
								</tr>
								<c:forEach var="order" items="${orderlist }">
								<tr>
									<td><a href="orderInfo.do?oid=${order.oid }" style="text-decoration:underline;"><fmt:formatDate pattern="yyyyMMdd" value="${order.odate }" />
										-<fmt:formatNumber value="${order.oid }" pattern="00000"/></a></td>
									<td><fmt:formatDate pattern="yyyy.MM.dd HH:mm" value="${order.odate }" /></td>
									<td><fmt:formatNumber value="${order.oamount }" pattern="#,###"/></td>
									<td><c:choose>
										<c:when test="${order.ostate eq '0' }">구매 취소 
										</c:when>
										<c:when test="${order.ostate eq '1' }">입금 대기중 
										</c:when>
										<c:when test="${order.ostate eq '2' }">입금 완료 
										</c:when>
										<c:when test="${order.ostate eq '3' }">배송중 
										</c:when>
										<c:when test="${order.ostate eq '4' }">배송 완료</c:when>
										<c:when test="${order.ostate eq '5' }">구매확정</c:when>
										<c:when test="${order.ostate eq '6' }">리뷰 완료</c:when>
									</c:choose></td>
								</tr>
								</c:forEach>
							</table>
						</c:if>
						<c:if test="${option eq 'orderCode' }">
							<table>
								<tr>
									<th colspan="2">주문 정보</th>
								</tr>
								<tr>
									<td>주문번호</td>
									<td><fmt:formatDate pattern="yyyyMMdd" value="${order.odate }" />
										- <fmt:formatNumber value="${order.oid }" pattern="00000"/></td>
								</tr>
								<tr>
									<td>진행상태</td>
									<td><c:choose>
										<c:when test="${order.ostate eq '0' }">
											구매 취소
										</c:when>
										<c:when test="${order.ostate eq '1' }">
											입금 대기중 <input type="button" value="입금완료" onclick="location.href='orderChangeOstate.do?oid=${order.oid}'">
										</c:when>
										<c:when test="${order.ostate eq '2' }">
											입금 완료 <input type="button" value="배송출발"  onclick="location.href='orderChangeOstate.do?oid=${order.oid}'">
										</c:when>
										<c:when test="${order.ostate eq '3' }">
											배송중 <input type="button" value="배송완료"  onclick="location.href='orderChangeOstate.do?oid=${order.oid}'">
										</c:when>
										<c:when test="${order.ostate eq '4' }">배송 완료</c:when>
										<c:when test="${order.ostate eq '5' }">구매확정</c:when>
										<c:when test="${order.ostate eq '6' }">리뷰 완료</c:when>
									</c:choose></td>
								</tr>
								<tr>
									<td>주문일</td>
									<td><fmt:formatDate pattern="yyyy.MM.dd HH:mm" value="${order.odate }" /></td>
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
									<td><fmt:formatNumber value="${order.oamount+order.odelivery }" pattern="#,###"/>원 
									(배송비 : <fmt:formatNumber value="${order.odelivery }" pattern="#,###"/> 원)
									</td>
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
										<td rowspan="3">
											<img src="${detail.pthumbimg }" style="width:100px; height:100px; vertical-align:center;"></td>
										<td>상품명 : ${detail.pname }</td>
									</tr>
									<tr>
										<td>상품 가격 : <fmt:formatNumber value="${detail.pprice }" pattern="#,###"/>원</td>
									</tr>
									<tr>
										<td>수량 : ${detail.dqty } 개 
										<c:if test="${detail.poption ne null }">
										| 옵션 : [${detail.poption }]</c:if></td>
									</tr>
								</c:forEach>
							</table>
						</c:if>
					</c:if>
				</div>
			</div>
		</div>
	</div>
</body>
</html>