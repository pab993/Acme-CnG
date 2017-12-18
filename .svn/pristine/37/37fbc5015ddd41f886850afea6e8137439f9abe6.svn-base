/*
 * CustomerController.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.CustomerService;
import domain.Customer;
import forms.CustomerForm;

@Controller
@RequestMapping("/customer")
public class CustomerController extends AbstractController {

	@Autowired
	private CustomerService customerService;


	// Constructors -----------------------------------------------------------

	public CustomerController() {
		super();
	}

	
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;

		CustomerForm customerForm = new CustomerForm();

		result = createEditModelAndView(customerForm);

		return result;
	}

	// @Autowired private Validator validator;
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(CustomerForm customerForm, BindingResult binding) {
		ModelAndView result;

		Customer customer = customerService.reconstruct(customerForm, binding);
		if (binding.hasErrors())
			result = createEditModelAndView(customerForm);

		else {
			try {

				customerService.save(customer);
				result = new ModelAndView("redirect:/welcome/index.do");
			} catch (Throwable oops) {
				// TODO: handle exception
				result = createEditModelAndView(customerForm,
						"customer.commit.error");
			}
		}

		return result;
	}

	// ===========================================
	// Ancilliary methods

	private ModelAndView createEditModelAndView(CustomerForm customerForm) {
		// TODO Auto-generated method stub
		return createEditModelAndView(customerForm, null);
	}

	private ModelAndView createEditModelAndView(CustomerForm customerForm,
			String message) {
		// TODO Auto-generated method stub

		ModelAndView resul = new ModelAndView("customer/edit");

		resul.addObject("customerForm", customerForm);
		resul.addObject("message", message);
		return resul;
	}

}
