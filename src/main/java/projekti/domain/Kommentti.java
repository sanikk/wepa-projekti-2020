package projekti.domain;

import java.time.LocalDateTime;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
    //private Kayttaja kommentoija;
    private LocalDateTime lahetysaika;
    private String sisalto;
}
