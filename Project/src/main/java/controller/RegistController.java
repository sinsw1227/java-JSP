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

@WebServlet("/Regist")
public class RegistController extends HttpServlet {
	//private static final long serialVersionUID = 1L;
	private LoginService loginService = new LoginService();

//    public RegistController() {
//        super();
//        // TODO Auto-generated constructor stub
//    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// return regist.jsp
		System.out.println("Regist get() return regist.jsp");
		request.getRequestDispatcher("regist.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String passwd = request.getParameter("password");
		String name = request.getParameter("name");
		System.out.println(id+", "+passwd+"," +name+ " Regist post() => check id");
		User input = new User(id, passwd, name);

		if(loginService.addUser(input)) {
			// regist 성공
			System.out.println("id ok => regist=> redirect Login");
			response.sendRedirect("Login");
		}
		else {
			// regist 불가능 중복 id
			System.out.println("duplicated id => return regist.jsp + err");
			request.setAttribute("err", "duplicated id");
			request.getRequestDispatcher("regist.jsp").forward(request, response);
		}
	}
}
