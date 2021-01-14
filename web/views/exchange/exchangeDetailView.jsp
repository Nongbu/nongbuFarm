<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="exchange.model.vo.Exchange,member.model.vo.Member" %>   
<%
	Exchange exchange = (Exchange)request.getAttribute("exchange");
	int currentPage = ((Integer)request.getAttribute("currentPage")).intValue();
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
<table align="center" cellpadding="10" cellspacing="0" border="1" width="500">
<tr align="center" valign="middle">
	<th colspan="2"><%= exchange.getExNo() %> 번글 상세보기</th>
</tr>
<tr><th>제 목</th><td><%= exchange.getExTitle() %></td></tr>
<tr><th>작성자</th><td><%= exchange.getExWriter() %></td></tr>
<tr><th>내 용</th><td><%= exchange.getExContent() %></td></tr>
<tr><th>첨부파일</th>
<td>
<% if(exchange.getOriginalFileName() == null){ //첨부파일이 없다면 %>
첨부파일 없음
<% }else{ //첨부파일이 있다면 %>
<a href="/nongbu/efdown?ofile=<%= exchange.getOriginalFileName() %>&rfile=<%= exchange.getRenameFileName() %>">
<%= exchange.getOriginalFileName() %></a>
<% } %>
</td></tr>
<tr align="center" valign="middle"><th colspan="2">
<% if(loginMember != null){ //로그인한 경우 %>
	<% if(exchange.getExReplyLev() < 3) { %>
	<a href="/nongbu/views/exchange/exchangeReplyForm.jsp?bnum=<%= exchange.getExNo() %>&page=<%= currentPage %>">
	[댓글달기]</a>
	&nbsp; &nbsp;
	<% } %>
	<% if(loginMember.getUserId().equals(exchange.getExWriter()) == true){ //회원 자신의 글일때만 수정/삭제할 수 있게 함 %>
	<a href="/nongbu/eupview?bnum=<%= exchange.getExNo() %>&page=<%= currentPage %>">[수정페이지로 이동]</a> &nbsp; &nbsp; 
	<a href="/nongbu/edelete?bnum=<%= exchange.getExNo() %>&level=<%= exchange.getExReplyLev() %>&rfile=<%= exchange.getRenameFileName() %>">[글삭제]</a> &nbsp; &nbsp; 
<% }} %>
<a href="/nongbu/elist?page=<%= currentPage %>">[목록]</a>
</th></tr>
</table>
<hr>
<%@ include file="../common/footer.jsp" %>
</body>
</html>





