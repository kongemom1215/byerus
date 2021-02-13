<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bye-rus!</title>
<link rel="stylesheet" type="text/css" href="css/YoungCSS.css?ver=1">
<style type="text/css">
h1{font-weight: 900;}
	#find{text-align: center; width: 300px; overflow: hidden; position: absolute; right: 50%; top: 30%;}
</style>
</head>
<body>

<div id="find">
<h1>계정찾기</h1>
<c:if test="${not empty result }">
	찾으시는 계정은 ${result }입니다. <p>
	<a href="login.do">로그인하러가기</a>
</c:if>
<c:if test="${empty result }">
<script type="text/javascript">
alert("잘못입력했습니다");
history.go(-1);
</script>
</c:if>
</div>
</body>
</html>