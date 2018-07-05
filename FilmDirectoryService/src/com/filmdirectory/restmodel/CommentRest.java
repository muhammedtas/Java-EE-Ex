package com.filmdirectory.restmodel;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CommentRest {

	private int id;

	private String owner;

	private String text;

	private String moviename;
	private int movieid;
	
	public CommentRest() {
		// TODO Auto-generated constructor stub
	}

	public CommentRest(int id, String owner, String text, String moviename,
			int movieid) {
		super();
		this.id = id;
		this.owner = owner;
		this.text = text;
		this.moviename = moviename;
		this.movieid = movieid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getMoviename() {
		return moviename;
	}

	public void setMoviename(String moviename) {
		this.moviename = moviename;
	}

	public int getMovieid() {
		return movieid;
	}

	public void setMovieid(int movieid) {
		this.movieid = movieid;
	}
	
	
}
