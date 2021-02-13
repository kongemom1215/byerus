<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bye-rus!</title>
<link rel="stylesheet" type="text/css" href="css/YoungCSS.css?ver=1">
<link rel="stylesheet" type="text/css" href="css/cartnbuy.css?ver=2"> 
<script type="text/javascript" src="http://code.jquery.com/jquery-2.1.3.min.js"></script>
<script type="text/javascript">
//전체선택&전체해제
	$(document).ready(function(){
		$('#chkAll').click(function(){
			if($("#chkAll").prop("checked")){
				$("input[type=checkbox]").prop("checked",true);
			}else{
				$("input[type=checkbox]").prop("checked",false);
			}
		});
		});
		
  		
  		$(document).ready(function(){
  		   $('#checkdel').click(function(){
  		      if($("input[type='checkbox']").is(':checked')){
  		         return true;
  		      }else{
  		         alert("삭제할 상품을 체크하세요");
  		         return false;
  		      }
  		      
  		   });
  		   }); 
</script>
<style type="text/css">
.main_go{
		
		background-color: white;
		border: 0px;
		margin-left : 350px;
		width : 170px;
		height : 60px;
		border-radius: 12px;
		margin-top: 200px;
	
	}

</style>

</head>
<body>

<header>
<div class="main">
<div style="margin-top: 15px;">
<a href="main.do"><img src="./img/Logo.png"></a>

<c:choose>
<c:when test="${session_stype eq '1'}">
<a class="top_button">위시리스트</a>
<a href="cart.do" class="top_button">장바구니</a>
<a class="top_button">주문/배송</a>
<a href="mypage.do" class="top_button">마이페이지</a>
<a href="main.do?logout=logout" class="top_button">로그아웃</a>
<a class="top_button">${session_sname } 님</a>
</c:when>
<c:when test="${session_stype eq '0'}">
<a href="main.do?logout=logout" class="top_button">로그아웃</a>
<a class="top_button">${session_semail } 님</a>
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
<div class="main">
<div style="height: 17.33px;">
<div class="nav_button"><a href="aboutUs.do">ABOUT US</a></div>
<div class="nav_button"><a href="shoppingMain.do">SHOPPING</a></div>
<div class="nav_button"><a href="board.do?type=notice">BOARD</a></div>
</div>
</div>
<hr style="margin-bottom: 0px; border-bottom: 0px;">

</header>
<div class="main">
<div class="aa" style="z-index: 2">
		<span class="step1" style=" z-index: 1"><h2>장바구니</h2></span>
		<span class="step2" style=" z-index: 1; color : #848484;"><h2>주문결제</h2></span>
		<span class="step3" style="z-index: 1; color : #848484;"><h2>결제완료</h2></span>
</div><!--aa 끝  -->
<div class="bb" style="z-index: 2">
	<div style="margin-top: 20px; height: 20px;"></div>
		<h2>${session_sname }님의 장바구니</h2>
		<h2><input type="hidden" value="${sid}"></h2>
	
	<c:if test="${empty list }">
	<div name="cartDiv" >
			<form>
			<table class="cart_tb">
				<tr height="100" style="height: 200px;">
					<td colspan="6" align="center"><h2>장바구니에 담기 상품이 없습니다</h2></td>
				</tr>
				
			</table>
			</form>
	</div>			
			<div style="height: 340px;">
				<input  class="main_go" type="button" value="메인으로" onclick="location.href='main.do'">
			</div>
			
			<div  >		
			</div><!--name="goMain"끝  -->
	
	</c:if>
	
	<c:if test="${!  empty list }">
	<div name="cartDiv">
		<form name="cartForm" method="post" >
				<table class="cart_tb" id="cartList"  >
					<tr class="cart_tr" >
						<th class="cart_td"><input type="checkbox"  name="chkAll" id="chkAll"   ></th>
						<th class="cart_td" >상품코드</th>
						<th class="cart_td">상품명</th>
						<th class="cart_td">옵션</th>
						<th class="cart_td">판매가격</th>
						<th class="cart_td">할인판매가</th>
						<th class="cart_td">수량</th>
						<th class="cart_td">주문금액</th>
						
						
						
				</tr>
			<c:forEach var="arr" items="${list}" varStatus="status">
						

 					<tr  value="${list}" class="cart_tr">
						<td class=cart_td>
 					<input type="checkbox" name="check1" value="${arr.cwid }" ></td>						
