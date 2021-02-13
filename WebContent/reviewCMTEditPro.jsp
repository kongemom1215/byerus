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
	<c:if test="${result>0 }">
		<script type="text/javascript">
			alert("등록 완료");
			location.href="reviewInfo.do?rid=${rid}&pageNum=${pageNum}&rctg=${rctg}";
		</script>
	</c:if>
</body>
</html>