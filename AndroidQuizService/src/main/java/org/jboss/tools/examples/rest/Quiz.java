package org.jboss.tools.examples.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.ejb.Stateless;
import javax.faces.application.Application;
import javax.persistence.EntityManager;
import javax.print.attribute.standard.Media;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.tools.examples.model.Answer;
import org.jboss.tools.examples.model.Question;

@Path("/quiz")
@Stateless
public class Quiz {

	@Inject
	private EntityManager em;
	
	@Path("/question")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getQuestion(){
		
		try {
			List<Question> questions = em.createNamedQuery("Question.findAll",Question.class).getResultList();
			
			Random rnd = new Random();
			int randomQuestionIndex = rnd.nextInt(questions.size());
			System.out.println("Random q:" + randomQuestionIndex);
			Question selectedQ = questions.get(randomQuestionIndex);

			
			System.out.println("Selected q:" + selectedQ.getText());
			
			String answers = "";
			for (Answer ans : selectedQ.getAnswers()) {
				answers+= ans.getOption() + "->" + ans.getText() + " ";
			}
			
			Map<String, String> responseObj = new HashMap<>();
			responseObj.put("questionid",String.valueOf(selectedQ.getId()));
			responseObj.put("question", selectedQ.getText());
			responseObj.put("answers", answers);
			
			 Response.ResponseBuilder builder = Response.status(Response.Status.OK).entity(responseObj);
			 return builder.build();
		} catch (Exception e) {
			Map<String, String> responseObj = new HashMap<>();
			responseObj.put("error",e.getMessage());
			 Response.ResponseBuilder builder = Response.status(Response.Status.OK).entity(responseObj);
			 return builder.build();
		}
		
	}
	
	@Path("answer")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response answerQuestion(Map<String, String> answer){
		
		
		try {
			String qId = answer.get("questionid");
			String option = answer.get("option");
			
			Question q = em.find(Question.class, Integer.valueOf(qId));
			List<Answer> answers = q.getAnswers();
			Answer correctAns = null;
			for (Answer ans : answers) {
				if(ans.getIscorrect() ==1){
					correctAns = ans;
					break;
				}
			}
			boolean success = false;
			
			if(correctAns.getOption().equals(option)){
				success = true;
			}
			
			
			Map<String, String> responseObj = new HashMap<>();
			if(success){
				responseObj.put("result","correct");
			}else{
				responseObj.put("result","incorrect");
			}
			
			
			 Response.ResponseBuilder builder = Response.status(Response.Status.OK).entity(responseObj);
			 return builder.build();
		} catch (NumberFormatException e) {
			
			Map<String, String> responseObj = new HashMap<>();
			responseObj.put("error",e.getMessage());
			 Response.ResponseBuilder builder = Response.status(Response.Status.OK).entity(responseObj);
			 return builder.build();
		}
		
	}
	
	
}
