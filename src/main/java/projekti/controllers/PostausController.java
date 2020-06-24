package projekti.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import projekti.domain.Kayttaja;
import projekti.domain.Postaus;
import projekti.repos.KayttajaRepository;
import projekti.repos.KommenttiRepository;
import projekti.repos.PostausRepository;

/**
 *
 * @author karpo
 */
@Controller
public class PostausController {

    @Autowired
    private KayttajaRepository kayttajaRepository;
    @Autowired
    private PostausRepository postrepo;
    @Autowired
    private KommenttiRepository komrepo;

    @GetMapping("/posts")
    public String list(Model model, @RequestParam(defaultValue = "0") Integer page) {
        Kayttaja loggedIn = kayttajaRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("loggedIn", loggedIn);
        Pageable pageable = PageRequest.of(page, 25, Sort.by("lahetysaika").descending());
        List<Kayttaja> kokoelma = loggedIn.getHyvaksytyt();
        kokoelma.add(loggedIn);
        model.addAttribute("postaukset", postrepo.findByLahettajaIn(kokoelma, pageable));
        return "postaukset";
    }

    //hyvää matskua päiväämisestä @3.07, tuo muunnos on kait vähän turha, tai jotain
    @PostMapping("/posts")
    public String newPost(@RequestParam String sisalto) {
        Kayttaja loggedIn = kayttajaRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        LocalDateTime aikaleima = LocalDateTime.now();
        Postaus p = new Postaus(loggedIn, aikaleima, sisalto);
        postrepo.save(p);
        return "redirect:/posts";
    }
}
