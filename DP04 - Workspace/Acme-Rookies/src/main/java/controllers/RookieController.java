
package controllers;

import java.util.Collection;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import security.Authority;
import security.UserAccount;
import services.RookieService;
import domain.Rookie;
import forms.RookieForm;

@Controller
@RequestMapping("/rookie")
public class RookieController extends AbstractController {

	@Autowired
	RookieService	rookieService;


	// Constructors -----------------------------------------------------------

	public RookieController() {
		super();
	}

	// Edition ------------------------------
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView res;

		Authority authority;
		Collection<Authority> authorities;
		UserAccount userAccount;
		userAccount = new UserAccount();
		authorities = userAccount.getAuthorities();
		authority = new Authority();
		authority.setAuthority(Authority.ROOKIE);
		authorities.add(authority);
		userAccount.setAuthorities(authorities);

		final RookieForm rookieForm = new RookieForm();
		rookieForm.setUserAccount(userAccount);
		res = this.createEditModelAndView(rookieForm);

		//rookie = this.rookieService.create();
		//res = this.createEditModelAndView(rookie);
		return res;

	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute final RookieForm rookieForm, final BindingResult binding) {
		ModelAndView result;

		try {
			Assert.isTrue(rookieForm.isConditionsAccepted(), "conditionsAccepted");
			final Rookie rookie = this.rookieService.reconstruct(rookieForm, binding);
			final String vacia = "";
			if (!rookie.getEmail().isEmpty() || rookie.getEmail() != vacia)
				Assert.isTrue(rookie.getEmail().matches("^[A-z0-9]+@[A-z0-9.]+$") || rookie.getEmail().matches("^[A-z0-9 ]+ <[A-z0-9]+@[A-z0-9.]+>$"), "Wrong email");

			this.rookieService.save(rookie);
			result = new ModelAndView("redirect:/welcome/index.do");
		} catch (final ValidationException oops) {
			result = this.createEditModelAndView(rookieForm);
		} catch (final Throwable oops) {
			if (oops.getMessage() == "Wrong email")
				result = this.createEditModelAndView(rookieForm, "rookie.email.error");
			else if (oops.getMessage() == "conditionsAccepted")
				result = this.createEditModelAndView(rookieForm, "rookie.conditionsError");
			else
				result = this.createEditModelAndView(rookieForm, "rookie.comit.error");
		}
		return result;

	}

	// Ancillary methods ------------------------------
	protected ModelAndView createEditModelAndView(final RookieForm rookieForm) {
		ModelAndView result;

		result = this.createEditModelAndView(rookieForm, null);

		return result;

	}

	protected ModelAndView createEditModelAndView(final RookieForm rookieForm, final String message) {
		ModelAndView result;

		result = new ModelAndView("rookie/register");
		result.addObject("rookieForm", rookieForm);
		result.addObject("message", message);

		return result;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView res;

		final Collection<Rookie> rookies = this.rookieService.findAll();

		res = new ModelAndView("rookie/list");
		res.addObject("requestURI", "rookie/list.do");
		res.addObject("rookies", rookies);
		return res;
	}
	//
	//	@RequestMapping(value = "/showRecords", method = RequestMethod.GET)
	//	public ModelAndView showRecords(@RequestParam final int rookieId) {
	//		ModelAndView res;
	//		Rookie rookie;
	//
	//		//rookie = this.rookieService.findByPrincipal();
	//		rookie = this.rookieService.findOne(rookieId);
	//		res = this.createShowRecordsModelAndView(rookie);
	//		return res;
	//
	//	}
	//
	//	protected ModelAndView createShowRecordsModelAndView(final Rookie rookie) {
	//		ModelAndView result;
	//
	//		result = this.createShowRecordsModelAndView(rookie, null);
	//
	//		return result;
	//
	//	}

	//	protected ModelAndView createShowRecordsModelAndView(final Rookie rookie, final String message) {
	//		final ModelAndView result;
	//
	//		UserAccount userAccount;
	//		userAccount = rookie.getUserAccount();
	//
	//		final Collection<LegalRecord> legalRecords = rookie.getLegalRecords();
	//		final Collection<PeriodRecord> periodRecords = rookie.getPeriodRecords();
	//		final InceptionRecord inceptionRecord = rookie.getInceptionRecord();
	//		final Collection<LinkRecord> linkRecords = rookie.getLinkRecords();
	//
	//		result = new ModelAndView("rookie/showRecords");
	//		result.addObject("rookie", rookie);
	//		result.addObject("legalRecords", legalRecords);
	//		result.addObject("periodRecords", periodRecords);
	//		result.addObject("inceptionRecord", inceptionRecord);
	//		result.addObject("linkRecords", linkRecords);
	//		result.addObject("message", message);
	//		result.addObject("userAccount", userAccount);
	//		return result;
	//	}

}
