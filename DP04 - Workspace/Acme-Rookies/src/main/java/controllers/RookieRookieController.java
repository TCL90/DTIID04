
package controllers;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import security.UserAccount;
import services.RookieService;
import domain.Rookie;
import domain.SocialProfile;

@Controller
@RequestMapping("/rookie/rookie")
public class RookieRookieController extends AbstractController {

	@Autowired
	RookieService	rookieService;


	// Constructors -----------------------------------------------------------

	public RookieRookieController() {
		super();
	}

	// //////////////////////////
	// ////////EDIT//////////////
	// //////////////////////////

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView editEdit() {
		ModelAndView res;
		Rookie rookie;

		rookie = this.rookieService.findByPrincipal();
		// rookie = this.rookieService.findOne(rookieId);
		res = this.createEditEditModelAndView(rookie);
		return res;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute Rookie rookie, final BindingResult binding) {
		ModelAndView result;

		try {
			final String vacia = "";
			if (!rookie.getEmail().isEmpty() || rookie.getEmail() != vacia)
				Assert.isTrue(rookie.getEmail().matches("^[A-z0-9]+@[A-z0-9.]+$") || rookie.getEmail().matches("^[A-z0-9 ]+ <[A-z0-9]+@[A-z0-9.]+>$"), "Wrong email");

			rookie = this.rookieService.reconstruct(rookie, binding);
			TimeUnit.SECONDS.sleep(1);

			this.rookieService.save(rookie);
			result = new ModelAndView("redirect:/welcome/index.do");
		} catch (final ValidationException oops) {
			result = this.createEditEditModelAndView(rookie);
		} catch (final Throwable oops) {
			if (oops.getMessage() == "Wrong email")
				result = this.createEditEditModelAndView(rookie, "rookie.email.error");
			else
				result = this.createEditEditModelAndView(rookie, "rookie.comit.error");
		}

		return result;

	}

	protected ModelAndView createEditEditModelAndView(final Rookie rookie) {
		ModelAndView result;

		result = this.createEditEditModelAndView(rookie, null);

		return result;

	}

	protected ModelAndView createEditEditModelAndView(final Rookie rookie, final String message) {
		ModelAndView result;

		UserAccount userAccount;
		userAccount = rookie.getUserAccount();

		result = new ModelAndView("rookie/edit");
		result.addObject("rookie", rookie);
		result.addObject("message", message);
		result.addObject("userAccount", userAccount);
		return result;
	}

	// //////////////////////////
	// ////////SHOW//////////////
	// //////////////////////////

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show() {
		ModelAndView res;
		Rookie rookie;

		rookie = this.rookieService.findByPrincipal();
		// rookie = this.rookieService.findOne(rookieId);
		res = this.createShowModelAndView(rookie);
		return res;

	}

	protected ModelAndView createShowModelAndView(final Rookie rookie) {
		ModelAndView result;

		result = this.createShowModelAndView(rookie, null);

		return result;

	}

	protected ModelAndView createShowModelAndView(final Rookie rookie, final String message) {
		ModelAndView result;
		// Collection<Box> boxes;
		Collection<SocialProfile> socialProfiles;
		UserAccount userAccount;

		// boxes = rookie.getBoxes();
		socialProfiles = rookie.getSocialProfiles();
		userAccount = rookie.getUserAccount();
		if (socialProfiles.isEmpty())
			socialProfiles = null;
		// if (boxes.isEmpty())
		// boxes = null;

		result = new ModelAndView("rookie/show");
		result.addObject("rookie", rookie);
		// result.addObject("boxes", boxes);
		result.addObject("socialProfiles", socialProfiles);
		result.addObject("message", message);
		result.addObject("userAccount", userAccount);
		return result;
	}

	// //////////////////////////
	// ////////DELETE//////////////
	// //////////////////////////

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "leave")
	public ModelAndView saveLeave(final Rookie rookie, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditEditModelAndView(rookie);
		else
			try {

				this.rookieService.leave();
				result = new ModelAndView("redirect:../../j_spring_security_logout");
			} catch (final Throwable error) {
				if (error.getMessage() == "Wrong email")
					result = this.createEditEditModelAndView(rookie, "rookie.email.error");
				else
					result = this.createEditEditModelAndView(rookie, "rookie.comit.error");
				System.out.println(error.getMessage());
			}

		return result;

	}

}
