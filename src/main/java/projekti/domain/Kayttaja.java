package projekti.domain;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
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
public class Kayttaja extends AbstractPersistable<Long> {

    private String name;
    private String username;
    private String password;
    @Column(unique=true)
    private String profile;
    
    //yhteydet muihin käyttäjiin -- katso minne/miten pyynnöt
    @ManyToMany
    private List<Kayttaja> pyynnot;
    @ManyToMany
    private List<Kayttaja> vahvistetut;
    
    //taidot
    @OneToMany(mappedBy = "kayttaja")
    private List<Taito> taidot;
    
    //profiilikuva
    //kuva kuva
    
    //postaukset
    @OneToMany(mappedBy = "lahettaja")
    private List<Postaus> postaukset;

}
