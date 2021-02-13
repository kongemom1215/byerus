<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="dao.god.ShoppingUserDao" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 중복 확인</title>
</head>
<body>
<%
 	int result = 0;
 	String semail=request.getParameter("semail");
 	
 	ShoppingUserDao sd=ShoppingUserDao.getInstance();
 	result = sd.idCheck(semail);
%>
<%
	if(result == 1){
%>
	<script type="text/javascript">
		window.opener.document.fr.confirm.value=1;
	</script>
	사용 가능한 아이디입니다.
<%}else{ %>
	<script type="text/javascript">
		window.opener.document.fr.confirm.value=0;
	</script>
	중복된 아이디 입니다. 다른 아이디를 사용해주세요.
<%} %>
<input type="button" value="창닫기" onclick="window.close()">
</body>
</html>