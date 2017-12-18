
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
import services.RequestService;
import controllers.AbstractController;
import domain.Customer;
import domain.Request;

@Controller
@RequestMapping("/request/customer")
public class RequestCustomerController extends AbstractController {

	//Services ===============================================================================

	@Autowired
	private RequestService	requestService;
	
	@Autowired
	private	CustomerService customerService;

	//Constructor ============================================================================

	public RequestCustomerController() {
		super();
	}
	
	// Listing A+
	// ====================================================================================

	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Request> requests;
		boolean own;
		
		requests = requestService.findAll();
		own = true;

		result = new ModelAndView("request/list");
		result.addObject("requests", requests);
		result.addObject("own", own);
		result.addObject("requestURI", "request/customer/list.do");

		return result;
	}

	// Listing By Not Customer
	// ====================================================================================

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listByNotCustomer() {
		ModelAndView result;
		Collection<Request> requests;
		Customer principal;
		boolean own;
		
		principal = customerService.findByPrincipal();
		requests = requestService.findAllByNotCustomer(principal.getId());
		own = true;

		result = new ModelAndView("request/list");
		result.addObject("requests", requests);
		result.addObject("own", own);
		result.addObject("principal", principal);
		result.addObject("requestURI", "request/customer/list.do");

		return result;
	}
	
	//Listing By Customer ======================================================================

	@RequestMapping(value = "/myList", method = RequestMethod.GET)
	public ModelAndView listByCustomer(){
		ModelAndView result;
		Collection<Request> requests;
		Customer customer;
		boolean owner;
		
		customer = customerService.findByPrincipal();
		requests = requestService.findAllByCustomer(customer.getId());
		owner = true;

		result = new ModelAndView("request/list");
		result.addObject("requests", requests);
		result.addObject("owner", owner);
		result.addObject("requestURI","request/customer/myList.do");
		
		return result;
	}
	
	//SearchForm ==============================================================================

	@RequestMapping(value = "/searchForm", method = RequestMethod.GET)
	public ModelAndView listSearch() {
		ModelAndView result;
		Collection<Request> requests;
		Boolean firstTime;

		requests = requestService.findAllFilter();
		firstTime = true;

		result = new ModelAndView("request/search");
		result.addObject("requests", requests);
		result.addObject("requestURI", "request/customer/searchForm.do");
		result.addObject("firstTime", firstTime);

		return result;
	}

	//Search ==============================================================================	

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView search(@RequestParam("keyword") String keyword) {
		ModelAndView result;
		Collection<Request> requests;

		requests = requestService.getRequestByKeyWord(keyword);

		result = new ModelAndView("request/search");
		result.addObject("requests", requests);

		return result;
	}

	//Creation 
	// ====================================================================================

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Request request;

		request = requestService.create();

		result = createEditModelAndView(request);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Request request, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(request);
		} else {
			try {

				requestService.save(request);
				result = new ModelAndView("redirect:/welcome/index.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(request, "announcement.commit.error");
			}
		}
		return result;
	}

	// The ancillary methods 
	// ====================================================================================

	protected ModelAndView createEditModelAndView(Request request) {
		ModelAndView result;

		result = createEditModelAndView(request, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Request request, String message) {
		ModelAndView result;

		result = new ModelAndView("announcement/edit");

		result.addObject("request", request);
		result.addObject("message", message);

		return result;
	}

}
