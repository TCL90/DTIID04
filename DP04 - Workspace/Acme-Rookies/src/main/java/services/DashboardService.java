
package services;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.DashboardRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Company;
import domain.Hacker;
import domain.Position;

@Service
@Transactional
public class DashboardService {

	@Autowired
	DashboardRepository	repository;


	private boolean checkAdmin() {
		final Authority a = new Authority();
		a.setAuthority(Authority.ADMIN);
		final UserAccount user = LoginService.getPrincipal();
		return user.getAuthorities().contains(a);
	}

	public Double avgPositions() {
		Assert.isTrue(this.checkAdmin());
		return this.repository.avgPositions();
	}

	public Integer minPositions() {
		Assert.isTrue(this.checkAdmin());
		return this.repository.minPositions();
	}

	public Integer maxPositions() {
		Assert.isTrue(this.checkAdmin());
		return this.repository.maxPositions();
	}

	public Double stddevPositions() {
		Assert.isTrue(this.checkAdmin());
		return this.repository.stddevPositions();
	}

	public Double avgApplications() {
		Assert.isTrue(this.checkAdmin());
		return this.repository.avgApplications();
	}

	public Integer minApplications() {
		Assert.isTrue(this.checkAdmin());
		return this.repository.minApplications();
	}

	public Integer maxApplications() {
		Assert.isTrue(this.checkAdmin());
		return this.repository.maxApplications();
	}

	public Double stddevApplications() {
		Assert.isTrue(this.checkAdmin());
		return this.repository.stddevApplications();
	}

	public Collection<String> companiesWithMorePositions() {
		Assert.isTrue(this.checkAdmin());
		return this.repository.companiesWithMorePositions();
	}

	public Collection<String> hackersWithMorePositions() {
		Assert.isTrue(this.checkAdmin());
		return this.repository.hackersWithMoreApplications();
	}

	public Double avgSalary() {
		Assert.isTrue(this.checkAdmin());
		return this.repository.avgSalary();
	}

	public Integer minSalary() {
		Assert.isTrue(this.checkAdmin());
		return this.repository.minSalary();
	}

	public Integer maxSalary() {
		Assert.isTrue(this.checkAdmin());
		return this.repository.maxSalary();
	}

	public Double stddevSalary() {
		Assert.isTrue(this.checkAdmin());
		return this.repository.stddevSalary();
	}

	public Collection<String> highestSalaryPosition() {
		Assert.isTrue(this.checkAdmin());
		return this.repository.highestSalaryPosition();
	}

	public Collection<String> lowestSalaryPosition() {
		Assert.isTrue(this.checkAdmin());
		return this.repository.lowestSalaryPosition();
	}

	public Integer minCurricula() {
		Assert.isTrue(this.checkAdmin());
		return this.repository.minCurricula();
	}

	public Integer maxCurricula() {
		Assert.isTrue(this.checkAdmin());
		return this.repository.maxCurricula();
	}

	public Double avgCurricula() {
		Assert.isTrue(this.checkAdmin());
		return this.repository.avgCurricula();
	}

	public Double stddevCurricula() {
		Assert.isTrue(this.checkAdmin());
		return this.repository.stddevCurricula();
	}

	public Integer minResults() {
		Assert.isTrue(this.checkAdmin());
		return this.repository.minResults();
	}

	public Integer maxResults() {
		Assert.isTrue(this.checkAdmin());
		return this.repository.maxResults();
	}

	public Double avgResults() {
		Assert.isTrue(this.checkAdmin());
		return this.repository.avgResults();
	}

	public Double stddevResults() {
		Assert.isTrue(this.checkAdmin());
		return this.repository.stddevResults();
	}

	public Double ratioFinders() {
		Assert.isTrue(this.checkAdmin());
		return this.repository.ratioFinders();
	}

	public Double avgAuditScore() {
		Assert.isTrue(this.checkAdmin());
		return this.repository.avgAuditScore();
	}

	public Double minAuditScore() {
		Assert.isTrue(this.checkAdmin());
		return this.repository.minAuditScore();
	}

	public Double maxAuditScore() {
		Assert.isTrue(this.checkAdmin());
		return this.repository.maxAuditScore();
	}

	public Double stddevAuditScore() {
		Assert.isTrue(this.checkAdmin());
		return this.repository.stddevAuditScore();
	}

	public Double avgCompanyScore() {
		Assert.isTrue(this.checkAdmin());
		return this.repository.avgCompanyScore();
	}

	public Double minCompanyScore() {
		Assert.isTrue(this.checkAdmin());
		return this.repository.minCompanyScore();
	}

	public Double maxCompanyScore() {
		Assert.isTrue(this.checkAdmin());
		return this.repository.maxCompanyScore();
	}

	public Double stddevCompanyScore() {
		Assert.isTrue(this.checkAdmin());
		return this.repository.stddevCompanyScore();
	}

	public List<String> highestScoreCompanies() {
		Assert.isTrue(this.checkAdmin());
		List<String> res = this.repository.highestScoreCompanies();
		if (res.size() > 3)
			res = res.subList(0, 3);
		return res;
	}

	public Double avgSalaryHighestScore() {
		Assert.isTrue(this.checkAdmin());
		List<Position> pos = this.repository.positionsHighestScore();
		if (pos.size() > 3)
			pos = pos.subList(0, 3);
		Double res = 0.0;
		for (final Position p : pos)
			res += p.getSalary();
		return res / pos.size();
	}

	public Double avgItems() {
		Assert.isTrue(this.checkAdmin());
		return this.repository.avgItems();
	}

	public Integer minItems() {
		Assert.isTrue(this.checkAdmin());
		return this.repository.minItems();
	}

	public Integer maxItems() {
		Assert.isTrue(this.checkAdmin());
		return this.repository.maxItems();
	}

	public Double stddevItems() {
		Assert.isTrue(this.checkAdmin());
		return this.repository.stddevItems();
	}

	public List<String> top5Providers() {
		Assert.isTrue(this.checkAdmin());
		List<String> res = this.repository.top5Providers();
		if (res.size() > 5)
			res = res.subList(0, 5);
		return res;
	}

}
