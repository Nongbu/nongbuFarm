<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FAQ 작성</title>
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
	<%@ include file="../common/header2.jsp"%>
	<hr>
	<h1 align="center">새 FAQ 등록 페이지</h1>
	<%-- form 에서 입력값들과 파일을 같이 전송하려면 
	반드시 enctype="multipart/form-data" 속성을 추가해야 함 --%>
	<form action="/nongbu/fainsert.ad" method="post"
		enctype="multipart/form-data">
		<table align="center" width="500" border="1" cellspacing="0"
			cellpadding="5">
			<tr>
				<th>분 류</th>
				<td valign="top">
				<label for="category-select">분류를 선택하세요</label>
				<select name="facategory" id="category-select">
						<option value="">------</option>
						<option value="주문/배송">주문/배송</option>
						<option value="환불/교환">환불/교환</option>
						<option value="쿠폰/적립금">쿠폰/적립금</option>
						<option value="판매자">판매자</option>
						<option value="기타">기타</option>
				</select></td>
			</tr>
			<tr>
				<th>질 문</th>
				<td><input type="text" name="fatitle" size="50"></td>
			</tr>
			<tr>
				<th>답변내용</th>
				<td><textarea rows="5" cols="50" name="facontent"></textarea></td>
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
	<%@ include file="../common/footer.jsp"%>
</body>
</html>