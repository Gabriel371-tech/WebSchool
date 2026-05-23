package br.com.webschool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class WebSchoolController {

    @GetMapping("/")
    public String landing(Model model) {
        return "index"; 
    }

    @GetMapping("/webschool")
    public String index(Model model) {
        return "index"; 
    }
    
}
