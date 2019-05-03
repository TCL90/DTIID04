
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.Authority;
import security.UserAccount;
import services.ProviderService;
import domain.Provider;
import forms.ProviderForm;

@Controller
@RequestMapping("/provider")
public class ProviderController extends AbstractController {

	@Autowired
	ProviderService	providerService;


	// Constructors -----------------------------------------------------------

	public ProviderController() {
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
		authority.setAuthority(Authority.PROVIDER);
		authorities.add(authority);
		userAccount.setAuthorities(authorities);

		final ProviderForm providerForm = new ProviderForm();
		providerForm.setUserAccount(userAccount);
		res = this.createEditModelAndView(providerForm);

		//Provider = this.ProviderService.create();
		//res = this.createEditModelAndView(Provider);
		return res;

	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute final ProviderForm providerForm, final BindingResult binding) {
		ModelAndView result;

		try {
			Assert.isTrue(providerForm.isConditionsAccepted(), "conditionsAccepted");
			final Provider Provider = this.providerService.reconstruct(providerForm, binding);
			final String vacia = "";
			if (!Provider.getEmail().isEmpty() || Provider.getEmail() != vacia)
				Assert.isTrue(Provider.getEmail().matches("^[A-z0-9]+@[A-z0-9.]+$") || Provider.getEmail().matches("^[A-z0-9 ]+ <[A-z0-9]+@[A-z0-9.]+>$"), "Wrong email");

			this.providerService.save(Provider);
			result = new ModelAndView("redirect:/welcome/index.do");
		} catch (final ValidationException oops) {
			result = this.createEditModelAndView(providerForm);
		} catch (final Throwable oops) {
			if (oops.getMessage() == "Wrong email")
				result = this.createEditModelAndView(providerForm, "provider.email.error");
			else if (oops.getMessage() == "conditionsAccepted")
				result = this.createEditModelAndView(providerForm, "provider.conditionsError");
			else
				result = this.createEditModelAndView(providerForm, "provider.comit.error");
		}
		return result;

	}

	// Ancillary methods ------------------------------
	protected ModelAndView createEditModelAndView(final ProviderForm ProviderForm) {
		ModelAndView result;

		result = this.createEditModelAndView(ProviderForm, null);

		return result;

	}

	protected ModelAndView createEditModelAndView(final ProviderForm providerForm, final String message) {
		ModelAndView result;

		result = new ModelAndView("provider/register");
		result.addObject("providerForm", providerForm);
		result.addObject("message", message);

		return result;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int providerId) {
		ModelAndView res;
		final Provider provider;

		provider = this.providerService.findOne(providerId);
		res = this.createShowModelAndView(provider);
		return res;

	}

	protected ModelAndView createShowModelAndView(final Provider provider) {
		ModelAndView result;

		result = this.createShowModelAndView(provider, null);

		return result;

	}

	protected ModelAndView createShowModelAndView(final Provider provider, final String message) {
		ModelAndView result;

		result = new ModelAndView("provider/show");
		result.addObject("provider", provider);
		result.addObject("message", message);

		return result;
	}
}
