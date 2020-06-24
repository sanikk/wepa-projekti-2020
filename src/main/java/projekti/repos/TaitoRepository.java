package projekti.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import projekti.domain.Taito;

/**
 *
 * @author karpo
 */
public interface TaitoRepository extends JpaRepository<Taito, Long>{
    Taito findByName(String name);
}
