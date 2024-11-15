package model;

import java.sql.Timestamp;

//게시글 클래스
public class Board {
 private int id;
 private String title;
 private String content;
 private String author;
 private String imgURL;
 private java.sql.Timestamp createdAt;
 	public Board() {}
 	
	public Board(int id, String title, String content, String author, String imgURL) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.author = author;
		this.imgURL = imgURL;
		this.createdAt = new java.sql.Timestamp(50);
	}


	public String getImgURL() {
		return imgURL;
	}
	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
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
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public java.sql.Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(java.sql.Timestamp createdAt) {
		this.createdAt = createdAt;
	}

 
}