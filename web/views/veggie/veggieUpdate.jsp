<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="veggie.model.vo.Product"%>
<%
	Product pro = (Product) request.getAttribute("pro");

	int category = pro.getProCtgr();
	
	String[] categories = new String[4];
	
	if(category == 1 || category == 3) {
		categories[1] = "selected";
	} else if (category == 2){
		categories[2] = "selected";
	} else if (category >= 4){
		categories[3] = "selected";
	}
	
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 수정 페이지</title>

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
	<form action="/nongbu/proUpdate" method="post"
		enctype="multipart/form-data"> 
		<input type="hidden" name="proid" size="50" value="<%=pro.getProID()%>">
		<input type="hidden" name="pronum" size="50" value="<%=pro.getProNum()%>">
		<table align="center" width="500" border="1" cellspacing="0"
			cellpadding="5">
			<tr>
				<th>분 류</th>
				<td valign="top"><label for="category-select">분류를 선택하세요</label>
					<select name="pacategory" id="category-select">
						<option value="" <%= categories[0] %>>------</option>
						<option value="1" <%= categories[1] %>>야채/채소</option>
						<option value="2" <%= categories[2] %>>곡물</option>
						<option value="4" <%= categories[3] %>>과일</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>상품명</th>
				<td><input type="text" name="proname" size="50" value="<%=pro.getProName()%>" required></td>
			</tr>
			<tr>
				<th>가격</th>
				<td><input type="number" name="proprice" size="50" min="0" value="<%=pro.getProPrice()%>" required></td>
			</tr>
			<tr>
				<th>원산지</th>
				<td><input type="text" name="prorigin" size="50" value="<%=pro.getProOrgin()%>"></td>
			</tr>
			<tr>
				<th colspan="2"><input type="submit" value="수정하기">
					&nbsp; <input type="reset" value="수정취소"> &nbsp; <input
					type="button" value="목록"
					onclick="javascript:history.go(-1); return false;"></th>
			</tr>
		</table>
	</form>
	<hr>
	<%@include file="../common/footer.jsp"%>
</body>
</html>
