package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Announcement;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Integer>{

	@Query("select a from Announcement a where a.customer.id=?1")
	Collection<Announcement> findAllByCustomer(int id);
	
	@Query("select a from Announcement a where a.customer.id!=?1 and a.ban = false")
	Collection<Announcement> findAllByNotCustomer(int id);
	
	//Average number of offers and request per customer.
	@Query("select avg(c.announcements.size) from Customer c")
	Double avgAnnouncementsPerCustomer();
	
	//Average number of applications per offer or request.
	@Query("select avg(a.applications.size) from Announcement a")
	Double avgApplicationsPerAnnouncements();
}
