<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="board.model.vo.Board" %>   
<%
	Board board = (Board)request.getAttribute("board");
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
	<th colspan="2"><%= board.getBoardNum() %> 번글 상세보기</th>
</tr>
<tr><th>제 목</th><td><%= board.getBoardTitle() %></td></tr>
<tr><th>작성자</th><td><%= board.getBoardWriter() %></td></tr>
<tr><th>내 용</th><td><%= board.getBoardContent() %></td></tr>
<tr><th>첨부파일</th>
<td>
<% if(board.getBoardOriginalFileName() == null){ //첨부파일이 없다면 %>
첨부파일 없음
<% }else{ //첨부파일이 있다면 %>
<a href="/nongbu/bfdown?ofile=<%= board.getBoardOriginalFileName() %>&rfile=<%= board.getBoardRenameFileName() %>">
<%= board.getBoardOriginalFileName() %></a>
<% } %>
</td></tr>
<tr align="center" valign="middle"><th colspan="2">
<% if(loginMember != null){ //로그인한 경우 %>
	<% if(board.getBoardReplyLev() < 3) { %>
	<a href="/nongbu/views/board/boardReplyForm.jsp?bnum=<%= board.getBoardNum() %>&page=<%= currentPage %>">
	[댓글달기]</a>
	&nbsp; &nbsp;
	<% } %>
	<% if(loginMember.getUserId().equals(board.getBoardWriter()) == true){ //회원 자신의 글일때만 수정/삭제할 수 있게 함 %>
	<a href="/nongbu/bupview?bnum=<%= board.getBoardNum() %>&page=<%= currentPage %>">[수정페이지로 이동]</a> &nbsp; &nbsp; 
	<a href="/nongbu/bdelete?bnum=<%= board.getBoardNum() %>&level=<%= board.getBoardReplyLev() %>&rfile=<%= board.getBoardRenameFileName() %>">[글삭제]</a> &nbsp; &nbsp; 
<% }} %>
<a href="/nongbu/blist?page=<%= currentPage %>">[목록]</a>
</th></tr>
</table>
<hr>
<%@ include file="../common/footer.jsp" %>
</body>
</html>





