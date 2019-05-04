
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

	@Query("select c from Company c where c.userAccount.id = ?1")
	Company findByUserAccountId(int userAccount);

	@Query("select avg(a.score) from Audit a where a.position.company.id = ?1")
	Double avgAuditScores(Integer companyId);

}
