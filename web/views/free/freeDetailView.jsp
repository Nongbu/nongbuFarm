<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="free.model.vo.Free,member.model.vo.Member" %>   
<%
	Free free = (Free)request.getAttribute("free");
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
	<th colspan="2"><%= free.getFreeNo() %> 번글 상세보기</th>
</tr>
<tr><th>제 목</th><td><%= free.getFreeTitle() %></td></tr>
<tr><th>작성자</th><td><%= free.getFreeWriter() %></td></tr>
<tr><th>내 용</th><td><%= free.getFreeContent() %></td></tr>
<tr><th>첨부파일</th>
<td>
<% if(free.getOriginalFileName() == null){ //첨부파일이 없다면 %>
첨부파일 없음
<% }else{ //첨부파일이 있다면 %>
<a href="/nongbu/ffdown?ofile=<%= free.getOriginalFileName() %>&rfile=<%= free.getRenameFileName() %>">
<%= free.getOriginalFileName() %></a>
<% } %>
</td></tr>
<tr align="center" valign="middle"><th colspan="2">
<% if(loginMember != null){ //로그인한 경우 %>
	<% if(free.getFreeReplyLev() < 3) { %>
	<a href="/nongbu/views/free/freeReplyForm.jsp?bnum=<%= free.getFreeNo() %>&page=<%= currentPage %>">
	[댓글달기]</a>
	&nbsp; &nbsp;
	<% } %>
	<% if(loginMember.getUserId().equals(free.getFreeWriter()) == true){ //회원 자신의 글일때만 수정/삭제할 수 있게 함 %>
	<a href="/nongbu/fupview?bnum=<%= free.getFreeNo() %>&page=<%= currentPage %>">[수정페이지로 이동]</a> &nbsp; &nbsp; 
	<a href="/nongbu/fdelete?bnum=<%= free.getFreeNo() %>&level=<%= free.getFreeReplyLev() %>&rfile=<%= free.getRenameFileName() %>">[글삭제]</a> &nbsp; &nbsp; 
<% }} %>
<a href="/nongbu/flist?page=<%= currentPage %>">[목록]</a>
</th></tr>
</table>
<hr>
<%@ include file="../common/footer.jsp" %>
</body>
</html>





