package projekti.controllers;

import java.util.ArrayList;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import projekti.domain.Account;
import projekti.domain.Kayttaja;
import projekti.repos.AccountRepository;
import projekti.repos.KayttajaRepository;

/**
 *
 * @author karpo
 */
@Controller
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private KayttajaRepository kayttajaRepository;

    @GetMapping("/register")
    public String list(Model model) {
        model.addAttribute("accounts", accountRepository.findAll());
        return "register";
    }

    @Transactional
    @PostMapping("/register")
    public String add(@RequestParam String username, @RequestParam String password, @RequestParam String profile) {
        if (kayttajaRepository.findByProfile(profile) != null) {
            return "redirect:/register";
        }
        //tehdään uusi käyttäjä tiedoilla, tallennetaan se accoon
        Kayttaja uusi = new Kayttaja(username, password, profile, null, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        kayttajaRepository.save(uusi);
        if (accountRepository.findByUsername(username) != null) {
            return "redirect:/register";
        }

        Account a = new Account(username, passwordEncoder.encode(password), profile, uusi);
        accountRepository.save(a);
        //linkataan vielä uuteen
        uusi.setAccount(a);
        kayttajaRepository.save(uusi);
        return "redirect:/register";
    }

}
