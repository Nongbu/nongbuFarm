<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int currentPage = Integer.parseInt(request.getParameter("page"));
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>nongbu</title>
</head>
<body>
<%@ include file="../common/header.jsp" %>
<hr>
<h2 align="center">무료나눔 글 등록 페이지</h2>
<%-- form 의 입력값 전송시, 파일업로드도 함께 하려면
     반드시 enctype="multipart/form-data" 속성 추가해야 함 --%>
<form action="/nongbu/finsert" method="post" enctype="multipart/form-data">
<table align="center" width="500" border="1" cellspacing="0" cellpadding="3">
<tr><th>제 목</th><td><input type="text" name="btitle" size="50"></td></tr>
<tr><th>작성자</th>
<td><input type="text" name="bwriter" value="<%= loginMember.getUserId() %>" readonly></td></tr>
<tr><th>첨부파일</th><td><input type="file" name="upfile"></td></tr>
<tr><th>내용</th><td><textarea name="bcontent" rows="5" cols="50"></textarea></td></tr>
<tr><th colspan="2"><input type="submit" value="원글등록"> &nbsp; 
<input type="reset" value="작성취소"></th></tr>
<tr><th colspan="2">
	<a href="/nongbu/flist?page=<%= currentPage %>">목록</a> &nbsp; 
	<a href="javascript:history.go(-1);">이전 페이지로</a>
</th></tr>
</table>
</form>
<hr>
<%@ include file="../common/footer.jsp" %>
</body>
</html>