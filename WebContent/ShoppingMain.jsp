<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bye-rus!</title>
<link rel="stylesheet" type="text/css" href="css/YoungCSS.css?ver=1">
<link rel="stylesheet" href="./css/ShoppingMainCss.css?ver=5">
<style>
.displaytable {
	padding-down: 5px;
}
</style>
<script type="text/javascript">
	function submit() {
		document.displayform.submit();
	}
</script>
</head>
<body>

	<div class="main">
		<div style="margin-top: 15px;">
			<a href="main.do"><img src="./img/Logo.png"></a>

			<c:choose>
				<c:when test="${session_stype eq '1'}">
					<a href="jjimForm.do" class="top_button">위시리스트</a>
					<a href="cart.do" class="top_button">장바구니</a>
					<a href="mypageOrder.do" class="top_button">주문/배송</a>
					<a href="mypage.do" class="top_button">마이페이지</a>
					<a href="main.do?logout=logout" class="top_button">로그아웃</a>
					<a class="top_button">${session_sname } 님</a>
				</c:when>
				<c:when test="${session_stype eq '0'}">
					<a href="main.do?logout=logout" class="top_button">로그아웃</a>
					<a class="top_button">${session_sname } 님</a>
					<a href="adminPage.do" class="top_button">관리페이지</a>
				</c:when>
				<c:otherwise>
					<a href="login.do?url=jjimForm.do" class="top_button">위시리스트</a>
					<a href="login.do?url=cart.do" class="top_button">장바구니</a>
					<a href="login.do?url=mypageOrder.do" class="top_button">주문/배송</a>
					<a href="login.do?url=mypage.do" class="top_button">마이페이지</a>
					<a href="login.do?url=shoppingMain.do" class="top_button">로그인/회원가입</a>
				</c:otherwise>
			</c:choose>

		</div>
	</div>
	<hr style="width: 900px; border-bottom: 0xp;">
	<div class="main">
		<div style="height: 17.33px;">
			<div class="nav_button">
				<a href="aboutUs.do">ABOUT US</a>
			</div>
			<div class="nav_button">
				<a href="shoppingMain.do">SHOPPING</a>
			</div>
			<div class="nav_button">
				<a href="board.do?type=notice">BOARD</a>
			</div>
		</div>
	</div>
	<hr style="width: 900px; margin-bottom: 0px; border-bottom: 0px;">
	<div class="main">
		<div class="catemenu">
			<div class="catesmallmenu">
				<a href='shoppingMain.do?cate=total'> <input type="button"
					style="width: 170px; background-color: white; border: 0px; font-size: 20px;"
					value="전체상품 (${totCnt })"></input></a> <br /> <a
					href='shoppingMain.do?cate=cheon'><input type="button"
					class="catebutton"
					style="width: 170px; background-color: white; border: 0px; font-size: 16px;"
					value="체온측정 (${cheonCnt})"></input></a> <br /> <a
					href='shoppingMain.do?cate=sodok'> </a><input type="button"
					style="width: 170px; background-color: white; border: 0px; font-size: 16px;"
					value="소독 / 손 (${sodokCnt})"></input><a> <br /> <a
					href='shoppingMain.do?cate=alco'> </a><input type="button"
					style="width: 170px; background-color: white; border: 0px; font-size: 16px;"
					value="소독 / 알콜 (${alcoCnt})"></input></a> <br /> <a
					href='shoppingMain.do?cate=mask'><input type="button"
					style="width: 170px; background-color: white; border: 0px; font-size: 16px;"
					value="마스크 (${maskCnt })"></input></a> <br /> <a
					href='shoppingMain.do?cate=bangdok'><input type="button"
					style="width: 170px; background-color: white; border: 0px; font-size: 16px;"
					value="방독면 등 (${bangdokCnt })"></input> </a><br /> <a
					href='shoppingMain.do?cate=sejung'><input type="button"
					style="width: 170px; background-color: white; border: 0px; font-size: 16px;"
					value="세정제 (${sejungCnt})"></input></a> <br /> <a
					href='shoppingMain.do?cate=home'><input type="button"
					style="width: 170px; background-color: white; border: 0px; font-size: 16px;"
					value="재택용품 (${homeCnt})"></input></a> <br /> <a
					href='shoppingMain.do?cate=etc'><input type="button"
					style="width: 170px; background-color: white; border: 0px; font-size: 16px;"
					value="기타 (${etcCnt })"></input></a> <br />
			</div>
			<div class="catespecialmenu">
				<a href='shoppingMain.do?cate=sale'><input type="button"
					style="width: 170px; background-color: white; border: 0px; font-size: 20px; color: #00EBFF;"
					value="ON SALE"><font size="3px">(${saleCnt})</font></input></a>
			</div>
		</div>
		<div class="product">
			<div class="bestimages">
				<table class="besttable" style="table-layout: fixed">
					<tr height="40px">
						<th width="151px"><img src="./img/crown.png" width="30"
							height="30">Top 1</th>
						<th width="151px"><img src="./img/crown.png" width="20"
							height="20">Top 2</th>
						<th width="151px"><img src="./img/crown.png" width="20"
							height="20">Top 3</th>
					</tr>

					<tr height="165px"
						style="text-overflow: ellipsis; overflow: hidden; white-space: nowrap;">
						<c:forEach var="best3" items="${BESTPRODUCTS }">
							<td width="151px"
								style="text-overflow: ellipsis; overflow: hidden; white-space: nowrap;"><a
								href='productDetail.do?pid=${best3.pid }'> <img
									src="${best3.pthumbimg }" width="150" height="150"></img></a></td>
						</c:forEach>
					</tr>
					<tr height="20px"
						style="text-overflow: ellipsis; overflow: hidden; white-space: nowrap;">
						<c:forEach var="best3" items="${BESTPRODUCTS }">
							<td width="151px"
								style="text-overflow: ellipsis; overflow: hidden; white-space: nowrap;"><a
								href='productDetail.do?pid=${best3.pid }'>${best3.pname }</a></td>
						</c:forEach>
					</tr>
					<tr>
						<c:forEach var="best3" items="${BESTPRODUCTS }">
							<td width="151px"
								style="text-overflow: ellipsis; overflow: hidden; white-space: nowrap;">

								<c:if test="${best3.pdiscount > 0 }">
									<font color="gray"><span
										style="text-decoration: line-through;"> <fmt:formatNumber
												type="number" pattern="#,###" value="${best3.pprice}" />원
									</span><br />
								</c:if> <span style="font-color: black;"> <fmt:formatNumber
										type="number" pattern="#,###"
										value="${best3.pprice - (best3.pdiscount / 100 * best3.pprice) }" />원
							</span> <c:if test="${best3.pdiscount > 0 }">
									</font>
									<font color="red"> (${best3.pdiscount }% off)</font>
								</c:if>
							</td>
						</c:forEach>
					</tr>
				</table>

			</div>
			<hr>

			<!-- 무엇을 보고있는지 출력 -->
			<div class="productdisplaygroup">
				<div class="watching">
					<font color="white" size="x-small"> <c:if test="${cate eq 'total'}">전체상품 : </c:if>
						<c:if test="${cate eq 'cheon'}">체온측정 : </c:if> <c:if
							test="${cate eq 'sodok'}">소독 / 손 : </c:if> <c:if
							test="${cate eq 'alco'}">소독 / 알콜 : </c:if> <c:if
							test="${cate eq 'mask'}">마스크 : </c:if> <c:if
							test="${cate eq 'bangdok'}">방독면 등 : </c:if> <c:if
							test="${cate eq 'sejung'}">세정제 : </c:if> <c:if
							test="${cate eq 'home'}">재택용품 : </c:if> <c:if
							test="${cate eq 'etc'}">기타 : </c:if> <c:if
							test="${cate eq 'sale'}">On Sale : </c:if> <c:if
							test="${display_select eq 'hot'}">인기상품순</c:if> <c:if
							test="${display_select eq 'new'}">최신순</c:if> <c:if
							test="${display_select eq 'expensive'}">높은가격순</c:if> <c:if
							test="${display_select eq 'cheap'}">낮은가격순</c:if>
					</font>
				</div>

				<div class="dropdown">
					<form
						action="shoppingMain.do?display_select=${display_select }&cate=${cate}"
						name="displayform">
						<select name="display_select" onchange="submit()">
							<option>== 정렬 ==</option>

							<option value="hot"
								<c:if test="${option eq 'hot'}">selected="selected"</c:if>>인기상품순</option>
							<option value="new"
								<c:if test="${option eq 'new'}">selected="selected"</c:if>>최신순</option>
							<option value="expensive"
								<c:if test="${option eq 'expensive'}">selected="selected"</c:if>>높은가격순</option>
							<option value="cheap"
								<c:if test="${option eq 'cheap'}">selected="selected"</c:if>>낮은가격순</option>
						</select> <input type="hidden" name="cate" value="${cate }">
					</form>
				</div>
				<!-- 여기부터 디스플레이 구역  -->
				<c:if test="${totCnt > 0 }">
					<div class="downside">

						<c:forEach var="hotproduct" items="${hotlist }">
							<table class="displaytable" style="table-layout: fixed">
								<tr height="150px">
									<td width="160px"><a
										href='productDetail.do?pid=${hotproduct.pid }'> <img
											src="${hotproduct.pthumbimg }" width="120" height="120"></img>
									</a></td>
								</tr>
								<tr height="35px">
									<td width="160px"><a
										href='productDetail.do?pid=${hotproduct.pid }'> <c:if
												test="${fn:length(hotproduct.pname)>25}">
											${fn:substring(hotproduct.pname,0,25) } ...
										</c:if> <c:if test="${fn:length(hotproduct.pname)<26}">
											${hotproduct.pname }
										</c:if>

									</a> <c:if test="${hotproduct.pdiscount > 0 }">
											<img src="./img/sale.png" width="25" height="17">
										</c:if></td>
								</tr>
								<tr height="40px">
									<td width="160px"><c:if
											test="${hotproduct.pdiscount > 0 }">
											<font color="#00F5FF"><span
												style="text-decoration: line-through;"> <fmt:formatNumber
														type="number" pattern="#,###" value="${hotproduct.pprice}" />원
											</span><br />
										</c:if> <font color="gray"><fmt:formatNumber type="number"
												pattern="#,###"
												value="${hotproduct.pprice - (hotproduct.pdiscount / 100 * hotproduct.pprice) }" />원</font>
										<c:if test="${hotproduct.pdiscount > 0 }">
											<font color="red" size="2"> (${hotproduct.pdiscount }%
												off)</font>
										</c:if></td>
								</tr>

							</table>
						</c:forEach>
					</div>
				</c:if>
				<br /> <br />

				<!-- 쪽번호 구역 -->
				<div class="pages">
					<c:if test="${startPage > blockSize }">
						<a
							href='shoppingMain.do?pageNum=${startPage-blockSize }&display_select=${display_select }&cate=${cate}'>[이전]</a>

					</c:if>
					<c:forEach var="i" begin="${startPage }" end="${endPage }">
						<a
							href='shoppingMain.do?pageNum=${i}&display_select=${display_select }&cate=${cate}'>[${i }]
						</a>
					</c:forEach>
					<c:if test="${endPage < pageCnt }">
						<a
							href='shoppingMain.do?pageNum=${startPage+blockSize }&display_select=${display_select }&cate=${cate}'>[다음]</a>
					</c:if>
				</div>
				<a
					style="display: scroll; position: fixed; bottom: 10px; right: 200px;"
					href="#" title=”맨위로"><img src="./img/top.png" width="50"
					height="40"></a>

			</div>

		</div>
		<div style="height: 1300px;"></div>
		<div class="main"
			style="height: 50px; background-color: #767171; display: table;">
			<div style="width: 10px;"></div>
			<div
				style="display: table-cell; vertical-align: middle; margin-left: 5px;">
				대표 : 임주혜 / 사업자등록번호 : 123-45-67899</div>
			<div
				style="display: table-cell; vertical-align: middle; text-align: right;">
				<a>이용약관</a> / <a>개인정보처리방침</a> / <a>입점문의</a>
			</div>
			<div style="width: 10px;"></div>
		</div>
	</div>
</body>
</html>