package forms;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

import domain.Actor;

public class MessageForm{

	public MessageForm(){
		super();
	}
	
	private Date createMoment;
	private String title;
	private String text;
	private String attachment;
		

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getcreateMoment() {
		return createMoment;
	}
		
	public void setCreateMoment(Date createMoment) {
		this.createMoment = createMoment;
	}
		
	@NotBlank
	public String getTitle() {
		return title;
	}
		
	public void setTitle(String title) {
		this.title = title;
	}
		
	@NotBlank
	public String getText() {
		return text;
	}
		
	public void setText(String text) {
		this.text = text;
	}
	
	@URL
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
		
	// Relationships
	// ====================================================================================


	private Actor actorSender;
	private Actor actorRecipient;
	
	@NotNull
	@Valid
	public Actor getActorSender() {
		return actorSender;
	}

	public void setActorSender(Actor actorSender) {
		this.actorSender = actorSender;
	}

	@NotNull
	@Valid
	public Actor getActorRecipient() {
		return actorRecipient;
	}

	public void setActorRecipient(Actor actorRecipient) {
		this.actorRecipient = actorRecipient;
	}
	
}
