<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bye-rus!</title>
<link rel="stylesheet" type="text/css" href="css/cartnbuy.css?ver=1"> 
<link rel="stylesheet" type="text/css" href="css/YoungCSS.css?ver=2">
<style type="text/css">


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
<a class="top_button">${session_sname } 님</a>
<a href="adminPage.do" class="top_button">관리페이지</a>
</c:when>
<c:otherwise>
<a href="login.do?url=jjimForm.do" class="top_button">위시리스트</a>
<a href="login.do?url=cart.do" class="top_button">장바구니</a>
<a href="login.do?url=mypageOrder.do" class="top_button">주문/배송</a>
<a href="login.do?url=mypage.do" class="top_button">마이페이지</a>
<a href="login.do?url=main.do" class="top_button">로그인/회원가입</a>
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
   <div class="aa" style="z-index: 2" >
      <span class="step1" style=" z-index: 1; color : #848484;"><h2>장바구니</h2></span>
      <span class="step2" style=" z-index: 1; color : #848484;"><h2>주문결제</h2></span>
      <span class="step3" style=" z-index: 1; "><h2>결제완료</h2></span>
   </div>
   <div class="main">
   <div class="bb" style="z-index: 2">
      <br>
      <div style="width: 800px; height: 800px; background-color: white; margin-left: 50px; ">
      <!--if 문으로 성공이면 성공내용 보이게  -->
      <c:if test="${result >0 }">
            <br><br><br><br><br>
            <div style="text-align: center; " ><h2>주문/결제 요청이 정상적으로 완료되었습니다</h2></div>
            <br><br><br>
            <form action="">
            <table class="order">
               <tr class="info_tr">
                  <td>주문번호</td>
                  <td>
                  <fmt:formatDate value="${order_selct.odate }" pattern="yyyyMMdd"/>-
                  <fmt:formatNumber value="${order_selct.oid }" pattern="00000"/>
                  
                  </td>
               </tr>
               <tr class="info_tr">
                  <td>주문일자</td>
                  <td>${order_selct.odate }</td>
               </tr>
            
            </table>
            
            </form>
      </c:if>      
            <!--결제실패시   -->
      <c:if test="${result == 0 }">   
            <br><br><br><br><br>
            <div style="text-align: center; " ><h2>주문/결제 실패되었습니다</h2></div>
            <br><br><br>
            <form action="">
            <table class="order">
               <tr class="info_tr">
                  <td>오류원인</td>
                  <td><input type="text" name="orderNum"></td>
               </tr>
               
            </table>
            
            </form>
      </c:if>      
         
         
      
      </div>
   <form action="">
         <span><input type="button" class="pay" id="cancel" value="주문내역 확인" onclick="location.href='mypage.do'"></span>
         <span><input type="button" class="pay" id="pay" value="메인화면" onclick="location.href='main.do'"></span>
      </form>
   
   
   
   
   
   
   <br>
   </div>
   </div>

</div>
</body>
</html>