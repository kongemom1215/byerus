<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BYE-RUS 공지사항</title>
<link rel="stylesheet" href="./css/popup.css?ver=2">

<script type="text/javascript">
	function setCookie(name, value, expiredays) {
		var date = new Date();
		date.setDate(date.getDate() + expiredays);
		document.cookie = escape(name) + "=" + escape(value) + "; expires="
				+ date.toUTCString();
	}

	function closePopup() {
		if (document.getElementById("check").value) {
			setCookie("popupYN", "N", 1);
			self.close();
		}
	}
</script>
</head>
<body>
	<table>
		<tr>
			<th>${ntc.ntitle }</th>
		</tr>
		<tr>
			<td></td>
		</tr>
		<tr>
			<td>${ntc.ncontent}</td>
		</tr>
		<c:if test="${ntc.nfile ne null}">
			<tr>
				<td><img src="${ntc.nfile }" width="200"></td>
			</tr>
		</c:if>
		<tr>
			<form>
				<input type="checkbox" id="check" onclick="closePopup();">
				<span style="font-size: small; font-color: gray;"> <b>24시간 안보기</b></span> </font>
				<br/><br/>
			</form>
		</tr>

	</table>
	<br />

</body>
</html>