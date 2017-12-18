package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.AnnouncementRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Actor;
import domain.Announcement;
import domain.Customer;

@Service
@Transactional
public class AnnouncementService {

	// Repositories
	//====================================================================

	@Autowired
	private AnnouncementRepository	announcementRepository;
	
	// Supporting services
	//====================================================================
	
	@Autowired
	private ActorService actorService;
	@Autowired
	private CustomerService customerService;
	
	// Simple CRUD methods
	//====================================================================
	
	public Announcement findOne(int announcementId){
		Assert.isTrue(announcementId != 0);
		Announcement result;

		result = announcementRepository.findOne(announcementId);

		return result;
	}

	public Collection<Announcement> findAll() {
		Collection<Announcement> result;

		result = announcementRepository.findAll();

		return result;
	}
	
//	public Announcement create() {
//		Announcement result;
//		Customer customer;
//		
//		UserAccount userAccount = LoginService.getPrincipal();
//		Authority i = new Authority();
//		i.setAuthority("CUSTOMER");
//		Assert.isTrue(userAccount.getAuthorities().contains(i));
//
//		customer= customerService.findByPrincipal();
//		
//		result = new Announcement();
//		result.setBan(false);
//		result.setCustomer(customer);
//
//		return result;
//	}
	
	public Announcement save(Announcement announcement) {
		Assert.notNull(announcement);
		Announcement result;
		Actor actor;

		UserAccount userAccount = LoginService.getPrincipal();
		
		Authority i1 = new Authority();
		i1.setAuthority("ADMINISTRATOR");		
		actor = actorService.findByPrincipal();
		Assert.isTrue(actor.equals(announcement.getCustomer()) || userAccount.getAuthorities().contains(i1));

		result = announcementRepository.save(announcement);

		return result;
	}
	
	public void delete(Announcement announcement) {
		Assert.notNull(announcement);
		Customer customer;

		UserAccount userAccount = LoginService.getPrincipal();
		Authority i = new Authority();
		i.setAuthority("CUSTOMER");
		Assert.isTrue(userAccount.getAuthorities().contains(i));
		
		customer = customerService.findByPrincipal();
		Assert.isTrue(customer.equals(announcement.getCustomer()));

		announcementRepository.delete(announcement);
	}
	
	// Other business methods
	// ===================================================================
	
	public Collection<Announcement> findAllByCustomer(int idCustomer){
		Collection<Announcement> result;
		
		result = announcementRepository.findAllByCustomer(idCustomer);
		Assert.notNull(result);
		
		return result;
	}

	public Collection<Announcement> findAllByNotCustomer(int idCustomer){
		Collection<Announcement> result;
		
		result = announcementRepository.findAllByNotCustomer(idCustomer);
		Assert.notNull(result);
		
		return result;
	}
	
	public Double avgAnnouncementsPerCustomer(){
		Double result;
		
		result = announcementRepository.avgAnnouncementsPerCustomer();
		Assert.notNull(result);
		
		return result;
	}
	
	public Double avgApplicationsPerAnnouncements(){
		Double result;
		
		result = announcementRepository.avgApplicationsPerAnnouncements();
		Assert.notNull(result);
		
		return result;
	}
	
	public void checkIfCustomer(){
		Customer principal;
		
		principal = customerService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Customer.class, principal);
	}

	public Announcement ban(Announcement announcement) {
		if(announcement.getBan()) announcement.setBan(false);
		else announcement.setBan(true);
		return this.save(announcement);
	}
	
}
