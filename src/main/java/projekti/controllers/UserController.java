package projekti.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import projekti.repos.KayttajaRepository;
import projekti.repos.KommenttiRepository;
import projekti.repos.PostausRepository;

/**
 *
 * @author karpo
 */
@Controller
public class UserController {

    @Autowired
    private KayttajaRepository kaytrepo;
    @Autowired
    private PostausRepository postrepo;
    @Autowired
    private KommenttiRepository komrepo;
    
    @GetMapping("/users")
    public String list(Model model, @RequestParam(defaultValue = "0") Integer page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by("username").descending());
        model.addAttribute("kayttajat", kaytrepo.findAll(pageable));
        return "kayttajat";
    }
}
