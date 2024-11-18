<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Board" %>
<%
String path = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>게시판 목록</title>
</head>
<body>
<h1>게시판 목록</h1>

<a href="BoardPage/create">게시글 추가</a>

<table border="1">
    <tr>
        <th>제목</th>
        <th>작성자</th>
        <th>내용</th>
        <th>이미지</th>
        <th>액션</th>
        <th>작성 시간</th>
    </tr>

    <%
    // request로 전달된 postList 객체를 가져옵니다.
            ArrayList<Board> boardList = (ArrayList<Board>)request.getAttribute("boards");

            // postList를 순차적으로 출력
            for (Board post : boardList) {
    %>
    <tr>
        <td><%= post.getTitle() %></td>
        <td><%=post.getUserName()%>
        <td><%= post.getContent() %></td>
        <td><img src="<%=path+"/images/"+post.getImgURI()%>"/></td>
        <td>
            <a href="<%= path + "/board/edit/" + post.getId() %>">수정</a>
            <form action="<%=path%>/board/<%=post.getId()%>" method="delete"> <button type="submit">삭제</button> </form>
        </td>
        <td> <%= post.getCreatedAt() %></td>
    </tr>
    <% 
        }
    %>
</table>

</body>
</html>