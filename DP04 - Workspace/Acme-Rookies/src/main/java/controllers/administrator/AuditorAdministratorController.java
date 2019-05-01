
package controllers.administrator;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import security.Authority;
import security.UserAccount;
import services.AuditorService;
import controllers.AbstractController;
import domain.Auditor;
import domain.Box;
import domain.SocialProfile;
import forms.AuditorForm;

@Controller
@RequestMapping("/auditor")
public class AuditorAdministratorController extends AbstractController {

	@Autowired
	private AuditorService	auditorService;


	// Constructors -----------------------------------------------------------
	public AuditorAdministratorController() {
		super();
	}

	////////////////////////////
	//////////EDIT//////////////
	////////////////////////////

	@RequestMapping(value = "/auditor/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView res;
		final Auditor auditor;

		auditor = this.auditorService.findByPrincipal();
		//customer = this.customerService.findOne(customerId);
		res = this.createEditModelAndView(auditor);
		return res;

	}

	@RequestMapping(value = "/auditor/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Auditor auditor, final BindingResult binding) {
		ModelAndView result;

		try {
			final String vacia = "";
			if (!auditor.getEmail().isEmpty() || auditor.getEmail() != vacia)
				Assert.isTrue(auditor.getEmail().matches("^[A-z0-9]+@[A-z0-9.]+$") || auditor.getEmail().matches("^[A-z0-9 ]+ <[A-z0-9]+@[A-z0-9.]+>$"), "Wrong email");

			auditor = this.auditorService.reconstruct(auditor, binding);
			TimeUnit.SECONDS.sleep(1);
			this.auditorService.save(auditor);
			result = new ModelAndView("redirect:/welcome/index.do");
		} catch (final ValidationException oops) {
			result = this.createEditModelAndView(auditor);
		} catch (final Throwable oops) {
			if (oops.getMessage() == "Wrong email")
				result = this.createEditModelAndView(auditor, "auditor.email.error");
			else
				result = this.createEditModelAndView(auditor, "auditor.comit.error");
		}

		return result;

	}
	protected ModelAndView createEditModelAndView(final Auditor auditor) {
		ModelAndView result;

		result = this.createEditModelAndView(auditor, null);

		return result;

	}

	protected ModelAndView createEditModelAndView(final Auditor auditor, final String message) {
		ModelAndView result;
		Collection<Box> boxes;
		final Collection<SocialProfile> socialProfiles;
		UserAccount userAccount;

		boxes = auditor.getBoxes();
		socialProfiles = auditor.getSocialProfiles();
		userAccount = auditor.getUserAccount();

		result = new ModelAndView("auditor/edit");
		result.addObject("auditor", auditor);
		result.addObject("boxes", boxes);
		result.addObject("socialProfiles", socialProfiles);
		result.addObject("message", message);
		result.addObject("userAccount", userAccount);
		return result;
	}

	////////////////////////////
	//////////CREATE////////////
	////////////////////////////
	@RequestMapping(value = "/administrator/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;

		Authority authority;
		Collection<Authority> authorities;
		UserAccount userAccount;
		userAccount = new UserAccount();
		authorities = userAccount.getAuthorities();
		authority = new Authority();
		authority.setAuthority(Authority.AUDITOR);
		authorities.add(authority);
		userAccount.setAuthorities(authorities);

		final AuditorForm adminForm = new AuditorForm();
		adminForm.setUserAccount(userAccount);
		res = this.createCreateModelAndView(adminForm);

		//return res;

		//		auditor = this.auditorService.create();
		//		res = this.createCreateModelAndView(auditor);
		return res;

	}

	protected ModelAndView createCreateModelAndView(final AuditorForm auditorForm) {
		ModelAndView result;

		result = this.createCreateModelAndView(auditorForm, null);

		return result;

	}

	protected ModelAndView createCreateModelAndView(final AuditorForm auditorForm, final String message) {
		ModelAndView result;
		result = new ModelAndView("auditor/create");
		result.addObject("auditorForm", auditorForm);
		result.addObject("message", message);
		return result;
	}

	@RequestMapping(value = "administrator/create", method = RequestMethod.POST, params = "save")
	public ModelAndView saveCreate(@Valid final AuditorForm auditorForm, final BindingResult binding) {
		ModelAndView result;

		try {
			Assert.isTrue(auditorForm.isConditionsAccepted(), "conditionsAccepted");
			final Auditor auditor = this.auditorService.reconstruct(auditorForm, binding);
			final String vacia = "";
			if (!auditor.getEmail().isEmpty() || auditor.getEmail() != vacia)
				Assert.isTrue(auditor.getEmail().matches("^[A-z0-9]+@[A-z0-9.]+$") || auditor.getEmail().matches("^[A-z0-9 ]+ <[A-z0-9]+@[A-z0-9.]+>$"), "Wrong email");
			this.auditorService.save(auditor);
			result = new ModelAndView("redirect:/welcome/index.do");
		} catch (final ValidationException oops) {
			result = this.createCreateModelAndView(auditorForm);
		} catch (final Throwable oops) {
			if (oops.getMessage() == "Wrong email")
				result = this.createCreateModelAndView(auditorForm, "auditor.email.error");
			else if (oops.getMessage() == "conditionsAccepted")
				result = this.createCreateModelAndView(auditorForm, "auditor.conditionsError");
			else
				result = this.createCreateModelAndView(auditorForm, "auditor.comit.error");
		}
		if (!auditorForm.isConditionsAccepted())
			result = this.createCreateModelAndView(auditorForm, "auditor.conditionsError");

		return result;

	}

	////////////////////////////
	//////////DELETE////////////
	////////////////////////////

	@RequestMapping(value = "/auditor/edit", method = RequestMethod.POST, params = "leave")
	public ModelAndView saveLeave(final Auditor auditor, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(auditor);
		else
			try {

				this.auditorService.leave();
				result = new ModelAndView("redirect:../../j_spring_security_logout");
			} catch (final Throwable error) {
				if (error.getMessage() == "Wrong email")
					result = this.createEditModelAndView(auditor, "brotherhood.email.error");
				else
					result = this.createEditModelAndView(auditor, "brotherhood.comit.error");
				System.out.println(error.getMessage());
			}

		return result;

	}

	////////////////////////////
	///////////SHOW/////////////
	////////////////////////////

	@RequestMapping(value = "/auditor/show", method = RequestMethod.GET)
	public ModelAndView show() {
		ModelAndView res;
		Auditor auditor;

		auditor = this.auditorService.findByPrincipal();
		//brotherhood = this.brotherhoodService.findOne(brotherhoodId);
		res = this.createShowModelAndView(auditor);
		return res;

	}

	protected ModelAndView createShowModelAndView(final Auditor auditor) {
		ModelAndView result;

		result = this.createShowModelAndView(auditor, null);

		return result;

	}

	protected ModelAndView createShowModelAndView(final Auditor auditor, final String message) {
		ModelAndView result;

		UserAccount userAccount;

		userAccount = auditor.getUserAccount();

		result = new ModelAndView("auditor/show");
		result.addObject("auditor", auditor);
		result.addObject("message", message);
		result.addObject("userAccount", userAccount);
		return result;
	}

}
