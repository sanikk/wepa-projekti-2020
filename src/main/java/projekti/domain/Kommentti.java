package projekti.domain;

import java.time.LocalDateTime;
import javax.persistence.Entity;
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
    
    //private Kayttaja kommentoija;
    private LocalDateTime lahetysaika;
    private String sisalto;
}
