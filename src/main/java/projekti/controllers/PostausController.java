package projekti.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import projekti.domain.Kayttaja;
import projekti.domain.Kommentti;
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
    public String list(Model model) {
        Kayttaja loggedIn = kayttajaRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("loggedIn", loggedIn);
        Pageable pageable = PageRequest.of(0, 25, Sort.by("lahetysaika").descending());
        List<Kayttaja> kokoelma = loggedIn.getHyvaksytyt();
        kokoelma.add(loggedIn);
        model.addAttribute("postaukset", postrepo.findByLahettajaIn(kokoelma, pageable));
        return "postaukset";
    }
    
    @GetMapping("/posts/{id}")
    public String seeComments(Model model, @PathVariable Long id) {
        Kayttaja loggedIn = kayttajaRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("loggedIn", loggedIn);
        Postaus posti = postrepo.getOne(id);
        model.addAttribute("posti", posti);
        Pageable pageable = PageRequest.of(0, 10, Sort.by("lahetysaika").descending());
        List<Kommentti> kommentit = komrepo.findByPosti(posti, pageable);
        model.addAttribute("kommentit", kommentit);
        return "yksiposti";
    }

    @PostMapping("/posts")
    public String newPost(@RequestParam String sisalto) {
        Kayttaja loggedIn = kayttajaRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        LocalDateTime aikaleima = LocalDateTime.now();
        Postaus p = new Postaus(loggedIn, aikaleima, sisalto, new ArrayList<>(), new ArrayList<>());
        postrepo.save(p);
        return "redirect:/posts";
    }
    
    @PostMapping("/posts/{command}/{id}")
    public String likeDislike(@PathVariable String command, @PathVariable Long id) {
        Kayttaja loggedIn = kayttajaRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Postaus posti = postrepo.getOne(id);
        if(posti == null || loggedIn == null) {
            return "redirect:/posts";
        }
        if(command.equals("like") && !posti.getTykkaykset().contains(loggedIn)) {
               posti.getTykkaykset().add(loggedIn);
        } else if(command.equals("unlike") && posti.getTykkaykset().contains(loggedIn)) {
                posti.getTykkaykset().remove(loggedIn);
        }
        postrepo.save(posti);
        return "redirect:/posts";
    }
    
    @Transactional
    @PostMapping("/posts/{id}")
    public String comment(@PathVariable Long id, @RequestParam String sisalto) {
        Kayttaja loggedIn = kayttajaRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Postaus posti = postrepo.getOne(id);
        if(posti == null || loggedIn == null) {
            return "redirect:/posts";
        }
        Kommentti uusi = new Kommentti(loggedIn, posti, LocalDateTime.now(), sisalto);
        uusi = komrepo.save(uusi);
        posti.getKommentit().add(uusi);
        return "redirect:/posts";
    }
}
