package com.restobjects;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ServiceObject<T> {

	
	private int serviceMessageCode;
	private String serviceMessageText;
	private List<T> items;
	
	public ServiceObject() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public ServiceObject(int serviceMessageCode, String serviceMessageText,
			List<T> items) {
		super();
		this.serviceMessageCode = serviceMessageCode;
		this.serviceMessageText = serviceMessageText;
		this.items = items;
	}



	public int getServiceMessageCode() {
		return serviceMessageCode;
	}
	public void setServiceMessageCode(int serviceMessageCode) {
		this.serviceMessageCode = serviceMessageCode;
	}
	public String getServiceMessageText() {
		return serviceMessageText;
	}
	public void setServiceMessageText(String serviceMessageText) {
		this.serviceMessageText = serviceMessageText;
	}
	public List<T> getItems() {
		return items;
	}
	public void setItems(List<T> items) {
		this.items = items;
	}
	
	
	
	
	
}
