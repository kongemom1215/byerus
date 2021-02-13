<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bye-rus!</title>
<link rel="stylesheet" href="./css/ProductDetailCss.css?ver=3">
<link rel="stylesheet" type="text/css" href="css/YoungCSS.css?ver=2s">
<style>
.qnaboard {
	text-align: center;
	float: left;
}

.qnaboardtable {
	margin-top: 40px;
	padding: 5px;
	margin-left: 40px;
	float: left;
}

.qnaboardtable th {
	background-color: #C8FFFF;
	height: 30px;
	border-bottom: 3px solid #E1F6FA;
}

.reviewboard {
	text-align: center;
	float: left;
}

.reviewboardtable {
	margin-top: 40px;
	padding: 5px;
	margin-left: 40px;
	padding: 5px;
}

.reviewboardtable th {
	background-color: #C8FFFF;
	height: 30px;
	border-bottom: 3px solid #E1F6FA;
}
</style>
</head>
<body onload="init();">
	<script type="text/javascript">
		function addComma(num) {
			var regexp = /\B(?=(\d{3})+(?!\d))/g;
			return num.toString().replace(regexp, ',');
		}

		var sell_price;
		var howmany;

		function init() {
			sell_price = document.form.sell_price.value;
			howmany = document.form.howmany.value;
			document.form.sum.value = addComma(Math.round(sell_price));
			change();
		}

		function add() {
			hm = document.form.howmany;
			sum = document.form.sum;
			hm.value++;

			sum.value = addComma(Math.round(parseInt(hm.value) * sell_price));
		}

		function del() {
			hm = document.form.howmany;
			sum = document.form.sum;
			if (hm.value > 1) {
				hm.value--;
				sum.value = addComma(Math
						.round(parseInt(hm.value) * sell_price));
			}
		}

		function change() {
			hm = document.form.howmany;
			sum = addComma(Math.round(document.form.sum));

			if (hm.value < 0) {
				hm.value = 0;
			}

			addComma(Math.round(parseInt(hm.value) * sell_price));
			remoconSwitch();
		}
		
		function reviewchk() {
			let buycheck = ${buycheck};

			if (buycheck < 0) {
				alert('구매이력이 없습니다');
				return false;
			} else if (buycheck == 1) {
				alert('구매이력 확인을 위해 로그인하세요.');
				return false;
			} else if (buycheck == 7) {
				alert('이미 리뷰를 작성하셨습니다');
				return false;
			} else if (buycheck == 0) {
				location.href="mypageYetReview.do";
				return false;
			}
		}
		
		function qnachk() {
			let buycheck = ${buycheck};
			
			if (buycheck == 1) {
				alert('문의글 작성을 위해 로그인하세요.');
				return false;
			} else {
				location.href="writeForm.do?pid=" + ${pid};
				return false;
			}
		}
		
	</script>
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
					<a href="login.do?url=productDetail.do&pid=${pid }" class="top_button">로그인/회원가입</a>
				</c:otherwise>
			</c:choose>

		</div>
		<hr>
	</div>
	
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
		<hr style="margin-bottom: 10px; border-bottom: 0px;">
		<div class="main">
			<div class="upmenubutton">
				<a href='shoppingMain.do?cate=total'> <input type="button"
					style="width: 90px; background-color: white; border: 0px; font-size: 16px;"
					value="전체상품"></input></a> <a href='shoppingMain.do?cate=cheon'><input
					type="button"
					style="width: 90px; background-color: white; border: 0px; font-size: 16px;"
					value="체온측정"></input></a> <a href='shoppingMain.do?cate=sodok'> <input
					type="button"
					style="width: 90px; background-color: white; border: 0px; font-size: 16px;"
					value="소독 / 손"></input><a> <a href='shoppingMain.do?cate=alco'><input
							type="button"
							style="width: 90px; background-color: white; border: 0px; font-size: 16px;"
							value="소독 / 알콜"></input></a><a href='shoppingMain.do?cate=mask'><input
							type="button"
							style="width: 90px; background-color: white; border: 0px; font-size: 16px;"
							value="마스크"></input></a> <a href='shoppingMain.do?cate=bangdok'><input
							type="button"
							style="width: 90px; background-color: white; border: 0px; font-size: 16px;"
							value="방독면"></input></a> <a href='shoppingMain.do?cate=sejung'><input
							type="button"
							style="width: 90px; background-color: white; border: 0px; font-size: 16px;"
							value="세정제"></input></a> <a href='shoppingMain.do?cate=home'><input
							type="button"
							style="width: 90px; background-color: white; border: 0px; font-size: 16px;"
							value="재택용품"></input></a> <a href='shoppingMain.do?cate=etc'><input
							type="button"
							style="width: 90px; background-color: white; border: 0px; font-size: 16px;"
							value="기타"></input></a>
			</div>
			<!-- upmenubutton 닫음 -->
		</div>
		<!-- upmenu 닫음 -->
		<br> <br> <br> <br>
		<div class="main">
			<div class="productpath">
				<a href='shoppingMain.do?cate=${pobject.ptype }'> HOME > <c:if
						test="${pobject.ptype eq 'cheon'}">체온측정 </c:if> <c:if
						test="${pobject.ptype eq 'sodok'}">소독 / 손 </c:if> <c:if
						test="${pobject.ptype eq 'alco'}">소독 / 알콜 </c:if> <c:if
						test="${pobject.ptype eq 'mask'}">마스크 </c:if> <c:if
						test="${pobject.ptype eq 'bangdok'}">방독면 등 </c:if> <c:if
						test="${pobject.ptype eq 'sejung'}">세정제 </c:if> <c:if
						test="${pobject.ptype eq 'home'}">재택용품 </c:if> <c:if
						test="${pobject.ptype eq 'etc'}">기타 </c:if>
				</a>
			</div>
			<hr>
			<br />
			<div class="productimage">
				<img src="${pobject.pthumbimg }" width="400" height="400"></img>
			</div>
			<div class="productinfo">
				<br /> <strong>${pobject.pname }</strong> <br /> <br />
				<hr>
				<form name="form">
					<div class="infotablediv">

						<table class="infotable">
							<tr height="30">
								<td width="130">판매가</td>

								<td><fmt:formatNumber type="number" pattern="#,###"
										value="${pobject.pprice }" />원</td>
							</tr>

							<c:if test="${pobject.pdiscount > 0 }">
								<tr height="30">
									<td width="130">할인판매가</td>
									<td><font color="red"> <fmt:formatNumber
												type="number" pattern="#,###"
												value="${pobject.pprice - (pobject.pdiscount / 100 * pobject.pprice) }" />원
											<font color="gray"><font size=2>(${pobject.pdiscount }%
													off)
													</h5></td>
									</font>
								</tr>
							</c:if>
							<tr height="30">
								<td width="130">주문수량</td>
								<td><input type="button" value=" - " onclick="del();">
									<input type=hidden name="sell_price"
									value="${pobject.pprice - (pobject.pdiscount / 100 * pobject.pprice) }">
									<input type="text" name="howmany" value="1" size="3"
									onchange="change();"> <input type="button" value=" + "
									onclick="add();"></td>
							</tr>

							<c:if test="${poptions ne null}">

								<tr height="30">
									<td width="130">옵션선택</td>
									<td><select name="option" style="width: 100px;">

											<c:forEach var="options" items="${poptions }">
												<option value="${options }">${options }</option>
											</c:forEach>
									</select></td>
								</tr>
							</c:if>

							<tr height="50"></tr>
							<tr height="50">

								<td width="130">예상결제금액</td>
								<td>금액 : <input type="text" name="sum" size="11" readonly>원
								</td>


							</tr>
						</table>
						<input type="hidden" name="pid" value="${pobject.pid }">
					</div>
					<!-- infotablediv close -->
					<!-- 홍주님과 협업 -->
					<div class="purchase">
						<c:if test="${empty session_sid}">
							<input type="button" class="pbuttons" value="바로구매"
								onclick="location.href='login.do?url=productDetail.do&pid=${pid }'">
							<input type="button" class="pbuttons" value="장바구니"
								onclick="location.href='login.do?url=productDetail.do&pid=${pid }'">
							<input type="button" class="pbuttons" value="찜"
								onclick="location.href='login.do?url=productDetail.do&pid=${pid }'">
						</c:if>
						<c:if test="${not empty session_sid}">
							<input type="submit" value="바로구매" class="pbuttons"
								formaction="goOrder.do?pid=${pid}&option=${option}&howmany=${howmany}"></input>
							<input type="submit" value="장바구니" class="pbuttons"
								formaction="cartAdd.do?pid=${pid}&option=${option}&howmany=${howmany}&sum=${sum}"></input>
							<input type="submit" value="찜" class="pbuttons"
								formaction="jjim.do"></input>
						</c:if>
					</div>
				</form>
				<!-- purchase close -->
			</div>
			<!-- productinfo close -->
			<div class="detailbody">

				<div class="detailupside"></div>
				<br /> <br />
				<div class="detailimages">
					<img src="${pobject.col1 }" width="690"></img> <img
						src="${pobject.col2 }" width="690"></img> <img
						src="${pobject.col3 }" width="690"></img>

				</div>



				<div class="boardzone">

					<div class="reviewboard">
						<table class="reviewboardtable">
							<a name="revlocation"></a>
							<tr hegiht="30px">
								<td colspan=4><span style="color: navy;">후기게시판</span></td>
							</tr>
							<tr height="40px">
								<td></td>
								<td></td>
								<td></td>
								<td>
									<form onsubmit="return reviewchk();">
										<input type="submit" value="리뷰글 쓰기" class="pbuttons"></input>
									</form> <br />
								</td>
							</tr>
							<tr height="25px">
								<th width="100px">글번호</th>
								<th width="400px">리뷰제목</th>
								<th width="200px">작성자</th>
								<th width="100px">작성일</th>
							</tr>
							<c:if test="${rtotCnt > 0 }">

								<c:forEach var="reviews" items="${rlist }">
									<tr height="25px">
										<td>${rstartNum }</td>
										<td>${reviews.rtitle }</td>
										<td>${reviews.sname }</td>
										<td>${reviews.rdate }</td>
									</tr>
									<tr height="20px"></tr>
									<tr height="40px">
										<td></td>

										<td style="text-align: left; background-color: #B9E2FA;"
											colspan=2;><span
											style="font-size: x-small; color: #1E3269; padding: 7px;">
												${reviews.rcontent }</span></td>
									</tr>
									<c:set var="rstartNum" value="${rstartNum - 1 }" />
								</c:forEach>
							</c:if>
							<c:if test="${rtotCnt == 0 }">
								<tr>
									<td colspan=4>작성된 리뷰가 없습니다.</td>
								</tr>
							</c:if>
						</table>
						<br />

						<div style="text-align: center;">
							<c:if test="${rstartPage > rblockSize }">
								<a
									href='productDetail.do?rpageNum=${rstartPage-rblockSize}&pid=${pid }#revlocation'>[이전]</a>
							</c:if>
							<c:forEach var="i" begin="${rstartPage}" end="${rendPage}">
								<a href='productDetail.do?rpageNum=${i}&pid=${pid }#revlocation'>[${i}]</a>
							</c:forEach>
							<c:if test="${rendPage < rpageCnt }">
								<a
									href='productDetail.do?rpageNum=${rstartPage+rblockSize}&pid=${pid }#revlocation'>[다음]</a>
							</c:if>
						</div>
					</div>


					<div class="qnaboard">
						<table class="qnaboardtable">
							<a name="qnalocation"></a>
							<tr hegiht="30px">
								<td colspan=4><span style="color: navy;">문의게시판</span></td>
							</tr>
							<tr height="40px">
								<td></td>
								<td></td>
								<td></td>
								<td>
									<form onsubmit="return qnachk();">
										<input type="submit" value="문의글 쓰기" class="pbuttons"></input>
									</form> <br />
								</td>
							</tr>
							<tr height="25px">
								<th width="100px">글번호</th>
								<th width="400px">문의제목</th>
								<th width="200px">작성자</th>
								<th width="100px">작성일</th>
							</tr>
							<c:if test="${qtotCnt > 0 }">
								<c:forEach var="qnas" items="${qlist }">
									<tr height="25px">
										<td>${qstartNum }</td>
										<td style="text-align: left;"><a href=''>${qnas.qcontent }</a></td>
										<td>${qnas.sname }</td>
										<td>${qnas.qdate }</td>
									</tr>
									<c:if test="${qnas.qfile ne null}">
										<tr>
											<td></td>
											<td colspan="2"><img src="${qnas.qfile }" height="300">
											</td>
											<td></td>
										</tr>
									</c:if>
									<c:if test="${qnas.qcmt ne null}">
										<tr>
											<td></td>
											<td style="text-align: left; background-color: #B9E2FA;"><span
												style="font-size: x-small; color: #1E3269; padding: 7px;">${qnas.qcmt }</span>
											</td>
											<td>BYE-RUS</td>
											<td></td>
										</tr>
									</c:if>
									<c:set var="qstartNum" value="${qstartNum - 1 }" />
								</c:forEach>
							</c:if>
							<c:if test="${qtotCnt == 0 }">
								<tr>
									<td colspan=4>작성된 문의가 없습니다.</td>
								</tr>
							</c:if>

						</table>
						<br />

						<div style="text-align: center;">
							<c:if test="${qstartPage > qblockSize }">
								<a
									href='productDetail.do?qpageNum=${qstartPage-qblockSize}&pid=${pid }#qnalocation'>[이전]</a>
							</c:if>
							<c:forEach var="i" begin="${qstartPage}" end="${qendPage}">
								<a href='productDetail.do?qpageNum=${i}&pid=${pid }#qnalocation'>[${i}]</a>
							</c:forEach>
							<c:if test="${qendPage < qpageCnt }">
								<a
									href='productDetail.do?qpageNum=${qstartPage+qblockSize}&pid=${pid }#qnalocation'>[다음]</a>
							</c:if>
						</div>
					</div>


					<a
						style="display: scroll; position: fixed; bottom: 10px; right: 200px;"
						href="#" title=”맨위로"> <img src="./img/top.png" width="50"
						height="40">
					</a> <br /> <br />

					<div class="recom">
						<img src="./img/bulb.gif" width="50">이 카테고리의 인기 상품
					</div>
					<br /> <br />
					<table class="recomproduct" id="rptable">
						<tr height="180px">

							<c:forEach var="best4" items="${BEST4PRODUCTS }">

								<td width="180px"><a
									href='productDetail.do?pid=${best4.pid }'><img
										src="${best4.pthumbimg }" width="100" height="100"></img></td>
								<td width="50px"></td>
							</c:forEach>

						</tr>
						<tr height="40px">
							<c:forEach var="best4" items="${BEST4PRODUCTS }">
								<td><span style="font-size: x-small; color: gray;">${best4.pname }</span></td>
								<td></td>
							</c:forEach>

						</tr>

					</table>
				</div>
				<br /> <br />

				<div class="main"
					style="height: 50px; background-color: #767171; display: table;">
					<div style="width: 10px;"></div>
					<div
						style="display: table-cell; vertical-align: middle; margin-left: 5px; text-align: left;">
						대표 : 임주혜 / 사업자등록번호 : 123-45-67899</div>
					<div
						style="display: table-cell; vertical-align: middle; text-align: right;">
						<a>이용약관</a> / <a>개인정보처리방침</a> / <a>입점문의</a>
					</div>
					<div style="width: 10px;"></div>
				</div>
			</div>
		</div>
	</div>



	<c:if test="${remotelength > 3 }">
		<div id="remocon"
			style="width: 120px; background: #F0F0F0; border: 1px solid #D7D7D7; cursor: move; display: none; text-align: center;"
			draggable="true" dragarea="document.body">
			내가 본 상품&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:remoconSwitch();">x</a>
			<table border="0" width="100%" height="100%" bgcolor="#FFFFFF"
				style="cursor: default; font-size: 9pt;">
				<tr>
					<td><a href='productDetail.do?pid=${nprdt.pid }'><img
							src="${nprdt.pthumbimg }" width="100" height="100"></img></a></td>
				</tr>

				<tr>
					<td><a href='productDetail.do?pid=${oprdt.pid }'><img
							src="${oprdt.pthumbimg }" width="100" height="100"></img></a></td>
				</tr>

				<tr>
					<td><a href='productDetail.do?pid=${pprdt.pid }'><img
							src="${pprdt.pthumbimg }" width="100" height="100"></img></a></td>
				</tr>
			</table>
		</div>
	</c:if>





	<script language="javascript" src="./js/Floating.js"></script>
	<script language="javascript" src="./js/Drag.js"></script>
	<script language="javascript">
		var myFloating = new Floating(document.getElementById("remocon"), 0, 0,
				10, 10);
		var myDrag = new Drag("draggable", "dragelement", "dragarea");
		var FloatingPause = false;

		myDrag
				.addEvent(
						document.getElementById("remocon"),
						"onmousedown",
						function(event) {
							if (event.button == 0 || event.button == 1) {
								window.clearTimeout(myFloating.setTimeOut);
								if (myFloating.Body.scrollWidth > myFloating.Body.clientWidth) {
									myDrag.LimitEndX += (myFloating.Body.scrollWidth - myFloating.Body.clientWidth);
								}
								if (myFloating.Body.scrollHeight > myFloating.Body.clientHeight) {
									myDrag.LimitEndY += (myFloating.Body.scrollHeight - myFloating.Body.clientHeight);
								}
								FloatingPause = true;
							}
						});

		myDrag
				.addEvent(
						window,
						"onscroll",
						function() {
							if (FloatingPause == true
									&& document.getElementById("FloatingOnOff").checked == true) {
								myFloating.MarginX = document
										.getElementById("remocon").offsetLeft
										- myFloating.Body.scrollLeft;
								myFloating.Run();
								FloatingPause = false;
							}
						});

		function remoconSwitch() {
			document.getElementById('remocon').style.display = (document
					.getElementById('remocon').style.display == "inline") ? "none"
					: "inline";
			myFloating.MarginX = myFloating.Body.clientWidth
					- document.getElementById("remocon").offsetWidth;
		}
	</script>
</body>
</html>