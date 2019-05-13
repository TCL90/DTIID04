
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AuditService;
import services.PositionService;
import domain.Audit;
import domain.Position;

@Controller
@RequestMapping("/position/auditor")
public class PositionAuditorController extends AbstractController {

	@Autowired
	private PositionService	positionService;

	@Autowired
	private AuditService	auditService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		final Collection<Position> positions = this.positionService.findAllNotAssigned();

		result = new ModelAndView("position/list");
		result.addObject("positions", positions);
		result.addObject("creatingAudit", true);
		result.addObject("requestURI", "/position/auditor/list.do");

		return result;
	}

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int positionId) {
		ModelAndView result;
		Position position;

		position = this.positionService.findOne(positionId);

		final Audit a = this.auditService.findByPositionId(positionId);

		Assert.notNull(position);

		result = this.createDisplayModelAndView(position, a);

		return result;
	}

	protected ModelAndView createDisplayModelAndView(final Position position, final Audit a) {
		ModelAndView result;
		result = this.createDisplayModelAndView(position, null, a);

		return result;
	}

	protected ModelAndView createDisplayModelAndView(final Position position, final String messageCode, final Audit a) {
		ModelAndView result;

		result = new ModelAndView("position/company/display");
		result.addObject("position", position);
		result.addObject("audit", a);
		result.addObject("messageCode", messageCode);

		return result;

	}
}
