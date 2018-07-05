package com.filmdirectory.restmodel;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DirectorRest {

	private int id;

	private String image;

	private String lastname;

	private String name;
	
	public DirectorRest() {
		// TODO Auto-generated constructor stub
	}

	public DirectorRest(int id, String image, String lastname, String name) {
		super();
		this.id = id;
		this.image = image;
		this.lastname = lastname;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
