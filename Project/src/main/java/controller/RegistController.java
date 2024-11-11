package controller;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import service.EmailService;
import service.LoginService;

@WebServlet("/RegistPage")
public class RegistController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginService loginService;
	private EmailService emailService;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// return regist.jsp
		System.out.println("Regist get() return regist.jsp");
		request.getRequestDispatcher("regist.jsp").forward(request, response);
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		User input = (new ObjectMapper()).readValue(request.getReader().readLine(), User.class);
		System.out.println(input.getId()+", "+input.getPasswd()+"," +input.getName()+ ","+input.getEmail()+" Regist put() => check id");
		
		loginService = new LoginService();
		emailService = new EmailService();

		// token이 있다면 id와 email이 이전과 같은지 확인 => 같다면 passwd, name변경 기능을 구현
		
		if(loginService.isUser(input).isEmpty()) {
			// 동일 id 없음 => email 발송
			emailService.sendEmail(input);
			System.out.println("isUser() Empty => send Email + return 200");
			// 응답 상태 200 OK
            response.setStatus(HttpServletResponse.SC_OK);
		}else {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		User input = (new ObjectMapper()).readValue(request.getReader().readLine(), User.class);
		System.out.println(input.getId()+", "+input.getPasswd()+"," +input.getName()+ ","+input.getEmail()+","+input.getKey()+" Regist post() => check emailKey");
		
		emailService = new EmailService();
		loginService = new LoginService();
		if(emailService.checkByKeyCode(input, input.getKey())) {
			// email success => update database + return 200
			System.out.println("emailKey is success => update database + return 200");
			loginService.addUser(input);
			response.setStatus(HttpServletResponse.SC_OK);
		}
		else {
			System.out.println("emailKey is not exists => return 400");
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		
	}
}