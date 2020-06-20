package projekti.repos;

import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import projekti.domain.Kayttaja;

/**
 *
 * @author karpo
 */
public interface KayttajaRepository extends JpaRepository<Kayttaja, Long>{
    //esimerkki entity graphin käytöstä n+1 ongelman ratkaisemiseksi. Määriteltyyn kenttään liittyvät tiedot haetaan komennolla yhdellä kysymyksellä
    //käytä tätä johonkin oikeaan kohtaan =D
    //@EntityGraph(attributePaths = {"vahvistetut"})
    //List<Kayttaja> findAll();
    //List<Kayttaja> getVahvistetut();
    
    //esimerkki etsimisestä nimen osan perusteella (kokeillaan samantien case insensitiveä):
    //Kayttaja findByNameContainingIgnoreCase(String name);
    
    //esimerkki etsimisestä nimen ja usernamen perusteella:
    //Kayttaja findByNameAndUsername(String name, String username);
    
    Kayttaja findByProfile(String profile);
    
}
