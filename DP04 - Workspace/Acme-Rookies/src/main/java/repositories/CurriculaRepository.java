
package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Curricula;

@Repository
public interface CurriculaRepository extends JpaRepository<Curricula, Integer> {

	@Query("select c from Curricula c where c.rookie.id = ?1")
	List<Curricula> findCurriculaByRookieId(int rookieId);

	@Query("select c from Curricula c where c.rookie.id = ?1 and c.isCopy='0'")
	List<Curricula> findCurriculaByRookieIdNotCopies(int id);

}
