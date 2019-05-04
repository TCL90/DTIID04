
package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AdministratorService;

@Controller
@RequestMapping("/score/administrator")
public class ScoreAdministratorController extends AbstractController {

	@Autowired
	private AdministratorService	administratorService;


	@RequestMapping(value = "/calculate", method = RequestMethod.GET)
	public ModelAndView calculate() {
		ModelAndView res;

		this.administratorService.computeCompanyScore();
		res = new ModelAndView("../../../company/list.do");
		return res;

	}

}
