package projekti.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import projekti.domain.Kommentti;

/**
 *
 * @author karpo
 */
public interface KommenttiRepository extends JpaRepository<Kommentti, Long>{
    
}
