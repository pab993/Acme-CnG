package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.OfferRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Actor;
import domain.Customer;
import domain.Offer;

@Service
@Transactional
public class OfferService {
	
	// Repositories
	//====================================================================

	@Autowired
	private OfferRepository	offerRepository;
	
	// Supporting services
	//====================================================================
	@Autowired
	private ActorService actorService;
	
	@Autowired
	private CustomerService customerService;
	
	// Simple CRUD methods
	//====================================================================
	
	public Offer findOne(int offerId){
		Assert.isTrue(offerId != 0);
		Offer result;

		result = offerRepository.findOne(offerId);

		return result;
	}

	public Collection<Offer> findAll() {
		Collection<Offer> result;

		result = offerRepository.findAll();

		return result;
	}

	public Offer create() {
		Offer result;
		Customer customer;
		
		UserAccount userAccount = LoginService.getPrincipal();
		Authority i = new Authority();
		i.setAuthority("CUSTOMER");
		Assert.isTrue(userAccount.getAuthorities().contains(i));

		customer= customerService.findByPrincipal();
		
		result = new Offer();
		result.setBan(false);
		result.setCustomer(customer);

		return result;
	}

	public Offer save(Offer offer) {
		Assert.notNull(offer);
		Offer result;
		Customer customer;

		UserAccount userAccount = LoginService.getPrincipal();
		Authority i = new Authority();
		i.setAuthority("CUSTOMER");
		Assert.isTrue(userAccount.getAuthorities().contains(i));
		
		customer = customerService.findByPrincipal();
		Assert.isTrue(customer.equals(offer.getCustomer()));

		result = offerRepository.save(offer);

		return result;
	}

	public void delete(Offer offer) {
		Assert.notNull(offer);
		Customer customer;

		UserAccount userAccount = LoginService.getPrincipal();
		Authority i = new Authority();
		i.setAuthority("CUSTOMER");
		Assert.isTrue(userAccount.getAuthorities().contains(i));
		
		customer = customerService.findByPrincipal();
		Assert.isTrue(customer.equals(offer.getCustomer()));

		offerRepository.delete(offer);
	}
	
	// Other business methods
	// ===================================================================
	
	public Collection<Offer> findAllByCustomer(int idCustomer){
		Collection<Offer> result;
		
		result = offerRepository.findAllByCustomer(idCustomer);
		Assert.notNull(result);
		
		return result;
	}

	public Collection<Offer> findAllByNotCustomer(int idCustomer){
		Collection<Offer> result;
		
		result = offerRepository.findAllByNotCustomer(idCustomer);
		Assert.notNull(result);
		
		return result;
	}
	
	public Collection<Offer> getOfferByKeyWord(String keyWord){
		Assert.notNull(keyWord);
		Collection<Offer> result;
		
		
		Actor principal = actorService.findByPrincipal();
		Authority i = new Authority();
		i.setAuthority("ADMINISTRATOR");
		if(principal.getUserAccount().getAuthorities().contains(i)){
			result = offerRepository.searchOfferByWordsAdmin(keyWord);
		}else{
			result = offerRepository.searchOfferByWords(keyWord, principal.getId());
		}
		
		
		return result;
	}
	
	public Double ratioOfOffers(){
		Double result;
		
		result = offerRepository.ratioOfOffers();
		Assert.notNull(result);
		
		return result;
	}
	
	public void checkIfCustomer(){
		Customer principal;
		
		principal = customerService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Customer.class, principal);
	}

	public Collection<Offer> findAllFilter() {
		Collection<Offer> result;
		Actor principal;
		principal = actorService.findByPrincipal();
		Authority i = new Authority();
		i.setAuthority("ADMINISTRATOR");
		if(principal.getUserAccount().getAuthorities().contains(i)){
			result = offerRepository.findAll();
		}else{
			result = offerRepository.findAllFilter(principal.getId());
		}

		
		return result;
	}

}
