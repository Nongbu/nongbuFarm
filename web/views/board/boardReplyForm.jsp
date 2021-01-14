<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int boardNum = Integer.parseInt(request.getParameter("bnum"));
	int currentPage = Integer.parseInt(request.getParameter("page"));
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의사항 댓글달기</title>
</head>
<body>
<%@ include file="../common/header.jsp" %>
<hr>
<h2 align="center"><%= boardNum %>번 게시글 댓글달기</h2>

<form action="/nongbu/breply" method="post">
<input type="hidden" name="bnum" value="<%= boardNum %>">
<input type="hidden" name="page" value="<%= currentPage %>">
<table align="center" width="500" border="1" cellspacing="0" cellpadding="3">
<tr><th>제 목</th><td><input type="text" name="btitle" size="50"></td></tr>
<tr><th>작성자</th>
<td><input type="text" name="bwriter" value="<%= loginMember.getUserId() %>" readonly></td></tr>
<tr><th>내용</th><td><textarea name="bcontent" rows="5" cols="50"></textarea></td></tr>
<tr><th colspan="2"><input type="submit" value="댓글등록"> &nbsp; 
<input type="reset" value="작성취소"></th></tr>
<tr><th colspan="2">
	<a href="/nongbu/blist?page=<%= currentPage %>">목록</a> &nbsp; 
	<a href="javascript:history.go(-1);">이전 페이지로</a>
</th></tr>
</table>
</form>


<hr>
<%@ include file="../common/footer.jsp" %>
</body>
</html>