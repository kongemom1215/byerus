<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="dao.god.ShoppingUserDao" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>���̵� �ߺ� Ȯ��</title>
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
	��� ������ ���̵��Դϴ�.
<%}else{ %>
	<script type="text/javascript">
		window.opener.document.fr.confirm.value=0;
	</script>
	�ߺ��� ���̵� �Դϴ�. �ٸ� ���̵� ������ּ���.
<%} %>
<input type="button" value="â�ݱ�" onclick="window.close()">
</body>
</html>