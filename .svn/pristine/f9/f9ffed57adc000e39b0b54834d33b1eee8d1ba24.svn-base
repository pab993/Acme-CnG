
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	
	//The customer who has more applications accepted.
	@Query("select c1 from Customer c1 where (select count(ap1) from Customer c join c.applications ap1 where ap1.status='ACCEPTED' and c1=c) >= All(select count(ap) from Customer c join c.applications ap where ap.status='ACCEPTED' group by c)")
	Collection<Customer> customersMoreApplicationAccepted();
	
	//The customer who has more applications accepted.
	@Query("select c1 from Customer c1 where (select count(ap1) from Customer c join c.applications ap1 where ap1.status='DENIED' and c1=c) >= All(select count(ap) from Customer c join c.applications ap where ap.status='DENIED' group by c)")
	Collection<Customer> customersMoreApplicationDenied();

	//Find the userAccount by the Id
	@Query("select c from Customer c where c.userAccount.id = ?1")
	Customer findByUserAccountId(int userAccountId);
}

