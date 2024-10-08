package service;

import java.util.ArrayList;
import java.util.Optional;

import model.User;
import repository.LoginRepository;

public class LoginService {
	private ArrayList<User> userDatabase = new ArrayList<>();
	private LoginRepository loginRepository;
	
	public LoginService() {
		userDatabase.add(new User("id","passwd","name"));
		//try {
			loginRepository = new LoginRepository();
//		}catch (Exception e) {
//			System.out.println("Login Service >> can not create LoginRepository");
//		}
	}
	
	public boolean addUser(User user) { // regist용
		if(checkById(user)){
			// already same id is exist
			return false;
		}
		else {
			// passwd가 10자리 이하인지 확인
			
			//authority = "user" 설정
			user.setAuthority("user");
						
			userDatabase.add(user);
			
			loginRepository.addUser(user);
			
			System.out.println("LoginService >> regist success");
			userDatabase.stream().forEach(u->{
				System.out.println(u.getId()+", "+u.getPasswd());
			});
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
