package com.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.ejb.EJBTransactionRolledbackException;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.model.Comment;
import com.model.News;
import com.model.NewsCategory;
import com.model.Token;
import com.restobjects.CategoryObject;
import com.restobjects.CommentObject;
import com.restobjects.NewsObject;
import com.restobjects.ServiceObject;

@Stateless
@Path("/news")
public class NewsService {

	
	@Inject
	private EntityManager em;
	
	/*
	//OK
	@GET
	@Path("/login/{username}/{password}")
	@Produces(MediaType.APPLICATION_JSON)
	public ServiceObject<String> login(@PathParam("username") String username,@PathParam("password")  String password, @Context HttpServletRequest req){
		
		String keychars = "abcdefghijklmnoprstyvwzx123456789";
		int length = keychars.length();
		Random rnd = new Random();
		
		StringBuffer apikey = new StringBuffer();
		
		for (int i = 0; i <10; i++) {
			char rndChar = keychars.charAt(rnd.nextInt(length));
			apikey.append(rndChar);
		}
		

		ServiceObject<String> resultObj = new ServiceObject<String>();
		
		
		try {
			
			if(username.equals("newsconsumer") && password.equals("news2013")){
				
				resultObj.setServiceMessageCode(1);
				resultObj.setServiceMessageText(apikey.toString());
				req.getSession().setAttribute("token", apikey.toString());
				
				
				Token token = new Token();
				token.setTokenKey(apikey.toString());
			
				Calendar timeout = Calendar.getInstance();
				timeout.add(Calendar.HOUR, 1);
				
				token.setTimeout(timeout.getTime());
				
				em.persist(token);
				
				
			}else{
				resultObj.setServiceMessageCode(1);
				resultObj.setServiceMessageText("LOGIN ERROR");
			}
			
		} catch (Exception e) {
			resultObj.setServiceMessageCode(0);
			resultObj.setServiceMessageText("LOGIN ERROR");
			return resultObj;
		}
		
		return resultObj;
				
		
	}
	*/
	//OK
	@GET
	@Path("/getall")
	@Produces(MediaType.APPLICATION_JSON)
	public ServiceObject<NewsObject> getAllNews(@Context HttpServletRequest req){
		
		List<NewsObject> datalist = new ArrayList<NewsObject>();
		ServiceObject<NewsObject> resultObj = new ServiceObject<NewsObject>();
		String query = "select n from News n order by n.ndate DESC";
		
		String contextPath = req.getContextPath();
		String ip = req.getLocalAddr();
		int port = req.getLocalPort();
		String fulladdress = "http://" + ip + ":" + port + contextPath;
		
		try {
			List<News> newsList=  em.createQuery(query).getResultList();
			
			for (News news : newsList) {
				datalist.add(
						new NewsObject(
								news.getId(), 
								news.getTitle(), 
								news.getText(), 
								news.getNdate(), 
								fulladdress + "/images/"+ news.getImage(), 
								news.getNewsCategory().getName()));
			}
			
			
			resultObj.setItems(datalist);
			resultObj.setServiceMessageCode(1);
			resultObj.setServiceMessageText("SUCCESS");
		} catch (Exception e) {
			resultObj.setServiceMessageCode(0);
			resultObj.setServiceMessageText(e.getMessage());
		}
		
		
		return resultObj;
		
		
	}
	//OK
	@GET
	@Path("/getbycategoryid/{catid}")
	@Produces(MediaType.APPLICATION_JSON)
	public ServiceObject<NewsObject> getNewsByCategoryId(@Context HttpServletRequest req, @PathParam("catid") int catid){
		
		List<NewsObject> datalist = new ArrayList<NewsObject>();
		ServiceObject<NewsObject> resultObj = new ServiceObject<NewsObject>();
		String query = "select n from News n where n.newsCategory.id=:catid order by n.ndate DESC";
		
		String contextPath = req.getContextPath();
		String ip = req.getLocalAddr();
		int port = req.getLocalPort();
		String fulladdress = "http://" + ip + ":" + port + contextPath;
		
		try {
			List<News> newsList=  em.createQuery(query).setParameter("catid", catid).getResultList();
			
			for (News news : newsList) {
				datalist.add(
						new NewsObject(
								news.getId(), 
								news.getTitle(), 
								news.getText(), 
								news.getNdate(), 
								fulladdress + "/images/"+ news.getImage(), 
								news.getNewsCategory().getName()));
			}
			
			
			resultObj.setItems(datalist);
			resultObj.setServiceMessageCode(1);
			resultObj.setServiceMessageText("SUCCESS");
		} catch (Exception e) {
			resultObj.setServiceMessageCode(0);
			resultObj.setServiceMessageText(e.getMessage());
		}
		
		
		return resultObj;
		
		
	}
	
