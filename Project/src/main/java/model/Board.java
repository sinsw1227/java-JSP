package model;

import java.sql.Timestamp;

//게시글 클래스
public class Board {
 private int id; // generate created
 private String title;
 private String content;
 private String userName; // String userId
 private String imgURI; // int imgId
 private java.sql.Timestamp createdAt;
 	public Board() {}
 	
	public Board(int id, String title, String content) {
		this.id = id;
		this.title = title;
		this.content = content;
	}
	public Board(String title, String content) {
		this.title = title;
		this.content = content;
	}
	public String getImgURI() {
		return imgURI;
	}
	public void setImgURI(String imgURL) {
		this.imgURI = imgURL;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String author) {
		this.userName = author;
	}
	public java.sql.Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(java.sql.Timestamp createdAt) {
		this.createdAt = createdAt;
	}

 
}