package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import security.Authority;
import security.UserAccount;
import utilities.AbstractTest;
import domain.Customer;

@Transactional
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerServiceTest extends AbstractTest {

	// The SUT
	// ====================================================
	
	@Autowired 
	private CustomerService customerService;
	
	// Tests
	
	@Test
	public void driver(){
		
		Customer customerToSave = new Customer();
		
		UserAccount userAccount = new UserAccount();	// añado autoridad CUSTOMER a la cuenta de usuario del customer
		Collection<Authority> authorities = new ArrayList<Authority>();
		Authority authority = new Authority();
		authority.setAuthority("CUSTOMER");
		authorities.add(authority);
		userAccount.setAuthorities(authorities);

		customerToSave.setUserAccount(userAccount);
		
		customerToSave.setId(0);
		customerToSave.setVersion(0);
		customerToSave.setEmail("test@hotmail.com");
		customerToSave.setName("test");
		customerToSave.setSurname("test");
		customerToSave.setPhoneNumber("(+34)647382899");
		customerToSave.getUserAccount().setUsername("userTest");
		customerToSave.getUserAccount().setPassword(
			new Md5PasswordEncoder().encodePassword(
				"userTest", null));
		
		
		Object testingData[][] = {
			{customerToSave, null},
			{null, IllegalArgumentException.class}
		};
		
		for(int i = 0; i < testingData.length; i++){
			templateSave((Customer) testingData[i][0],
				(Class<?>) testingData[i][1]);
		}
		
		testingData = null;
		
		Object testingData2[][]={
			
			{"customer1", null},
			{null, null}
		};
		
		for(int i = 0; i < testingData2.length; i++){
			templateCreate((String) testingData2[i][0],
				(Class<?>) testingData2[i][1]);
		}
	
		}
	

	protected void templateCreate(String username, Class<?> expected) {
		// TODO Auto-generated method stub
		
		Class<?> caught;
		
		caught = null;
		try{
			
			authenticate(username);
			Customer c = customerService.create();
			unauthenticate();
			
		}catch (Throwable oops) {
			caught = oops.getClass();
		}
		
		checkExceptions(expected, caught);
	}


	protected void templateSave(Customer customer, Class<?> expected) {
		Class<?> caught;
		
		caught = null;
		try{
			
			customerService.save(customer);
		}catch (Throwable oops) {
			
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
	}


	

}
