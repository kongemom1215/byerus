<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div id="content">
<form action="reviewWritePro.do?pageNum=${pageNum }&session_sid=${session_sid}&session_stype=${session_stype}&session_sname=${session_sname}" method="post" enctype="multipart/form-data">
   <input type="hidden" name="num" value="${review.sid }">
   <input type="hidden" name="pageNum" value="${pageNum }">
   <table>
      <caption><h2>리뷰 작성</h2></caption>
      <tr><td>제목</td><td><input type="text" name="rtitle" required="required" placeholder="제목을 입력해주세요."></td></tr>
      <tr><td>내용</td><td  class="id"><textarea rows="10" cols="30" name="rcontent"
         required="required"></textarea></td></tr>
         
         <tr><td>파일</td><td><input type="file" name="rimg"></td></tr>
      <tr><td colspan="2"><input type="submit" value="글쓰기" style="height:30px; width:100px; cursor: pointer; background-color : white;"
   class="submit">
      </td></tr>
   </table>
</form>
</div>
</body>
</html>