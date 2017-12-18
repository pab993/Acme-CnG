package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import domain.Customer;

import utilities.AbstractTest;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class AnnouncementServiceTest extends AbstractTest {
	
	// The SUT
	//====================================================

	@Autowired
	private AnnouncementService	announcementService;
	
	@Autowired
	private CustomerService	customerService;
	
	// Tests
	//====================================================
	
	/*
	 * Post a request in which he or she informs that he or she wishes to move
	 * from a place to another one and would like to find someone with whom he
	 * or she can share the trip.
	 * 
	 * En este caso de uso se listan las requests que pertenecen al 'customer' que
	 * se encuentra actualmente autentificado, por lo tanto es accesible para cualquier 'customer'.
	 * Para forzar el error se intenta acceder con un administrador y con un usuario no autentificado.
	 */
	public void myListOfAnnouncementTest(final String username, final Class<?> expected) {
		Class<?> caught = null;

		try {

			Customer customer = new Customer();

			this.authenticate(username);

			this.announcementService.checkIfCustomer();

			for (final Customer c : this.customerService.findAll())
				if (c.getUserAccount().getUsername().equals(username))
					customer = c;

			this.announcementService.findAllByCustomer(customer.getId());

			this.unauthenticate();

		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	// Drivers
	//====================================================
	
	@Test
	public void driverMyListTest() {
		final Object testingData[][] = {
			// Mi lista con customer -> true
			{
				"customer1", null
			},
			// Mi lista con admin -> false
			{
				"admin", IllegalArgumentException.class
			},
			// Mi lista con usuario no autentificado -> false
			{
				null, IllegalArgumentException.class
			}
		};
		for (int i = 0; i < testingData.length; i++)
			this.myListOfAnnouncementTest((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

}
