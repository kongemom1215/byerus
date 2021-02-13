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


	<c:choose>
<c:when test="${not empty session_sid }">
<div id="content">
	<h2>리뷰</h2>
	<table>
		<td style="text-align:right;"><a href="reviewWriteForm.do?session_sid=${session_sid}&session_stype=${session_stype}&session_sname=${session_sname}&pageNum=${pageNum }">글쓰기</td>
	</table>
	<table>
		<tr>
			<th>리뷰번호</th>
			<th>작성자</th>
			<th>제목</th>
			<th>상품번호</th>
			<th>작성일</th>
		</tr>
		<c:if test="${totCnt > 0 }">
		     <c:forEach var="review" items="${list }">
		        <tr>
                    <td>${review.rid }</td>
                    <td>${review.sname }</td>
                    <td><a href='reviewContent.do?sid=${review.sid }&pageNum=${currentPage}&rid=${review.rid}'>${review.rtitle }</a></td>
                    <td>${review.pid }</td>
                    <td>${review.rdate }</td>
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
			<a href='review.do?pageNum=${startPage-blockSize}'>[이전]</a>
		</c:if>
		<c:forEach var="i" begin="${startPage}" end="${endPage}">
			<a href='review.do?pageNum=${i}'>[${i}]</a>
		</c:forEach>
		<c:if test="${endPage < pageCnt }">
			<a href='review.do?pageNum=${startPage+blockSize}'>[다음]</a>
		</c:if>
	</div>
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