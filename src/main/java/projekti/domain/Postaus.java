package projekti.domain;

import java.time.LocalDateTime;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
public class Postaus extends AbstractPersistable<Long>{
    
    @Basic(fetch = FetchType.LAZY)
    @ManyToOne
    private Kayttaja lahettaja;
    private LocalDateTime lahetysaika;
    private String sisalto;
    //private List<Kayttaja> tykkaykset;
}
