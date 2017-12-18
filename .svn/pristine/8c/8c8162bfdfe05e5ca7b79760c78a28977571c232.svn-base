package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Application;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer>{

	@Query("select a from Application a where a.customer.id=?1")
	Collection<Application> findAllByCustomer(int id);
	
	@Query("select ap from Application ap where ap.announcement.id=?1 and ap.status = 'PENDING'")
	Collection<Application> findAllByAnnouncement(int id);
	
	@Query("select ap from Application ap where ap.announcement.id=?1 and ap.status = 'PENDING'")
	Collection<Application> findAllByRequest(int id);

	@Query("select count(a) from Announcement a join a.applications ap where ap.customer.id=?1 and a.id=?2")
	Integer countApplicationByCustomerForAnnouncement(int idCustomer, int idAnnouncement);
	
	@Query("select count(r) from Request r join r.applications ap where ap.customer.id=?1 and r.id=?2")
	Integer countApplicationByCustomerForRequest(int idCustomer, int idRequest);

	@Query("select count(o) from Offer o join o.applications ap where ap.customer.id=?1 and o.id=?2")
	Integer countApplicationByCustomerForOffer(int idCustomer, int idOffer);
}
