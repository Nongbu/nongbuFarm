<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="faq.model.vo.FAQ"%>
<%
	FAQ faq = (FAQ) request.getAttribute("faq");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FAQ</title>
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
	<%@ include file="../common/header2.jsp"%>
	<hr>
	<h3 align="center"><%=faq.getFaqNum()%>
		번 FAQ 상세보기
	</h3>
	<br>
	<table align="center" width="500" border="1" cellspacing="0"
		cellpadding="5">
		<tr>
			<th>분 류</th>
			<td><%=faq.getFaqCategory()%></td>
		</tr>
		<tr>
			<th>질 문</th>
			<td><%=faq.getFaqTitle()%></td>
		</tr>
		<tr>
			<th>답변내용</th>
			<td><%=faq.getFaqContent()%></td>
		</tr>
		<tr>
			<th colspan="2"><button onclick="javascript:history.go(-1);">목록</button></th>
		</tr>
	</table>
	<hr>
	<%@ include file="../common/footer.jsp"%>
</body>
</html>