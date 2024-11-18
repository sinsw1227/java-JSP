<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Board" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시글 상세 보기</title>
</head>
<body>
    <h1>게시글 상세 보기</h1>
    
    <%
        Board post = (Board) request.getAttribute("post");
                if (post != null) {
        %>
        <h2><%= post.getTitle() %></h2>
        <p><strong>작성자:</strong> <%=post.getUserId()%></p>
        <p><strong>작성일:</strong> <%= post.getCreatedAt() %></p>
        <hr>
        <p><%= post.getContent() %></p>
    <%
        } else {
    %>
        <p>게시글을 찾을 수 없습니다.</p>
    <%
        }
    %>
    
    <a href="Board">목록으로 돌아가기</a>
</body>
</html>
