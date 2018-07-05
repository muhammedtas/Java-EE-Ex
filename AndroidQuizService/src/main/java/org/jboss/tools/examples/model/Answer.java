package org.jboss.tools.examples.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the answer database table.
 * 
 */
@Entity
@NamedQuery(name="Answer.findAll", query="SELECT a FROM Answer a")
@Table(name="answer")
public class Answer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private byte iscorrect;

	private String option;

	private String text;

	//bi-directional many-to-one association to Question
	@ManyToOne
	private Question question;

	public Answer() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte getIscorrect() {
		return this.iscorrect;
	}

	public void setIscorrect(byte iscorrect) {
		this.iscorrect = iscorrect;
	}

	public String getOption() {
		return this.option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Question getQuestion() {
		return this.question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

}