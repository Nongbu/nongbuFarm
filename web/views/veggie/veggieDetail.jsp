<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="veggie.model.vo.Product"%>
<%
	Product pro = (Product) request.getAttribute("pro");
	int category = pro.getProCtgr();
	String proId = pro.getProID();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 상세 페이지</title>

<link rel="stylesheet" href="../../resources/css/bootstrap.min.css">
<link rel="stylesheet" href="../../resources/css/font-awesome.min.css">
<link rel="stylesheet" href="../../resources/css/owl.carousel.css">
<link rel="stylesheet" href="../../resources/css/owl.theme.css">
<link rel="stylesheet" href="../../resources/css/animate.css">
<link rel="stylesheet" href="../../resources/css/flexslider.css">
<link rel="stylesheet" href="../../resources/css/pricing.css">
<link rel="stylesheet" href="../../resources/css/main.css">
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

	<%@ include file="../common/header.jsp"%>
	<hr>

	<h3 align="center"><%=pro.getProNum()%>
		번 상품
	</h3>
	<br>
	<table align="center" width="500" border="1" cellspacing="0"
		cellpadding="5">
		<tr>
			<th>분 류</th>
			<%
				if (category == 1 || category == 3) {
			%>
			<td>야채/채소</td>
			<%
				} else if (category == 2) {
			%>
			<td>곡물</td>
			<%
				} else {
			%>
			<td>과일</td>
			<%
				}
			%>
		</tr>
		<tr>
			<th>작성자</th>
			<td><%=pro.getProID()%></td>
		</tr>
		<tr>
			<th>상품명</th>
			<td><%=pro.getProName()%></td>
		</tr>
		<tr>
			<th>가격</th>
			<td><%=pro.getProPrice()%></td>
		</tr>
		<tr>
			<th>작성 날짜</th>
			<td><%=pro.getProDate()%></td>
		</tr>
		<tr>
			<th>원산지</th>
			<td><%=pro.getProOrgin()%></td>
		</tr>
		<%
			if (loginMember != null && loginMember.getUserId().equals(proId)) {
		%>
		<tr>
			<th colspan="2">
				<form action="/nongbu/proUpdateView" method="post">
					<input type="hidden" name="no" value="<%=pro.getProNum()%>">
					<input type="submit" value="수정하기">
				</form>
				&nbsp; <input type="button" value="글삭제"
				onclick="javascript:location.href='/nongbu/proDelete?no=<%=pro.getProNum()%>'; return false;">
				&nbsp; <input type="button" value="목록"
				onclick="javascript:history.go(-1); return false;">
			</th>
		</tr>
		<%
			} else {
		%>
		<tr>
			<th colspan="2"><button
					onclick="javascript:history.go(-1); return false;">목록</button>
				<button
					onclick="javascript:location.href='/nongbu/views/test/testfood1.jsp'; return false;">상품상세보기</button>
			</th>
		</tr>
		<%
			}
		%>
	</table>

	<hr>
	<%@include file="../common/footer.jsp"%>
</body>
</html>
