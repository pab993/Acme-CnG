package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.RequestRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Actor;
import domain.Customer;
import domain.Request;

@Service
@Transactional
public class RequestService {

	// Repositories
	//====================================================================

	@Autowired
	private RequestRepository	requestRepository;
	
	// Supporting services
	//====================================================================
	
	@Autowired
	private ActorService actorService;
	@Autowired
	private CustomerService customerService;
	
	// Simple CRUD methods
	//====================================================================
	
	public Request findOne(int requestId){
		Assert.isTrue(requestId != 0);
		Request result;

		result = requestRepository.findOne(requestId);

		return result;
	}

	public Collection<Request> findAll() {
		Collection<Request> result;

		result = requestRepository.findAll();

		return result;
	}

	public Request create() {
		Request result;
		Customer customer;
		
		UserAccount userAccount = LoginService.getPrincipal();
		Authority i = new Authority();
		i.setAuthority("CUSTOMER");
		Assert.isTrue(userAccount.getAuthorities().contains(i));

		customer= customerService.findByPrincipal();
		
		result = new Request();
		result.setBan(false);
		result.setCustomer(customer);

		return result;
	}

	public Request save(Request request) {
		Assert.notNull(request);
		Request result;
		Customer customer;

		UserAccount userAccount = LoginService.getPrincipal();
		Authority i = new Authority();
		i.setAuthority("CUSTOMER");
		Assert.isTrue(userAccount.getAuthorities().contains(i));
		
		customer = customerService.findByPrincipal();
		Assert.isTrue(customer.equals(request.getCustomer()));

		result = requestRepository.save(request);

		return result;
	}

	public void delete(Request request) {
		Assert.notNull(request);
		Customer customer;

		UserAccount userAccount = LoginService.getPrincipal();
		Authority i = new Authority();
		i.setAuthority("CUSTOMER");
		Assert.isTrue(userAccount.getAuthorities().contains(i));
		
		customer = customerService.findByPrincipal();
		Assert.isTrue(customer.equals(request.getCustomer()));

		requestRepository.delete(request);
	}
	
	// Other business methods
	// ===================================================================
	
	public Collection<Request> findAllByCustomer(int idCustomer){
		Collection<Request> result;
		
		result = requestRepository.findAllByCustomer(idCustomer);
		Assert.notNull(result);
		
		return result;
	}

	public Collection<Request> findAllByNotCustomer(int idCustomer){
		Collection<Request> result;
		
		result = requestRepository.findAllByNotCustomer(idCustomer);
		Assert.notNull(result);
		
		return result;
	}
	
	public Collection<Request> getRequestByKeyWord(String keyWord){
		Assert.notNull(keyWord);
		Collection<Request> result;
		Actor principal;
		principal = actorService.findByPrincipal();
		Authority i = new Authority();
		i.setAuthority("ADMINISTRATOR");
		if(principal.getUserAccount().getAuthorities().contains(i)){
			result = requestRepository.searchRequestByWordsAdmin(keyWord);
		}else{
			result = requestRepository.searchRequestByWords(keyWord, principal.getId());
		}
		
		
		
		return result;
	}
	
	public Double ratioOfRequests(){
		Double result;
		
		result = requestRepository.ratioOfRequests();
		Assert.notNull(result);
		
		return result;
	}

	public Collection<Request> findAllFilter() {

		Collection<Request> result;
		Actor principal;
		principal = actorService.findByPrincipal();
		Authority i = new Authority();
		i.setAuthority("ADMINISTRATOR");
		if(principal.getUserAccount().getAuthorities().contains(i)){
			result = requestRepository.findAll();
		}
		else{
			result = requestRepository.findAllFilter(principal.getId());
		}
		
		
		return result;
	}
	
	public void checkIfCustomer(){
		Customer principal;
		
		principal = customerService.findByPrincipal();
		Assert.notNull(principal);
		Assert.isInstanceOf(Customer.class, principal);
	}
}
