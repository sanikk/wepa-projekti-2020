package projekti;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import projekti.domain.Kayttaja;
import projekti.repos.KayttajaRepository;

@Controller
public class DefaultController {

    @Autowired
    private KayttajaRepository kayttajaRepository;
    
    @GetMapping("/")
    public String helloWorld(Model model) {
        Kayttaja loggedIn = kayttajaRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("loggedIn", loggedIn);
        return "index";
    }
}
