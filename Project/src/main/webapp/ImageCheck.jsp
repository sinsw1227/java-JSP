<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    // 이미지 파일 경로를 지정합니다. 실제 서버의 이미지 경로를 넣으세요.
    String imagePath = "/img/1-11.jpg"; // 예: WebContent/images/sample.jpg
%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>이미지 표시</title>
    <style>
        /* 이미지 스타일 설정 */
        .image-container {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #f0f0f0;
        }
        .image-container img {
            max-width: 100%;
            height: auto;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        }
    </style>
</head>
<body>
    <div class="image-container">
        <!-- 이미지 태그에 이미지 경로를 넣어줍니다 -->
        <img src="<%= request.getContextPath() + imagePath %>" alt="Sample Image">
    </div>
</body>
</html>
