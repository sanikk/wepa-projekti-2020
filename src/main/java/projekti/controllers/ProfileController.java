package projekti.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import projekti.domain.Kayttaja;
import projekti.repos.KayttajaRepository;

/**  Eli tähän käyttäjien kotisivut, 
 *
 * @author karpo
 */
@Controller
public class ProfileController {
    
    @Autowired
    private KayttajaRepository kayttajaRepository;
    
    @GetMapping("/profiles/{profile}")
    public String singleProfile(Model model, @PathVariable String profile) {
        Kayttaja k = kayttajaRepository.findByProfile(profile);
        model.addAttribute("profile", k);
        return "profile";
    }
    
}
