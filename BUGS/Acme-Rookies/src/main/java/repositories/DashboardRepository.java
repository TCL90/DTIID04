
package repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Administrator;
import domain.Position;

@Repository
public interface DashboardRepository extends JpaRepository<Administrator, Integer> {

	@Query("select avg(1.0*(select count(p) from Position p where p.company.id =c.id group by p.company)) from Company c")
	public Double avgPositions();

	@Query("select min(1*(select count(p) from Position p where p.company.id =c.id group by p.company)) from Company c")
	Integer minPositions();

	@Query("select max(1*(select count(p) from Position p where p.company.id =c.id group by p.company)) from Company c")
	Integer maxPositions();

	@Query("select stddev(1.0*(select count(p) from Position p where p.company.id =c.id group by p.company)) from Company c")
	Double stddevPositions();

	@Query("select avg(1.0*(select count(a) from Application a where a.hacker.id =h.id group by a.hacker)) from Hacker h")
	Double avgApplications();

	@Query("select min(1*(select count(a) from Application a where a.hacker.id =h.id group by a.hacker)) from Hacker h")
	Integer minApplications();

	@Query("select max(1*(select count(a) from Application a where a.hacker.id =h.id group by a.hacker)) from Hacker h")
	Integer maxApplications();

	@Query("select stddev(1.0*(select count(a) from Application a where a.hacker.id =h.id group by a.hacker)) from Hacker h")
	Double stddevApplications();

	@Query("select c.companyName from Position p join p.company c group by p.company order by count(p) desc")
	Collection<String> companiesWithMorePositions();

	@Query("select h.userAccount.username from Application a join a.hacker h group by a.hacker order by count(a) desc")
	Collection<String> hackersWithMoreApplications();

	@Query("select avg(p.salary) from Position p")
	Double avgSalary();

	@Query("select min(p.salary) from Position p")
	Integer minSalary();

	@Query("select max(p.salary) from Position p")
	Integer maxSalary();

	@Query("select stddev(p.salary) from Position p")
	Double stddevSalary();

	@Query("select p.title from Position p order by p.salary desc")
	Collection<String> highestSalaryPosition();

	@Query("select p.title from Position p order by p.salary asc")
	Collection<String> lowestSalaryPosition();

	@Query("select min(1*(select count(c) from Curricula c where c.hacker.id = h.id group by c.hacker)) from Hacker h")
	Integer minCurricula();

	@Query("select max(1*(select count(c) from Curricula c where c.hacker.id = h.id group by c.hacker)) from Hacker h")
	Integer maxCurricula();

	@Query("select avg(1.0*(select count(c) from Curricula c where c.hacker.id = h.id group by c.hacker)) from Hacker h")
	Double avgCurricula();

	@Query("select stddev(1.0*(select count(c) from Curricula c where c.hacker.id = h.id group by c.hacker)) from Hacker h")
	Double stddevCurricula();

	@Query("select min(f.positions.size) from Finder f")
	Integer minResults();

	@Query("select max(f.positions.size) from Finder f")
	Integer maxResults();

	@Query("select avg(f.positions.size) from Finder f")
	Double avgResults();

	@Query("select stddev(f.positions.size) from Finder f")
	Double stddevResults();

	@Query("select 1.0 * count(f)/(select count(f1) from Finder f1 where f1.positions.size = 0) from Finder f where f.positions.size>0")
	Double ratioFinders();

	@Query("select avg(1.0*(select sum(a.score) from Audit a where a.position.id = p.id group by a.position)) from Position p")
	Double avgAuditScore();

	@Query("select min(1.0*(select sum(a.score) from Audit a where a.position.id = p.id group by a.position)) from Position p")
	Double minAuditScore();

	@Query("select max(1.0*(select sum(a.score) from Audit a where a.position.id = p.id group by a.position)) from Position p")
	Double maxAuditScore();

	@Query("select stddev(1.0*(select sum(a.score) from Audit a where a.position.id = p.id group by a.position)) from Position p")
	Double stddevAuditScore();

	@Query("select avg(c.score) from Company c")
	Double avgCompanyScore();

	@Query("select avg(c.score) from Company c")
	Double minCompanyScore();

	@Query("select avg(c.score) from Company c")
	Double maxCompanyScore();

	@Query("select avg(c.score) from Company c")
	Double stddevCompanyScore();

	@Query("select c.userAccount.username from Company c order by c.score desc")
	List<String> highestScoreCompanies();

	@Query("select p from Position p order by (1.0 * (select avg(a.score) from Audit a where a.position.id = p.id group by a.position)) desc")
	List<Position> positionsHighestScore();

	@Query("select avg(1.0*(select count(i) from Item i where i.provider.id = p.id group by i.provider)) from Provider p")
	Double avgItems();

	@Query("select min(1*(select count(i) from Item i where i.provider.id = p.id group by i.provider)) from Provider p")
	Integer minItems();

	@Query("select max(1.0*(select count(i) from Item i where i.provider.id = p.id group by i.provider)) from Provider p")
	Integer maxItems();

	@Query("select stddev(1.0*(select count(i) from Item i where i.provider.id = p.id group by i.provider)) from Provider p")
	Double stddevItems();

	@Query("select p.userAccount.username from Provider p order by(1*(select count(i) from Item i where i.provider.id = p.id group by i.provider)) desc")
	List<String> top5Providers();

}
