package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.User;

public class LoginRepository {
	Connection conn;
	
//	public LoginRepository() throws ClassNotFoundException{
//		Class.forName("com.mysql.cj.jdbc.Driver");
//		//conn = DriverManager.getConnection("jdbc::mysql://localhost::3306/JSP","jsp","dongyang");
//		System.out.println("LoginRpository operator() >> class.forname() success");
//	}
	
	private void getConn() throws SQLException {
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jsp?CHARACTER SET utf8","jsp","dongyang");
	}
	
	//insert into user (id, passwd, name, authority) values(?,?,?,?,?);
	public boolean addUser(User user)  {
		boolean re = false;
		try {
			getConn();
			PreparedStatement statement = conn.prepareStatement("insert into user (id, passwd, name, authority) values(?,?,?,?);");
			statement.setString(1, user.getId());
			statement.setString(2, user.getPasswd());
			statement.setString(3, user.getName());
			statement.setString(4, user.getAuthority());
			
			re = statement.executeUpdate() > 0;
			
			if(re)
				System.out.println("login repository >> addUser() success");
			
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return re; //성공시 true 반환
	}
	
}
