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
 * The persistent class for the director database table.
 * 
 */
@Entity
@Table(name="director")
@NamedQueries({
	@NamedQuery(name="Director.findAll",query ="select d from Director d order by d.lastname ASC"),
	@NamedQuery(name="Director.findById",query ="select d from Director d where d.id=:did")
})
public class Director implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String image;

	private String lastname;

	private String name;

	//bi-directional many-to-one association to Movie
	@OneToMany(mappedBy="director", fetch=FetchType.LAZY)
	private List<Movie> movies;

	public Director() {
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

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Movie> getMovies() {
		return this.movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

}