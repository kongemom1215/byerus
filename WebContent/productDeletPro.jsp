<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bye-rus!</title>
</head>
<body>
	<c:if test="${result>0 }">
		<script type="text/javascript">
			alert("삭제 완료");
		</script>
		<c:choose>
			<c:when test="${search_value ne null }">
				<script type="text/javascript">
					location.href="productSearch.do?pageNum=${pageNum}&option=${option}&search_value=${search_value}"
				</script>
			</c:when>
			<c:otherwise>
				<script type="text/javascript">
					location.href="productAdminPage.do?pageNum=${pageNum }";
				</script>
			</c:otherwise>
		</c:choose>
	</c:if>
</body>
</html>