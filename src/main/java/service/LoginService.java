package service;

import java.util.ArrayList;
import java.util.Optional;

import model.User;

public class LoginService {
	private ArrayList<User> userDatabase = new ArrayList<>();
	
	public boolean addUser(User user) {
		if(isUser(user).isEmpty()){
			userDatabase.add(user);
			return true;
		}
		else {
			// already exist
			return false;
		}
	}
	
	public Optional<User> isUser(User input) {
		return userDatabase.stream().filter(u->{
			return (u.getId().equals(input.getId()) && u.getPasswd().equals(input.getPasswd()) );
		}).findAny();
	}
}
