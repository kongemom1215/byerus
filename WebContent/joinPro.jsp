<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bye-rus!</title>
</head>
<body>

<c:if test="${result > 0 }">
	<script type="text/javascript">
		alert("회원가입 되었습니다");  
		location.href="login.do";
	</script>
</c:if>
<c:if test="${result == 0 }">  
	<script type="text/javascript">
		alert("회원가입 실패했습니다");  
		location.href="join.do";
	</script>
</c:if>
<%-- <c:if test="${sagree2 == n }">  
	<script type="text/javascript">
		alert("마케팅동의에 y해주세요");  
		location.href="main.do?pageNum=${pageNum}";
	</script>
</c:if> --%>
</body>
</html>