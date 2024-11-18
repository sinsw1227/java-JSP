<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% String path = request.getContextPath(); %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>게시글 추가/수정</title>
</head>
<body>
<h1>게시글 추가/수정</h1>

<form action="${path}/Project/BoardPage" method="post" enctype="multipart/form-data">
    <input type="hidden" name="_method" value="${param.id != null ? 'put' : 'post'}">
    
    <label for="title">제목:</label>
    <input type="text" id="title" name="title" required><br>
    
    <label for="content">내용:</label>
    <textarea id="content" name="content" required></textarea><br>
    
    <label for="image">Upload Image:</label>
    <input type="file" id="image" name="image" accept="image/*">
    
    <button type="submit">저장</button>
</form>

</body>
</html>
