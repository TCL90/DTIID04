
package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AuditService;
import domain.Audit;

@Controller
@RequestMapping("/audit")
public class AuditShowController extends AbstractController {

	@Autowired
	private AuditService	auditService;


	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int companyId) {
		final ModelAndView result;
		Audit audit;

		audit = this.auditService.findByPositionId(companyId);

		Assert.notNull(audit);

		result = new ModelAndView("audit/show");
		result.addObject("audit", audit);

		return result;
	}
}
