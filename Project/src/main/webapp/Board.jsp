<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Board" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시판</title>
    <link rel="stylesheet" href="styles.css"> <!-- 스타일 파일 추가 (필요시) -->
</head>
<body>
    <h1>게시판</h1>

    <h2>새 게시글 작성</h2>
    <form action="Board" method="post">
        <label for="title">제목:</label>
        <input type="text" id="title" name="title" required>
        <br>
        <label for="content">내용:</label>
        <textarea id="content" name="content" required></textarea>
        <br>
        <input type="hidden" name="author" value="<%=request.getSession().getAttribute("name")%>">
        <button type="submit">게시글 작성</button>
    </form>

    <h2>게시글 목록</h2>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>제목</th>
                <th>작성자</th>
                <th>작성일</th>
            </tr>
        </thead>
        <tbody>
            <%
            List<Board> posts = (List<Board>) request.getAttribute("posts");
                            if (posts != null) {
                                for (Board post : posts) {
            %>
                <tr>
                    <td><%= post.getId() %></td>
                    <td><a href="ViewPost?id=<%= post.getId() %>"><%= post.getTitle() %></a></td>
                    <td><%= post.getAuthor() %></td>
                    <td><%= post.getCreatedAt() %></td>
                </tr>
            <%
                    }
                } else {
            %>
                <tr>
                    <td colspan="4">게시글이 없습니다.</td>
                </tr>
            <%
                }
            %>
        </tbody>
    </table>
</body>
</html>
