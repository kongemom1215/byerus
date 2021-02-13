<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/mypage.css?ver=1">
<style type="text/css">
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
.button1 {
   float:right;
    width:150px;
    background-color: pink;
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
   .button1:hover {
    background-color: red;
}   
</style>
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
   <div class="myorderviewdetail">   
      <input type="button" class="button" value="상품 Q&A" onclick="location.href='mypageQna.do'">
      <input type="button" class="button1" value="작성한 리뷰" onclick="location.href='mypageReview.do'">
   <div>
            <div>
               <h4>작성가능한 리뷰 : ${cntYetReview }</h4>
            </div>
         <div class="myorderviewdetail">
            <c:forEach var="orderjoin" items="${list}" varStatus="status">
   <c:set var="str" value="${list[status.index + 1].oid }"/>
      <c:if test="${orderjoin.oid != str }">
       <span>${orderjoin.getOdate()}</span>      
      <span style="float:right; color:blue; font-weight:bold;"><fmt:formatDate pattern="yyyyMMdd"
                                    value="${orderjoin.odate }" /> - <fmt:formatNumber
                                    value="${orderjoin.oid }" pattern="00000" /></span>
      <span style="float:right;">주문번호 : </span>
      <br><hr>
      <!-- pid가 같으면 하나만 출력 -->
      <c:forEach var="orderjoin" items="${list}" varStatus="status">
      <c:set var="stl" value="${list[status.index + 1].pid }"/>
      <c:if test="${orderjoin.pid != stl }">   
      <div class="TBcss">
      <div class="tablecss">
      <table>
      <!-- 주문상품 중 대표 사진 -->
         <tr><td rowspan="4"><img src="${orderjoin.getPthumbimg() }" width="100"/></td>
         <td colspan="6">${orderjoin.getPname() }</td></tr>
         <tr><td style="font-size: small;">
         <c:set var="poption" value="${orderjoin.poption }"/>
                <c:if test="${poption ne null}">
                옵션 : 
                <c:forEach var="orderjoin" items="${list}">
                   ${orderjoin.poption } (수량:${orderjoin.getDqty() })
                </c:forEach>
                </c:if>
                <c:if test="${poption eq null}">
                   수량 : ${orderjoin.getDqty() }
                </c:if></td></tr><tr></tr>
         <tr><td>주문총액 : <fmt:formatNumber type="number" pattern="#,###" value="${orderjoin.oamount }"/>원 </td>
         </tr>
         <tr></tr>
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
            <c:set var="ostate" value="${orderjoin.getOstate() }"/>
            <c:choose>
                <c:when test="${ostate eq '0' }">
                   구매취소
                </c:when>
                <c:when test="${ostate eq '1' }">
                   결제대기
                   <input type="button"  value=" 주문취소 "  class="button"
                  onclick="location.href='mypageOrderdelete.do?oid=${orderjoin.oid }'" >
                </c:when>
                <c:when test="${ostate eq '2' }">
                   결제완료
                   <input type="button"  value=" 주문취소 " class="button" 
                  onclick="location.href='mypageOrderdelete.do?oid=${orderjoin.oid }'" >
                </c:when>
                <c:when test="${ostate eq '3' }">
                   배송중
                </c:when>
                <c:when test="${ostate eq '4' }">
                   배송완료
                   <input type="button"  value=" 구매확정 " class="button" 
                  onclick="location.href='mypageOrderhwakjeong.do?oid=${orderjoin.oid }'" >
                </c:when>
                <c:when test="${ostate eq '5' }">
                   구매확정
                </c:when>
                <c:otherwise></c:otherwise>
            </c:choose>
         </span>
         <input type="button" value="주문자 정보" onclick="location.href='mypageOrderBy.do?sid=${session_sid }&oid=${orderjoin.oid }'" style="float:right;">
         <p>
         </c:if>
   </c:forEach>
   </div>
   </div>
         </div>
</div>
</div>         
</body>
</html>