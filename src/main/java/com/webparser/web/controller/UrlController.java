package com.webparser.web.controller;

import com.webparser.web.dto.UrlDto;
import com.webparser.web.model.Url;
import com.webparser.web.services.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import parser.info.Info;

import java.util.Arrays;
import java.util.List;

@Controller
public class UrlController {
    private UrlService urlService;
    public static Url current_Url;

    @Autowired
    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("/urls")
    public String listUrl(Model model){

//        Url our_url = urlService.isIn(this.current_url_name);
        List<Url> urls = Arrays.asList(current_Url);
        model.addAttribute("urls", urls);
        return "urls-list";
    }

    @GetMapping("/")
    public String createUrlForm(Model model){
        String the_value = "44";
        Url url = new Url();
        model.addAttribute("url", url);
        return "urls-create";
    }

    @PostMapping("/")
    public String saveUrl(@ModelAttribute("url") Url url){
        current_Url = urlService.isIn(url.getName_url());

        if (current_Url == null){
            Info data = new Info(url.getName_url());
            url.setName(data.getName());
            url.setEmployees(data.getSize());
            url.setLogo(data.getLogo());
            url.setTwitter(data.getTwitter());
            url.setFacebook(data.getFacebook());
            url.setIcon(data.getIcon());
            url.setAddress(data.getAddress());
            urlService.saveUrl(url);
            current_Url = url;
        }
        return "redirect:/urls";
    }

}
