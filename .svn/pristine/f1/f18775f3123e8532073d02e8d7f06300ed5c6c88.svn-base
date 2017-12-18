
package controllers.Customer;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CustomerService;
import services.OfferService;
import controllers.AbstractController;
import domain.Customer;
import domain.Offer;

@Controller
@RequestMapping("/offer/customer")
public class OfferCustomerController extends AbstractController {

	//Services ===============================================================================

	@Autowired
	private OfferService	offerService;
	
	@Autowired
	private	CustomerService customerService;


	//Constructor ============================================================================

	public OfferCustomerController() {
		super();
	}
	
	// Listing
	// ====================================================================================

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Offer> offers;
		Customer principal;
		boolean own;
		
		principal = customerService.findByPrincipal();
		offers = offerService.findAllByNotCustomer(principal.getId());
		own = true;

		result = new ModelAndView("offer/list");
		result.addObject("offers", offers);
		result.addObject("own", own);
		result.addObject("principal", principal);
		result.addObject("requestURI", "offer/customer/list.do");

		return result;
	}
	
	//Listing By Customer ======================================================================

	@RequestMapping(value = "/myList", method = RequestMethod.GET)
	public ModelAndView listByCustomer(){
		ModelAndView result;
		Collection<Offer> offers;
		Customer customer;
		boolean owner;
		
		customer = customerService.findByPrincipal();
		offers = offerService.findAllByCustomer(customer.getId());
		owner = true;

		result = new ModelAndView("offer/list");
		result.addObject("offers", offers);
		result.addObject("owner", owner);
		result.addObject("requestURI","offer/customer/myList.do");
		
		return result;
	}

	//SearchForm ==============================================================================

	@RequestMapping(value = "/searchForm", method = RequestMethod.GET)
	public ModelAndView listSearch() {
		ModelAndView result;
		Collection<Offer> offers;
		Boolean firstTime;
		

		offers = offerService.findAllFilter();
		firstTime = true;

		result = new ModelAndView("offer/search");
		result.addObject("offers", offers);
		result.addObject("requestURI", "offer/customer/searchForm.do");
		result.addObject("firstTime", firstTime);

		return result;
	}

	//Search ==============================================================================	

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView search(@RequestParam("keyword") String keyword) {
		ModelAndView result;
		Collection<Offer> offers;

		offers = offerService.getOfferByKeyWord(keyword);

		result = new ModelAndView("offer/search");
		result.addObject("offers", offers);

		return result;
	}

	//Creation 
	// ====================================================================================

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Offer offer;

		offer = offerService.create();

		result = createEditModelAndView(offer);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Offer offer, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(offer);
		} else {
			try {

				offerService.save(offer);
				result = new ModelAndView("redirect:/welcome/index.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(offer, "announcement.commit.error");
			}
		}
		return result;
	}

	// The ancillary methods 
	// ====================================================================================

	protected ModelAndView createEditModelAndView(Offer offer) {
		ModelAndView result;

		result = createEditModelAndView(offer, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Offer offer, String message) {
		ModelAndView result;

		result = new ModelAndView("announcement/editOffer");

		result.addObject("offer", offer);
		result.addObject("message", message);

		return result;
	}

}
