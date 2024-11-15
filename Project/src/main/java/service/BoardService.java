package service;

import model.Board;
import model.User;
import repository.BoardRepository;

public class BoardService {
	BoardRepository repository = new BoardRepository();
	
	public void createBoard(Board board, User user) {
		//img 생성
		// update img db
		
		// update board db
	}
	
	public boolean removeBoard(int boardId, User user) {
		if(isSameUserId(boardId, user))
			return false;
		
		// remove img
		
		repository.remove(boardId);
	}
	
	public boolean updateBoard(Board board, User user) {
		if(isSameUserId(board, user))
			return false;
		
		// create new img
		// update img db
		
		repository.update(board, user);
	}
	
	private boolean isSameUserId(int boardId, User user) {
		return !(repository.checkUserId() || user.isAdmin());
	}
}
