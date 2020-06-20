package projekti.domain;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 *
 * @author karpo
 */
@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
public class Taito extends AbstractPersistable<Long>{

    @ManyToOne
    private Kayttaja kayttaja;
    private String name;
    //@OneToMany(mappedBy = "taito")
    //private List<Kayttaja> tykkaykset;
}
