package com.model;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.validator.constraints.NotEmpty;


/**
 * The persistent class for the comment database table.
 * 
 */
@Entity
@Table(name="comment")
public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	
	@NotEmpty
	private String name;

	@NotEmpty
	private String textt;

	//bi-directional many-to-one association to New
	@ManyToOne
	@JoinColumn(name="news_id")
	private News news;

	public Comment() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTextt() {
		return this.textt;
	}

	public void setTextt(String textt) {
		this.textt = textt;
	}

	public News getNews() {
		return this.news;
	}

	public void setNews(News news) {
		this.news = news;
	}

}