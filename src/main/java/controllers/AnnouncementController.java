package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AnnouncementService;
import services.CommentService;
import domain.Announcement;
import domain.Comment;

@Controller
@RequestMapping("/announcement")
public class AnnouncementController extends AbstractController {

	// Services
	// ===============================================================================

	@Autowired
	private AnnouncementService announcementService;
	@Autowired
	private CommentService commentService;

	// Constructor
	// ============================================================================

	public AnnouncementController() {
		super();
	}

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int announcementId) {

		Announcement announcement = announcementService.findOne(announcementId);
		ModelAndView resul = new ModelAndView("announcement/display");
		resul.addObject("announcement", announcement);
		resul.addObject("comments", announcement.getComments());
		resul.addObject("requestURI", "announcement/display.do");

		return resul;
	}

	@RequestMapping(value = "/banAnnouncement", method = RequestMethod.GET)
	public ModelAndView ban(@RequestParam int announcementId) {

		Announcement announcement = announcementService.findOne(announcementId);
		Announcement announcementBaned = announcementService.ban(announcement);
		Collection<Comment> comments = commentService.filterComments(announcementBaned);
		ModelAndView resul = new ModelAndView("announcement/display");
		resul.addObject("announcement", announcementBaned);
		resul.addObject("comments", comments);
		resul.addObject("requestURI", "announcement/display.do");

		return resul;

	}

}
