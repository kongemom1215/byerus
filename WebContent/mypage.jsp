<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/mypage.css?ver=1">
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/YoungCSS.css?ver=1">
<style type="text/css">
.mypageinfo{
      width:600px;
      float: right;
      margin-left: 10px;
   }
   .mypagecoupon{
      width:300px;
      float: right;
      margin-left: 10px;
      margin-top: 85px;
   }
   .mypagename{
      width:240px;
      float: right;
      margin-left: 10px;
      margin-top: 85px;
   }
   .reviewbutton{ 
      width: 80px;
      float: right;
   }
   .tablecss{ 
      width: 560px;
      margin-right:10px;
      float: left;
   }
   .TBcss{ 
      width: 660px;
      height: 111px;
   }
   .button {
    width:75px;
    background-color: #14D3FF;
    border: none;
    color:#fff;
    padding: 8px 0;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 15px;
    margin: 4px;
    cursor: pointer;
   border-radius:10px;
}
   .button:hover {
    background-color: blue;
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
   <!-- 회원아이콘 출력-->
   <div class="mypagepersonicon">
      <img src="./img/mypageicon.png" width="140px"/>
   </div>
   <!-- 쿠폰/이름 출력-->
   <div class="mypageinfo">
      <div class="mypagecoupon">
      <a href="mypageCoupon.do?sid=${shoppinguser.sid }" style="text-decoration: none; color: black;">
      <span>사용가능 쿠폰 : </span>
      <span style=" color:blue; font-weight:bold;">${count }</span>
      <span >장</span>
      </a>
      </div>
      <div class="mypagename">
      <span style="color:black; font-weight:bold;">${shoppinguser.sname }</span>
      <span>님</span>
      </div>
   </div>
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
   <div class="myorderview">
      <h3>주문내역</h3>
   <div class="myorderviewdetail">
   <c:forEach var="orderjoin" items="${list_main}" varStatus="status">
   <c:set var="str" value="${list_main[status.index + 1].oid }"/>
      <c:if test="${orderjoin.oid != str }">
            <p>
      <a href="mypageOrder.do?sid=${shoppinguser.sid }" style="text-decoration: none; color: black;">
      <span>${orderjoin.getOdate()}</span>
      
      <span style="float:right; color:blue; font-weight:bold;"><fmt:formatDate pattern="yyyyMMdd"
                                    value="${orderjoin.odate }" /> - <fmt:formatNumber
                                    value="${orderjoin.oid }" pattern="00000" /></span>
      <span style="float:right;">주문번호 : </span>
      </a>
      <hr>
      <c:forEach var="orderjoin2" items="${list_main}" varStatus="status">
      <c:set var="stl" value="${list_main[status.index + 1].pid }"/>
      <c:if test="${orderjoin2.pid != stl }">
      <div class="TBcss">
      <div class="tablecss">
      <table>
      <!-- 주문상품 중 대표 사진 -->
         <tr><td rowspan="4"><img src="${orderjoin2.pthumbimg }" width="100"/></td>
         <td colspan="6">${orderjoin2.pname }</td></tr>
         <tr><td style="font-size: small;">
         <c:set var="poption" value="${orderjoin.poption }"/>
                <c:if test="${poption ne null}">
                옵션 : 
                <c:forEach var="orderjoin3" items="${list_main}">
                   ${orderjoin3.poption } (수량:${orderjoin3.getDqty() })
                </c:forEach>
                </c:if>
                <c:if test="${poption eq null}">
                   수량 : ${orderjoin2.getDqty() }
                </c:if></td></tr><tr></tr>
                <tr><td>주문총액 : <fmt:formatNumber type="number" pattern="#,###" value="${orderjoin.oamount }"/>원 </td></tr>
      </table>
      </div>
       <div class="reviewbutton">

               <c:set var="reviewox" value="${orderjoin.reviewox }"/>
               <c:choose>
               <c:when test="${reviewox eq '0' }">
                   <input type="button"  value=" 리뷰쓰기 " class="button" style="float:right;"
                  onclick="location.href='reviewWrite.do?oid=${orderjoin.oid }&pid=${orderjoin.pid }'" >
               </c:when>
               <c:when test="${reviewox eq '1' }">
               </c:when>
               <c:otherwise></c:otherwise>
               </c:choose>

      </div>
      </div>
      </c:if>
      </c:forEach>
      <hr>
            <span style="font-size:large; color:blue; font-weight:bold;">
            <c:choose>
                <c:when test="${orderjoin.ostate eq '0' }">
                   구매취소
                </c:when>
                <c:when test="${orderjoin.ostate eq '1' }">
                   결제대기
                   <input type="button"  value=" 주문취소 " class="button" 
                  onclick="location.href='mypageOrderdelete.do?oid=${orderjoin.oid }'" >
                </c:when>
                <c:when test="${orderjoin.ostate eq '2' }">
                   결제완료
                   <input type="button"  value=" 주문취소 " class="button" 
                  onclick="location.href='mypageOrderdelete.do?sid=${shoppinguser.sid}&oid=${orderjoin.oid }'" >
                </c:when>
                <c:when test="${orderjoin.ostate eq '3' }">
                   배송중
                </c:when>
                <c:when test="${orderjoin.ostate eq '4' }">
                   배송완료
                   <input type="button"  value=" 구매확정 " class="button" 
                  onclick="location.href='mypageOrderhwakjeong.do?oid=${orderjoin.oid }'" >
                </c:when>
                <c:when test="${orderjoin.ostate eq '5' }">
                   구매확정
                </c:when>
                <c:otherwise></c:otherwise>
            </c:choose>
            </span>
            <input type="button" value="주문자 정보" onclick="location.href='mypageOrderBy.do?sid=${session_sid }&oid=${orderjoin.oid }'" style="float:right;">            <p>
            </c:if>
   </c:forEach>
   </div>
   </div>
</div>
<table>
</table>
</div>
<br>
<br>
<div style="height: 340px;"></div>

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