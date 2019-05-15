
package controllers.administrator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.DashboardService;
import controllers.AbstractController;

@Controller
@RequestMapping("/dashboard/administrator")
public class DashboardAdministratorController extends AbstractController {

	@Autowired
	DashboardService	dashboardService;


	DashboardAdministratorController() {
		super();
	}

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView dashboard() {
		final ModelAndView res;
		res = new ModelAndView("dashboard/dashboard");

		final Double avgPositions = this.dashboardService.avgPositions();
		final Integer minPositions = this.dashboardService.minPositions();
		final Integer maxPositions = this.dashboardService.maxPositions();
		final Double stddevPositions = this.dashboardService.stddevPositions();

		final Double avgApplications = this.dashboardService.avgApplications();
		final Integer minApplications = this.dashboardService.minApplications();
		final Integer maxApplications = this.dashboardService.maxApplications();
		final Double stddevApplications = this.dashboardService.stddevPositions();

		final List<String> companiesWithMorePositions = new ArrayList<>(this.dashboardService.companiesWithMorePositions());
		final List<String> rookiesWithMorePositions = new ArrayList<>(this.dashboardService.rookiesWithMorePositions());

		final Double avgSalary = this.dashboardService.avgSalary();
		final Integer minSalary = this.dashboardService.minSalary();
		final Integer maxSalary = this.dashboardService.maxSalary();
		final Double stddevSalary = this.dashboardService.stddevSalary();

		final List<String> highestSalaryPosition = new ArrayList<>(this.dashboardService.highestSalaryPosition());
		final List<String> lowestSalaryPosition = new ArrayList<>(this.dashboardService.lowestSalaryPosition());

		final Integer minCurricula = this.dashboardService.minCurricula();
		final Integer maxCurricula = this.dashboardService.maxCurricula();
		final Double avgCurricula = this.dashboardService.avgCurricula();
		final Double stddevCurricula = this.dashboardService.stddevCurricula();

		final Integer minResults = this.dashboardService.minResults();
		final Integer maxResults = this.dashboardService.maxResults();
		final Double avgResults = this.dashboardService.avgResults();
		final Double stddevResults = this.dashboardService.stddevResults();

		final Double ratioFinders = this.dashboardService.ratioFinders();

		final Double minAuditScore = this.dashboardService.minAuditScore();
		final Double maxAuditScore = this.dashboardService.maxAuditScore();
		final Double avgAuditScore = this.dashboardService.avgAuditScore();
		final Double stddevAuditScore = this.dashboardService.stddevAuditScore();
		final Double minCompanyScore = this.dashboardService.minCompanyScore();
		final Double maxCompanyScore = this.dashboardService.maxCompanyScore();
		final Double avgCompanyScore = this.dashboardService.avgCompanyScore();
		final Double stddevCompanyScore = this.dashboardService.stddevCompanyScore();
		final List<String> highestScoreCompanies = this.dashboardService.highestScoreCompanies();
		final Double avgSalaryHighestScore = this.dashboardService.avgSalaryHighestScore();
		final Integer minItems = this.dashboardService.minItems();
		final Integer maxItems = this.dashboardService.maxItems();
		final Double avgItems = this.dashboardService.avgItems();
		final Double stddevItems = this.dashboardService.stddevItems();
		final List<String> top5Providers = this.dashboardService.top5Providers();
		res.addObject("avgPositions", avgPositions);
		res.addObject("minPositions", minPositions);
		res.addObject("maxPositions", maxPositions);
		res.addObject("stddevPositions", stddevPositions);
		res.addObject("avgApplications", avgApplications);
		res.addObject("minApplications", minApplications);
		res.addObject("maxApplications", maxApplications);
		res.addObject("stddevApplications", stddevApplications);
		res.addObject("avgSalary", avgSalary);
		res.addObject("minSalary", minSalary);
		res.addObject("maxSalary", maxSalary);
		res.addObject("stddevSalary", stddevSalary);
		res.addObject("companiesWithMorePositions", companiesWithMorePositions);
		res.addObject("rookiesWithMoreApplications", rookiesWithMorePositions);
		res.addObject("highestSalaryPosition", highestSalaryPosition.get(0));
		res.addObject("lowestSalaryPosition", lowestSalaryPosition.get(0));

		res.addObject("avgCurricula", avgCurricula);
		res.addObject("minCurricula", minCurricula);
		res.addObject("maxCurricula", maxCurricula);
		res.addObject("stddevCurricula", stddevCurricula);
		res.addObject("avgResults", avgResults);
		res.addObject("minResults", minResults);
		res.addObject("maxResults", maxResults);
		res.addObject("stddevResults", stddevResults);
		res.addObject("ratioFinders", ratioFinders);

		res.addObject("avgAuditScore", avgAuditScore);
		res.addObject("minAuditScore", minAuditScore);
		res.addObject("maxAuditScore", maxAuditScore);
		res.addObject("stddevAuditScore", stddevAuditScore);
		res.addObject("avgCompanyScore", avgCompanyScore);
		res.addObject("minCompanyScore", minCompanyScore);
		res.addObject("maxCompanyScore", maxCompanyScore);
		res.addObject("stddevCompanyScore", stddevCompanyScore);
		res.addObject("avgItems", avgItems);
		res.addObject("minItems", minItems);
		res.addObject("maxItems", maxItems);
		res.addObject("stddevItems", stddevItems);
		res.addObject("highestScoreCompanies", highestScoreCompanies);
		res.addObject("avgSalaryHighestScore", avgSalaryHighestScore);
		res.addObject("top5Providers", top5Providers);

		return res;
	}
}
