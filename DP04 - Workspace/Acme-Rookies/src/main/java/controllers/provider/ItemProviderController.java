
package controllers.provider;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.Authority;
import services.ActorService;
import services.ItemService;
import services.ProviderService;
import controllers.AbstractController;
import domain.Actor;
import domain.Item;
import domain.Provider;

@Controller
@RequestMapping("/item/provider")
public class ItemProviderController extends AbstractController {

	@Autowired
	private ItemService		itemService;

	@Autowired
	private ActorService	actorService;

	@Autowired
	private ProviderService	providerService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView res;
		final Actor actual = this.actorService.findByPrincipal();
		final Authority a = new Authority();
		a.setAuthority(Authority.PROVIDER);
		Assert.isTrue(actual.getUserAccount().getAuthorities().contains(a));

		final Collection<Item> items = this.itemService.findAllByProvider(actual);

		res = new ModelAndView("item/list");
		res.addObject("requestURI", "item/provider/list.do");
		res.addObject("items", items);

		return res;

	}

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int itemId) {
		final ModelAndView result;
		Item item;
		final Actor actual = this.actorService.findByPrincipal();
		final Authority a = new Authority();
		a.setAuthority(Authority.PROVIDER);
		Assert.isTrue(actual.getUserAccount().getAuthorities().contains(a));

		item = this.itemService.findOne(itemId);
		Assert.notNull(item);

		if (item.getProvider().getId() != actual.getId())
			return new ModelAndView("redirect:/welcome/index.do");

		result = this.createDisplayModelAndView(item);

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Item item;

		item = this.itemService.create();

		result = this.createEditModelAndView(item);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Item item, final BindingResult binding) {
		ModelAndView result;

		final Actor actual = this.actorService.findByPrincipal();
		//final Authority a = new Authority();
		//a.setAuthority(Authority.PROVIDER);
		//Assert.isTrue(actual.getUserAccount().getAuthorities().contains(a));

		final Provider prov = (Provider) actual;
		item.setProvider(prov);

		//if (item.getProvider() != null)
		//	Assert.isTrue(prov.getId() == item.getProvider().getId());
		//else
		//	item.setProvider(prov);

		if (binding.hasErrors())
			result = this.createEditModelAndView(item);
		else
			try {

				final Collection<String> photos = item.getPhotos();
				for (final String trozo : photos) {
					Assert.isTrue(trozo.matches("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]"), "errorPhotos");
					Assert.isTrue(!trozo.contains(";"), "errorPhotos2");
				}
				//Delete if I fix this vaina
				this.itemService.save(item);
				this.itemService.flush();
				result = new ModelAndView("redirect:list.do");

			} catch (final Throwable oops) {

				if (oops.getMessage() == "errorPhotos")
					result = this.createEditModelAndView(item, "errorPhotos");

				else if (oops.getMessage() == "errorPhotos2")
					result = this.createEditModelAndView(item, "errorPhotos2");

				else
					result = this.createEditModelAndView(item, "item.commit.error");
			}

		return result;
	}
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int itemId) {
		ModelAndView result;
		Item item;
		final Actor actual = this.actorService.findByPrincipal();
		final Authority a = new Authority();
		a.setAuthority(Authority.PROVIDER);
		Assert.isTrue(actual.getUserAccount().getAuthorities().contains(a));

		item = this.itemService.findOne(itemId);

		if (item.getProvider().getId() != actual.getId())
			return new ModelAndView("redirect:list.do");
		else
			result = this.createEditModelAndView(item);

		return result;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int itemId) {
		final ModelAndView result;
		Item item;
		final Actor actual = this.actorService.findByPrincipal();
		final Authority a = new Authority();
		a.setAuthority(Authority.PROVIDER);
		Assert.isTrue(actual.getUserAccount().getAuthorities().contains(a));

		item = this.itemService.findOne(itemId);

		if (item.getProvider().getId() != actual.getId())
			return new ModelAndView("redirect:/welcome/index.do");
		else {
			this.itemService.delete(itemId);
			result = new ModelAndView("redirect:/item/provider/list.do");
		}
		return result;
	}

	protected ModelAndView createDisplayModelAndView(final Item item) {
		ModelAndView result;
		result = this.createDisplayModelAndView(item, null);

		return result;
	}

	protected ModelAndView createDisplayModelAndView(final Item item, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("item/provider/display");
		result.addObject("item", item);
		result.addObject("messageCode", messageCode);

		return result;

	}
	protected ModelAndView createEditModelAndView(final Item item) {

		ModelAndView result;
		result = this.createEditModelAndView(item, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Item item, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("item/provider/edit");
		result.addObject("item", item);

		result.addObject("message", messageCode);

		return result;
	}

}
