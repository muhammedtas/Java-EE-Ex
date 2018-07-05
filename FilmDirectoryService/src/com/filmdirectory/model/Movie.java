package com.filmdirectory.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the movie database table.
 * 
 */
@Entity
@Table(name="movie")
@NamedQueries({
	@NamedQuery(name="Movie.findAll",query ="select m from Movie m order by m.name ASC"),
	@NamedQuery(name="Movie.findByDirectorId", query="select m from Movie m where m.director.id =:did"),
	@NamedQuery(name="Movie.findById", query="select m from Movie m where m.id =:mid")
})
public class Movie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String description;

	private String image;

	private String name;

	private double rating;

	private String year;

	//bi-directional many-to-one association to Comment
	@OneToMany(mappedBy="movie", fetch=FetchType.LAZY)
	private List<Comment> comments;

	//bi-directional many-to-one association to Director
	@ManyToOne
	private Director director;

	public Movie() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getRating() {
		return this.rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getYear() {
		return this.year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Director getDirector() {
		return this.director;
	}

	public void setDirector(Director director) {
		this.director = director;
	}

}