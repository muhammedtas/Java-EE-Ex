package com.filmdirectory.restmodel;


import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement
public class MovieRest {

	private int id;

	private String description;

	private String image;

	private String name;

	private double rating;

	private String year;


	private DirectorRest director;
	
	public MovieRest() {
		// TODO Auto-generated constructor stub
	}

	public MovieRest(int id, String description, String image, String name,
			double rating, String year, DirectorRest director) {
		super();
		this.id = id;
		this.description = description;
		this.image = image;
		this.name = name;
		this.rating = rating;
		this.year = year;
		this.director = director;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public DirectorRest getDirector() {
		return director;
	}

	public void setDirector(DirectorRest director) {
		this.director = director;
	}
	
	
	
}
