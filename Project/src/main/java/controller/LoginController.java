package controller;

import java.io.IOException;
import java.util.Optional;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import service.LoginService;

@WebServlet("/Login")
public class LoginController extends HttpServlet {
	//private static final long serialVersionUID = 1L;
	private LoginService loginService = new LoginService();
	
//    public LoginController() {
//        super();
//    }

	// login 페이지 return
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		System.out.println("Login get() >> return login.jsp");
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	// login 기능
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		User input = new User(id, passwd);
		System.out.println(id + ", " + passwd + " Login post() >> check login");
		
		Optional<User> user = loginService.isUser(input);
		if(user.isEmpty()) {
			// login fail => 현재 : adduser
			System.out.println("login fail return login.jsp + err");
			request.setAttribute("err", "login fail");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		else {
			// login success
			input = user.get();
			System.out.println("login success => session => redirect Main");
			
            // session 발급 밑 정보 저장
			request.getSession().setAttribute("name", input.getName());
			
			// send main page
			response.sendRedirect("/Project");
		}
	}

}
