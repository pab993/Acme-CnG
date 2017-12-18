package controllers.Customer;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AnnouncementService;
import services.ApplicationService;
import services.CustomerService;
import services.OfferService;
import services.RequestService;
import controllers.AbstractController;
import domain.Announcement;
import domain.Application;
import domain.Customer;
import domain.Offer;
import domain.Request;

@Controller
@RequestMapping("/application/customer")
public class ApplicationCustomerController extends AbstractController {

	//Services ===============================================================================
	
	@Autowired
	private ApplicationService applicationService;
	
	@Autowired
	private AnnouncementService announcementService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private RequestService requestService;
	
	@Autowired
	private OfferService offerService;
	
	//Constructor ============================================================================
	
	public ApplicationCustomerController(){
		super();
	}
	
	//Listing By Customer ======================================================================

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listByCustomer(){
		ModelAndView result;
		Collection<Application> applications;
		Customer customer;
		
		customer = customerService.findByPrincipal();
		applications = applicationService.findAllByCustomer(customer.getId());

		result = new ModelAndView("application/list");
		result.addObject("applications", applications);
		result.addObject("requestURI","application/customer/list.do");
		
		return result;
	}
	
	//Listing By Announcement ======================================================================

	@RequestMapping(value = "/listByAnnouncement", method = RequestMethod.GET)
	public ModelAndView listByAnnouncement(@RequestParam int announcementId){
		ModelAndView result;
		Collection<Application> applications;
		Announcement announcement;
		boolean owner;

		owner = true;
		announcement = announcementService.findOne(announcementId);
		applications = applicationService.findAllByAnnouncement(announcement.getId());

		result = new ModelAndView("application/list");
		result.addObject("applications", applications);
		result.addObject("owner", owner);
		result.addObject("requestURI","application/customer/listByAnnouncement.do");
		
		return result;
	}
	
	//Listing By Request ======================================================================

	@RequestMapping(value = "/listByRequest", method = RequestMethod.GET)
	public ModelAndView listByRequest(@RequestParam int requestId){
		ModelAndView result;
		Collection<Application> applications;
		Request request;
		boolean owner;

		owner = true;
		request = requestService.findOne(requestId);
		applications = applicationService.findAllByRequest(request.getId());

		result = new ModelAndView("application/list");
		result.addObject("applications", applications);
		result.addObject("owner", owner);
		result.addObject("requestURI","application/customer/listByRequest.do");
		
		return result;
	}
	
	//Listing By Request ======================================================================

	@RequestMapping(value = "/listByOffer", method = RequestMethod.GET)
	public ModelAndView listByOffer(@RequestParam int offerId){
		ModelAndView result;
		Collection<Application> applications;
		Offer offer;
		boolean owner;

		owner = true;
		offer = offerService.findOne(offerId);
		applications = applicationService.findAllByRequest(offer.getId());

		result = new ModelAndView("application/list");
		result.addObject("applications", applications);
		result.addObject("owner", owner);
		result.addObject("requestURI","application/customer/listByOffer.do");
		
		return result;
	}
//	// Creation 
//	// ====================================================================================
//
//	@RequestMapping(value = "/create", method = RequestMethod.GET)
//	public ModelAndView create(@RequestParam int announcementId) {
//		ModelAndView result;
//		Application application;
//		Announcement announcement;
//
//		try {
//		announcement = announcementService.findOne(announcementId);
//		Assert.isTrue(applicationService.countApplicationByCustomerForAnnouncement(customerService.findByPrincipal().getId(), announcementId) == 0);
//		application = applicationService.create(announcement);
//
//		result = createEditModelAndView(application);
//		
//		} catch (Throwable e) {
//			result = listByCustomer();
//			result.addObject("message", "application.commit.error");
//		}
//		return result;
//	}
	
	// Create With requestId 
	// ====================================================================================

	@RequestMapping(value = "/createToRequest", method = RequestMethod.GET)
	public ModelAndView createToRequest(@RequestParam int requestId) {
		ModelAndView result;
		Application application;
		Request request;

		try {
			request = requestService.findOne(requestId);
		Assert.isTrue(applicationService.countApplicationByCustomerForRequest(customerService.findByPrincipal().getId(), requestId) == 0);
		application = applicationService.create(request);

		result = createEditModelAndView(application);
		
		} catch (Throwable e) {
			result = listByCustomer();
			result.addObject("message", "application.commit.error");
		}
		return result;
	}
	
	// Create With requestId 
	// ====================================================================================

	@RequestMapping(value = "/createToOffer", method = RequestMethod.GET)
	public ModelAndView createToOffer(@RequestParam int offerId) {
		ModelAndView result;
		Application application;
		Offer offer;

		try {
			offer = offerService.findOne(offerId);
		Assert.isTrue(applicationService.countApplicationByCustomerForOffer(customerService.findByPrincipal().getId(), offerId) == 0);
		application = applicationService.create(offer);

		result = createEditModelAndView(application);
		
		} catch (Throwable e) {
			result = listByCustomer();
			result.addObject("message", "application.commit.error");
		}
		return result;
	}

	//Editing -------------------------------------------------------------------------------------
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int applicationId){
	ModelAndView result;
	Application application;
	
	application = applicationService.findOne(applicationId);
	Assert.notNull(application);
	result = createEditModelAndView(application);
	
	return result;	
	}
	
	@RequestMapping(value = "/edit", method=RequestMethod.POST, params="save")
	public ModelAndView save(@Valid Application application, BindingResult binding){
		ModelAndView result;
		
		if(binding.hasErrors()){
			result = createEditModelAndView(application);
		}else{
			try{
				
				applicationService.save(application);
				result = new ModelAndView("redirect:/request/customer/list.do");
			}catch(Throwable oops){
				result = createEditModelAndView(application, "application.commit.error");
			}
		}
		return result;
	}
	
	@RequestMapping(value = "/accepted", method = RequestMethod.GET)
	public ModelAndView accepted(@RequestParam int applicationId) {
		ModelAndView result;

		applicationService.accepted(applicationId);
		result = new ModelAndView("redirect:/welcome/index.do");

		return result;
	}

	@RequestMapping(value = "/denied", method = RequestMethod.GET)
	public ModelAndView denied(@RequestParam int applicationId) {
		ModelAndView result;

		applicationService.denied(applicationId);
		result = new ModelAndView("redirect:/welcome/index.do");
		
		return result;
	}
	
	// The ancillary methods 
	// ====================================================================================

	protected ModelAndView createEditModelAndView(Application application) {
		ModelAndView result;

		result = createEditModelAndView(application, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Application application, String message) {
		ModelAndView result;

		result = new ModelAndView("application/edit");

		result.addObject("application", application);
		result.addObject("message", message);

		return result;
	}
}
