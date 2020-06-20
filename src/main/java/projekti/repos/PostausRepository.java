package projekti.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import projekti.domain.Postaus;

/**
 *
 * @author karpo
 */
public interface PostausRepository extends JpaRepository<Postaus, Long>{
    
}
