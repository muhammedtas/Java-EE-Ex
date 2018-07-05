package com.utils;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class Resources {

	@Produces
	@PersistenceContext(unitName="newsservice")
	private EntityManager em;
	
	
	
}
