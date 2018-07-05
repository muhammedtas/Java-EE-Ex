package com.filmdirectory.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the comment database table.
 * 
 */
@Entity
@Table(name="comment")
@NamedQueries({
	@NamedQuery(name="Comment.findByMovieId",query ="select cmm from Comment cmm where cmm.movie.id=:mid order by cmm.id DESC")
})
public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String owner;

	private String text;

	//bi-directional many-to-one association to Movie
	@ManyToOne
	private Movie movie;

	public Comment() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOwner() {
		return this.owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Movie getMovie() {
		return this.movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

}