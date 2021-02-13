<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/mypageEditForm.css?ver=1">
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/YoungCSS.css?ver=1">
</head>
<body>
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
<div class="main">
   <div class="mypagehead">
      <input type="button" value="마이페이지" style="margin-top: 30px; font-weight:bold; font-size: 30px; color: #00EBFF; background-color: white; width: 170px; border: 0; outline: 0;" onclick="location.href='mypage.do?sid=${2 }'">
   <hr>
   </div>
   <div class="down">
   <div class="mypagemainside">
   <div class="mypageside">
      <br>
      <a href="mypageOrder.do?sid=${shoppinguser.sid }" style="text-decoration: none; color: black;">
      <span>주문조회</span>
      <span style="float:right;">></span>
      </a><p>
      <a href="mypageCoupon.do?sid=${shoppinguser.sid }" style="text-decoration: none; color: black;">
      <span>쿠폰</span>
      <span style="float:right;">></span>
      </a><p>
      <a href="mypageReview.do" style="text-decoration: none; color: black;">
      <span>리뷰/문의</span>
      <span style="float:right;">></span>
      </a><p>
      <a href="mypageEditForm.do?sid=${shoppinguser.sid }" style="text-decoration: none; color: black;">
      <span>회원정보수정</span>
      <span style="float:right;">></span>
      </a><p>
      <a href="mypageDeleteForm.do?sid=${shoppinguser.sid }" style="text-decoration: none; color: black;">
      <span>회원탈퇴</span>
      <span style="float:right;">></span>
      </a><p>
   </div>
   </div>

<div class="useredit">
   <table>
      <caption><h2>주문자 정보</h2></caption>
      <tr><td>수령자 : </td><td>${orderjoin.oname }</td></tr>
      <tr><td>연락처 : </td><td>${orderjoin.ocontact }</td></tr>
      <tr><td>우편번호 : </td><td>${orderjoin.opost }</td></tr>
      <tr><td>주소 : </td><td>${orderjoin.oaddress }</td></tr>
      <tr><td>결제수단 : </td><td>
      <c:set var="opay" value="${orderjoin.opay }"/>
            <c:choose>
                <c:when test="${opay eq '1' }">
                   카드
                </c:when>
                <c:when test="${opay eq '2' }">
                   무통장
                </c:when>         
                <c:otherwise></c:otherwise>       
            </c:choose>            
      </td></tr>
      <tr><td>배송비 : </td><td>
      <c:set var="odelivery" value="${orderjoin.odelivery }"/>
            <c:choose>
                <c:when test="${odelivery eq '0' }">
                   무료배송
                </c:when>
                <c:when test="${odelivery eq '3000' }">
                   3,000원
                </c:when>         
                <c:otherwise></c:otherwise>       
            </c:choose>            
      </td></tr>
      <tr>
      <c:set var="cid" value="${orderjoin.cid }"/>
            <c:choose>
                <c:when test="${cid eq '0' }">
                </c:when>
                <c:when test="${cid ne '0' }">
                <td>할인가액 : </td><td>
                <c:set var="cprice" value="${((orderjoin.oamount-orderjoin.odelivery)*100/(100 - orderjoin.cdiscount))*(orderjoin.cdiscount/100) }" />
                <fmt:formatNumber type="number" pattern="#,###" value="${cprice }"/>원
                </td>
                </c:when>         
                <c:otherwise></c:otherwise>       
            </c:choose>            
      </tr>
   </table>
</div>   
</div>
</div>
<div style="height: 90%;">
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