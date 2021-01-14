<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>맛잇는 라면</title>

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
			<br>
			<hr>
			<br>
			<div class="col-lg-7">
				<img class="img-fluid rounded mb-4 mb-lg-0"
					src="/nongbu/resources/images/food4.jpg" alt="">
			</div>

			<div class="col-lg-5"
				style="font-weight: bold; font-size: 2.0em; line-height: 20px; font-family: Verdana;">
				<h4 class="font-weight-light">
					<br> <b>[농부의 텃밭] 유기농 이천 쌀</b>
				</h4>
				<br> <br>
				<h6 class="font-weight-light">가격 : 32,000원(3kg)</h6>
				<br>
				<h6 class="font-weight-light">원산지 : 경기도 이천시</h6>
				<br>
				<h6 class="font-weight-light">친환경 인증 : 유기농</h6>
				<br>
				<h6 class="font-weight-light">생산시기 : 연중생산(2020년)</h6>
				<br>
				<h6 class="font-weight-light">등급 : ★★★★☆</h6>
				<br> <br> <br> <br>
				<div align="center">
					<a class="btn btn-primary" href="#">주문하기</a> <a
						class="btn btn-primary" href="#">장바구니</a> <a
						class="btn btn-primary" href="#">문의작성</a>
				</div>
				<br>
			</div>
		</div>
		<div align="center">
			<form>
				<textarea name="상세설명" wrap="virtual"
					style="width: 100%; height: 550px; overflow: visible;"> 상품의 상세 설명을 작성해주세요.</textarea>
			</form>
		</div>
		<br>
	</div>
	<br>
	<hr>
	<br>

	<%@include file="../common/footer.jsp"%>
</body>
</html>
