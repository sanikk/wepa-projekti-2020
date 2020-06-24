/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekti.controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import projekti.domain.FoundUser;
import projekti.domain.Kayttaja;
import projekti.repos.KayttajaRepository;

/**
 *
 * @author karpo
 */
@Controller
public class ContactController {
    
    @Autowired
    private KayttajaRepository kayttajaRepository;
    
    @GetMapping("/contacts")
    public String list(Model model) {
        Kayttaja loggedIn = kayttajaRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("loggedIn", loggedIn);
        model.addAttribute("accepted", loggedIn.getHyvaksytyt());
        List<FoundUser> muokattu = new ArrayList<>();
        for (Kayttaja k : loggedIn.getItseEhdotetut()) {
            muokattu.add(new FoundUser(k.getName(), k.getUsername(), k.getProfile(), false, false, true, false));
        }
        for (Kayttaja k : loggedIn.getMuidenEhdottamat()) {
            muokattu.add(new FoundUser(k.getName(), k.getUsername(), k.getProfile(), false, true, false, false));
        }
        model.addAttribute("waiting", muokattu);
        return "contacts";
    }
    
}
