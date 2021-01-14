<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, free.model.vo.Free, java.sql.Date, member.model.vo.Member" %> 
<%
	Member loginMember = (Member)session.getAttribute("loginMember");
%> 
<%
	ArrayList<Free> list = (ArrayList<Free>)request.getAttribute("list");
	int listCount = ((Integer)request.getAttribute("listCount")).intValue();
	int maxPage = ((Integer)request.getAttribute("maxPage")).intValue();
	int startPage = ((Integer)request.getAttribute("startPage")).intValue();
	int endPage = ((Integer)request.getAttribute("endPage")).intValue();
	int currentPage = ((Integer)request.getAttribute("currentPage")).intValue();
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- <link rel="shortcut icon" href="resources/images/star.png" type="favicon/ico" /> -->

<title>nongbu</title>

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

<link rel="stylesheet" href="/nongbu/resources/css/bootstrap.min.css">
<link rel="stylesheet" href="/nongbu/resources/css/font-awesome.min.css">
<link rel="stylesheet" href="/nongbu/resources/css/owl.carousel.css">
<link rel="stylesheet" href="/nongbu/resources/css/owl.theme.css">
<link rel="stylesheet" href="/nongbu/resources/css/animate.css">
<link rel="stylesheet" href="/nongbu/resources/css/flexslider.css">
<link rel="stylesheet" href="/nongbu/resources/css/pricing.css">
<link rel="stylesheet" href="/nongbu/resources/css/main.css">


<script src="/nongbu/resources/js/bootstrap.min.js"></script>
<script src="/nongbu/resources/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript"
	src="/nongbu/resources/js/jquery.flexslider.min.js"></script>
<script type="text/javascript"
	src="/nongbu/resources/js/jquery.hoverdir.js"></script>
<script type="text/javascript" src="resources/js/jquery.mixitup.min.js"></script>
<script type="text/javascript"
	src="/nongbu/resources/js/jQuery.scrollSpeed.js"></script>
<script src="/nongbu/resources/js/jquery.validate.js"></script>

<script src="/nongbu/resources/js/jssor.core.js"></script>
<script src="/nongbu/resources/js/jssor.slider.js"></script>
<script src="/nongbu/resources/js/jssor.utils.js"></script>

<script src="/nongbu/resources/js/owl.carousel.min.js"></script>
<script src="/nongbu/resources/js/script.js"></script>
<script src="/nongbu/resources/js/wow.min.js"></script>

<script type="text/javascript">
	$(window).load(function() {
		$('.flexslider').flexslider({
			animation : "slide",
			controlsContainer : ".flexslider-container"
		});
	});
	
	function showWriteForm(){
		location.href="/nongbu/views/free/freeWriteForm.jsp?page=<%= currentPage%>";
	}
</script>



</head>
<body data-spy="scroll" data-target="#template-navbar">

	<header>
		<!--== 헤더 ==-->
		<div class="blank" style="background-color: #a4cd73; height: 140px;"></div>

		<!--== 네비게이션 바 ==-->
		<div class="pricing-filter">
			<div class="pricing-filter-wrapper">
				<div class="container">
					<div class="row">
						<div class="col-md-10 col-md-offset-1">
							<div class="section-header">
								<ul id="filter-list" class="clearfix" align="middle">
									<li class="filter" data-filter="all">전체</li>
									<li class="filter" data-filter=".breakfast">야채/채소</li>
									<li class="filter" data-filter=".special">곡물</li>
									<li class="filter" data-filter=".desert"><a
										style="color: red;">할인상품</a></li>
									<li class="filter" data-filter=".dinner"><a
										href="/nongbu/flist"><b><u>나눔의 장</u></b></a></li>
								</ul>
								<!-- @end #filter-list -->
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

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
						src="/nongbu/resources/images/Logo_main.png"
						class="logo img-responsive">
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

	<div align="center">
		<a href="/nongbu/flist" style="background: #a4cd73;"><b><u>무료나눔</u></b></a> &nbsp;
		&nbsp; &nbsp; <a href="/nongbu/elist" style="background: #a4cd73;">교환</a>
	</div>

<hr>
<h1 align="center">무료나눔 목록 보기</h1>
<h5 align = "right">총 목록 갯수 : <%= listCount %></h5>
<br>
<%-- 로그인한 사용자만 글쓰기 기능 사용할 수 있게 함 --%>
<% if(loginMember != null){%>
<div style = "align:center; text-align:center;">
	<button onclick="showWriteForm();">글쓰기</button>
</div>
<% } %>
<br>
<%-- 무료나눔 검색 --%>
<center>
<div>
	<h2>검색할 항목을 선택하시오.</h2>
	<input type="radio" name="item" value="title" checked> 제목 &nbsp; &nbsp; &nbsp;
	<input type="radio" name="item" value="writer"> 작성자 &nbsp; &nbsp; &nbsp;
	<input type="radio" name="item" value="date"> 날짜
