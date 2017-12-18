package services;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.CommentRepository;
import security.Authority;
import domain.Actor;
import domain.Comentable;
import domain.Comment;
import forms.CommentForm;

@Service
@Transactional
public class CommentService {

	// Repositories
	// ====================================================================

	@Autowired
	private CommentRepository commentRepository;

	// Supporting services
	// ====================================================================

	@Autowired
	private ComentableService comentableService;

	@Autowired
	private ActorService actorService;

	// Simple CRUD methods
	// ====================================================================

	public Comment findOne(int commentId) {
		Assert.isTrue(commentId != 0);
		Comment result;

		result = commentRepository.findOne(commentId);

		return result;
	}

	public Collection<Comment> findAll() {
		Collection<Comment> result;

		result = commentRepository.findAll();

		return result;
	}

	public Comment create(Comentable comentable) {
		Comment result;
		Actor principal;
		Date createMoment;

		principal = actorService.findByPrincipal();
		Assert.notNull(principal);

		createMoment = new Date(System.currentTimeMillis());
		result = new Comment();
		result.setCreateMoment(createMoment);
		result.setActor(principal);
		comentable.getComments().add(result);
		result.setComentable(comentable);
		result.setBan(false);

		return result;
	}

	public Comment save(Comment comment) {
		Assert.notNull(comment);
		Comment result;

		result = commentRepository.save(comment);

		return result;
	}

	// Other business methods
	// ===================================================================

	public Double avgPerActorOfferRequest() {
		Double result;

		result = commentRepository.avgPerActorOfferRequest();
		Assert.notNull(result);

		return result;
	}

	public Double avgPerAdminsAndCustomers() {
		Double result;

		result = commentRepository.avgPerAdminsAndCustomers();
		Assert.notNull(result);

		return result;
	}

	public Collection<Actor> actorsBetweenAvgTenPerCent() {
		Collection<Actor> result;

		result = commentRepository.actorsBetweenAvgTenPerCent();
		Assert.notNull(result);

		return result;
	}
	@Autowired
	private Validator	validator;
	
	public Comment reconstruct(CommentForm commentForm, BindingResult binding) {
		//Comentable comentable2 = comentableService.findOne(commentForm.getIdComentable());
		Comentable comentable = comentableService.findOneAux(commentForm
				.getIdComentable());
		Comment res = this.create(comentable);
		res.setStars(commentForm.getStars());
		res.setText(commentForm.getText());
		res.setTitle(commentForm.getTitle());
		validator.validate(res, binding);

		return res;

	}

	public Collection<Comment> filterComments(Comentable comentable) {
		Actor principal = actorService.findByPrincipal();
		Collection<Comment> res = new HashSet<Comment>();
		Authority i = new Authority();
		i.setAuthority("ADMINISTRATOR");
		if(principal.getUserAccount().getAuthorities().contains(i)){
			res = comentable.getComments();
		}else{
			res = commentRepository.findNotBaned(comentable.getId(),principal.getId());
			
		}
		
		return res;
	}

	public void banMethod(int commentId) {
		Comment comment = this.findOne(commentId);
		comment.setBan(!comment.getBan());
		this.save(comment);
		
	}
}
