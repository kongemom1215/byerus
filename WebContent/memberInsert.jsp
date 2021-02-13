<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bye-rus!</title>
<link rel="stylesheet" type="text/css" href="css/memberadmin.css?ver=52"/>
<link rel="stylesheet" type="text/css" href="css/YoungCSS.css?ver=1">
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">
    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수
                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }
                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("sample6_extraAddress").value = extraAddr;
                
                } else {
                    document.getElementById("sample6_extraAddress").value = '';
                }
                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample6_postcode').value = data.zonecode;
                document.getElementById("sample6_address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("sample6_detailAddress").focus();
            }
        }).open();
    }
    
    
    
    function id_contain(){
		if(document.fr.semail1.value =="" || document.fr.semail1.value.length < 0){
			alert("이메일을 먼저 입력해주세요");
			document.fr.semail1.focus();
		}
		else{
			if(document.all("semail2").readOnly==true){
				if(document.all("semail2").value==""){
					alert("이메일 주소 선택해주세요");
					document.fr.semail2.focus();
				}
				else{
					var url="idCheck.jsp";
					var semail=document.fr.semail1.value+"@"+document.fr.semail2.value;
					url=url+"?semail="+semail;
					window.open(url, "get", "height = 180, width = 300");
				}
			}
			else{
				if(fr.semail2.value!=""){
					var url="idCheck.jsp";
					var semail=document.fr.semail1.value+"@"+document.fr.semail2.value;
					url=url+"?semail="+semail;
					window.open(url, "get", "height = 180, width = 300");
				}
				else{	
					alert("이메일 주소 선택해주세요");
					document.fr.semail2.focus();
				}
			}	
		}
			
	}
	function SetEmailTail(emailValue) {
		  var email = document.all("semail1")    // 사용자 입력
		  var emailTail = document.all("semail2") // Select box
		  
		  if ( emailValue == "selfInput" ) {
		   emailTail.readOnly = false;
		   emailTail.value = "";
		   emailTail.focus();
		  } 
		  else if ( emailValue == "" ) {
			   emailTail.readOnly = true;
			   emailTail.value = "";
		  }
		  else {
		   emailTail.readOnly = true;
		   emailTail.value = emailValue;
		  }
	}
	
	function chk(){
		if(document.fr.confirm.value==0){
			alert("아이디 중복 체크해주세요");
			return false;
		}
		else
			return true;
	}
</script>
<style type="text/css">
table{
	display: inline-block;
	width: 550px;
	height:200px;
	margin-left:0px;
	margin-top:100px;
	table-layout: fixed;
	text-align:left;
}
td, th{
	padding:10px;
	background-color: white;
}
td:first-child{
	width:100px;
}
td:nth-child(2){
	width:350px;
}
</style>
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
			<img src="./img/admin_member.JPG" id="img1">
			<h2 class="memberMenu">회원 관리</h2>
			<div class="memberMenuButton">
				<form>
					<input type="button" value="● 회원 정보 조회" class="button" onclick="location.href='memberAdminPage.do'" ></input><br>
					<input type="button" value="● 회원 추가" class="button" style="color:#00B9FF; font-weight:bold;" onclick="location.href='memberInsert.do'"></input><br>
					<input type="button" value="● 쿠폰 등록" class="button" onclick="location.href='couponAdminPage.do'"></input>
				</form>
			</div>
		</div>
		<div id="content">
			<div id="insert_content">
				<div id="insert_content2">
					<form name="fr" action="memberInsertPro.do" method="post"  onsubmit="return chk()">
						<input type="hidden" id="confirm">
						<table>
							<tr>
								<th colspan="2">회원 등록하기</th>
							</tr>
							<tr>
								<td>이름</td>
								<td><input type="text" name="sname" required="required"></td>
							</tr>
							<tr>
								<td>이메일</td>
								<td><input type="text" name="semail1" required="required" size=5 maxlength=20>
										@
									<input type="text" name="semail2" value="" readonly="readonly" size=10/>
									<select name="emailCheck"
									onchange="SetEmailTail(emailCheck.options[this.selectedIndex].value)">
										<option value="">===선택===</option>
										<option value="gmail.com">gmail.com</option>
										<option value="naver.com">naver.com</option>
										<option value="daum.net">daum.net</option>
										<option value="selfInput">직접입력</option>
									</select>
								</td>
								<td><input type="button" value="중복체크" onclick="id_contain()" class="button3"></td>
							</tr>
							<tr>
								<td>비밀번호</td>
								<td><input type="text" name="spwd" required="required"></td>
							</tr>
							<tr>
								<td>연락처</td>
								<td><input type="text" name="scontact" required="required"></td>
							</tr>
							<tr>
								<td>우편번호</td>
								<td><input type="text" name="spost" id="sample6_postcode" required="required"></td>
								<td><input type="button" value="주소검색" onclick="sample6_execDaumPostcode()" class="button3"></td>
							</tr>
							<tr>
								<td>주소</td>
								<td><input type="text" name="saddress" id="sample6_address" style="border:none;"><input type="text" id="sample6_extraAddress" style="border:none;"></td>
							</tr>
							<tr>
								<td>상세 주소</td>
								<td><input type="text" id="sample6_detailAddress" name="saddress2" required="required"></td>
							</tr>
							<tr>
								<td>질문 선택</td>
								<td><select name="squestion" required="required">
									<option value="1">기억에 남는 추억의 장소는?</option>
									<option value="2">자신의 인생 좌우명은?</option>
									<option value="3">자신의 보물 제1호는?</option>
									<option value="4">가장 기억에 남는 선생님 성함은?</option>
									<option value="5">타인이 모르는 자신만의 신체비밀이 있다면?</option>
									<option value="6">추억하고 싶은 날짜가 있다면?</option>
									<option value="7">받았던 선물 중 기억에 남는 독특한 선물은?</option>
									</select>
								</td>
							</tr>
							<tr>
								<td>질문 대답</td>
								<td><input type="text" name="sanswer" required="required"></td>
							</tr>
							<tr>
								<td>마켓팅 동의</td>
								<td>
									<input type="radio" name="sagree" value="y"><label for='y'>예</label>
									<input type="radio" name="sagree" value="n"><label for='n'>아니오</label></td>
								<td><input type="button" value="약관보기" onclick="window.open('agree.jsp', 'get', 'height = 200, width = 630')" class="button3"></td>
							</tr>
							<tr>
								<td><input type="submit" value="등록" class="button2"></td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>