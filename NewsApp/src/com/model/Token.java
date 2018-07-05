package com.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="token")
public class Token {

	@Id
	private long id;
	
	private String tokenKey;
	private Date timeout;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTokenKey() {
		return tokenKey;
	}
	public void setTokenKey(String tokenKey) {
		this.tokenKey = tokenKey;
	}
	public Date getTimeout() {
		return timeout;
	}
	public void setTimeout(Date timeout) {
		this.timeout = timeout;
	}
	
	
}
