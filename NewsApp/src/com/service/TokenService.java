package com.service;

import java.util.Calendar;
import java.util.Date;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.model.Token;

@Stateless
public class TokenService {

	@Inject
	private EntityManager em;
	
	
	public String checkToken(String token){
		
		Query q= em.createQuery("select t from Token t where t.tokenKey=:tokenkey",Token.class).setParameter("tokenkey", token);
		Token tokenObj = null;
		try {
			tokenObj = (Token)q.getSingleResult();
		} catch (NoResultException e) {
			//No token
			//System.out.println("no");
		}
		
			
		if(tokenObj!=null){
			
			Date tokenTimeout = tokenObj.getTimeout();
			
			Calendar calTimeOut = Calendar.getInstance();
			calTimeOut.setTime(tokenTimeout);
	
			Calendar calNow = Calendar.getInstance();
			if(calNow.after(calTimeOut)){
				
				em.remove(tokenObj);
				
				return "timeout";
			}else{
				return "success";
			}
			
			
		}
		
		return "notoken";
		
	}
	
	
}
