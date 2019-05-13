
package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Audit;

@Repository
public interface AuditRepository extends JpaRepository<Audit, Integer> {

	@Query("select a from Audit a where a.auditor.id = ?1")
	List<Audit> findByAuditorId(int auditorId);

	@Query("select count(a) from Audit a where a.position.company.id = ?1")
	Integer numOfAudits(int id);

	@Query("select a from Audit a where a.position.id = ?1")
	Audit findByPositionId(int companyId);
}
