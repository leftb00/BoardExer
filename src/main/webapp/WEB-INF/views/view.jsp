<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 내용 보기</title>
<script>
function confirmDelete(bid) {
    if (confirm("정말로 삭제하시겠습니까?")) {
        window.location.href = "delete?bid=" + bid;
    }
}
</script>
</head>
<body>
	<h2>글 내용 보기</h2>
	<hr/>
	<table border=1 cellspacing=0 cellpadding=0 width=600>
		<tr>
			<td>번호</td>
			<td>${dto.bid}</td>
		</tr>
		<tr>
			<td>조회수</td>
			<td>${dto.bhit}</td>
		</tr>
		<tr>
			<td>작성자</td>
			<td>${dto.bname}</td>
		</tr>
		<tr>
			<td>제목</td>
			<td>${dto.btitle}</td>
		</tr>
		<tr>
			<td>내용</td>
			<td>${dto.bcontent}</td>
		</tr>
		<tr>
			<td>등록일</td>
			<td>${dto.bdate}</td>
		</tr>
		<tr>
			<td colspan=2>
			<input type="button" value="수정" onclick="javascript:window.location='modify_form?bid=${dto.bid}'"/>
			<input type="button" value="삭제" onclick="confirmDelete(${dto.bid})"/>
			<input type="button" value="목록" onclick="javascript:window.location='list'"/>
			</td>
		</tr>
	</table>
</body>
</html>