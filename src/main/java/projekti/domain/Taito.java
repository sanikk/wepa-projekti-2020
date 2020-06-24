package projekti.domain;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
public class Taito extends AbstractPersistable<Long> implements Comparable<Taito>{

    @ManyToOne
    private Kayttaja kayttaja;
    private String name;
    @ManyToMany
    private List<Kayttaja> tykkaykset;

    @Override
    public int compareTo(Taito t) {
        return t.tykkaykset.size() - this.tykkaykset.size();
    }
}
