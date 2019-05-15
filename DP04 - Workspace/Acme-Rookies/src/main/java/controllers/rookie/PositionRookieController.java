
package controllers.rookie;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ApplicationService;
import services.RookieService;
import services.PositionService;
import domain.Application;
import domain.Rookie;
import domain.Position;

@Controller
@RequestMapping("/position/rookie")
public class PositionRookieController {

	@Autowired
	private PositionService		positionService;

	@Autowired
	private RookieService		rookieService;

	@Autowired
	private ApplicationService	applicationService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		//Listamos las positions en finalmode y no canceladas
		final Collection<Position> positions = this.positionService.findPositionFinalModeNotCancelled();

		//Nos quedamos solo con aquellas para las que no ha enviado una aplicación ya
		final Rookie logged = this.rookieService.findByPrincipal();
		final Collection<Application> applications = this.applicationService.getApplicationsByRookie(logged);
		final List<Position> positionsRookie = new ArrayList<>();
		for (final Application a : applications)
			positionsRookie.add(a.getPosition());
		positions.removeAll(positionsRookie);

		result = new ModelAndView("position/list");
		result.addObject("positions", positions);
		result.addObject("requestURI", "/position/rookie/list.do");
		final boolean showError = false;
		result.addObject("showError", showError);

		return result;
	}
}
