<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, notice.model.vo.Notice" %>
<%
	ArrayList<Notice> list = (ArrayList<Notice>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<script type="text/javascript" src="/nongbu/resources/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
$(function(){
	showDiv();
	
	$("input[name=item]").on("change", function(){
		showDiv();
	});
}); //jquery document ready

function showDiv(){
	if($("input[name=item]").eq(0).is(":checked")){
		$("#titleDiv").css("display", "block");
		$("#writerDiv").css("display", "none");
		$("#dateDiv").css("display", "none");
	}
	
	if($("input[name=item]").eq(1).is(":checked")){
		$("#titleDiv").css("display", "none");
		$("#writerDiv").css("display", "block");
		$("#dateDiv").css("display", "none");
	}
	
	if($("input[name=item]").eq(2).is(":checked")){
		$("#titleDiv").css("display", "none");
		$("#writerDiv").css("display", "none");
		$("#dateDiv").css("display", "block");
	}
}
</script>
<style type="text/css">
	td {text-align:center;
		background-color: #d2e6b9;}
	table tr th {
		text-align: center;
		background-color: #a4cd73;}
</style>
</head>
<body>
<%@ include file="../common/header2.jsp" %>
<hr>
<br>
<h1 align="center">공지사항 목록 보기</h1>
<br>
<%-- 공지사항 검색 --%>
<div style="text-align:center;">
<div>
	<input type="radio" name="item" value="title" checked> 제목 &nbsp; &nbsp; &nbsp;
	<input type="radio" name="item" value="writer"> 작성자 &nbsp; &nbsp; &nbsp;
	<input type="radio" name="item" value="date"> 날짜
</div>
<div id="titleDiv">
	<form action="/nongbu/nsearch" method="post">
		<input type="hidden" name="search" value="title">
		<label>검색할 제목을 입력하시오 : <input type="search" name="keyword"></label>
		<input type="submit" value="검색">
	</form>
</div>
<div id="writerDiv">
	<form action="/nongbu/nsearch" method="post">
		<input type="hidden" name="search" value="writer">
		<label>검색할 작성자 아이디를 입력하시오 : <input type="search" name="keyword"></label>
		<input type="submit" value="검색">
	</form>
</div>
<div id="dateDiv">
	<form action="/nongbu/nsearch" method="post">
		<input type="hidden" name="search" value="date">
		<label>검색할 날짜를 선택하시오 : 
		<input type="date" name="from"> ~ <input type="date" name="to"></label>
		<input type="submit" value="검색">
	</form>
</div>
</div>
<br>
<table align="center" width="1000" border="1" bordercolor="white" cellspacing="0" cellpadding="1">
<tr><th>번호</th><th>제목</th><th>작성자</th><th>첨부파일</th><th>등록날짜</th></tr>
<% for(Notice n : list){ %>
	<tr>
		<td><%= n.getNoticeNo() %></td>
		<td><a href="/nongbu/ndetail.ad?noticeno=<%= n.getNoticeNo() %>"><%= n.getNoticeTitle() %></a></td>
		<td><%= n.getNoticeWriter() %></td>
		<td>
			<% if(n.getOriginalFilePath() != null){ //첨부파일이 있을 때 %>
			◎
			<% }else{ //첨부파일이 없을 때 %>
			&nbsp;
			<% } %>
		</td>
		<td><%= n.getNoticeDate() %></td>
	</tr>
<% } //for each %>
</table>
<br>
<div style="text-align: center;">
<button onclick="javascript:location.href='/nongbu/views/notice/noticeWriteForm.jsp';">공지글 등록</button>
</div>
<hr>
<%@ include file="../common/footer.jsp" %>
</body>
</html>