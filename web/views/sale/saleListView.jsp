<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="sale.model.vo.Sale"%>

    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>sale 등록하기</title>

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
<hr>
<form name="insert" action="/nongbu/sinsert" enctype="multipart/form-data" method="post">
<table style="width: 80%">
<tr><th colspan="2"> 상품 등록  </th></tr>
<tr><td style="width:20%">상품명  : </td>
	<td><input type="text" name="name"></td></tr>
<tr><td>가격 : </td>
<td><input type="text" name="price"></td></tr>
<tr><td>설 명 : </td>
	<td><textarea name="detail" rows="3" cols="30"></textarea></td></tr>
<tr><td>재고량 : </td>
	<td><input type="text" name="stock"></td></tr>
<tr><td>이미지</td>
<td><img name="preview" src="../images/product/noimage.jpg" style="width:100%"><br/>
	<input type="file" name="image" size="30" onchange="filePreview()"></td></tr>
<tr><td colspan="2">
<br>
<input type="submit" value="상품 등록">
<input type="reset" value="새로 입력" onclick="resetInsertData()"></td></tr>

	</table>
</form>
<%@ include file="../common/footer.jsp"%>
</body>
</html>




















