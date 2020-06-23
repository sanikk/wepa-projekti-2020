/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

    @GetMapping("/postaukset")
    public String list(Model model, @RequestParam(defaultValue = "0") Integer page) {
        Kayttaja loggedIn = kayttajaRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("loggedIn", loggedIn);
        Pageable pageable = PageRequest.of(page, 25, Sort.by("lahetysaika").descending());
        //tehdään Collection jossa on kontaktit ja käyttäjä itse, ja katsotaan löytyykö kokoelmasta
        List<Kayttaja> kokoelma = new ArrayList<>();
        //eli haetaan jotenkin käyttäjä, ja sitten käyttäjän kaverit, ja tungetaan tänne
        model.addAttribute("postaukset", postrepo.findAll(pageable));
        return "postaukset";
    }

    //hyvää matskua päiväämisestä @3.07, tuo muunnos on kait vähän turha, tai jotain
    @PostMapping("/postaukset")
    public String newPost(@RequestParam Kayttaja kayttaja, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime examDate, @RequestParam String sisalto) {
        Postaus p = new Postaus(kayttaja, examDate, sisalto);
        return "redirect:/postaukset";
    }
}
