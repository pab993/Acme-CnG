package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CommentService;
import domain.Comment;
import forms.CommentForm;

@Controller
@RequestMapping("/comment")
public class CommentController extends AbstractController {

	@Autowired
	private CommentService commentService;

	@RequestMapping(value = "/postComment", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int comentableId) {
		ModelAndView result;
		CommentForm commentForm = new CommentForm();
		commentForm.setIdComentable(comentableId);

		result = new ModelAndView("comment/postComment");
		result.addObject("commentForm", commentForm);

		return result;

	}

	@RequestMapping(value = "/postComment", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid CommentForm commentForm,
			BindingResult binding) {
		ModelAndView result;
		System.out.println(binding.getAllErrors());
		Comment comment = commentService.reconstruct(commentForm, binding);

		if (binding.hasErrors()) {
			result = createEditModelAndView(commentForm);

		} else {
			try {

				commentService.save(comment);
				result = new ModelAndView("redirect:/welcome/index.do");

			} catch (Throwable oops) {
				result = createEditModelAndView(commentForm,
						"comment.commit.error");
			}
		}

		return result;
	}

	@RequestMapping(value = "/ban", method = RequestMethod.GET)
	public ModelAndView ban(@RequestParam int commentId) {
		commentService.banMethod(commentId);
		ModelAndView result = new ModelAndView("redirect:/welcome/index.do");
		return result;
	}

	protected ModelAndView createEditModelAndView(CommentForm commentForm) {
		ModelAndView result;

		result = createEditModelAndView(commentForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(CommentForm commentForm,
			String message) {
		ModelAndView result;

		result = new ModelAndView("comment/postComment");

		result.addObject("commentForm", commentForm);
		result.addObject("message", message);

		return result;
	}

}
