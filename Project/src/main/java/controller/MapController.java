package controller;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import netscape.javascript.JSObject;
import repository.MapRepository;

/**
 * Servlet implementation class MapController
 */
@WebServlet("/Map")
public class MapController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MapRepository mapRepository;

//    public MapController() {
//        super();
//        // TODO Auto-generated constructor stub
//    }

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		System.out.println("MapController init()");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//return map.jsp
		System.out.println("Map get() >> try to get coordinate String from repository + return map.jsp");
		
		mapRepository = (MapRepository) getServletContext().getAttribute("mapRepository");
		request.setAttribute("coordinate", mapRepository.get());
		
		request.getRequestDispatcher("map.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// post 아직 없음
	}

//	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//	}

}