	@GET
	@Path("/getcommentsbynewsid/{newsid}")
	@Produces(MediaType.APPLICATION_JSON)
	public ServiceObject<CommentObject> getCommentsByNewsId(@Context HttpServletRequest req, @PathParam("newsid") int newsid){
		
		List<CommentObject> datalist = new ArrayList<CommentObject>();
		ServiceObject<CommentObject> resultObj = new ServiceObject<CommentObject>();
		String query = "select cmm from Comment cmm where cmm.news.id=:newsid order by cmm.id DESC";
		
		
		
		try {
			List<Comment> commentList=  em.createQuery(query).setParameter("newsid", newsid).getResultList();
			
			for (Comment comm : commentList) {
				datalist.add(
						new CommentObject(comm.getId(),comm.getNews().getId(),comm.getTextt(),comm.getName()));
			}
			
			
			resultObj.setItems(datalist);
			resultObj.setServiceMessageCode(1);
			resultObj.setServiceMessageText("SUCCESS");
		} catch (Exception e) {
			resultObj.setServiceMessageCode(0);
			resultObj.setServiceMessageText(e.getMessage());
		}
		
		
		return resultObj;
		
		
	}
	
	@GET
	@Path("/getnewsbyid/{newsid}")
	@Produces(MediaType.APPLICATION_JSON)
	public ServiceObject<NewsObject> getNewsById(@Context HttpServletRequest req, @PathParam("newsid") int newsid){
		
		List<NewsObject> datalist = new ArrayList<NewsObject>();
		ServiceObject<NewsObject> resultObj = new ServiceObject<NewsObject>();
		String query = "select n from News n where n.id=:nid";
		
		String contextPath = req.getContextPath();
		String ip = req.getLocalAddr();
		int port = req.getLocalPort();
		String fulladdress = "http://" + ip + ":" + port + contextPath;
		
		try {
			News news =  (News)em.createQuery(query).setParameter("nid", newsid).getSingleResult();
			
			datalist.add(
					new NewsObject(
							news.getId(), 
							news.getTitle(), 
							news.getText(), 
							news.getNdate(), 
							fulladdress + "/images/"+ news.getImage(), 
							news.getNewsCategory().getName()));
			
			resultObj.setItems(datalist);
			resultObj.setServiceMessageCode(1);
			resultObj.setServiceMessageText("SUCCESS");
		}catch(NoResultException nox){
			resultObj.setServiceMessageCode(0);
			resultObj.setServiceMessageText("NO RESULT");
		}
		catch (Exception e) {
			resultObj.setServiceMessageCode(0);
			resultObj.setServiceMessageText(e.getMessage());
		}
		
		
		return resultObj;
		
		
	}
	
	@GET
	@Path("/getallnewscategories")
	@Produces(MediaType.APPLICATION_JSON)
	public ServiceObject<CategoryObject> getAllNewsCategories(){
		
		List<CategoryObject> datalist = new ArrayList<CategoryObject>();
		ServiceObject<CategoryObject> resultObj = new ServiceObject<CategoryObject>();
		String query = "select nc from NewsCategory nc order by nc.name ASC";
	
		
		try {
			List<NewsCategory> catList=  em.createQuery(query).getResultList();
			
			for (NewsCategory cat : catList) {
				datalist.add(
						new CategoryObject(cat.getId(), cat.getName()));
			}
			
			
			resultObj.setItems(datalist);
			resultObj.setServiceMessageCode(1);
			resultObj.setServiceMessageText("SUCCESS");
		} catch (Exception e) {
			resultObj.setServiceMessageCode(0);
			resultObj.setServiceMessageText(e.getMessage());
		}
		
		
		return resultObj;
		
		
	}
	
	@POST
	@Path("/savecomment")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ServiceObject<String> saveComment(@Context HttpServletRequest req, CommentObject input){
		ServiceObject<String> obj = new ServiceObject<String>();
		try {
			String text = input.getText();
			String name = input.getName();
			int newsid = input.getNews_id();
			
			if(text==null || name==null){
				obj.setServiceMessageCode(0);
				obj.setServiceMessageText("text, name, lastname cannot be null");
				return obj;
			}
			
			if(text.equals("") || name.equals("")){
				obj.setServiceMessageCode(0);
				obj.setServiceMessageText("text, name, lastname cannot be null");
				return obj;
			}
			
			Comment comm = new Comment();
			comm.setName(name);
			comm.setTextt(text);
			
			News news  = em.find(News.class, newsid);
			
			if(news ==null){
				obj.setServiceMessageCode(0);
				obj.setServiceMessageText("news_id is not valid");
				return obj;
			}
			
			comm.setNews(news);
			
			
			em.persist(comm);
			obj.setServiceMessageCode(1);
			obj.setServiceMessageText("SUCCESS");
		}catch(EJBTransactionRolledbackException rox){
			obj.setServiceMessageCode(0);
			obj.setServiceMessageText(rox.getMessage());
		}
		catch(ConstraintViolationException cex){
			obj.setServiceMessageCode(0);
			obj.setServiceMessageText(cex.getMessage());
		}
		catch (Exception e) {
			obj.setServiceMessageCode(0);
			obj.setServiceMessageText(e.getMessage());
		}
		
		return obj;
		
	}
	
	@GET
	@Path("/error")
	@Produces(MediaType.APPLICATION_JSON)
	public ServiceObject<String> getError(){
		ServiceObject<String> resultObj = new ServiceObject<String>();
		resultObj.setServiceMessageCode(0);
		resultObj.setServiceMessageText("LOGIN FAIL");
		return resultObj;
	}
	
	
}
