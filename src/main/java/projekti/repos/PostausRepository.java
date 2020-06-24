package projekti.repos;

import java.util.Collection;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import projekti.domain.Kayttaja;
import projekti.domain.Postaus;

/**
 *
 * @author karpo
 */
public interface PostausRepository extends JpaRepository<Postaus, Long>{
    List<Postaus> findByLahettajaIn(Collection<Kayttaja> kokoelma, Pageable pageable);
}
