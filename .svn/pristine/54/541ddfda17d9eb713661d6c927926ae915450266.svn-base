
package controllers.Customer;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AnnouncementService;
import services.CustomerService;
import controllers.AbstractController;
import domain.Announcement;
import domain.Customer;



@Controller
@RequestMapping("/announcement/customer")
public class AnnouncementCustomerController extends AbstractController {

	//Services ===============================================================================

	@Autowired
	private AnnouncementService	announcementService;

	@Autowired
	private CustomerService		customerService;
	
	//Constructor ============================================================================

	public AnnouncementCustomerController() {
		super();
	}

	// Listing
	// ====================================================================================

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Announcement> announcements;
		Customer principal;
		boolean own;
		
		principal = customerService.findByPrincipal();
		announcements = announcementService.findAllByNotCustomer(principal.getId());
		own = true;

		result = new ModelAndView("announcement/list");
		result.addObject("announcements", announcements);
		result.addObject("own", own);
		result.addObject("principal", principal);
		result.addObject("requestURI", "announcement/customer/list.do");

		return result;
	}
	
	//Listing By Customer ======================================================================

	@RequestMapping(value = "/myList", method = RequestMethod.GET)
	public ModelAndView listByCustomer(){
		ModelAndView result;
		Collection<Announcement> announcements;
		Customer customer;
		boolean owner;
		
		customer = customerService.findByPrincipal();
		announcements = announcementService.findAllByCustomer(customer.getId());
		owner = true;

		result = new ModelAndView("announcement/list");
		result.addObject("announcements", announcements);
		result.addObject("owner", owner);
		result.addObject("requestURI","announcement/customer/myList.do");
		
		return result;
	}

}
