<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="exchange.model.vo.Exchange" %>
<%
	Exchange exchange = (Exchange)request.getAttribute("exchange");
	int currentPage = ((Integer)request.getAttribute("page")).intValue();
%>    
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>nongbu</title>
</head>
<body>
<%@ include file="../common/header.jsp" %>
<hr>
<h2 align="center"><%= exchange.getExNo() %>번 교환 글 수정 페이지</h2>
<%-- 원글 수정 --%>
<% if(exchange.getExReplyLev() == 1){ %>
<form action="/nongbu/eoriginup" method="post" enctype="multipart/form-data">
<input type="hidden" name="bnum" value="<%= exchange.getExNo() %>">
<input type="hidden" name="page" value="<%= currentPage %>">
<input type="hidden" name="ofile" value="<%= exchange.getOriginalFileName() %>">
<input type="hidden" name="rfile"	value="<%= exchange.getRenameFileName() %>"> 
<table align="center" width="500" border="1" cellspacing="0" cellpadding="3">
<tr><th>제 목</th><td><input type="text" name="btitle" size="50" value="<%= exchange.getExTitle() %>"></td></tr>
<tr><th>작성자</th>
<td><input type="text" name="bwriter" value="<%= exchange.getExWriter() %>" readonly></td></tr>
<tr><th>첨부파일</th>
<td>
<% if(exchange.getOriginalFileName() != null){  %>
	<%= exchange.getOriginalFileName() %> &nbsp; 
	<input type="checkbox" name="deleteFlag" value="yes"> 파일삭제
<% } %>
<input type="file" name="upfile">
</td></tr>
<tr><th>내용</th><td><textarea name="bcontent" rows="5" cols="50"><%= exchange.getExContent() %></textarea></td></tr>
<tr><th colspan="2"><input type="submit" value="수정하기"> &nbsp; 
<input type="reset" value="수정취소"></th></tr>
<tr><th colspan="2">
	<a href="/nongbu/elist?page=<%= currentPage %>">목록</a> &nbsp; 
	<a href="javascript:history.go(-1);">이전 페이지로</a>
</th></tr>
</table>
</form>
<% }else{ %>
<%-- 댓글 | 대댓글 수정 --%>
<form action="/nongbu/ereplyup" method="post">
<input type="hidden" name="bnum" value="<%= exchange.getExNo() %>">
<input type="hidden" name="page" value="<%= currentPage %>">
<table align="center" width="500" border="1" cellspacing="0" cellpadding="3">
<tr><th>제 목</th><td><input type="text" name="btitle" size="50" value="<%= exchange.getExTitle() %>"></td></tr>
<tr><th>작성자</th>
<td><input type="text" name="bwriter" value="<%= exchange.getExWriter() %>" readonly></td></tr>
<tr><th>내용</th><td><textarea name="bcontent" rows="5" cols="50"><%= exchange.getExContent() %></textarea></td></tr>
<tr><th colspan="2"><input type="submit" value="수정하기"> &nbsp; 
<input type="reset" value="수정취소"></th></tr>
<tr><th colspan="2">
	<a href="/nongbu/elist?page=<%= currentPage %>">목록</a> &nbsp; 
	<a href="javascript:history.go(-1);">이전 페이지로</a>
</th></tr>
</table>
</form>
<% } %>
<hr>
<%@ include file="../common/footer.jsp" %>
</body>
</html>