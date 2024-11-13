package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Repository {
	public Connection getConnection() {
        // MySQL 데이터베이스 연결
        try {
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/jsp?CHARACTER SET utf8", "jsp", "dongyang");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("repository:getConntection error :: cannot get connection");
			e.printStackTrace();
			return null;
		}
    }
}
