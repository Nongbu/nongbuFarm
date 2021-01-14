<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="member.model.vo.Member"%>
<% Member loginMember = (Member)session.getAttribute("loginMember"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/nongbu/resources/css/bootstrap.min.css">
<link rel="stylesheet" href="/nongbu/resources/css/font-awesome.min.css">
<link rel="stylesheet" href="/nongbu/resources/css/owl.carousel.css">
<link rel="stylesheet" href="/nongbu/resources/css/owl.theme.css">
<link rel="stylesheet" href="/nongbu/resources/css/animate.css">
<link rel="stylesheet" href="/nongbu/resources/css/flexslider.css">
<link rel="stylesheet" href="/nongbu/resources/css/pricing.css">
<link rel="stylesheet" href="/nongbu/resources/css/main.css">
</head>
<body>
	<header>
		<!--== 헤더 ==-->
		<div class="blank" style="background-color:#a4cd73; height:90px;"></div>
			
		<nav id="template-navbar"
			class="navbar navbar-default custom-navbar-default navbar-fixed-top">
			<div class="container">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#Food-fair-toggle">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="/nongbu/index.jsp"> <img id="logo"
						src="/nongbu/resources/images/Logo_main.png" class="logo img-responsive">
					</a>
				</div>
				
				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse" id="Food-fair-toggle">
					<ul class="nav navbar-nav navbar-right">
						<li><a href="/nongbu/nlist">공지사항</a></li>
						<li><a href="/nongbu/blist?page=1">고객센터</a></li>
						<%
	                     if (loginMember == null) { //로그인 안했을 때 보여질 내용
	                  %>
	                  <li><a href="/nongbu/views/member/loginPage.jsp">로그인/회원가입</a></li>
	                  <%
	                     } else { //로그인 했다면
	                  %>
	                  <li><a href="/nongbu/views/member/memberDetailView.jsp">마이페이지</a></li>
	                  <%
	                     }
	                  %>
					</ul>
				</div>
				<!-- /.navbar-collapse -->
			</div>
			<!-- /.row -->
		</nav>
	</header>
</body>
</html>





