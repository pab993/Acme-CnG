
package controllers;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.MessageService;
import domain.Actor;
import domain.Message;
import forms.MessageForm;

@Controller
@RequestMapping("/message")
public class MessageController extends AbstractController {

	//Services ===============================================================================

	@Autowired
	private MessageService	messageService;

	@Autowired
	private ActorService	actorService;


	//Constructor ============================================================================

	public MessageController() {
		super();
	}

	// Listing
	// ====================================================================================

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Message> messages;
		Actor actor;
		int principalId;
		
		actor = actorService.findByPrincipal();
		messages = messageService.findAllByActor(actor.getId());
		principalId = actor.getId();

		result = new ModelAndView("message/list");
		result.addObject("messages", messages);
		result.addObject("principalId", principalId);
		result.addObject("requestURI", "message/list.do");

		return result;
	}

	//Creation 
	// ====================================================================================

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		MessageForm messageForm;

		messageForm = new MessageForm();

		result = createEditModelAndView(messageForm);

		return result;
	}

	//Edit 
	// ====================================================================================

	@RequestMapping(value = "/editReply", method = RequestMethod.GET)
	public ModelAndView editReply(@RequestParam int messageId) {
		ModelAndView result;
		Message message;
		Message messageReply;

		message = messageService.findOne(messageId);
		messageReply = messageService.create();
		
		messageReply.setId(message.getId());
		
		result = createEditModelAndViewReply(messageReply);

		return result;
	}
	
	@RequestMapping(value = "/editForward", method = RequestMethod.GET)
	public ModelAndView editForward(@RequestParam int messageId) {
		ModelAndView result;
		Message message;
		Message messageForward;

		message = messageService.findOne(messageId);
		messageForward = messageService.create();
		
		messageForward.setId(message.getId());
		
		result = createEditModelAndViewForward(messageForward);

		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(MessageForm messageForm, BindingResult binding) {
		ModelAndView result;

		Message messageRec = messageService.reconstruct(messageForm, binding);

		if (binding.hasErrors()) {
			result = createEditModelAndView(messageForm);
		} else {
			try {
				messageService.save(messageRec);
				result = new ModelAndView("redirect:/message/list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(messageForm, "message.commit.error");
			}
		}
		return result;
	}
	
	
	
	@RequestMapping(value = "/editReply", method = RequestMethod.POST, params = "save")
	public ModelAndView saveReply(Message message, BindingResult binding) {
		ModelAndView result;
		Date createMoment;
		
		createMoment = new Date(System.currentTimeMillis());
		Actor actorReply = messageService.findOne(message.getId()).getActorSender();
		message.setActorSender(actorService.findByPrincipal());
		message.setActorRecipient(actorReply);
		message.setId(0);
		message.setCreateMoment(createMoment);

		if (binding.hasErrors()) {
			result = createEditModelAndViewReply(message);
		} else {
			try {
				messageService.save(message);
				result = new ModelAndView("redirect:/message/list.do");
			} catch (Throwable oops) {
				result = createEditModelAndViewReply(message, "message.commit.error");
			}
		}
		return result;
	}
	
	@RequestMapping(value = "/editForward", method = RequestMethod.POST, params = "save")
	public ModelAndView saveForward(Message message, BindingResult binding) {
		ModelAndView result;
		Date createMoment;
		
		createMoment = new Date(System.currentTimeMillis());
		Message messageForward = messageService.findOne(message.getId());
		message.setActorSender(actorService.findByPrincipal());
		message.setTitle(messageForward.getTitle());
		message.setText(messageForward.getText());
		message.setAttachment(messageForward.getAttachment());
		message.setId(0);
		message.setCreateMoment(createMoment);

		if (binding.hasErrors()) {
			result = createEditModelAndViewReply(message);
		} else {
			try {
				messageService.save(message);
				result = new ModelAndView("redirect:/message/list.do");
			} catch (Throwable oops) {
				result = createEditModelAndViewReply(message, "message.commit.error");
			}
		}
		return result;
	}

	//Delete 
	// ====================================================================================
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam int messageId) {
		ModelAndView result;
		Message messageDelete;
		
		
		messageDelete = messageService.findOne(messageId);

		result = new ModelAndView("message/delete");
		result.addObject("messageDelete", messageDelete);

		return result;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(Message message, BindingResult binding) {
		ModelAndView result;

		try {
			messageService.delete(message);
			result = new ModelAndView("redirect:/message/list.do");
		} catch (Throwable oops) {
			result = new ModelAndView("redirect:/message/list.do");
		}
		return result;
	}
	
	// The ancillary methods 
	// ====================================================================================

	protected ModelAndView createEditModelAndView(MessageForm messageSend) {
		ModelAndView result;

		result = createEditModelAndView(messageSend, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(MessageForm messageSend, String message) {
		ModelAndView result;
		Collection<Actor> actorRecipients;

		result = new ModelAndView("message/edit");
		actorRecipients = actorService.findAll();

		result.addObject("messageForm", messageSend);
		result.addObject("actorRecipients", actorRecipients);
		result.addObject("message", message);

		return result;
	}
	
	protected ModelAndView createEditModelAndViewReply(Message messageReply) {
		ModelAndView result;

		result = createEditModelAndViewReply(messageReply, null);

		return result;
	}

	protected ModelAndView createEditModelAndViewReply(Message messageReply, String message) {
		ModelAndView result;

		result = new ModelAndView("message/editReply");

		result.addObject("messageReply", messageReply);
		result.addObject("message", message);

		return result;
	}

	protected ModelAndView createEditModelAndViewForward(Message messageForward) {
		ModelAndView result;

		result = createEditModelAndViewForward(messageForward, null);

		return result;
	}

	protected ModelAndView createEditModelAndViewForward(Message messageForward, String message) {
		ModelAndView result;
		Collection<Actor> actorRecipients;		

		result = new ModelAndView("message/editForward");
		actorRecipients = actorService.findAll();

		result.addObject("messageForward", messageForward);
		result.addObject("actorRecipients", actorRecipients);
		result.addObject("message", message);

		return result;
	}
}
