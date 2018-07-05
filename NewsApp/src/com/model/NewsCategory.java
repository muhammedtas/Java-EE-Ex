package com.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the news_category database table.
 * 
 */
@Entity
@Table(name="news_category")
public class NewsCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String name;

	//bi-directional many-to-one association to New
	@OneToMany(mappedBy="newsCategory")
	private List<News> news;

	public NewsCategory() {
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

	public List<News> getNews() {
		return this.news;
	}

	public void setNews(List<News> news) {
		this.news = news;
	}

}