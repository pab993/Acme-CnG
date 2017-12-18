
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public abstract class Announcement extends Comentable {

	//Constructor
	//=====================================================

	//Attributes
	//=====================================================

	private String	title;
	private String	description;
	private Date	moment;
	private Address	originPlace;
	private Address	destinyPlace;
	private Boolean	ban;


	@NotBlank
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@NotBlank
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getMoment() {
		return this.moment;
	}

	public void setMoment(Date moment) {
		this.moment = moment;
	}

	@Valid
	@AttributeOverrides({
		@AttributeOverride(name = "place", column = @Column(name = "originplace")), @AttributeOverride(name = "coordinateX", column = @Column(name = "originCoordX")), @AttributeOverride(name = "coordinateY", column = @Column(name = "originCoordY")),
	})
	public Address getOriginPlace() {
		return this.originPlace;
	}

	public void setOriginPlace(Address originPlace) {
		this.originPlace = originPlace;
	}

	@Valid
	@AttributeOverrides({
		@AttributeOverride(name = "place", column = @Column(name = "destinyplace")), @AttributeOverride(name = "coordinateX", column = @Column(name = "destinyCoordX")), @AttributeOverride(name = "coordinateY", column = @Column(name = "destinyCoordY")),
	})
	public Address getDestinyPlace() {
		return this.destinyPlace;
	}

	public void setDestinyPlace(Address destinyPlace) {
		this.destinyPlace = destinyPlace;
	}

	@NotNull
	public Boolean getBan() {
		return this.ban;
	}

	public void setBan(Boolean ban) {
		this.ban = ban;
	}


	// Relationships
	// ====================================================================================

	private Customer				customer;
	private Collection<Application>	applications;


	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Valid
	@OneToMany(mappedBy = "announcement")
	public Collection<Application> getApplications() {
		return this.applications;
	}

	public void setApplications(Collection<Application> applications) {
		this.applications = applications;
	}

}
