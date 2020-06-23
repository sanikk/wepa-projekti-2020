package projekti.domain;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
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
public class Kayttaja extends AbstractPersistable<Long> {

    

    private String name;
    @Column(unique=true)
    private String username;
    private String password;
    @Column(unique=true)
    private String profile;
    @OneToOne(mappedBy = "kayttaja")
    private Account account;
    
    //yhteydet muihin käyttäjiin -- katso minne/miten pyynnöt
    //@OneToMany(mappedBy = "fromUser")
    //private List<Contact> kontaktit;
    
    @ManyToMany
    private List<Kayttaja> hyvaksytyt;
    @ManyToMany(mappedBy = "itseEhdotetut")
    private List<Kayttaja> muidenEhdottamat;
    @ManyToMany
    private List<Kayttaja> itseEhdotetut;
    
    //taidot
    @OneToMany(mappedBy = "kayttaja")
    private List<Taito> taidot;
    
    //profiilikuva -- haetaan id perusteella
    private Long picid;
    
    //postaukset
    @OneToMany(mappedBy = "lahettaja")
    private List<Postaus> postaukset;

}
