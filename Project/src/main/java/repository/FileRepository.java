package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FileRepository extends Repository{
	private Connection conn;
	
	public int insertImage(String imgURI) {
		conn = getConnection();
        String query = "INSERT INTO img (uri) VALUES (?)";
        int imgId = -1;
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, imgURI);
            int affectedCount = pstmt.executeUpdate();
            
            if(affectedCount > 0) {
            	ResultSet resultset = pstmt.getGeneratedKeys();
            	if(resultset.next())
            		imgId = resultset.getInt("id");
            }
            
            System.out.println("FileRepository >> insertImage() >> success generated imgId:"+imgId);
        } catch (SQLException e) {
        	System.out.println("FileRepository >> insertImage() >> fail");
            e.printStackTrace();
        }
        disConnection(conn);
        
        return imgId;
    }

	// ID로 URI 조회 메서드
    public String getURI(int primaryKey) {
        conn = getConnection();
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
        disConnection(conn);
        return uri;
    }

    // 이미지 삭제 메서드
    public void removeImage(int primaryKey) {
        conn = getConnection();
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
        disConnection(conn);
    }
}
