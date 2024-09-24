package service;

import java.util.ArrayList;
import java.util.Optional;

import model.User;

public class LoginService {
	private ArrayList<User> userDatabase = new ArrayList<>();
	
	public LoginService() {
		userDatabase.add(new User("id","passwd","name"));
	}
	
	public boolean addUser(User user) { // regist용
		if(checkById(user)){
			// already same id is exist
			return false;
		}
		else {
			// passwd가 8자리 이상인지 확인
			
			//authority = "user" 설정
			user.setAuthority("user");
						
			userDatabase.add(user);
			System.out.println("LoginService >> regist success");
			return true;
		}
	}
	
	public Optional<User> isUser(User input) { // login 확인용
		System.out.println("loginService >> isUser() >> check function");
		return userDatabase.stream().filter(u->{
			System.out.println(u.getId() + "," + u.getPasswd()+","+input.getId()+","+input.getPasswd());
			return (u.getId().equals(input.getId()) && u.getPasswd().equals(input.getPasswd()) );
		}).findAny();
	}
	
	private boolean checkById(User input){// regist => id가 같은 user가 있는지 확인 있다면 true
		return !(userDatabase.stream().filter(u->{
			return (u.getId().equals(input.getId()));
		}).findAny().isEmpty());
	}
}
