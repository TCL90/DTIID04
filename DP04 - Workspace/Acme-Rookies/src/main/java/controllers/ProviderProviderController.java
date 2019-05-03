
package controllers;

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
import services.ProviderService;
import domain.Provider;

@Controller
@RequestMapping("/provider/provider")
public class ProviderProviderController extends AbstractController {

	@Autowired
	ProviderService	providerService;


	// Constructors -----------------------------------------------------------

	public ProviderProviderController() {
		super();
	}

	////////////////////////////
	//////////EDIT//////////////
	////////////////////////////

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView editEdit() {
		ModelAndView res;
		Provider provider;

		provider = this.providerService.findByPrincipal();
		//provider = this.providerService.findOne(providerId);
		res = this.createEditEditModelAndView(provider);
		return res;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute Provider provider, final BindingResult binding) {
		ModelAndView result;

		try {
			final String vacia = "";
			if (!provider.getEmail().isEmpty() || provider.getEmail() != vacia)
				Assert.isTrue(provider.getEmail().matches("^[A-z0-9]+@[A-z0-9.]+$") || provider.getEmail().matches("^[A-z0-9 ]+ <[A-z0-9]+@[A-z0-9.]+>$"), "Wrong email");

			provider = this.providerService.reconstruct(provider, binding);
			TimeUnit.SECONDS.sleep(1);

			this.providerService.save(provider);
			result = new ModelAndView("redirect:/welcome/index.do");
		} catch (final ValidationException oops) {
			result = this.createEditEditModelAndView(provider);
		} catch (final Throwable oops) {
			if (oops.getMessage() == "Wrong email")
				result = this.createEditEditModelAndView(provider, "provider.email.error");
			else
				result = this.createEditEditModelAndView(provider, "provider.comit.error");
		}

		return result;

	}
	protected ModelAndView createEditEditModelAndView(final Provider provider) {
		ModelAndView result;

		result = this.createEditEditModelAndView(provider, null);

		return result;

	}

	protected ModelAndView createEditEditModelAndView(final Provider provider, final String message) {
		ModelAndView result;

		UserAccount userAccount;
		userAccount = provider.getUserAccount();

		result = new ModelAndView("provider/edit");
		result.addObject("provider", provider);
		result.addObject("message", message);
		result.addObject("userAccount", userAccount);
		return result;
	}

	////////////////////////////
	//////////SHOW//////////////
	////////////////////////////

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show() {
		ModelAndView res;
		Provider provider;

		provider = this.providerService.findByPrincipal();
		//provider = this.providerService.findOne(providerId);
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

		UserAccount userAccount;

		userAccount = provider.getUserAccount();

		result = new ModelAndView("provider/show");
		result.addObject("provider", provider);
		result.addObject("message", message);
		result.addObject("userAccount", userAccount);
		return result;
	}

	////////////////////////////
	//////////DELETE//////////////
	////////////////////////////

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "leave")
	public ModelAndView saveLeave(final Provider provider, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditEditModelAndView(provider);
		else
			try {

				this.providerService.leave();
				result = new ModelAndView("redirect:../../j_spring_security_logout");
			} catch (final Throwable error) {
				if (error.getMessage() == "Wrong email")
					result = this.createEditEditModelAndView(provider, "provider.email.error");
				else
					result = this.createEditEditModelAndView(provider, "provider.comit.error");
				System.out.println(error.getMessage());
			}

		return result;

	}

}
