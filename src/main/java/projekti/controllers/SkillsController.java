package projekti.controllers;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import projekti.domain.Kayttaja;
import projekti.domain.Taito;
import projekti.repos.KayttajaRepository;
import projekti.repos.TaitoRepository;

/**
 *
 * @author karpo
 */
@Controller
public class SkillsController {
    
    @Autowired
    private KayttajaRepository kayttajaRepository;
    
    @Autowired
    private TaitoRepository taitoRepository;
    
    @PostMapping("/skills/add")
    public String add(@RequestParam String name) {
        Kayttaja loggedIn = kayttajaRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Taito taito = taitoRepository.save(new Taito(loggedIn, name, new ArrayList<>()));
        loggedIn.getTaidot().add(taito);
        kayttajaRepository.save(loggedIn);
        return "redirect:/users/" + loggedIn.getProfile();
    }
    
    @PostMapping("/skills/like/{id}")
    public String like(@PathVariable Long id) {
        Kayttaja loggedIn = kayttajaRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Taito taito = taitoRepository.getOne(id);
        if(taito.getTykkaykset().contains(loggedIn)) {
            return "redirect:/users/" + taito.getKayttaja().getProfile();
        }
        taito.getTykkaykset().add(loggedIn);
        taitoRepository.save(taito);
        return "redirect:/users/" + taito.getKayttaja().getProfile();
    }
    
    @PostMapping("/skills/dislike/{id}")
    public String dislike(@PathVariable Long id) {
        Kayttaja loggedIn = kayttajaRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Taito taito = taitoRepository.getOne(id);
        if(!taito.getTykkaykset().contains(loggedIn)) {
            return "redirect:/users/" + loggedIn.getProfile();
        }
        taito.getTykkaykset().remove(loggedIn);
        taitoRepository.save(taito);
        return "redirect:/users/" + taito.getKayttaja().getProfile();
    }
    
    @DeleteMapping("/skills/{id}")
    public String deleteTaito(@PathVariable Long id) {
        Kayttaja loggedIn = kayttajaRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        taitoRepository.deleteById(id);
        return "redirect:/users/" + loggedIn.getProfile();
    }
}
