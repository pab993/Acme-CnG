package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Announcement;
import domain.Application;
import domain.Customer;

import repositories.ApplicationRepository;

@Service
@Transactional
public class ApplicationService {

	
	// Managed Repository 
	// ====================================================================================

	@Autowired
	private ApplicationRepository	applicationRepository;

	// Supported Services 
	// ====================================================================================

	@Autowired
	private CustomerService	customerService;

	// Constructor methods
	// ====================================================================================

	public ApplicationService() {
		super();
	}

	// Simple CRUDS methods 
	// ====================================================================================

	public Application findOne(int applicationId) {

		Application result;
		Assert.notNull(applicationId);

		result = applicationRepository.findOne(applicationId);
		Assert.notNull(result);

		return result;
	}

	public Application create(Announcement announcement) {
		Application result;
		Customer principal;

		principal = customerService.findByPrincipal();
		Assert.notNull(principal);

		result = new Application();
		result.setCustomer(principal);
		result.setAnnouncement(announcement);

		return result;
	}

	public Application save(Application application) {
		Assert.notNull(application);
		Application result;
		Customer principal;

		principal = customerService.findByPrincipal();
		Assert.notNull(principal);

		application.setStatus("PENDING");
		result = applicationRepository.save(application);

		return result;
	}
	
	// Other business methods
	// ===================================================================
	
	public Collection<Application> findAllByCustomer(int idCustomer){
		Collection<Application> result;
		
		result = applicationRepository.findAllByCustomer(idCustomer);
		Assert.notNull(result);
		
		return result;
	}
	
	public Collection<Application> findAllByAnnouncement(int idAnnouncement){
		Collection<Application> result;
		
		result = applicationRepository.findAllByAnnouncement(idAnnouncement);
		Assert.notNull(result);
		
		return result;
	}
	
	public Collection<Application> findAllByRequest(int idRequest){
		Collection<Application> result;
		
		result = applicationRepository.findAllByRequest(idRequest);
		Assert.notNull(result);
		
		return result;
	}
	
	public void accepted(int applicationId) {
		Customer customer;
		Application application;

		customer = customerService.findByPrincipal();
		application = findOne(applicationId);

		Assert.isInstanceOf(Customer.class, customer);

		application.setStatus("ACCEPTED");

		applicationRepository.save(application);
	}

	public void denied(int applicationId) {
		Customer customer;
		Application application;

		customer = customerService.findByPrincipal();
		application = findOne(applicationId);

		Assert.isInstanceOf(Customer.class, customer);

		application.setStatus("DENIED");

		applicationRepository.save(application);
	}

	public Integer countApplicationByCustomerForAnnouncement(int idCustomer, int idAnnouncement) {
		Integer result;
	
		result = applicationRepository.countApplicationByCustomerForAnnouncement(idCustomer, idAnnouncement);
			
		return result;
	}
	
	public Integer countApplicationByCustomerForRequest(int idCustomer, int idRequest) {
		Integer result;
	
		result = applicationRepository.countApplicationByCustomerForRequest(idCustomer, idRequest);
			
		return result;
	}

	public Integer countApplicationByCustomerForOffer(int idCustomer, int idOffer) {
		Integer result;
	
		result = applicationRepository.countApplicationByCustomerForOffer(idCustomer, idOffer);
			
		return result;
	}
	
}
