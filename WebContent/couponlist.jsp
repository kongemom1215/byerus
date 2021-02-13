<%@page import="dao.god.Coupon"%>
<%@page import="dao.god.CouponDao"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
table{
	margin-left:50px;
	margin-top:10px;
	background-color: #EFF5FB;
	text-align: center;
}
td{
	padding:10px;
	border: 1px solid white;
}
</style>
<meta charset="UTF-8">
<title>Bye-rus!</title>
</head>
<body>
<%
	CouponDao cd=CouponDao.getInstance();
	int sid=Integer.parseInt(request.getParameter("sid"));
	List<Coupon> couponlist=cd.select(sid);
%>
	<table>
		<c:forEach var="coupon" items="<%=couponlist %>">
			<c:if test="${coupon.cusedate eq null }">
				<tr>
					<td rowspan="2"><img src="${coupon.couponimg }" style="width:100px; height:100px;"></td>
					<td>${coupon.cstartdate } ~ ${coupon.cenddate }</td>
				</tr>
				<tr>
					<td>할인률 : ${coupon.cdiscount }%</td>
				</tr>
			</c:if>
		</c:forEach>
	</table>
</body>
</html>