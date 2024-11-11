<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원가입</title>
    <script>
        async function sendEmail(event) {
            event.preventDefault(); // 기본 폼 제출 방지
            
            const id = document.querySelector('input[name="id"]').value;
            const password = document.querySelector('input[name="password"]').value;
            const name = document.querySelector('input[name="name"]').value;
            const email = document.querySelector('input[name="email"]').value;
            
            // 사용자 정보 객체 생성
            const user = {
                id: id,
                passwd: password,
                name: name,
                email: email
            };

            try {
                const response = await fetch('RegistPage', {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(user)
                });

                if (response.ok) {
                    alert('인증 이메일을 발송했습니다. 이메일을 확인하세요.');
                    document.querySelector('#emailKeySection').style.display = 'block'; // 이메일 키 입력 섹션 표시
                } else {
                    alert('이메일 발송 중 오류가 발생했습니다. 다시 시도해주세요.');
                }
            } catch (error) {
                console.error('Error:', error);
                alert('서버와의 연결에 문제가 발생했습니다.');
            }
        }

        async function registerUser(event) {
            event.preventDefault(); // 기본 폼 제출 방지
            
            const key = document.querySelector('input[name="emailKey"]').value;
            const id = document.querySelector('input[name="id"]').value;
            const password = document.querySelector('input[name="password"]').value;
            const name = document.querySelector('input[name="name"]').value;
            const email = document.querySelector('input[name="email"]').value;
            
            // 사용자 정보 객체 생성
            const user = {
                id: id,
                passwd: password,
                name: name,
                email: email,
                key: key // 이메일 키 추가
            };

            try {
                const response = await fetch('RegistPage', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(user)
                });

                if (response.ok) {
                    alert('회원가입이 완료되었습니다.');
                    window.location = "LoginPage"
                } else {
                    alert('회원가입 중 오류가 발생했습니다. 이메일 키를 확인하세요.');
                }
            } catch (error) {
                console.error('Error:', error);
                alert('서버와의 연결에 문제가 발생했습니다.');
            }
        }
    </script>
</head>
<body>
    <h1>회원가입</h1>
    <form onsubmit="sendEmail(event)">
        아이디 : <input type="text" name="id" required> <br>
        비밀번호 : <input type="password" name="password" required> <br>
        이름 : <input type="text" name="name" required><br>
        이메일 : <input type="email" name="email" required><br>
        <button type="submit">인증 이메일 발송</button>
    </form>

    <div id="emailKeySection" style="display: none;">
        <h2>이메일 인증</h2>
        인증 코드 : <input type="text" name="emailKey" required> <br>
        <button onclick="registerUser(event)">회원가입</button>
    </div>

    <div id="error-message">${err }</div>
</body>
</html>
