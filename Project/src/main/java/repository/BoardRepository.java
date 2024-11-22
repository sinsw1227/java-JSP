package repository;

import model.Board;

import java.sql.*;
import java.util.ArrayList;

public class BoardRepository extends Repository {

    public ArrayList<Board> getBoardList() {
        String sql = "SELECT b.id, b.title, b.content, u.name AS userName, i.uri AS imgURI, b.created_at " +
                     "FROM board b, user u, img i " +
                     "where b.userId = u.id and i.id = b.imgId";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<Board> boardList = new ArrayList<>();

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Board board = new Board();
                board.setId(rs.getInt("id"));
                board.setTitle(rs.getString("title"));
                board.setContent(rs.getString("content"));
                board.setUserName(rs.getString("userName")); // User name from user table
                board.setImgURI(rs.getString("imgURI")); // Image URI from img table
                board.setCreatedAt(rs.getTimestamp("created_at"));
                boardList.add(board); // Add to list
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect(conn, pstmt, rs); // Close resources
        }

        return boardList;
    }
    
    // Create a new board entry in the database
    public void create(Board board, String userId, int imgId) {
        String sql = "INSERT INTO board (title, content, userId, imgId) VALUES (?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, board.getTitle());
            pstmt.setString(2, board.getContent());
            pstmt.setString(3, userId); // Assumes userName is the userId
            pstmt.setInt(4, imgId); // Convert URI to imgId
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect(conn, pstmt);
        }
    }

    // Retrieve a board object by its ID
    public Board getBoard(int boardId) {
        String sql = "SELECT b.id, b.title, b.content, u.name AS userName, i.uri AS imgURI, b.created_at " +
                     "FROM board b " +
                     "JOIN user u ON b.userId = u.id " +
                     "LEFT JOIN img i ON b.imgId = i.id " +
                     "WHERE b.id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Board board = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, boardId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                board = new Board();
                board.setId(rs.getInt("id"));
                board.setTitle(rs.getString("title"));
                board.setContent(rs.getString("content"));
                board.setUserName(rs.getString("userName"));
                board.setImgURI(rs.getString("imgURI"));
                board.setCreatedAt(rs.getTimestamp("created_at"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect(conn, pstmt, rs);
        }

        return board;
    }

    // Remove a board entry from the database
    public void remove(int boardId) {
        String sql = "DELETE FROM board WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, boardId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect(conn, pstmt);
        }
    }

    // Update a board entry in the database
    public boolean update(Board board, String userId, int imgId) {
        String sql = "UPDATE board SET title = ?, content = ?, userId = ?, imgId = ? WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, board.getTitle());
            pstmt.setString(2, board.getContent());
            pstmt.setString(3, userId);
            pstmt.setInt(4, imgId);
            pstmt.setInt(5, board.getId());
            pstmt.executeUpdate();
            
            disconnect(conn, pstmt);
        } catch (SQLException e) {
            System.out.println("BoardRepository >> update >> fail" + e.getMessage());
            disconnect(conn, pstmt);
            return false;
        }
        disconnect(conn, pstmt);
        System.out.println("BoardRspository >> update >> success");
        return true;
    }

    // Retrieve the user ID associated with a specific board ID
    public String getUserIdWithBoardId(int boardId) {
    	//System.out.println("BoardRepository >> getUserIdWuthBoardId() boardId="+String.valueOf(boardId));
        String sql = "SELECT userId FROM board WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String userId = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, boardId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                userId = rs.getString("userId");
                System.out.println("BoardRepository > getUserIdWithBoardId() >> success userId =" +userId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
        disconnect(conn, pstmt, rs);
        return userId;
    }

    // Get the image URI associated with a board ID
    public String getImgURIWithBoardId(int boardId) {
        String sql = "SELECT i.uri " +
                     "FROM board b " +
                     "LEFT JOIN img i ON b.imgId = i.id " +
                     "WHERE b.id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String imgURI = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, boardId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                imgURI = rs.getString("uri");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect(conn, pstmt, rs);
        }

        return imgURI;
    }
    
    public int getImgIdWithBoardId(int boardId) {
        String sql = "SELECT imgId FROM board WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int imgId = -1; // 기본값으로 -1 설정 (유효하지 않은 경우 반환)

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, boardId); // boardId 매핑
            rs = pstmt.executeQuery();

            if (rs.next()) {
                imgId = rs.getInt("imgId"); // imgId 가져오기
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect(conn, pstmt, rs); // 자원 해제
        }

        return imgId;
    }

}
