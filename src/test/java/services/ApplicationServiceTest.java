package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.Announcement;
import domain.Application;

@ContextConfiguration(locations = {
		"classpath:spring/junit.xml"
	})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ApplicationServiceTest extends AbstractTest{

	// The SUT
	//====================================================

	@Autowired
	private ApplicationService	applicationService;
	
	@Autowired
	private AnnouncementService announcementService;
	
	@Autowired
	private CustomerService customerService;
	
	
	// Tests
	//====================================================
	
	public void applicationCreateTest(final String username, final Announcement announcement, final Class<?> expected) {
		
			Class<?> caught = null;
		
			try {
		
				this.authenticate(username);
		
		
				final Application application = applicationService.create(announcement);
		
		
				this.unauthenticate();
		
			} catch (final Throwable oops) {
		
				caught = oops.getClass();
		
			}
		
			this.checkExceptions(expected, caught);
		}
	
	
	public void applicationSaveTest(final String username, final Announcement announcement, final Class<?> expected) {
		
		Class<?> caught = null;
	
		try {
	
			this.authenticate(username);
			
			final Application application = applicationService.create(announcement);
			
			applicationService.save(application);
	
			this.unauthenticate();
	
		} catch (final Throwable oops) {
	
			caught = oops.getClass();
	
		}
	
		this.checkExceptions(expected, caught);
	}
	
	
	// Drivers
	// ====================================================
	
	
	@Test
	public void driverApplicationCreate() {
	
		final Object testingData[][] = {
			// Creación con customer y propiedades correctas -> true
			{
				"customer1", announcementService.findOne(42), null
			},
			// creaciñon de una application sin esta registrado -> false
			{
				null , announcementService.findOne(40), IllegalArgumentException.class
			},
	};
		
		for (int i = 0; i < testingData.length; i++)
			this.applicationCreateTest((String) testingData[i][0], (Announcement) testingData[i][1], (Class<?>) testingData[i][2]);
		}
	
	
	@Test
	public void driverApplicationSave() {
		final Object testingData[][] = {
			// application a un announcement de un customer -> true
			{
				"customer1" ,announcementService.findOne(42), null
			},
			// crear una application en un announcement sin estar logueado -> false
			{
				null, announcementService.findOne(40), IllegalArgumentException.class
			}
		};
		for (int i = 0; i < testingData.length; i++)
			this.applicationSaveTest((String) testingData[i][0], (Announcement) testingData[i][1], (Class<?>) testingData[i][2]);
	}

}
