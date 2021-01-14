<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="notice.model.vo.Notice" %>
<%
	Notice notice = (Notice)request.getAttribute("notice");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
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
<%@ include file="../common/header2.jsp" %>
<hr>
<h3 align="center"><%= notice.getNoticeNo() %> 번 공지 상세보기</h3>
<br>
<table align="center" width="500" border="1" cellspacing="0" cellpadding="5">
<tr><th>제 목</th><td><%= notice.getNoticeTitle() %></td></tr>
<tr><th>작성자</th><td><%= notice.getNoticeWriter() %></td></tr>
<tr><th>등록날짜</th><td><%= notice.getNoticeDate() %></td></tr>
<tr><th>첨부파일</th>
	<td>
		<% if(notice.getOriginalFilePath() != null){ //첨부파일 다운로드 기능 연결 %>
		<a href="/nongbu/nfdown?ofile=<%= notice.getOriginalFilePath() %>&rfile=<%= notice.getRenameFilePath() %>"><%= notice.getOriginalFilePath() %></a>
		<% }else{ %>
		첨부파일 없음
		<% } %>
	</td>
</tr>
<tr><th>내 용</th><td><%= notice.getNoticeContent() %></td></tr>
<tr><th colspan="2"><button onclick="javascript:history.go(-1);">목록</button></th></tr>
</table>
<hr>
<%@ include file="../common/footer.jsp" %>
</body>
</html>







