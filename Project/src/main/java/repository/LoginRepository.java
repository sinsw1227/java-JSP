package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import model.User;

public class LoginRepository {
	Connection conn;
	
	private void getConn() throws SQLException {
		// MySQL 데이터베이스 연결
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jsp?CHARACTER SET utf8", "jsp", "dongyang");
	}
	
	// 회원 추가
	// SQL: INSERT INTO user (id, passwd, name, authority) VALUES (?, ?, ?, ?);
	public boolean addUser(User user) {
		boolean re = false;
		try {
			getConn();
			PreparedStatement statement = conn.prepareStatement("INSERT INTO user (id, passwd, name, authority) VALUES (?, ?, ?, ?);");
			statement.setString(1, user.getId());
			statement.setString(2, user.getPasswd());
			statement.setString(3, user.getName());
			statement.setString(4, user.getAuthority());
			
			re = statement.executeUpdate() > 0; // 성공시 true 반환
			
			if(re)
				System.out.println("LoginRepository >> 사용자 추가 성공");
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return re;
	}
	
	// ID로 사용자 검색
	// SQL: SELECT * FROM user WHERE id = ?;
	public Optional<User> getUserById(String id) {
		Optional<User> user = Optional.empty();
		try {
			getConn();
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM user WHERE id = ?;");
			statement.setString(1, id);
			
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				user = Optional.of(new User(rs.getString("id"), rs.getString("passwd"), rs.getString("name"), rs.getString("authority")));
				System.out.println("LoginRepository >> ID로 사용자 검색 성공");
			}
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
}
