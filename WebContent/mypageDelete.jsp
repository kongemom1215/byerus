<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:if test="${result>0 }">
	<script type="text/javascript">
		alert("탈퇴 되었습니다ㅠㅠ");
		location.href="main.do";
	</script>
</c:if>
<c:if test="${result == 0 }">
	<script type="text/javascript">
		alert("비밀번호를 틀렸습니다. 이것은 탈퇴하지 말라는 운명..?^-^");
		location.href="mypageDeleteForm.do";
	</script>
</c:if>
</body>
</html>