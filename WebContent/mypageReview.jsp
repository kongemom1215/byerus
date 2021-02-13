<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/mypageCoupon.css?ver=1">
<style type="text/css">
.button {
    width:90px;
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
<style type="text/css">
   .view { display:none; }
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
      <input type="button" class="button1" value="작성 가능한 리뷰" onclick="location.href='mypageYetReview.do?sid=${session_sid}'">
         <table>
         <caption><h2>상품 리뷰</h2></caption>
         <tr><td colspan="5" style="background-color:white;">내가 작성한 리뷰 : ${cntReview }</td></tr>   
               <tr>
                  <th>주문번호</th>
                  <th>상품명</th>
                  <th>리뷰제목</th>
                  <th>작성일</th>
                  <th>조회수</th>
               </tr>
               <c:forEach var="review" items="${list }">                  
                  <tr>                        
                     <td><fmt:formatDate pattern="yyyyMMdd"
                                    value="${review.odate }" /> - <fmt:formatNumber
                                    value="${review.oid }" pattern="00000" /></td>
                     <td>${review.getPname() }</td>
                     <td><a href="board.do?type=review&reivew_num=${review.rid }">${review.getRtitle() }</a></td>
                     <td>${review.getRdate() }</td>
                     <td>${review.getRhit() }</td>
                  </tr>
                  </c:forEach>   
               </table>
               
               <div style="text-align : center">
                  <c:if test="${startPage>blockSize }">
                     <a href="mypageReview.do?pageNum=${startPage-blockSize }">[이전]</a>
                  </c:if>
                  <c:forEach var="i" begin="${startPage }" end="${endPage}">
                     <a href="mypageReview.do?pageNum=${i }">[${i }]</a>
                  </c:forEach>
                  <c:if test="${endPage< pageCnt}">
                     <a href="mypageReview.do?pageNum=${startPage+blockPage }">[다음]</a>
                  </c:if>
               </div>
         </div>
         </div>
</div>         
</body>
</html>