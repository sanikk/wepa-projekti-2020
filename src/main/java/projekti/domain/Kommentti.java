package projekti.domain;

import java.time.LocalDateTime;
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
public class Kommentti extends AbstractPersistable<Long>{
    //@Basic(fetch = FetchType.LAZY)

    @ManyToOne
    private Kayttaja kommentoija;
    @ManyToOne
    private Postaus posti;
    private LocalDateTime lahetysaika;
    private String sisalto;
}
