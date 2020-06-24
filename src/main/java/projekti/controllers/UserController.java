package projekti.controllers;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import projekti.domain.FoundUser;
import projekti.domain.Kayttaja;
import projekti.domain.Taito;
import projekti.repos.KayttajaRepository;

/**
 *
 * @author karpo
 */
@Controller
public class UserController {

    @Autowired
    private KayttajaRepository kayttajaRepository;

    @GetMapping("/users")
    public String list(Model model, @RequestParam(required = false) String name, @RequestParam(defaultValue = "0") Integer page) {
        Kayttaja loggedIn = kayttajaRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("loggedIn", loggedIn);
        if (name == null || name.isEmpty()) {
            model.addAttribute("kayttajat", new ArrayList<>());
        } else {
            List<Kayttaja> lista = kayttajaRepository.findByNameContainingIgnoreCase(name);
            List<FoundUser> muokattu = new ArrayList<>();
            for (Kayttaja k : lista) {
                //LOLLERSKATES!!
                if (loggedIn == k) {
                    continue;
                }
                if (loggedIn.getHyvaksytyt().contains(k)) {
                    muokattu.add(new FoundUser(k.getName(), k.getUsername(), k.getProfile(), true, false, false, false));
                } else if (loggedIn.getItseEhdotetut().contains(k)) {
                    muokattu.add(new FoundUser(k.getName(), k.getUsername(), k.getProfile(), false, false, true, false));
                } else if (loggedIn.getMuidenEhdottamat().contains(k)) {
                    muokattu.add(new FoundUser(k.getName(), k.getUsername(), k.getProfile(), false, true, false, false));
                } else {
                    muokattu.add(new FoundUser(k.getName(), k.getUsername(), k.getProfile(), false, false, false, true));
                }
            }
            model.addAttribute("kayttajat", muokattu);
        }
        return "kayttajat";
    }

    @GetMapping("/users/{profile}")
    public String showOne(Model model, @PathVariable String profile) {
        Kayttaja loggedIn = kayttajaRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("loggedIn", loggedIn);
        Kayttaja k = kayttajaRepository.findByProfile(profile);
        model.addAttribute("kayttaja", k);
        List<Taito> taidot = k.getTaidot();
        taidot.sort(null);
        model.addAttribute("taidot", taidot);
        return "homepage";
    }

    @PostMapping("/users")
    public String find(@RequestParam String nameToFind) {
        if (nameToFind.isEmpty()) {
            return "redirect:/users";
        }
        return "redirect:/users?name=" + nameToFind;
    }

    //copy pastea mutta menköön...
    @Transactional
    @PostMapping("/users/add/{username}")
    public String add(@PathVariable String username) {
        Kayttaja loggedIn = kayttajaRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Kayttaja k = kayttajaRepository.findByUsername(username);
        if (k.equals(loggedIn) || loggedIn.getHyvaksytyt().contains(k) || loggedIn.getItseEhdotetut().contains(k) || loggedIn.getMuidenEhdottamat().contains(k)) {
            return "redirect:/users";
        }
        loggedIn.getItseEhdotetut().add(k);
        k.getMuidenEhdottamat().add(loggedIn);
        return "redirect:/users";
    }

    @Transactional
    @PostMapping("/users/accept/{username}")
    public String accept(@PathVariable String username) {
        Kayttaja loggedIn = kayttajaRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Kayttaja k = kayttajaRepository.findByUsername(username);
        if (k.equals(loggedIn) || loggedIn.getHyvaksytyt().contains(k) || loggedIn.getItseEhdotetut().contains(k) || !loggedIn.getMuidenEhdottamat().contains(k)) {
            return "redirect:/users";
        }
        loggedIn.getHyvaksytyt().add(k);
        loggedIn.getMuidenEhdottamat().remove(k);
        k.getHyvaksytyt().add(loggedIn);
        k.getItseEhdotetut().remove(loggedIn);
        return "redirect:/users";
    }

    @Transactional
    @PostMapping("/users/deny/{username}")
    public String deny(@PathVariable String username) {
        Kayttaja loggedIn = kayttajaRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Kayttaja k = kayttajaRepository.findByUsername(username);
        if (k.equals(loggedIn) || loggedIn.getHyvaksytyt().contains(k) || loggedIn.getItseEhdotetut().contains(k) || !loggedIn.getMuidenEhdottamat().contains(k)) {
            return "redirect:/users";
        }
        loggedIn.getMuidenEhdottamat().remove(k);
        k.getItseEhdotetut().remove(loggedIn);
        return "redirect:/users";
    }

    @Transactional
    @PostMapping("/users/cancel/{username}")
    public String cancel(@PathVariable String username) {
        Kayttaja loggedIn = kayttajaRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Kayttaja k = kayttajaRepository.findByUsername(username);
        if (k.equals(loggedIn) || loggedIn.getHyvaksytyt().contains(k) || !loggedIn.getItseEhdotetut().contains(k) || loggedIn.getMuidenEhdottamat().contains(k)) {
            return "redirect:/users";
        }
        loggedIn.getItseEhdotetut().remove(k);
        k.getMuidenEhdottamat().remove(loggedIn);
        return "redirect:/users";
    }

    @Transactional
    @PostMapping("/users/kick/{username}")
    public String kick(@PathVariable String username) {
        Kayttaja loggedIn = kayttajaRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Kayttaja k = kayttajaRepository.findByUsername(username);
        if (k.equals(loggedIn) || !loggedIn.getHyvaksytyt().contains(k)) {
            return "redirect:/users";
        }
        loggedIn.getHyvaksytyt().remove(k);
        k.getHyvaksytyt().remove(loggedIn);
        return "redirect:/users";
    }
}
