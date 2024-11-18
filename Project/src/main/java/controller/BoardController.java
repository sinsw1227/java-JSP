package controller;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.Board;
import model.User;
import repository.BoardRepository;
import service.BoardService;
import service.FileService;
import service.TokenService;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024*1024, maxRequestSize = 1024*1024*5)
@WebServlet("/BoardPage/*")
public class BoardController extends HttpServlet {
	
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        
        System.out.println("BoardPage get() >> action:"+action);
        
        if (action == null || action.equals("/")) {
            // 게시판 목록 페이지로 이동
        	request.setAttribute("boards", new BoardRepository().getBoardList());       	
            request.getRequestDispatcher("board/boardMain.jsp").forward(request, response);
            return;
        }else if(action.startsWith("/edit/")) {
        	// 현재 수정할 페이지의 내용을 jsp에게 넘겨줌
        	int boardId = Integer.parseInt(action.substring(6));
        	System.out.println("board edit id="+String.valueOf(boardId));
        }
        
        request.getRequestDispatcher("/board/boardForm.jsp").forward(request, response);
        
    }
    
    // new Board create : 동기 html form 태그 형식
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Board Post(create) >> save img and update Board db");
    	//get User from token
        String userId = new TokenService().getUserIdFromToken(request, response);
        
        // input img 저장
        Part inputPart = request.getPart("file");
    	int imgId = new FileService().saveFile((String)getServletContext().getAttribute("imgURL"), inputPart);
    	
    	// board 만들기 + get userId
    	Board newBoard = new Board(request.getParameter("title"), request.getParameter("content"));
    	
    	new BoardService().createBoard(newBoard, userId, imgId);
        
        response.sendRedirect(request.getContextPath() + "/BoardPage");
    }

    // update Board
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("Board Put(update) >> save new img again and update Board db");
    	//get User from token
        User user = new TokenService().getUserFromToken(request, response);
        if(user == null) {
        	return;
        }
        
        Part inputPart = request.getPart("file");
    	
    	// board 만들기 + get userId
    	Board newBoard = new Board(request.getParameter("title"), request.getParameter("content"));
    	
    	new BoardService().updateBoard(newBoard, user, (String)getServletContext().getAttribute("imgURL"), inputPart);
        
        response.sendRedirect(request.getContextPath() + "/BoardPage");
    }

    // delete Board
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       System.out.println("Board delete(delete) >> get boardId, userId form token check same id and try delete Board");
       
       User inputUser = new TokenService().getUserFromToken(request, response);
       int boardId = (int) request.getAttribute("boardId");// 해당 부분 수정 필요 url에서 get하는 방식으로
       
       new BoardService().removeBoard(boardId, (String)getServletContext().getAttribute("imgURL"), inputUser);
   
       response.sendRedirect(request.getContextPath() + "/BoardPage");
    }
}
