
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ItemService;
import domain.Item;

@Controller
@RequestMapping("/item")
public class ItemController extends AbstractController {

	@Autowired
	private ItemService	itemService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView res;
		final Collection<Item> items = this.itemService.findAll();

		res = new ModelAndView("item/list");
		res.addObject("requestURI", "item/list.do");
		res.addObject("items", items);

		return res;

	}

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int itemId) {
		final ModelAndView result;
		Item item;

		item = this.itemService.findOne(itemId);
		Assert.notNull(item);

		result = this.createDisplayModelAndView(item);

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

}
