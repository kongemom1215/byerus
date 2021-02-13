<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bye-rus!</title>
<link rel="stylesheet" type="text/css" href="css/salesAdmin.css?ver=127">
<link rel="stylesheet" type="text/css" href="css/YoungCSS.css?ver=1">
<script src="https://www.google.com/jsapi"></script>
<script type="text/javascript" src="./js/jquery.js"></script>
<script type="text/javascript">
	function chk(){
		if(frm.option.value=="none" || frm.option.value==""){
			alert("검색 옵션을 선택해 주십시오");
			return false;
		}
		if(!frm.search_value.value){
			alert("검색어를 입력해주세요.");
			return false;
		}
		if(isNaN(frm.search_value.value)){
			alert("숫자만 입력해주세요");
			return false;
		}
	}
	
	google.load('visualization', '1.0', {'packages':['corechart']});
	google.setOnLoadCallback(drawChart);

	function drawChart() {
		var data = new google.visualization.DataTable();
		var sixdays=new Array();
		var sixamount=new Array();
		sixdays.push("${sixdays[0]}");
		sixdays.push("${sixdays[1]}");
		sixdays.push("${sixdays[2]}");
		sixdays.push("${sixdays[3]}");
		sixdays.push("${sixdays[4]}");
		sixdays.push("${sixdays[5]}");
		sixamount.push("${salesAmount[0]}");
		sixamount.push("${salesAmount[1]}");
		sixamount.push("${salesAmount[2]}");
		sixamount.push("${salesAmount[3]}");
		sixamount.push("${salesAmount[4]}");
		sixamount.push("${salesAmount[5]}");
		data.addColumn('string', '날짜 ');
		data.addColumn('number', ' 매출액 ');
		data.addRows([
				[sixdays[0], Number(sixamount[0])], [sixdays[1], Number(sixamount[1])],
				[sixdays[2], Number(sixamount[2])], [sixdays[3], Number(sixamount[3])],
				[sixdays[4], Number(sixamount[4])], [sixdays[5], Number(sixamount[5])],
		]);

		var opt = {
			'title': '매출액 (단위 : 만원) ',
			'width': 600, 'height': 400,
		};

		var chart = new google.visualization.BarChart(
				document.getElementById('chart_div'));
		chart.draw(data, opt);
	}

</script>
</head>
<body>
<div class="main">
	<div style="margin-top: 15px;">
<a href="main.do"><img src="./img/Logo.png"></a>

<c:choose>
<c:when test="${session_stype eq '1'}">
<a class="top_button">위시리스트</a>
<a class="top_button">장바구니</a>
<a class="top_button">주문/배송</a>
<a class="top_button">마이페이지</a>
<a href="main.do?logout=logout" class="top_button">로그아웃</a>
<a class="top_button">${session_sname } 님</a>
</c:when>
<c:when test="${session_stype eq '0'}">
<a href="main.do?logout=logout" class="top_button">로그아웃</a>
<a class="top_button">${session_sname } 님</a>
<a href="adminPage.do" class="top_button">관리페이지</a>
</c:when>
<c:otherwise>
<a class="top_button">위시리스트</a>
<a class="top_button">장바구니</a>
<a class="top_button">주문/배송</a>
<a class="top_button">마이페이지</a>
<a href="login.do" class="top_button">로그인/회원가입</a>
</c:otherwise>
</c:choose>
</div>
</div>
<hr>
   <div class="main" style="width: 900px; height: 10px; display: table; vertical-align: middle; position: relative;">
      <div style="width: 900px; display: table-cell; text-align: center;">
       <input type="button" style="width: 300px; background-color: white; border: 0px;" value="ADMINISTRATOR SERVICE" onclick="location.href='adminPage.do'">
      </div>
   </div>
 <hr>
	<div class="main" style="width:1000px">
		<div id="sidebar">
			<img src="./img/admin_order.JPG" id="img1">
			<h2 class="memberMenu">결제&배송 관리</h2>
			<div class="memberMenuButton">
				<form>
					<input type="button" value="● 회원/주문번호 검색" class="button" onclick="location.href='orderAdminPage.jsp'" ></input><br>
					<input type="button" value="● 결제 내역 전체 조회" class="button" onclick="location.href='orderAllPage.do'"></input><br>
					<input type="button" value="● 매출 조회" class="button" style="color:#00B9FF; font-weight:bold;" onclick="location.href='salesAdminPage.do'"></input>
				</form>
			</div>
		</div>
		<div id="content">
			<div id="select">
			<table>
				<tr>
					<td><input type="button" value="일별" onclick="location.href='salesAdminPage.do'"></td>
					<td><input type="button" value="월별" onclick="location.href='salesMonth.do'"></td>
					<td><input type="button" value="연도별" onclick="location.href='salesYear.do'"></td>
				</tr>
			</table>
			</div>
			<div id="chart_div"></div>
		</div>
	</div>
</body>
</html>