<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="board.model.vo.Board" %>
<%
	Board board = (Board)request.getAttribute("board");
	int currentPage = ((Integer)request.getAttribute("page")).intValue();
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의사항 수정</title>
<style type="text/css">
td {
	text-align: center;
	background-color: #d2e6b9;
}

table tr th {
	text-align: center;
	background-color: #a4cd73;
}
</style>
<style type="text/css">
td {
	text-align: center;
	background-color: #d2e6b9;
}

table tr th {
	text-align: center;
	background-color: #a4cd73;
}
</style>
</head>
<body>
<%@ include file="../common/header.jsp" %>
<hr>
<h2 align="center"><%= board.getBoardNum() %>번 게시글 수정 페이지</h2>
<%-- 원글 수정 --%>
<% if(board.getBoardReplyLev() == 1){ %>
<form action="/nongbu/boriginup" method="post" enctype="multipart/form-data">
<input type="hidden" name="bnum" value="<%= board.getBoardNum() %>">
<input type="hidden" name="page" value="<%= currentPage %>">
<input type="hidden" name="ofile" value="<%= board.getBoardOriginalFileName() %>">
<input type="hidden" name="rfile"	value="<%= board.getBoardRenameFileName() %>"> 
<table align="center" width="500" border="1" cellspacing="0" cellpadding="3">
<tr>
				<th>분 류</th>
				<td valign="top">
				<label for="category-select">분류를 선택하세요</label>
				<select name="bcategory" id="category-select">
						<option value="<%= board.getBoardCategory() %>">------</option>
						<option value="주문/배송">주문/배송</option>
						<option value="환불/교환">환불/교환</option>
						<option value="쿠폰/적립금">쿠폰/적립금</option>
						<option value="판매자">판매자</option>
						<option value="기타">기타</option>
				</select></td>
</tr>
<tr><th>제 목</th><td><input type="text" name="btitle" size="50" value="<%= board.getBoardTitle() %>"></td></tr>
<tr><th>작성자</th>
<td><input type="text" name="bwriter" value="<%= board.getBoardWriter() %>" readonly></td></tr>
<tr><th>첨부파일</th>
<td>
<% if(board.getBoardOriginalFileName() != null){  %>
	<%= board.getBoardOriginalFileName() %> &nbsp; 
	<input type="checkbox" name="deleteFlag" value="yes"> 파일삭제
<% } %>
<input type="file" name="upfile">
</td></tr>
<tr><th>내용</th><td><textarea name="bcontent" rows="5" cols="50"><%= board.getBoardContent() %></textarea></td></tr>
<tr><th colspan="2"><input type="submit" value="수정하기"> &nbsp; 
<input type="reset" value="수정취소"></th></tr>
<tr><th colspan="2">
	<a href="/nongbu/blist?page=<%= currentPage %>">목록</a> &nbsp; 
	<a href="javascript:history.go(-1);">이전 페이지로</a>
</th></tr>
</table>
</form>
<% }else{ %>
<%-- 댓글 | 대댓글 수정 --%>
<form action="/nongbu/breplyup" method="post">
<input type="hidden" name="bnum" value="<%= board.getBoardNum() %>">
<input type="hidden" name="page" value="<%= currentPage %>">
<table align="center" width="500" border="1" cellspacing="0" cellpadding="3">
<tr><th>제 목</th><td><input type="text" name="btitle" size="50" value="<%= board.getBoardTitle() %>"></td></tr>
<tr><th>작성자</th>
<td><input type="text" name="bwriter" value="<%= board.getBoardWriter() %>" readonly></td></tr>
<tr><th>내용</th><td><textarea name="bcontent" rows="5" cols="50"><%= board.getBoardContent() %></textarea></td></tr>
<tr><th colspan="2"><input type="submit" value="수정하기"> &nbsp; 
<input type="reset" value="수정취소"></th></tr>
<tr><th colspan="2">
	<a href="/nongbu/blist?page=<%= currentPage %>">목록</a> &nbsp; 
	<a href="javascript:history.go(-1);">이전 페이지로</a>
</th></tr>
</table>
</form>
<% } %>
<hr>
<%@ include file="../common/footer.jsp" %>
</body>
</html>