<!-- 						<select name="check1" required="required">
							<option value="0">유지</option>
							<option value="1">삭제</option>
						</select></td>
 -->						
						<td class="cart_td">${arr.pid}</td>
						<td class="cart_td" style="width: 200px;" >${arr.pname}</td>
						<td class="cart_td">${arr.cwoption}</td>
						<td class="cart_td"><fmt:formatNumber value="${arr.pprice }"  pattern="#,###"></fmt:formatNumber></td>
						<c:set var="discount" value="0"></c:set>
						<c:set var="totalDis" value="0"></c:set>
						<c:set var="totalWho" value="0"></c:set>
						
						<c:if test="${arr.pdiscount > 0 }">
							<c:set var="discount" value="${arr.pprice - (arr.pdiscount / 100 *arr.pprice ) }"></c:set>
							<td class="cart_td"><fmt:formatNumber value="${discount}"  pattern="#,###"></fmt:formatNumber></td>
							<td class="cart_td"><fmt:formatNumber value="${arr.cwqty}" pattern="#,###"></fmt:formatNumber></td>
							<td class="cart_td"><fmt:formatNumber value="${discount*arr.cwqty}" pattern="#,###"></fmt:formatNumber></td>
							
						</c:if>
						
						<c:if test="${arr.pdiscount == 0 }">
							<td class="cart_td"><fmt:formatNumber value=""  pattern="#,###"></fmt:formatNumber></td>
							<td class="cart_td"><fmt:formatNumber value="${arr.cwqty}" pattern="#,###"></fmt:formatNumber></td>
							<td class="cart_td"><fmt:formatNumber value="${arr.pprice*arr.cwqty}" pattern="#,###"></fmt:formatNumber></td>
							
						</c:if>
						<c:set var="totalDis" value="${discount*arr.cwqty}"></c:set>
						<c:set var="totalWho" value="${arr.pprice*arr.cwqty}"></c:set>

<%-- 						<td class="cart_td" >
바로삭제,바로주문 버튼			<input type="button" name="checkDel" id="checkDel" value="삭제" onclick="location.href='checkDel.do?cwid=${arr.cwid }'">
							<input type="button" name="checkOrder"  id="checkOrder" value="바로주문"
							 onclick="location.href='checkOrder.do?cwid=${arr.cwid }&pprice=${arr.pprice }&cwqty=${arr.cwqty}'">
					</td>
 --%>						
					</tr>
					<input type="hidden" name="cwid[]" value="${arr.cwid }">
			</c:forEach>
			
			</table> <!--cart_tb 끝  -->
			
		
		
	<br>	
	</div>
	<div class="cc">
		<div class="result" >
<!-- 총 상품 금액 계산 + 배송비 계산  -->
		<div style="height: 60px;"></div>
		총<span>${count }</span>개 
상품 총 금액
 		<b>
 			
 			
 			<c:set var="sum" value="0"/>
 			<c:set var="sum1" value="0"/>
 			<c:set var="sum2" value="0"/>
			
			<c:forEach var="arr" items="${list}">
				<c:if test="${arr.pdiscount > 0 }">
					<c:set var="sum1" value="${sum+(arr.pprice - (arr.pdiscount / 100 *arr.pprice ))*arr.cwqty }"></c:set>
				</c:if>
				<c:if test="${arr.pdiscount == 0 }">
					<c:set var="sum1" value="${sum+arr.pprice*arr.cwqty}"></c:set>
				</c:if>
				<c:set var="sum" value="${sum1+sum2}"></c:set>
			</c:forEach>	
				<fmt:formatNumber value="${sum }" pattern="#,###"></fmt:formatNumber>
		</b>원 
		+
		
		배송비<b>
			<c:if test="${sum >=30000 }">
			0
			</c:if>
		
		<c:if test="${sum < 30000 }">
		3000
		</c:if>
		</b>원
		=
		총 결제 금액<b>
		<c:if test="${sum >=30000 }">
		<fmt:formatNumber value="${sum}" pattern="#,###"></fmt:formatNumber>
		
		</c:if>
		
		<c:if test="${sum < 30000 }">
		<fmt:formatNumber value="${sum+3000}" pattern="#,###"></fmt:formatNumber>
		
		</c:if>
		</b>원 
		
		</div><!--result 끝  -->
	</div><!--cc 끝  -->
	
	
	<input type="button" value="전체삭제 "onclick="location.href='cartDelete.do'" class="del" id="wholedel">
	<input type="submit" value="선택삭제"  class="del" id="checkdel" formaction="checkDel.do">
	<input type="button" value="주문하기 "onclick="location.href='order.do?sum=${sum}&count=${count}'" class="buy" id="wholebuy">
<!-- 	<input type="submit" value="선택주문 "onclick="" class="buy" id="checkbuy" formaction="checkOrder.do">
 -->	
	
	
	
	
</form>
</div><!--cartDiv 끝  -->
</c:if>
</div>

<div style="height: 340px;">
</div>
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
</body>
</html>