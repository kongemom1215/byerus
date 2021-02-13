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
		alert("로그인 하였습니다.");
			location.href="${url}?pid=${pid}&type=${type}";
		</script>
	</c:if>
	<c:if test="${result == 0 }">
		<script type="text/javascript">
			alert("비밀번호가 틀립니다");
			location.href="login.do?url=${url}";
		</script>
	</c:if>
	<c:if test="${result < 0 }">
		<script type="text/javascript">
			alert("없는 아이디입니다");
			location.href="login.do?url=${url}";
		</script>
	</c:if>
</body>
</html>