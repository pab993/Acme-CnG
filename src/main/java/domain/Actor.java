
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import security.UserAccount;

@Entity
@Access(AccessType.PROPERTY)
public abstract class Actor extends Comentable {

	// Attributes
	// ====================================================================================

	private String	name;
	private String	surname;
	private String	phoneNumber;
	private String	email;


	// Constructors
	// ====================================================================================

	public Actor() {
		super();
	}

	// Getters & setters
	// ====================================================================================

	@NotBlank
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotBlank
	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Email
	@NotBlank
	@Column(unique = true)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Pattern(regexp = "^[(][+][0-9]{2,3}[)][0-9]{9}$")
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	// Relationships
	// ====================================================================================

	private UserAccount			userAccount;
	private Collection<Comment>	sendComments;
	private Collection<Message>	sendMessages;
	private Collection<Message>	receivedMessages;


	@Valid
	@NotNull
	@OneToOne(cascade = CascadeType.ALL, optional = false)
	public UserAccount getUserAccount() {
		return this.userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	@Valid
	@OneToMany(mappedBy = "actor")
	public Collection<Comment> getSendComments() {
		return this.sendComments;
	}

	public void setSendComments(Collection<Comment> sendComments) {
		this.sendComments = sendComments;
	}

	@Valid
	@OneToMany(mappedBy = "actorSender")
	public Collection<Message> getSendMessages() {
		return this.sendMessages;
	}

	public void setSendMessages(Collection<Message> sendMessages) {
		this.sendMessages = sendMessages;
	}

	@Valid
	@OneToMany(mappedBy = "actorRecipient")
	public Collection<Message> getReceivedMessages() {
		return this.receivedMessages;
	}

	public void setReceivedMessages(Collection<Message> receivedMessages) {
		this.receivedMessages = receivedMessages;
	}

}
