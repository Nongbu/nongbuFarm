<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="faq.model.vo.FAQ" %>
<%
	FAQ faq = (FAQ)request.getAttribute("faq");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FAQ</title>
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
<%@ include file="../common/header2.jsp" %>
<hr>
<h3 align="center"><%= faq.getFaqNum() %> 번 FAQ 상세보기 및 수정페이지 [관리자용]</h3>
<br>
<form action="/nongbu/faupdate.ad" method="post" enctype="multipart/form-data">
<%-- 뷰페이지에 출력은 안되지만(안보여지지만) 요청시 컨트롤러로 같이 보내야 되는 값이 
     있다면, input 의 type="hidden" 을 사용함 --%>
<input type="hidden" name="no" value="<%= faq.getFaqNum() %>">  
<table align="center" width="500" border="1" cellspacing="0" cellpadding="5">
<tr>
				<th>분 류</th>
				<td valign="top">
				<label for="category-select">분류를 선택하세요</label>
				<select name="facategory" id="category-select">
						<option value="<%= faq.getFaqCategory() %>">------</option>
						<option value="주문/배송">주문/배송</option>
						<option value="환불/교환">환불/교환</option>
						<option value="쿠폰/적립금">쿠폰/적립금</option>
						<option value="판매자">판매자</option>
						<option value="기타">기타</option>
				</select></td>
			</tr>
<tr><th>질 문</th>
<td><input type="text" value="<%= faq.getFaqTitle() %>" name="fatitle"></td></tr>
<tr><th>답변내용</th>
<td><textarea name="facontent" rows="5" cols="50"><%= faq.getFaqContent() %></textarea></td></tr>
<tr><th colspan="2">
<input type="submit" value="수정하기"> &nbsp; 
<input type="reset" value="수정취소"> &nbsp;
<input type="button" value="글삭제" onclick="javascript:location.href='/nongbu/fadelete.ad?no=<%= faq.getFaqNum() %>'; return false;"> &nbsp; 
<input type="button" value="목록" onclick="javascript:history.go(-1); return false;"></th></tr>
</table>
</form>
<hr>
<%@ include file="../common/footer.jsp" %>
</body>
</html>







