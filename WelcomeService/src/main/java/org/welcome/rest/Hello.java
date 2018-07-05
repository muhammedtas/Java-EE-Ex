package org.welcome.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/hello")
@RequestScoped
public class Hello {

	@Path("/sayhello")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHello(){
		
		return "Hello user, from rest rest services";
		
	}
	
	@Path("/sayhello/{name}")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHelloToName(@PathParam("name") String name){
		
		return "Hello " + name + " , from rest services";
		
	}
	
	@Path("/sayhellojson")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response sayHelloJson(Map<String, String> payload){
		Response.ResponseBuilder builder = null;
		
		String name = payload.get("name");
		String lastname = payload.get("lastname");
		
		 Map<String, String> responseObj = new HashMap<>();
		 responseObj.put("date",new Date().toString());
		 responseObj.put("fullname", name + " " + lastname);
		
		builder = Response.status(Response.Status.OK).entity(responseObj);
		return builder.build();
		
	}
	
	@Path("/listdata/{count}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listData(@PathParam("count") String count){
		Response.ResponseBuilder builder = null;
		
		int countInt = Integer.valueOf(count);

		 Map<String, List<DataObject>> responseObj = new HashMap<>();
		 List<DataObject> data = new ArrayList<>();
		 for (int i = 0; i < countInt; i++) {
			 DataObject obj = new DataObject();
			 obj.setDate(new Date().toString());
			 obj.setName("Entry - " + i);
			 obj.setDescription("Long description.... For entry " + i + ". Lorem ipsum vitae curriculum. Lorem ipsum vitae curriculum. Lorem ipsum vitae curriculum.");
			 data.add(obj);
			 
			 
		}
		 
		 responseObj.put("data",data);
		
		
		builder = Response.status(Response.Status.OK).entity(responseObj);
		return builder.build();
		
	}
	
}
