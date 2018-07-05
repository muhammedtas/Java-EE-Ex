package com.filmdirectory.restmodel;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RestResult<T> {

	private int resultCode;
	private List<T> resultList;
	
	
	public RestResult() {
		// TODO Auto-generated constructor stub
	}


	public RestResult(int resultCode, List<T> resultList) {
		super();
		this.resultCode = resultCode;
		this.resultList = resultList;
	}


	public int getResultCode() {
		return resultCode;
	}


	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}


	public List<T> getResultList() {
		return resultList;
	}


	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}
	
	
	
}
