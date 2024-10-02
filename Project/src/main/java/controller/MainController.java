package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/")
public class MainController extends HttpServlet {
	//private static final long serialVersionUID = 1L;

	// session이 있는지 확인
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession(false) == null) {
			System.out.println("main get() => ckeck session");
			// session이 없음 => redirect Login 
			System.out.println("no session => redirect Login");
			response.sendRedirect("/Login");
		}
		
		// session 이상 없음
		System.out.println("session ok => return main.jsp");
		request.setAttribute("name", request.getSession().getAttribute("name"));
		request.getRequestDispatcher("main.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
