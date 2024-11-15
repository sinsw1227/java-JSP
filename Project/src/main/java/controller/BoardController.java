package controller;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Board;

@WebServlet("/BoardPage/*")
public class BoardController extends HttpServlet {
	
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        
        System.out.println("BoardPage get() >> action:"+action);
        
        if (action == null || action.equals("/")) {
            // 게시판 목록 페이지로 이동
        	ArrayList<Board> baords = new ArrayList<Board>();
        	baords.add(new Board(0,"title","content","author","test.png"));
        	baords.add(new Board(0,"title","content","author","test.png"));
        	request.setAttribute("posts", baords);
            request.getRequestDispatcher("board/boardMain.jsp").forward(request, response);
            return;
        }else if(action.startsWith("/edit/")) {
        	// 현재 수정할 페이지의 내용을 jsp에게 넘겨줌
        	int boardId = Integer.parseInt(action.substring(6));
        	System.out.println("board edit id="+String.valueOf(boardId));
        	
        	
        }
        request.getRequestDispatcher("/board/boardForm.jsp").forward(request, response);
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // 게시판 글 추가 로직
        response.sendRedirect(request.getContextPath() + "/BoardPage");
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        

        // 게시판 글 수정 로직
        response.sendRedirect(request.getContextPath() + "/BoardPage");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        

        // 게시판 글 삭제 로직
        response.sendRedirect(request.getContextPath() + "/BoardPage");
    }
}
