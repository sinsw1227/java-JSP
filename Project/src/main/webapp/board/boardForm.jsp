<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% String path = request.getContextPath(); %>
<%
	Integer id = 0;
    String title = "";
    String content = "";
    String method ="post";
    if (request.getAttribute("title") != null) {
        title = (String) request.getAttribute("title");
        content = (String) request.getAttribute("content");
        id = (Integer)(request.getAttribute("id"));
        method = "put";
    }
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>게시글 추가/수정</title>
</head>
<body>
<h1>게시글 추가/수정</h1>

<form action="${path}/Project/BoardPage" method="post" enctype="multipart/form-data">
	<input type="hidden" name="_method" value="<%= method %>">
	<input type="hidden" name="id" value="<%= id %>">
    제목:
    <input type="text" id="title" name="title" value="<%= title %>" required><br>
    
    내용:
    <textarea name="content" required><%= content %></textarea><br>
    
    Upload Image:
    <input type="file" name="img" accept="image/*" required>
    
    <button type="submit">저장</button>
</form>
</body>
</html>
