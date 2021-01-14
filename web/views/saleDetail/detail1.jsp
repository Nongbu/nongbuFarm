<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%
	String information = request.getParameter("infor");
	/* String productNo = request.getParameter("productNo");
	String sellerId = request.getParameter("sellerId");
	String sellerProduct = request.getParameter("sellerProduct");
	String userAddress = request.getParameter("userAddress");
	int price = Integer.parseInt(request.getParameter("price")); */
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>할인</title>

<link rel="stylesheet" href="../../resources/css/bootstrap.min.css">
<link rel="stylesheet" href="../../resources/css/font-awesome.min.css">
<link rel="stylesheet" href="../../resources/css/owl.carousel.css">
<link rel="stylesheet" href="../../resources/css/owl.theme.css">
<link rel="stylesheet" href="../../resources/css/animate.css">
<link rel="stylesheet" href="../../resources/css/flexslider.css">
<link rel="stylesheet" href="../../resources/css/pricing.css">
<link rel="stylesheet" href="../../resources/css/main.css">

</head>
<body>

	<%@ include file="../common/header.jsp"%>


	<div class="container">


		<div class="row align-items-center my-5">
			<div class="col-lg-7">
				<img class="img-fluid rounded mb-4 mb-lg-0"
					src="/nongbu/resources/images/1.jpg"  width= "500" height="350">
			</div>

			<div class="col-lg-5"
				style="font-weight: bold; font-size: 2.0em; line-height: 20px; font-family: Verdana;">
				<h4 class="font-weight-light">
					<b>[농부의 텃밭] 해남 고구마</b>
				</h4>
				<br> <br>
				<h6 class="font-weight-light">가격 :  20,000원(10kg)</h6>
				<br>
				<h6 class="font-weight-light">원산지 : 전라남도 해남</h6>
				<br>
				<h6 class="font-weight-light">친환경 인증 : 유기농</h6>
				<br>
				<h6 class="font-weight-light">생산시기 : 연중생산(2020년)</h6>
				<br>
				<h6 class="font-weight-light">등급 : ★★★☆☆</h6>
				<br> <br> <br> <br>
				<div align="center">
					<!-- <a class="btn btn-primary" href="/nongbu/views/pay/kaKao.jsp">주문하기</a>  -->
						
						<%-- <form action="/nongbu/views/pay/kaKao.jsp" method="post" value="주문하기">
						상품번호 : <input type="text" name="pno" value="<%= productno%>"> 
						판매자 아이디 : <input type="text"name="sid" value="<%= sellerid%>"> 
						상품명 : <input type="text" name="product" value="<%= product%>"> 
						구매자 주소 : <input type="text" name="addr" value="<%= address%>"> 
						가격 : <input type="number" name="price" value="<%= price%>"> 
						<input type="submit" value="주문하기">
						</form>  --%>
						<a class="btn btn-primary" href="#">주문하기</a>
						<a class="btn btn-primary" href="#">장바구니</a> 
					    <a class="btn btn-primary" href="#">문의작성</a>
				</div>
			</div>
			<br><br><br>
		</div>
		
			<form action="/nongbu/pOrder" method="post" value="주문하기">
			<a href="/nongbu/pinfor?infor=<%= information%>">주문하기</a>
			<tr><th>상품번호</th><td><input type="submit"></td></tr>
			<tr><th>판매자아이디</th><td><input type="submit"></td></tr>
			<tr><th>상품번호</th><td><input type="submit"></td></tr>
			<tr><th>상품번호</th><td><input type="submit"></td></tr>
			<tr><th>상품번호</th><td><input type="submit"></td></tr> 
				
				</form>
				<hr>
				<%@ include file="../common/footer.jsp" %>
		</div>
	</body>
</html>

