
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Application extends DomainEntity {

	// Constructors
	// ====================================================================================

	// Attributes
	// ====================================================================================

	private String	status;


	@NotNull
	public String getStatus() {
		return this.status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}


	// Relationships
	// ====================================================================================

	private Customer		customer;
	private Announcement	announcement;


	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(final Customer customer) {
		this.customer = customer;
	}

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Announcement getAnnouncement() {
		return this.announcement;
	}

	public void setAnnouncement(final Announcement announcement) {
		this.announcement = announcement;
	}

}
