package projekti.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import projekti.domain.ProfileImage;

/**
 *
 * @author karpo
 */
public interface ProfileImageRepository extends JpaRepository<ProfileImage, Long>{
    
}
