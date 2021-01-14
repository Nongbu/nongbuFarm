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
</head>
<body>
<%@ include file="../common/header2.jsp" %>
<hr>
<h3 align="center"><%= notice.getNoticeNo() %> 번 공지 상세보기 및 수정페이지 [관리자용]</h3>
<br>
<form action="/nongbu/nupdate.ad" method="post" enctype="multipart/form-data">
<%-- 뷰페이지에 출력은 안되지만(안보여지지만) 요청시 컨트롤러로 같이 보내야 되는 값이 
     있다면, input 의 type="hidden" 을 사용함 --%>
<input type="hidden" name="no" value="<%= notice.getNoticeNo() %>">  
<input type="hidden" name="ofile" value="<%= notice.getOriginalFilePath() %>">
<input type="hidden" name="rfile" value="<%= notice.getRenameFilePath() %>">   
<table align="center" width="500" border="1" cellspacing="0" cellpadding="5">
<tr><th>제 목</th>
<td><input type="text" value="<%= notice.getNoticeTitle() %>" name="title" size="50"></td></tr>
<tr><th>작성자</th>
<td><input type="text" readonly value="<%= notice.getNoticeWriter() %>" name="writer"></td></tr>
<tr><th>등록날짜</th>
<td><input type="date" readonly value="<%= notice.getNoticeDate() %>"></td></tr>
<tr><th>첨부파일</th>
	<td>
		<% if(notice.getOriginalFilePath() != null){ //첨부파일 다운로드 기능 연결 %>
		<a href="/nongbu/nfdown?ofile=<%= notice.getOriginalFilePath() %>&rfile=<%= notice.getRenameFilePath() %>"><%= notice.getOriginalFilePath() %></a>
		&nbsp; <input type="checkbox" name="deleteFlag" value="yes"> 파일삭제 <br>
		<% }else{ %>
		첨부파일 없음<br>		
		<% } %>
		<input type="file" name="upfile">
	</td>
</tr>
<tr><th>내 용</th>
<td><textarea name="content" rows="5" cols="50"><%= notice.getNoticeContent() %></textarea></td></tr>
<tr><th colspan="2">
<input type="submit" value="수정하기"> &nbsp; 
<input type="reset" value="수정취소"> &nbsp;
<input type="button" value="글삭제" onclick="javascript:location.href='/nongbu/ndelete.ad?no=<%= notice.getNoticeNo() %>&rfile=<%= notice.getRenameFilePath() %>'; return false;"> &nbsp; 
<input type="button" value="목록" onclick="javascript:history.go(-1); return false;"></th></tr>
</table>
</form>
<hr>
<%@ include file="../common/footer.jsp" %>
</body>
</html>







