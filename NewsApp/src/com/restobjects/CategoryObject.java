package com.restobjects;

public class CategoryObject {

	private int id;
	private String name;
	
	public CategoryObject() {
		// TODO Auto-generated constructor stub
	}

	public CategoryObject(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
