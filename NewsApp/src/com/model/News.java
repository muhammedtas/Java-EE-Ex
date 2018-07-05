package com.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the news database table.
 * 
 */
@Entity
@Table(name="news")
public class News implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String image;

	private Date ndate;

	private String text;

	private String title;

	//bi-directional many-to-one association to Comment
	@OneToMany(mappedBy="news")
	private List<Comment> comments;

	//bi-directional many-to-one association to NewsCategory
	@ManyToOne
	@JoinColumn(name="news_category_id")
	private NewsCategory newsCategory;

	public News() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Date getNdate() {
		return this.ndate;
	}

	public void setNdate(Date ndate) {
		this.ndate = ndate;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public NewsCategory getNewsCategory() {
		return this.newsCategory;
	}

	public void setNewsCategory(NewsCategory newsCategory) {
		this.newsCategory = newsCategory;
	}

}