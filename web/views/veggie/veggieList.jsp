<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, veggie.model.vo.Product"%>
<%
	ArrayList<Product> list = (ArrayList<Product>) request.getAttribute("list");
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- <link rel="shortcut icon" href="resources/images/star.png" type="favicon/ico" /> -->

<title>농부의 텃밭</title>

<link rel="stylesheet" href="../../resources/css/bootstrap.min.css">
<link rel="stylesheet" href="../../resources/css/font-awesome.min.css">
<link rel="stylesheet" href="../../resources/css/owl.carousel.css">
<link rel="stylesheet" href="../../resources/css/owl.theme.css">
<link rel="stylesheet" href="../../resources/css/animate.css">
<link rel="stylesheet" href="../../resources/css/flexslider.css">
<link rel="stylesheet" href="../../resources/css/pricing.css">
<link rel="stylesheet" href="../../resources/css/main.css">


<script src="../../resources/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript"
	src="../../resources/js/jquery.flexslider.min.js"></script>
<script type="text/javascript">
	$(window).load(function() {
		$('.flexslider').flexslider({
			animation : "slide",
			controlsContainer : ".flexslider-container"
		});
	});
</script>
<style type="text/css">
td {
	text-align: center;
	background-color: #d2e6b9;
}

table tr th {
	text-align: center;
	background-color: #a4cd73;
}

ul a {
	text-decoration: none;
	color: #5c9233;
}

ul a:hover {
	text-decoration: none;
	color: white;
}
</style>


</head>
<body data-spy="scroll" data-target="#template-navbar">

	<!--== 헤더 ==-->
	<%@ include file="../common/header.jsp"%>

	<h1 align="center">Product</h1>
	<h4 align="center">농부의 텃밭 상품 목록 입니다.</h4>
	<%-- 상품 검색 --%>
	<div style="text-align: center;">
		<form action="/nongbu/proSearch" method="post">
			<input type="hidden" name="search" value="title"> <label>상품명으로
				검색하기 : <input type="search" name="keyword">
			</label> <input type="submit" value="검색">
		</form>
	</div>
	<br>
	<table align="center" width="800" border="1" bordercolor="white"
		cellspacing="0" cellpadding="1">
		<tr>
			<th>번호</th>
			<th>작성자</th>
			<th>상품명</th>
			<th>작성일</th>
			<th>가격</th>
		</tr>
		<%
			for (Product n : list) {
		%>
		<tr>
			<td><%=n.getProNum()%></td>
			<td><%=n.getProID()%></td>
			<td><a href="/nongbu/veggieDetail?pronum=<%=n.getProNum()%>"><%=n.getProName()%></a></td>
			<td><%=n.getProDate()%></td>
			<td><%=n.getProPrice()%></td>
		</tr>
		<%
			} //for each
		%>
	</table>
	<br>
	<div style="text-align: center;">
		<button onclick="javascript:location.href='/nongbu/views/veggie/vaggieWriteForm.jsp';">
			상품 등록</button>
	</div>
	<hr>
	<!--== 푸터 ==-->
	<%@ include file="../common/footer.jsp"%>

</body>
</html>