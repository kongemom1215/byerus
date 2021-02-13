<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bye-rus!</title>
<link rel="stylesheet" type="text/css" href="css/YoungCSS.css?ver=1">
<style type="text/css">
h1{font-weight: 900;}
	#find{text-align: center; width: 300px; overflow: hidden; position: absolute; right: 50%; top: 50%;}
</style>
</head>
<body>
	<div style="display: table; width: 100%; height: 80%;">
	<div style="display: table-cell; text-align: center; vertical-align: middle; ">
	<h1>계정찾기</h1>
		찾으시는 계정의 비밀번호는 ${result }입니다.<p>
		<a href="login.do">로그인하러가기<p></a>
	</div>
	</div>
</body>
</html>