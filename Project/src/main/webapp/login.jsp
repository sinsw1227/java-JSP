<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ page import="service.TokenService" %>
	<%@ page import="model.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	<!-- jQuery -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

</head>
<script>
	
	<%// 토큰이 유효한지 확인
    	TokenService tokenService = new TokenService();
		User user = tokenService.getUserFromToken(request, response);
		if(user != null){%>
			alert("already login")
			window.location = "/Project/MainPage" //이전 화면으로 되돌리기 변경 필요
        	<%
		}
	%>


</script>
<body>
	<div class="login-container">
        <h1>로그인</h1>
        <form>
            <input type="text" placeholder="ID" required>
            <input type="password" placeholder="비밀번호" required>
            <button type="submit">로그인</button>
        </form>
	<form method="post" action="LoginPage" id="login-form">
		id : <input type="text" name="id" id="id"> <br>
		passwd : <input type="password" name="passwd" id="passwd"> <br>
		<button type="submit">로그인</button>
	</form>
		<div class="social-login">
            	   <a href="#" class="kakao-login">카카오 로그인</a>
           	   <a href="#" class="naver-login">네이버 로그인</a>
        	</div>
    	</div>

	<div id="err"></div>
	
	<br><br>
	<div class="container">
		<button class="btn btn-default" onclick="onSignIn">구글인증하기</button>
	</div>
	
	<a href="RegistPage">regist</a>
	
	<script>
	// 로그인 폼을 비동기로 처리하는 함수
    document.getElementById('login-form').addEventListener('submit', async function(event) {
        event.preventDefault();  // 기본 폼 전송 방지

        const id = document.getElementById('id').value;
        const passwd = document.getElementById('passwd').value;

        // 서버에 로그인 요청 (헤더에 ID와 패스워드를 포함하지 않고, JSON 형식으로 요청)
        const response = await fetch('LoginPage', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ id: id, passwd: passwd })
        });

        if (response.ok) {
            alert('Login successful!');
            window.location.href = '/Project/MainPage';  // 인증된 사용자 화면으로 리다이렉트
        } else if (response.status === 400) {  // 잘못된 요청 처리
            document.getElementById('err').textContent = 'ID 또는 패스워드가 일치하지 않습니다.';
        } else {
            alert('error');
        }
    });

    function onSignIn(googleUser) {
    	var access_token = googleUser.getAuthResponse().access_token
    	$.ajax({
        	// people api를 이용하여 프로필 및 생년월일에 대한 선택동의후 가져온다.
    		url: 'localhost:8080/Project/LoginPage'
            // key에 자신의 API 키를 넣습니다.
    		, data: {personFields:'birthdays', key:'AIzaSyCWaUtyCrFkMdjJxFB9NkIjn0-vJ21dWvQ', 'access_token': access_token}
    		, method:'GET'
    	})
    	.done(function(e){
            //프로필을 가져온다.
    		var profile = googleUser.getBasicProfile();
    		console.log(profile)
    	})
    	.fail(function(e){
    		console.log(e);
    	})
    }
	</script>
</body>
</html>
