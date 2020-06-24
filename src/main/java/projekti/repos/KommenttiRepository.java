package projekti.repos;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import projekti.domain.Kommentti;
import projekti.domain.Postaus;

/**
 *
 * @author karpo
 */
public interface KommenttiRepository extends JpaRepository<Kommentti, Long> {
    List<Kommentti> findByPosti(Postaus posti, Pageable pageable);
}
