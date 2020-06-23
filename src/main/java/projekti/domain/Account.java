package projekti.domain;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
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
public class Account extends AbstractPersistable<Long>{
    private String username;
    private String password;
    private String profile;
    //väärin päin hallinta?
    @Basic(fetch = FetchType.LAZY)
    @OneToOne
    private Kayttaja kayttaja;
}
