<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bye-rus!</title>
</head>
<body>
		<c:if test="${result == 1}">
		<input type="hidden" value="${sumDiscount}">
			<script type="text/javascript">
				alert("이미 장바구니에 담겨있어요");
				location.href="productDetail.do?pid="+${pid};
			</script>
	
		</c:if>
		<c:if test="${result == 0}">
		<input type="hidden" value="${sumDiscount}">
			<script type="text/javascript">
				alert("담기 성공");
				location.href="productDetail.do?pid="+${pid};
			</script>
		</c:if>
		<c:if test="${result == -1}">
		<input type="hidden" value="${sumDiscount}">
			<script type="text/javascript">
				alert("담기실패");
				location.href="productDetail.do?pid="+${pid};
			</script>
		</c:if>
	
	
</body>
</html>