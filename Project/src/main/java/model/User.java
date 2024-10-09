package model;

public class User {
	private String id, name, passwd;
	private String authority; // "user" / "admin"
	
	public User(String id, String passwd) {
		this.id = id;
		this.passwd = passwd;
	}
	
	public User(String id, String passwd, String name) {
		this.id = id;
		this.passwd = passwd;
		this.name = name;
	}
	public User(String id, String passwd, String name, String au) {
		this.id = id;
		this.passwd = passwd;
		this.name = name;
		this.authority = au;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	@Override
	public String toString() {
		return "id : "+id +", passwd : "+passwd+", name : " + name;
	}
}
