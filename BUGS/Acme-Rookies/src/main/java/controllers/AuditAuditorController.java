
package controllers;

import java.util.List;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AuditService;
import services.AuditorService;
import services.PositionService;
import domain.Audit;
import domain.Auditor;

@Controller
@RequestMapping("/audit/auditor")
public class AuditAuditorController extends AbstractController {

	@Autowired
	private AuditService	auditService;

	@Autowired
	private PositionService	positionService;

	@Autowired
	private AuditorService	auditorService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		final Auditor a = this.auditorService.findByPrincipal();

		final List<Audit> audits = this.auditService.findAllByAuditor(a.getId());

		result = new ModelAndView("audit/list");
		result.addObject("audits", audits);
		result.addObject("requestURI", "/audit/auditor/list.do");

		return result;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int auditId) {
		final ModelAndView result;
		Audit audit;

		audit = this.auditService.findOne(auditId);

		Assert.notNull(audit);

		result = new ModelAndView("audit/show");
		result.addObject("audit", audit);

		return result;
	}

	//crear
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int positionId) {
		final ModelAndView res;
		final Audit a = this.auditService.create(positionId);

		res = this.createEditModelAndView(a);

		return res;
	}

	private ModelAndView createEditModelAndView(final Audit a) {
		ModelAndView res;
		res = this.createEditModelAndView(a, null);

		return res;
	}

	private ModelAndView createEditModelAndView(final Audit a, final String messageCode) {
		ModelAndView res;
		res = new ModelAndView("audit/edit");

		res.addObject("audit", a);
		res.addObject("message", messageCode);

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView save(@RequestParam final int auditId) {
		ModelAndView res;
		final Audit a = this.auditService.findOne(auditId);

		res = new ModelAndView("audit/edit");

		res.addObject("audit", a);
		Assert.notNull(a);
		res = this.createEditModelAndView(a);

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(Audit a, final BindingResult binding) {
		ModelAndView res;

		try {
			//			if (a.getId() != 0)
			//				Assert.isTrue(!this.auditService.findOne(a.getId()).getFinalMode(), "auditFinalMode");
			if (a.getId() == 0)
				Assert.isTrue(this.positionService.findAllNotAssigned().contains(a.getPosition()), "auditAssigned");
			a = this.auditService.reconstruct(a, binding);
			this.auditService.save(a);

			res = new ModelAndView("redirect:list.do");
		} catch (final ValidationException oops) {
			res = this.createEditModelAndView(a);
		} catch (final Throwable oops) {
			if (oops.getMessage() == "auditFinalMode")
				res = this.createEditModelAndView(a, "audit.finalMode.error");
			else if (oops.getMessage() == "auditAssigned")
				res = this.createEditModelAndView(a, "audit.assigned.error");

			else
				res = this.createEditModelAndView(a, "error.audit");
		}

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Audit a, final BindingResult binding) {
		ModelAndView res;

		try {
			if (a.getId() != 0)
				Assert.isTrue(!this.auditService.findOne(a.getId()).getFinalMode(), "auditFinalModeDelete");
			final Audit a1 = this.auditService.findOne(a.getId());
			this.auditService.delete(a1);
			res = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			if (oops.getMessage() == "auditFinalModeDelete")
				res = this.createEditModelAndView(a, "audit.finalMode.error.delete");
			else
				res = this.createEditModelAndView(a, "error.audit");
		}

		return res;
	}

}
