
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class Customer extends Actor {

	//Constructor
	//=====================================================

	//Attributes
	//=====================================================

	// Relationships
	// ====================================================================================

	private Collection<Announcement>	announcements;
	private Collection<Application>		applications;


	@Valid
	@OneToMany(mappedBy = "customer")
	public Collection<Announcement> getAnnouncements() {
		return this.announcements;
	}

	public void setAnnouncements(Collection<Announcement> announcements) {
		this.announcements = announcements;
	}

	@Valid
	@OneToMany(mappedBy = "customer")
	public Collection<Application> getApplications() {
		return this.applications;
	}

	public void setApplications(Collection<Application> applications) {
		this.applications = applications;
	}

}
