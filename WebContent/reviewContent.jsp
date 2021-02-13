<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		  white-space: nowrap;;
		  overflow: hidden;
		  text-overflow: ellipsis;
		  } 
td{padding: 15px;}

</style>
</head>
<body>

<div id="content">
		<h2>리뷰내용</h2>
	
	<c:if test="${not empty rcontent}">
		     <table>
		<tr>
			<th>리뷰번호</th><th>리뷰내용</th><th>첨부파일</th>
			<th>작성자유저번호</th><th>작성일</th><th>조회수</th>

		</tr>
		        <tr>
		        	<td>${rid }</td>
                    <td>${rcontent }</td>
                    <td>${rimg }</td>
                    <td>${sid }</td>
                    <td>${rdate}</td>
                    <td>${rhit }</td>
		        </tr>
		        
		        <tr><td colspan="2">
		        	<input type="button" value="수정" onclick="location.href='reviewUpdateForm.do?sid=${sid}&pageNum=${pageNum }'">
		        	<%-- <input type="button" value="삭제"
		onclick="location.href='reviewDeleteForm.do?sid=${sid}&pageNum=${pageNum }'"></td></tr> --%>
		       </table>
		       
		       	    <%--  <input type="button" value="답변작성"
		onclick="location.href='answer.do?&pageNum=${pageNum }'"> --%>
		  </c:if>
		<c:if test="${empty rcontent }">
		<script type="text/javascript">
			alert("리뷰 내용이 없습니다..");
			history.go(-1);
			</script>
		</c:if>
		</div>
		

</body>
</html>