</div>
<div id="titleDiv">
	<form action="/nongbu/fsearch" method="post">
		<input type="hidden" name="search" value="title">
		<label>검색할 제목을 입력하시오 : <input type="search" name="keyword"></label>
		<input type="submit" value="검색">
	</form>
</div>
<div id="writerDiv">
	<form action="/nongbu/fsearch" method="post">
		<input type="hidden" name="search" value="writer">
		<label>검색할 작성자 아이디를 입력하시오 : <input type="search" name="keyword"></label>
		<input type="submit" value="검색">
	</form>
</div>
<div id="dateDiv">
	<form action="/nongbu/fsearch" method="post">
		<input type="hidden" name="search" value="date">
		<label>검색할 날짜를 선택하시오 : 
		<input type="date" name="from"> ~ <input type="date" name="to"></label>
		<input type="submit" value="검색">
	</form>
</div>
</center>
<br>
<table align="center" border="1" width="700" cellspacing="0">
<tr><th>번호</th><th>제목</th><th>작성자</th><th>날짜</th><th>조회수</th><th>첨부파일</th></tr>
<% for(Free free : list){ %>
<tr>
	<td align="center"><%= free.getFreeNo() %></td>
	<td>
	<%--댓글일때는 제목을 들여쓰기함 --%>
	<% if(free.getFreeReplyLev() == 2){ //원글의 댓글 일 때  %>
	&nbsp; &nbsp; ▶
	<% }else if(free.getFreeReplyLev() == 3){ //댓글의 댓글 일 때 %>
	&nbsp; &nbsp; &nbsp; &nbsp; ▶▶
	<% } %>
	<%-- 로그인 한 사용자만 게시글 상세보기 할 수 있게 처리함 --%>
	<% if( loginMember != null ){ %>
	<a href="/nongbu/fdetail?bnum=<%=free.getFreeNo() %>&page=<%= currentPage%>"><%= free.getFreeTitle() %></a>
	<% }else{ %>
	<%= free.getFreeTitle() %>
	<% } %>
	</td>
	<td align="center" ><%= free.getFreeWriter() %></td>
	<td align="center" ><%= free.getFreeDate() %></td>
	<td align="center" ><%= free.getFreeReadCount() %></td>
	<td align="center" >
		<% if(free.getOriginalFileName() != null){ %>
		◎
		<% }else{ %>
		&nbsp;
		<% } %>
	</td>
</tr>
<% } //for each 루프다 %>

</table>

<br>
<%--페이징 처리 
	[맨처음][이전] 숫자.......[다음][맨끝]
--%>
<div style="text-align: center;">
<%-- 현재 페이지가 1이 아니면 링크설정, 현재 1페이지면 링크 없음 --%>
<% if(currentPage <=1){ %>
[맨처음]
<% }else{ %>
<a href="/nongbu/flist?page=1">[맨처음]</a>
<% } %> &nbsp;

<%-- 이전 그룹이 있으면 링크설정, 이전 그룹 없으면 링크 없음 --%>
<% if((currentPage -10) < startPage && (currentPage - 10) >= 1){ %>
<a href="/nongbu/flist?page=<%= startPage - 10 %>">[이전]</a>
<% }else{ %>
[이전]
<% } %> &nbsp;

<%-- 가운데 표시할 페이지 그룹 숫자 링크 설정 --%>
<% for(int p = startPage; p <= endPage; p++) {
		if(p == currentPage){
%>
<font size = "4" color="red">[<%= p %>]</font>
<% }else{  %>
<a href="/nongbu/flist?page=<%= p %>"><%= p %></a>
<% }//else 닫기괄호
		} //for 닫기괄호 %> &nbsp;
		
<%-- 다음 그룹이 있으면 링크설정, 다음 그룹 없으면 링크 없음 --%>
<% if((currentPage + 10) > endPage && (currentPage + 10) < maxPage){ %>
<a href="/nongbu/flist?page=<%= endPage + 10 %>">[다음]</a>
<% }else{ %>
[다음]
<% } %> &nbsp;

<%-- 현재 페이지가 끝이 아니면 링크설정하고 끝페이지면 링크 없음 --%>
<% if(currentPage >=maxPage){ %>
[맨끝]
<% }else{ %>
<a href="/nongbu/flist?page=<%= maxPage %>">[맨끝]</a>
<% } %> &nbsp;

</div>
<hr>
	<!--== 푸터 ==-->
	<%@ include file="../common/footer.jsp"%>

</body>
</html>