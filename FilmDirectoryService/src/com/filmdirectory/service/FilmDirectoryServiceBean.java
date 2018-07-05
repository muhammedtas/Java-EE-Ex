package com.filmdirectory.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.filmdirectory.model.Comment;
import com.filmdirectory.model.Director;
import com.filmdirectory.model.Movie;
import com.filmdirectory.restmodel.CommentRest;
import com.filmdirectory.restmodel.DirectorRest;
import com.filmdirectory.restmodel.MovieRest;
import com.filmdirectory.restmodel.RestResult;

@Stateless
@Path("/service")
public class FilmDirectoryServiceBean {

	@Inject
	private EntityManager em;
	

	@GET
	@Path("/directors")
	@Produces(MediaType.APPLICATION_JSON)
	public RestResult<DirectorRest> getAllDirectors(@Context HttpServletRequest req){
		RestResult<DirectorRest> resultObj = new RestResult<DirectorRest>();
		List<Director> directors = em.createNamedQuery("Director.findAll").getResultList();
		List<DirectorRest> directorsRest = new ArrayList<DirectorRest>();
		
		String contextPath = req.getContextPath();
		String ip = req.getLocalAddr();
		int port = req.getLocalPort();
		String fulladdress = "http://" + ip + ":" + port + contextPath;
		
		for (Director director : directors) {
			directorsRest.add(new DirectorRest(director.getId(), fulladdress + "/images/directors/" + director.getImage(), director.getLastname(), director.getName()));
		}
		
		if(directorsRest.size()==0){
			resultObj.setResultCode(0);
		}else{
			resultObj.setResultCode(1);
			resultObj.setResultList(directorsRest);
		}
		
		return  resultObj;
	}
	
	@GET
	@Path("/movies")
	@Produces(MediaType.APPLICATION_JSON)
	public RestResult<MovieRest> getAllMovies(@Context HttpServletRequest req){
		RestResult<MovieRest> resultObj = new RestResult<MovieRest>();
		List<Movie> movies = em.createNamedQuery("Movie.findAll").getResultList();
		List<MovieRest> moviesRest = new ArrayList<MovieRest>();
		
		String contextPath = req.getContextPath();
		String ip = req.getLocalAddr();
		int port = req.getLocalPort();
		String fulladdress = "http://" + ip + ":" + port + contextPath;
		
		for (Movie movie : movies) {
			moviesRest.add(new MovieRest(movie.getId(),
					movie.getDescription(),fulladdress + "/images/movies/" + movie.getImage(), movie.getName(), movie.getRating(), movie.getYear(),
					new DirectorRest(movie.getDirector().getId(),
							fulladdress + "/images/directors/" +movie.getDirector().getImage(),
							movie.getDirector().getLastname(), movie.getDirector().getName())));
		}
		if(moviesRest.size()==0){
			resultObj.setResultCode(0);
		}else{
			resultObj.setResultCode(1);
			resultObj.setResultList(moviesRest);
		}
		return  resultObj;
	}
	
	@GET
	@Path("/moviesbydirectorid/{directorid}")
	@Produces(MediaType.APPLICATION_JSON)
	public RestResult<MovieRest> getMoviesByDirectorId(@PathParam("directorid") int directorid, @Context HttpServletRequest req){
		RestResult<MovieRest> resultObj = new RestResult<MovieRest>();
		List<Movie> movies = em.createNamedQuery("Movie.findByDirectorId").setParameter("did", directorid).getResultList();
		List<MovieRest> moviesRest = new ArrayList<MovieRest>();
		
		String contextPath = req.getContextPath();
		String ip = req.getLocalAddr();
		int port = req.getLocalPort();
		String fulladdress = "http://" + ip + ":" + port + contextPath;
		
		for (Movie movie : movies) {
			moviesRest.add(new MovieRest(movie.getId(),
					movie.getDescription(),fulladdress + "/images/movies/" + movie.getImage(), movie.getName(), movie.getRating(), movie.getYear(),
					new DirectorRest(movie.getDirector().getId(),
							fulladdress + "/images/directors/" +movie.getDirector().getImage(),
							movie.getDirector().getLastname(), movie.getDirector().getName())));
		}
		
		if(moviesRest.size()==0){
			resultObj.setResultCode(0);
		}else{
			resultObj.setResultCode(1);
			resultObj.setResultList(moviesRest);
		}
		return  resultObj;
	}
	
	

