package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import service.FileService;

import java.io.File;
import java.io.IOException;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024*1024, maxRequestSize = 1024*1024*5)
@WebServlet("/BoardImageTestPage")
public class BoardImageTestController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("testForImage.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FileService fileService = new FileService();
		System.out.println("BoardImageTestPage : post");
		
		Part inputPart = request.getPart("file");
		fileService.saveFile((String)getServletContext().getAttribute("imgURL"),inputPart);
		
		// 결과 출력
        response.getWriter().println("<h1>success upload</h1>");
	}
}
