package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.Comentable;
import domain.Comment;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class CommentServiceTest extends AbstractTest {

	// The SUT
	// ====================================================
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private ComentableService comentableService;
	
	
	// Tests
	// ====================================================
	
	/*
	 * Create comments over a comentable object.
	 * 
	 * En este caso de uso se crean y se guardan los comments que queramos realizar sobre un objeto comentable siempre y cuando
	 * nos encontramos autentificados, por lo tanto es accesible para cualquier 'actor'.
	 * Para provocar el error, se intenta guardar medianteun usuario no autentificado e incluyendo un objeto comentable no válido.
	 */
	
	protected void createTest(String username, int idComentable, Class<?> expected) {
		// TODO Auto-generated method stub
		Class<?> caught;
		
		caught = null;
		try{
			
			authenticate(username);
			Comentable comentable = comentableService.findOneAux(idComentable);
			commentService.create(comentable);
			unauthenticate();
			
		}catch (Throwable oops) {
			caught = oops.getClass();
		}
		checkExceptions(expected, caught);
		
	}
	
	protected void saveTest(String username, int idComentable, Class<?> expected) {
		Class<?> caught;
		
		caught = null;
		try{
			
			authenticate(username);
			Comentable comentable = comentableService.findOneAux(idComentable);
			Comment comment = commentService.create(comentable);
			comment.setText("test");
			comment.setTitle("test");
			comment.setStars(2);
			
			commentService.save(comment);
			
			unauthenticate();
			
		}catch (Throwable oops) {
			caught = oops.getClass();
		}
		
		checkExceptions(expected, caught);
	}
	
	/*
	 * Ban a comment, which requires be a admin
	 * 
	 * En este caso de uso se banea un comentario, para esto debe estarse autenticado como un admin,
	 * Para provocar el error, se intenta acceder mediante un usuario no autentificado.
	 * o intentado banear un comment inexistente
	 */
	public void banComment(final String username, final Class<?> expected){
		
		Class<?> caught = null;
		
		try {
			
			authenticate(username);
			Comment comment = this.createCommentAux();
			commentService.banMethod(comment.getId());
			unauthenticate();
			
		} catch (final Throwable oops) {
			
			caught = oops.getClass();
	
		}	
		checkExceptions(expected, caught);
	}
	

	
	
	private Comment createCommentAux() {
		Comentable comentable = comentableService.findOneAux(37);
		Comment comment = commentService.create(comentable);
		comment.setText("test");
		comment.setTitle("test");
		comment.setStars(2);
		
		return commentService.save(comment);
	}

	// Drivers
	// ====================================================
	
	@Test
	public void driverCreateComment(){
		
		Object testingData1[][] = {
			// Creación de un comment si estoy autentificado como customer -> true
			{
			"customer1", 39, null
			},
			// Creación de un comment si no estoy autentificado -> false
			{
			null, 39, IllegalArgumentException.class
			}
		};
		
		for(int i = 0; i<testingData1.length; i++){createTest((String) testingData1[i][0],(int) testingData1[i][1],
					(Class<?>) testingData1[i][2]);
		}
	}
	
	@Test
	public void driverSaveComment(){
			
		Object testingData[][] = {
			// Si existe el idComentable -> true
			{
			"customer1", 37, null
			},
			// Si no existe el idComentable -> false
			{
			"customer1", 989 , IllegalArgumentException.class},
			{
			// Si no estamos autentificados para guardar un comment -> false
			null, 37, IllegalArgumentException.class},
			{
			// Si no estamos autentificados para guardar un comment y el idComentable no existe -> false
			null, 989 , IllegalArgumentException.class}
			};
		
		for(int i = 0; i<testingData.length; i++){saveTest((String) testingData[i][0],
			(int) testingData[i][1],(Class<?>) testingData[i][2]);
		}
	}

	
	@Test
	public void driverBanComment() {
		final Object testingData[][] = {
			// Comment existente -> true
			{
				"admin" , null
			},
			// Banear un comment sin auth -> false
			{
				null, IllegalArgumentException.class
			},
			// Se indica un customer que no existe -> false
			{
				"customer" , IllegalArgumentException.class
			},
		};
		for (int i = 0; i < testingData.length; i++)
			this.banComment((String) testingData[i][0],(Class<?>) testingData[i][1]);
	}
	

	
}

