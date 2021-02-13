<%@page import="java.util.regex.Pattern"%>
<%@page import="dao.dragon.ShoppingUserDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bye-rus!</title>
</head>
<body>

<%
	String semail = request.getParameter("semail");
	
	System.out.println(semail);

	boolean b = Pattern.matches("[\\w\\~\\-\\.]+@[\\w\\~\\-]+(\\.[\\w\\~\\-]+)+",semail.trim());

	if (b) {
		ShoppingUserDao shoppinguserdao = ShoppingUserDao.getInstance();
		int result = shoppinguserdao.confirm(semail);
		if(result ==0){
			out.println("사용할 수 있는 아이디 입니다.");
		}else{
			out.println("이미 있는 아이디 입니다. 다른 것을 사용해주십시오.");
		}
	} else {
		out.println("이메일이 아닙니다.");
	}
 
	
%>
</body>
</html>