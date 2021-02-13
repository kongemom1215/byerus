<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
#content{text-align: center;}
table{width: 35%;
		  height: 80px;
		  margin: auto;
		  font-weight: bolder;
		  }
td{padding: 15px; border-bottom: 1px solid black;}
th{border: none;}
</style>
</head>

<body>
<div style="width: 900px; margin-left: 350px; position: relative;">
<img src="img/Logo.png">
<input type="button" value="위시리스트" class="top" style="float: right; background-color: white; border: 0px;"></input>
<input type="button" value="장바구니" class="top" style="float: right; background-color: white; border: 0px;"></input>
<input type="button" value="주문/배송" class="top" style="float: right; background-color: white; border: 0px;"></input>
<input type="button" value="마이페이지" class="top" style="float: right; background-color: white; border: 0px;"></input>
<c:if test="${stype ==2}">
<input type="button" value="로그인/회원가입" class="top" style="float: right; background-color: white; border: 0px;" onclick="location.href='login.do?stype=${stype}'"></input>
<form>
</c:if>
<c:if test="${stype ==1}">
<input type="button" value="로그아웃" class="top" style="float: right; background-color: white; border: 0px;" onclick="location.href='main.do?stype=${stype}'"></input>
</c:if>
</form>

</div>
<hr>
<div style="width: 900px; height: 10px; display: table-cell;  vertical-align: middle; position: relative;">
<div style="width: 900px; margin-left: 350px;">
<div style="position:relative; float: left;"><input type="button" style="width: 300px; background-color: white; border: 0px;" value="ABOUT US"></div>
<div style="position:relative; float: left;"><input type="button" style="width: 300px; background-color: white; border: 0px;" value="SHOPPING"></div>
<div style="position:relative; float: left;"><input type="button" style="width: 300px; background-color: white; border: 0px;" value="BOARD"></div>
</div>
</div>
<hr>

	
	<c:choose>
<c:when test="${not empty session_sid }">
	<div id="content">
		   <h2>문의</h2>
		   <table>
		    <td style="text-align:right;"><a href="writeForm.do?session_sid=${session_sid}&session_stype=${session_stype}&session_sname=${session_sname}">글쓰기</a></td>
		    </table>
		     <table>
		<tr>
			<th>문의번호</th>
			<th>작성자</th>
			<th>카테고리</th>
			<th>제목</th>
			<th>작성일</th>
		</tr>
			<c:if test="${totCnt > 0 }">
		     <c:forEach var="qna" items="${list }">
		        <tr>
                    <td>${qna.qid }</td>
                    <td>${qna.sname }</td>
                    <td>${qna.qctg }</td>
                     <td><a href='content.do?sid=${qna.sid }&pageNum=${currentPage}'>
                   문의드립니다~★</a></td> 
					<td>${qna.qdate}</td>
		     	</tr>
		      <c:set var="startNum" value="${startNum - 1 }" />
		      </c:forEach>
		</c:if>
		       </table>
	
		    
		    
		    
		    
	   <c:if test="${totCnt == 0 }">
			<tr> <td colspan=7>데이터가 없네</td> </tr>
	   </c:if>	
		</table>
	<div style="text-align: center;">
		<c:if test="${startPage > blockSize }">
			<a href='qna.do?pageNum=${startPage-blockSize}'>[이전]</a>
		</c:if>
		<c:forEach var="i" begin="${startPage}" end="${endPage}">
			<a href='qna.do?pageNum=${i}'>[${i}]</a>
		</c:forEach>
		<c:if test="${endPage < pageCnt }">
			<a href='qna.do?pageNum=${startPage+blockSize}'>[다음]</a>
		</c:if>
	</div>
</c:when>

<c:when test="${empty session_sid }">
	<script type="text/javascript">
		alert("로그인을 해주세요");
		history.go(-1);
	</script>
</c:when>
</c:choose>

</body>
</html>