	@GET
	@Path("/moviebyid/{movieid}")
	@Produces(MediaType.APPLICATION_JSON)
	public RestResult<MovieRest> getMoviesById(@PathParam("movieid") int movieid, @Context HttpServletRequest req){
		RestResult<MovieRest> resultObj = new RestResult<MovieRest>();
		resultObj.setResultCode(0);
		String contextPath = req.getContextPath();
		String ip = req.getLocalAddr();
		int port = req.getLocalPort();
		String fulladdress = "http://" + ip + ":" + port + contextPath;
		
		
		try {
			Movie movie = (Movie)em.createNamedQuery("Movie.findById").setParameter("mid", movieid).getSingleResult();
			MovieRest mov = new MovieRest();
			
			mov.setDescription(movie.getDescription());
			mov.setId(movie.getId());
			mov.setImage(fulladdress + "/images/movies/" + movie.getImage());
			mov.setName(movie.getName());
			mov.setRating(movie.getRating());
			mov.setYear(movie.getYear());
			mov.setDirector(new DirectorRest(movie.getDirector().getId(),
					fulladdress + "/images/directors/" + movie.getDirector().getImage(),
								movie.getDirector().getLastname(), movie.getDirector().getName()));
			List<MovieRest> moviesList = new ArrayList<MovieRest>();
			moviesList.add(mov);
			resultObj.setResultCode(1);
			resultObj.setResultList(moviesList);
			
			
		} catch (NoResultException ne) {
			System.out.println("No result");
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		return  resultObj;
	}
	

	@GET
	@Path("/directorbyid/{directorid}")
	@Produces(MediaType.APPLICATION_JSON)
	public RestResult<DirectorRest> getDirectorById(@PathParam("directorid") int directorid, @Context HttpServletRequest req){
		RestResult<DirectorRest> resultObj = new RestResult<DirectorRest>();
		resultObj.setResultCode(0);
		
		String contextPath = req.getContextPath();
		String ip = req.getLocalAddr();
		int port = req.getLocalPort();
		String fulladdress = "http://" + ip + ":" + port + contextPath;
		
		try {

			Director director = (Director)em.createNamedQuery("Director.findById").setParameter("did", directorid).getSingleResult();
			
			DirectorRest dir = new DirectorRest(director.getId(), fulladdress + "/images/directors/" + director.getImage(), director.getLastname(), director.getName());
			
			List<DirectorRest> dirsList = new ArrayList<DirectorRest>();
			dirsList.add(dir);
			resultObj.setResultCode(1);
			resultObj.setResultList(dirsList);
			
			
		} catch (NoResultException ne) {
			System.out.println("No result");
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return  resultObj;
	}
	
	@GET
	@Path("/moviebyid/{movieid}/comments")
	@Produces(MediaType.APPLICATION_JSON)
	public RestResult<CommentRest> getCommentsByMovieId(@PathParam("movieid") int movieid){
		RestResult<CommentRest> resultObj = new RestResult<CommentRest>();
		resultObj.setResultCode(0);
		List<Comment> comments = em.createNamedQuery("Comment.findByMovieId").setParameter("mid", movieid).getResultList();
		List<CommentRest> commentsRest = new ArrayList<CommentRest>();
		
		for (Comment comment : comments) {
			commentsRest.add(new CommentRest(comment.getId(), comment.getOwner(), comment.getText(), comment.getMovie().getName(), comment.getMovie().getId()));
		}
		
		if(commentsRest.size()>0){

			resultObj.setResultCode(1);
			resultObj.setResultList(commentsRest);
		}
		return resultObj;
		
		
	}
	
	@POST
	@Path("/postcomment")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String postComment(Map<String, String> input){

		String retVal = "1";
		try {
			
			Comment comment = new Comment();
			comment.setOwner(input.get("owner"));
			comment.setText(input.get("text"));
			
			int movieid = Integer.valueOf(input.get("movieid"));
			
			Movie mov = (Movie)em.find(Movie.class, movieid);
			
			comment.setMovie(mov);
			if(mov==null){
				retVal="No movie with id " + movieid;
				return retVal;
			}
			em.persist(comment);
			
		} catch (Exception e) {
			retVal =e.getMessage();
			e.printStackTrace();
		}
		return retVal;
		
	}
	
}
