package com.opsys.service;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.opsys.model.ActiveData;
import com.opsys.model.OperatingSystem;

@Stateless
@Path("/service")
public class OpSystemService {

	@Inject
	private EntityManager em;
	
	

	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getall")
	public List<OperatingSystem> getAllOpSystems(@Context HttpServletRequest req){
		List<OperatingSystem> ops = em.createQuery("select op from OperatingSystem op").getResultList();

		String contextPath = req.getContextPath();
		String ip = req.getLocalAddr();
		int port = req.getLocalPort();
		
		String fulladdress = "http://" + ip + ":" + port + contextPath;
		
		for (OperatingSystem operatingSystem : ops) {
			operatingSystem.setImagepath(fulladdress + "/images/" + operatingSystem.getImage()); 
		}
		
		
		return ops;
	}
	@GET
	@Path("/getbyid/{opid}")
	@Produces(MediaType.APPLICATION_JSON)
	public OperatingSystem getAllOpSystemsById(@Context HttpServletRequest req, @PathParam("opid") int id){
		OperatingSystem opsys = (OperatingSystem)em.createQuery("select op from OperatingSystem op where op.id=:opid").setParameter("opid", id).getSingleResult();

		String contextPath = req.getContextPath();
		String ip = req.getLocalAddr();
		int port = req.getLocalPort();
		
		String fulladdress = "http://" + ip + ":" + port + contextPath;
		opsys.setImagepath(fulladdress + "/images/" + opsys.getImage()); 

		
		return opsys;
	}
	
	@GET
	@Path("/getupdate/")
	@Produces(MediaType.APPLICATION_JSON)
	public ActiveData getValue(){
		
		
		Query q = em.createQuery("select a from ActiveData a where name='message'",ActiveData.class);
		
		ActiveData data = (ActiveData)q.getSingleResult();
		return data;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/saveos")
	@Produces(MediaType.TEXT_PLAIN)
	public String saveOs(Map<String, String> input){
		try {
			OperatingSystem opSys = new OperatingSystem();
			
			opSys.setHistory(input.get("history"));
			opSys.setImage("default.jpeg");
			opSys.setName(input.get("name"));
			
			em.persist(opSys);
			return "1";
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
			
		}
		
	
		
	}
	
	
	
}
