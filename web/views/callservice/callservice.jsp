<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>전화상담</title>
<style>
ul a {
	text-decoration: none;
	color: #5c9233;
}

ul a:hover {
	text-decoration: none;
	color: white;
	}
	
.parent{
    width: 40%;
    margin: 10px auto;
    display: flex;
}

.first {
    flex:0.8;
    box-sizing: border-box;
}

.second{
    flex:1;
    margin: 0px 10%;
    box-sizing: border-box;
}
</style>
</head>
<body>
<%@ include file="../common/header2.jsp"%>
<!-- 네비게이션 바 -->
	<div class="pricing-filter">
		<div class="pricing-filter-wrapper">
			<div class="container">
				<div class="row">
					<div class="col-md-10 col-md-offset-1">
						<div class="section-header">
							<ul id="filter-list" class="clearfix" align="middle">
								<li><a href="/nongbu/blist?page=1">문의사항</a></li>
								<li><a href="/nongbu/falist">FAQ</a></li>
								<li><a href="/nongbu/views/callservice/callservice.jsp">전화상담</a></li>
							</ul>
							<!-- @end #filter-list -->
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<hr>
	<h1 align="center">전화상담</h1>
	<h4 align="center">FAQ와 문의하기를 통해서도 문제가 해결이 안된다면,<br>직원이 친절하게 전화상담 해드립니다.</h4>
	<br>
	<div class="parent" style="text-align:center;">
		<div class="first">
			<img src="/nongbu/resources/images/call.png" style= "width:60px" align="right">
		</div>
		<div class="second" style="font-size:35px" align="left">
			02-123-4567
		</div>
	</div>
	<br><br>
	<div style="text-align:center;">
		고객센터 영업시간 :  9 a.m. ~ 6 p.m.
		<br><br>
		우리 기관은 관련법령에 따라 상담원 보호조치를 시행중입니다.
		<br>
		서로를 존중하는 말로 상담원을 보호해 주세요.
		<br><br>
		고객님의 말씀을 정성을 다해 듣겠습니다.
		<br><br>
		보다 나은 상담서비스 제공을 위하여 상담내용은 녹음됩니다.
	</div>
	<hr>
<%@ include file="../common/footer.jsp"%>
</body>
</html>