<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, faq.model.vo.FAQ"%>
<%
	ArrayList<FAQ> list = (ArrayList<FAQ>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FAQ</title>
<script type="text/javascript"
	src="/nongbu/resources/js/jquery-3.5.1.min.js">
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

	<h1 align="center">FAQ</h1>
	<h4 align="center">농부의 텃밭 관련 자주하는 질문에 대한 답변입니다.</h4>
	<%-- FAQ 검색 --%>
	<div style="text-align:center;">
		<form action="/nongbu/fasearch" method="post">
			<input type="hidden" name="search" value="title">
			<label>질문으로 검색하기 : <input type="search" name="keyword"></label>
			<input type="submit" value="검색">
		</form>
	</div>
	<br>
<table align="center" width="800" border="1" bordercolor="white" cellspacing="0" cellpadding="1">
<tr><th>번호</th><th>분류</th><th>질문</th></tr>
<% for(FAQ n : list){ %>
	<tr>
		<td><%= n.getFaqNum() %></td>
		<td><%= n.getFaqCategory() %></td>
		<td><a href="/nongbu/fadetail.ad?faqnum=<%= n.getFaqNum() %>"><%= n.getFaqTitle() %></a></td>
	</tr>
<% } //for each %>
</table>
<br>
<div style="text-align: center;">
<button onclick="javascript:location.href='/nongbu/views/faq/faqWriteForm.jsp';">FAQ 등록</button>
</div>
<hr>
	<%@ include file="../common/footer.jsp"%>
</body>
</html>