package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FileRepository {
	private Connection conn;
	private Repository repository;
	
	public FileRepository() {
		repository = new Repository();
	}
	
	public void insertImage(String imgURI) {
		conn = repository.getConnection();
        String query = "INSERT INTO img (uri) VALUES (?)";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, imgURI);
            pstmt.executeUpdate();
            System.out.println("FileRepository >> insertImage() >> success");
        } catch (SQLException e) {
        	System.out.println("FileRepository >> insertImage() >> fail");
            e.printStackTrace();
        }
        disConnection();
    }

	// ID로 URI 조회 메서드
    public String getURL(int primaryKey) {
        conn = repository.getConnection();
        String query = "SELECT uri FROM img WHERE id = ?";
        String uri = null;
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, primaryKey);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    uri = rs.getString("uri");
                    System.out.println("FileRepository >> getURL() >> imgid:"+primaryKey+"uri:"+uri);
                }
            }
        } catch (SQLException e) {
            System.out.println("FileRepository >> getURL() >> fail");
            e.printStackTrace();
        }
        disConnection();
        return uri;
    }

    // 이미지 삭제 메서드
    public void eraseImage(int primaryKey) {
        conn = repository.getConnection();
        String query = "DELETE FROM img WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, primaryKey);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("FileRepository >> eraseImage() >> success id:"+primaryKey);
            } else {
                System.out.println("FileRepository >> eraseImage() >> no image found with the given ID");
            }
        } catch (SQLException e) {
            System.out.println("FileRepository >> eraseImage() >> fail");
            e.printStackTrace();
        }
        disConnection();
    }
    
    private void disConnection() {
    	if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }
}
