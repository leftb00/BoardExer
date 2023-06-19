<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글쓰기</title>
</head>
<body>
  <h2>게시판 글쓰기</h2>
  <hr/>
  <form action="modify">
  <table border=1 cellspacing=0 cellpadding=0 width=600>
    <tr>
      <td>이 름</td>
      <td>${dto.bname}</td>
    </tr>
    <tr>
      <td>제 목</td>
      <td><input type="text" name="btitle" size="60" value="${dto.btitle}"></td>
    </tr>
    <tr>
      <td>내 용</td>
      <td><textarea rows=10 cols=45 name="bcontent">${dto.bcontent}</textarea></td>
    </tr>
    <tr>
      <td colspan=2>
      	<input type="hidden" name="bid" value="${dto.bid}">
      	<input type="submit" value="저장"/>
      	<input type="button" value="목록" onclick="javascript:window.location='list'"/>
      </td>
    </tr>
  </table>
  </form>
</body>
</html>