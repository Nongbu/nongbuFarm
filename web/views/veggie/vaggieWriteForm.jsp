<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>상품 등록</title>

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
	<h1 align="center">새 상품 등록 페이지</h1>
	<%-- form 에서 입력값들과 파일을 같이 전송하려면 
	반드시 enctype="multipart/form-data" 속성을 추가해야 함 --%>
	<form action="/nongbu/proInsert" method="post"
		enctype="multipart/form-data"> 
		<table align="center" width="500" border="1" cellspacing="0"
			cellpadding="5">
			<tr>
				<th>분 류</th>
				<td valign="top"><label for="category-select">분류를 선택하세요</label>
					<select name="pacategory" id="category-select">
						<option value="">------</option>
						<option value="1">야채/채소</option>
						<option value="2">곡물</option>
						<option value="4">과일</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>상품명</th>
				<td><input type="text" name="proname" size="50" required></td>
			</tr>
			<tr>
				<th>가격</th>
				<td><input type="number" name="proprice" size="50" min="0" required></td>
			</tr>
			<tr>
				<th>원산지</th>
				<td><input type="text" name="prorigin" size="50"></td>
			</tr>
			<tr>
				<th colspan="2"><input type="submit" value="등록하기">
					&nbsp; <input type="reset" value="작성취소"> &nbsp; <input
					type="button" value="목록"
					onclick="javascript:history.go(-1); return false;"></th>
			</tr>
		</table>
	</form>
	<hr>
	<!--== 푸터 ==-->
	<%@ include file="../common/footer.jsp"%>

</body>
</html>